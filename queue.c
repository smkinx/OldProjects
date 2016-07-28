#ifndef QUEUE_H
#define QUEUE_H	
#include <stddef.h>
#include <stdbool.h>

// On my honor: 
// 
// - I have not discussed the C language code in my program with 
// anyone other than my instructor or the teaching assistants 
// assigned to this course. 
// 
// - I have not used C language code obtained from another student, 
// or any other unauthorized source, either modified or unmodified. 
// 
// - If any C language code or documentation used in my program 
// was obtained from another source, such as a text book or course 
// notes, that has been clearly noted with a proper citation in 
// the comments of my program. 
// 
// - I have not designed this program in such a way as to defeat or 
// interfere with the normal operation of the Curator System. 
// 
// <Supratim Baruah>

// Queue node:
struct _QNode {

   struct _QNode *prev;     /* Previous list element. */
   struct _QNode *next;     /* Next list element.    */
};

typedef struct _QNode QNode;

// Queue object:
struct _Queue {
   QNode fGuard;    // sentinel node at the front of the queue
   QNode rGuard;     // sentinel node at the tail of the queue
}

typedef struct _Queue Queue;

// Initialize QNode pointers to NULL.
//
// Pre:  pN points to a QNode object
// Post: pN->prev and pN->next are NULL
//
void QNode_Init(QNode* const pN)
{
	if(pN != NULL)
	{
		pN->prev = NULL;
		pN->next = NULL;
	}
}

// Initialize Queue to empty state.
//
// Pre:  pQ points to a Queue object
// Post: *pQ has been set to an empty state (see preceding comment
//
void Queue_Init(Queue* const pQ)
{
	if(pQ != NULL)
	{
		pQ->fGuard->next = pQ->rGuard;
		pQ->rGuard->prev = pQ->fGuard;
	}
}

// Return whether Queue is empty.
//
// Pre:  pQ points to a Queue object
// Returns true if *pQ is empty, false otherwise
//
bool Queue_Empty(const Queue* const pQ)
{
	if(pQ != NULL)
	{
		if (pQ->fGuard->next = pQ->rGuard &&	pQ->rGuard->prev = pQ->fGuard)
		{	return true;}
		return false;
		
	}
	return false;
}

// Insert *pNode as last interior element of Queue.
//
// Pre:  pQ points to a Queue object
//       pNode points to a QNode object
// Post: *pNode has been inserted at the rear of *pQ
//
void Queue_Push(Queue* const pQ, QNode* const pNode)
{
	if(pQ != NULL && pNode !=NULL)
	{
		pQ->rGuard->prev->next=pNode;
		pNode->prev = pQ->rGuard->prev;
		pNode->next= pQ->rGuard;
	}
}

// Remove first interior element of Queue and return it.
//
// Pre:  pQ points to a proper Queue object
// Post: the interior QNode that was at the front of *pQ has been removed
// Returns pointer to the QNode that was removed, NULL if *pQ was empty
//
QNode* const Queue_Pop(Queue* const pQ)
{
	if(pQ != NULL)
	{
		if(Queue_Empty())
		{
			return NULL;
		}
		else
		{
			QNode* ret = pQ->fGuard->next;
			pQ->fGuard->next = pQ->fGuard->next->next;
			return ret;
		}
			
	}
}

// Return pointer to the first interior element, if any; does not remove
// the element.
//
// Pre:  pQ points to a proper Queue object
// Returns pointer first interior QNode in *pQ, NULL if *pQ is empty
//
QNode* const Queue_Front(const Queue* const pQ)
{
	if(pQ != NULL)
	{
		if(Queue_Empty())
		{
			return NULL;
		}
		else
		{
			QNode* ret = pQ->fGuard->next;
			return ret;
		}
			
	}
}

// Return pointer to the last interior element, if any; useful for client-
// side traversal code.
//
// Pre:  pQ points to a proper Queue object
// Returns pointer last interior QNode in *pQ, NULL if *pQ is empty
//
QNode* const Queue_Back(const Queue* const pQ)
{
		if(pQ != NULL)
	{
		if(Queue_Empty())
		{
			return NULL;
		}
		else
		{
			QNode* ret = pQ->rGuard->prev;
			return ret;
		}
			
	}
}

// Reverse the order of the interior nodes in the Queue.
//
// Pre:  pQ points to a proper Queue object
// Post: *pQ contains exactly the same nodes as before, but in reverse order
//
void Queue_Reverse(Queue* const pQ)
{
	int n = 0;l
	if(pQ != NULL)
	{
		if(!Queue_Empty())
		{
			Qnode* temp = Queue_Pop(pQ);
			Queue_Reverse(pQ);
			Queue_Push(temp);
		}
		return pQ;	
	}
}
