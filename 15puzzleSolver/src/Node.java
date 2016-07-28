/**
 * The node class to store the state and the parent
 * @author Supratim Baruah
 * @version 2014/4/10
 */
public class Node {

	private State board;
	private Node parent;

	/**
	 * Constructor for node class
	 * @param board board state
	 * @param parent parent node
	 */
	public Node(State board, Node parent)
	{
		this.board = board;
		this.parent = parent;
	}

	/**
	 * Gets the state
	 * @return state
	 */
	public State getCurState()
	{
		return board;
	}

	/**
	 * Gets the parent
	 * @return parent
	 */
	public Node getParent()
	{
		return parent;
	}

	/**
	 * Makes up the hashcode
	 * @return the hashcode
	 */
	@Override
	public int hashCode()
	{
		return board.hashCode();
	}

	/**
	 *Checks if two States are equal
	 * @param s the node to check with
	 * @return true or fals, if states are equal or not
	 */
	@Override
	public boolean equals(Object s)
	{
		return board.equals(((Node) s).getCurState());
	}
}
