import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * The BFS, puzzle solver
 * @author Supratim Baruah
 * @version 2014/4/10
 */
public class BFS {
	private static Node initialState;
	private static Node goalState;
	private int count = 0;
	private HashTable<Node, Node> h;

	/**
	 * Constructor for the BFS class
	 * @param initialState puzzle
	 * @param goalState solved puzzle
	 */
	public BFS(State initialState, State goalState)
	{
		this.initialState = new Node(initialState, null);
		this.goalState = new Node(goalState, null);
	}

	/**
	 * BFS with fifo
	 * @param printVisited true prints out visited states
	 * @param printState true prints out the states
	 */
	public void fifoSearch(Boolean printVisited, boolean printState)
	{
		ArrayDeque<Node> q = new ArrayDeque<Node>();
		Node current = new Node(null, null);
		int count = 2;
		q.add(initialState);
		h = new HashTable<Node, Node>(16);
		h.put(initialState, initialState);
		if (printState || printVisited)
		{
			printVisited(initialState, false, "");
		}
		if (printState)
		{
			printState(initialState);
		}
		if (initialState.equals(goalState))
		{
			q.poll();
//			System.out.println("found");
		}
		while (!q.isEmpty())
		{
			Node parent = q.poll();
			ArrayList<State> children = parent.getCurState().neighbours();
			for (int i = 0; i < children.size(); i++)
			{
				current = new Node(children.get(i), parent);
				if (!hasVisited(current))
				{
					q.add(current);
					h.put(current, current);
					if (printState || printVisited)
					{
						printVisited(current, false, "");
					}
					if (printState)
					{
						printState(current);
					}
					if (current.equals(goalState))
					{
//						System.out.println("found");
						break;
					}
					count++;
				}
			}
			if (current.equals(goalState))
			{
				break;
			}
		}
	}

	/**
	 * BFS with priorityQueue
	 * @param printVisited true prints out visited states
	 * @param printState true prints out the states
	 */
	public void prioritySearch(Boolean printVisited, boolean printState)
	{

		Comparator<Node> comparator = new DistComparator();
		PriorityQueue<Node> q = new PriorityQueue<Node>(1000, comparator);
		Node current = new Node(null, null);
		int count = 2;
		q.add(initialState);
		h = new HashTable<Node, Node>(16);
		h.put(initialState, initialState);
		if (printState || printVisited)
		{
			printVisited(initialState, false, "");
		}
		if (printState)
		{
			printState(initialState);
		}
		if (initialState.equals(goalState))
		{
			q.poll();
//			System.out.println("found");
		}
		while (!q.isEmpty())
		{
			Node parent = q.poll();
			ArrayList<State> children = parent.getCurState().neighbours();
			for (int i = 0; i < children.size(); i++)
			{
				current = new Node(children.get(i), parent);
				if (!hasVisited(current))
				{
					q.add(current);
					h.put(current, current);
					if (printState || printVisited)
					{
						printVisited(current, false, "");
					}
					if (printState)
					{
						printState(current);
					}
					if (current.equals(goalState))
					{
//						System.out.println("found");
						break;
					}
					count++;
				}
			}
			if (current.equals(goalState))
			{
				break;
			}
		}
	}

	/**
	 * helper function to print visited
	 * @param cur the node
	 * @param solution print solution true or false
	 * @param direction the move
	 */
	public void printVisited(Node cur, boolean solution, String direction)
	{
		count++;
		int row = (int) (cur.getCurState().getZero().getX() + 1);
		int col = (int) (cur.getCurState().getZero().getY() + 1);
		if (solution)
		{
			System.out.println(count + " (" + row + "," + col + ")" + " " +
					direction);

		}
		else
		{
			System.out.println(count + " (" + row + "," + col + ")");
		}
	}

	/**
	 * Helper function to print out the states
	 * @param cur node to print state of
	 */
	public void printState(Node cur)
	{
		System.out.print(System.getProperty("line.separator"));
		h.get(cur).getCurState().printState();
		System.out.print(System.getProperty("line.separator"));
	}

	/**
	 * Prints out the solution for the puzzle
	 * @param printState true or false
	 */
	public void printSolution(boolean printState)
	{
		System.out.println("Solution path:");
		count = 0;
		ArrayDeque<Node> sol = new ArrayDeque<Node>();
		Node n = h.get(goalState);
		while (n != null)
		{
			sol.add(n);
			n = n.getParent();
		}

		int count = 1;
		while (sol.peekLast() != null)
		{
			String direction = "";
			Node temp = sol.pollLast();
			Node child = sol.peekLast();
			if (child != null)
			{
				direction = getDirection(temp, child);
			}
			printVisited(temp, true, direction);
			if (printState)
			{
				if (sol.peekLast() == null)
				{
					System.out.print(System.getProperty("line.separator"));
					h.get(temp).getCurState().printState();
				}
				else
				{
					printState(temp);
				}

			}
			count++;

		}

	}

	/**
	 * Figure out the direction for the solution path
	 * @param parent the parent
	 * @param child the child
	 * @return the direction
	 */
	public String getDirection(Node parent, Node child)
	{
		//x is row, y is col
		String direction = "";
		int num = 0;
		if  (parent.getCurState().getZero().getX() <
				child.getCurState().getZero().getX())
		{
			direction = "down" ;
		}
		if (parent.getCurState().getZero().getY() <
				child.getCurState().getZero().getY())
		{
			direction = "right" ;
		}
		if (parent.getCurState().getZero().getX() >
				child.getCurState().getZero().getX())
		{
			direction = "up" ;
		}
		if (parent.getCurState().getZero().getY() >
				child.getCurState().getZero().getY())
		{
			direction = "left" ;
		}

		int row = (int) child.getCurState().getZero().getX();
		int col = (int) child.getCurState().getZero().getY();
		num = parent.getCurState().getPoint(row, col);
		return direction + " " + num;
	}

	/**
	 * Helper fucntion, so BFS doesn't check a visited state
	 * @param n node to check
	 * @return true if has alread visited
	 */
	public boolean hasVisited(Node n)
	{
		if (h.get(n) != null)
		{
			if (h.get(n).equals(n))
			{
				//System.out.println("REPEAT");
				return true;
			}
		}
		return false;

	}


}

/**
 * Class for the comparator for the priorityQueue
 * @author Supratim Baruah
 * @version 2014/4/10
 */
class DistComparator implements Comparator<Node> {

	/**
	 * Compare two nodes
	 * @param s1 node1
	 * @paran s2 node2
	 * @return 0 if equals, 1 or -1 otherwise
	 */
	public int compare(Node s1, Node s2) {
		if (s1.getCurState().getManDist() < s2.getCurState().getManDist())
		{
			return -1;
		}
		else if(s1.getCurState().getManDist() > s2.getCurState().getManDist())
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}
}

