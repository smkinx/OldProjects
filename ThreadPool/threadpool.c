
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>
#include <semaphore.h>
#include "list.h"
#include "threadpool.h"

static __thread struct workers* tlsWorker;
static __thread int isWorker = 0;

struct workers{
	struct thread_pool *p;
	pthread_t threadId;
	struct list queue;  //push pop order: LIFO
};

struct funcData{
	struct workers *w;
	struct thread_pool *pool;
};

struct thread_pool{
	int nThreads;   //max number of threads	
	pthread_mutex_t mutex;
	pthread_cond_t cond;
	int shutdown;
	struct list sQueue; //'Global' future list
	struct workers *thread_list;
	pthread_barrier_t barrier;
};


struct future{
	struct list_elem elem;
	fork_join_task_t task; //function to call
	void *data; //data for above function
	void *result;   //result of above function
	sem_t sem;
	int status; //0 = not started, 1 = in progress, 2 = finished
	pthread_t creatorId, workerId;
	
};



/* Function that the worker threads run */
static void* worker_func(void* funcData){
    //printf("starting worker %lu\n", pthread_self());
	struct funcData* data = (struct funcData*) funcData;
	struct thread_pool *pool = data->pool;
	struct workers *worker = data->w;
	tlsWorker = data->w;
	isWorker = 1;
	pthread_barrier_wait(&pool->barrier);
	pthread_mutex_lock(&pool->mutex);
    while(!pool->shutdown){
        //printf("begin while loop thread \n");
        struct future* curFuture;
        int hasFuture = 0;
        if(!list_empty(&(worker->queue))){
            curFuture = list_entry(list_pop_front(&worker->queue), struct future, elem);
			hasFuture = 1;
			//printf("future from self queue \n");
        }
        else if(!list_empty(&pool->sQueue)){

            curFuture = list_entry(list_pop_front(&pool->sQueue), struct future, elem);
			hasFuture = 1;
			//printf("future from global queue\n");
        }
        else {
            //printf("dirty theif work\n");
			int i;
			for (i = 0; i < pool->nThreads; i++)
			{
			    //printf("check worker %d\n", i);
				struct workers* temp = &pool->thread_list[i];
				if(temp->threadId != tlsWorker->threadId && !list_empty(&temp->queue))
				{
				    //printf("temp id %lu\n", temp->threadId);
					struct future* f;
					f = list_entry(list_back(&temp->queue), struct future, elem);
					if(f->status == 0){
					    //printf("stealing work");
					    curFuture = list_entry(list_pop_back(&temp->queue), struct future, elem);
					    hasFuture = 1;
					    break;
					}
				}
			}
			
        }
        
        if(hasFuture)
		{
		    //printf("starting solving \n");
			pthread_mutex_unlock(&pool->mutex);
			curFuture->status = 1;
			curFuture->result = curFuture->task(pool, curFuture->data);
			//printf("solved");
			curFuture->status = 2;
			sem_post(&curFuture->sem);
			pthread_mutex_lock(&pool->mutex);
		}
		else{
		    //printf("cond wait \n");
		    pthread_cond_wait(&pool->cond, &pool->mutex);
		    //sleep(5);
		}
        //printf("loop again \n");
    }
    pthread_mutex_unlock(&pool->mutex);
    pthread_barrier_wait(&pool->barrier);
	return NULL;
}


/* Create a new thread pool with no more than n threads. */
struct thread_pool * thread_pool_new(int nthreads)
{
	//printf("pool\n");
	struct thread_pool* pool;
	pool = malloc(sizeof(struct thread_pool));
	if(pool == NULL)
	{
		perror("malloc error");
	}
	pthread_cond_init(&pool->cond, NULL);
	pthread_mutex_init(&pool->mutex, NULL);
	
	pool->thread_list = (struct workers*)malloc(sizeof(struct workers) * nthreads);
	if(pool->thread_list == NULL)
	{
		perror("malloc error");
	}
	list_init(&pool->sQueue);
	pthread_barrier_init(&pool->barrier, 0, nthreads+1); 
	//list_init(&pool->thread_list);
	pool->nThreads = nthreads;
	pool->shutdown = 0;
	int i;
	for(i = 0; i < nthreads; i++)
	{
		struct workers* worker = &(pool->thread_list[i]);
		list_init(&worker->queue);	
		worker->p = pool;	
		struct funcData *funcData= malloc(sizeof(struct funcData));
		funcData->w = worker;
		funcData->pool = pool;
		
		pthread_create(&worker->threadId, NULL, worker_func, (void *)funcData);					
	}
	pthread_barrier_wait(&pool->barrier);
	//printf("poolcreated\n");
	return pool;
}

/* 
 * Shutdown this thread pool in an orderly fashion.  
 * Tasks that have been submitted but not executed may or
 * may not be executed.
 *
 * Deallocate the thread pool object before returning. 
 */
void thread_pool_shutdown_and_destroy(struct thread_pool *pool)
{
	pthread_mutex_lock(&pool->mutex);
    pool->shutdown = 1;
    pthread_cond_broadcast(&pool->cond);
    pthread_mutex_unlock(&pool->mutex);
    int i;	
    pthread_barrier_wait(&pool->barrier);
	for (i = 0; i < pool->nThreads; i++){
		struct workers* worker = &(pool->thread_list[i]);
		pthread_join(worker->threadId, NULL);
	}
	pthread_barrier_destroy(&pool->barrier);
    pthread_mutex_destroy(&pool->mutex);
    pthread_cond_destroy(&pool->cond);
    free(pool->thread_list);
    free(pool);	
}

/* 
 * Submit a fork join task to the thread pool and return a
 * future.  The returned future can be used in future_get()
 * to obtain the result.
 * 'pool' - the pool to which to submit
 * 'task' - the task to be submitted.
 * 'data' - data to be passed to the task's function
 *
 * Returns a future representing this computation.
 */
struct future * thread_pool_submit(
        struct thread_pool *pool, 
        fork_join_task_t task, 
        void * data)
{
	struct future* future = malloc(sizeof(struct future));
    future->task = task;
    future->data = data;
    future->result = NULL;
    future->status = 0;
    sem_init(&future->sem, 0, 0);
    pthread_mutex_lock(&pool->mutex);
    //If called internally, put on non-global queue in front
    if(isWorker)//check condition
	{
	    //printf("submit on self queue \n");
		list_push_front(&tlsWorker->queue, &future->elem);
	}
	else
	{
	    //printf("submit on global queue \n");
		list_push_back(&pool->sQueue, &future->elem);
	}
    pthread_cond_signal(&pool->cond);
    pthread_mutex_unlock(&pool->mutex);
    //printf("future submited\n");
    return future;
}

/* Make sure that the thread pool has completed the execution
 * of the fork join task this future represents.
 *
 * Returns the value returned by this task.
 */
void * future_get(struct future *future)
{
    //printf("future_get wait \n");
    if(!isWorker){
	    sem_wait(&future->sem);
	    return future->result;
	}
	
	else{
	    if(list_empty(&(tlsWorker->queue))){
	        sem_wait(&future->sem);
	        return future->result;
	    }
	    else{
	        struct future *curFuture;
            curFuture = list_entry(list_pop_front(&tlsWorker->queue), struct future, elem);
            curFuture->status = 1;
			//printf("leapfrog future from self queue \n");
			curFuture->result = curFuture->task(tlsWorker->p, curFuture->data);
			//printf("solved");
			curFuture->status = 2;
			sem_post(&curFuture->sem);
        }
        return future->result;
	}
}

/* Deallocate this future.  Must be called after future_get() */
void future_free(struct future *future)
{
	free(future);
}

