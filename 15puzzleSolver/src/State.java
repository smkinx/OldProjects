import java.awt.Point;
import java.util.ArrayList;

/**
 * The state of the puzzle
 * @author Supratim Baruah
 * @version 2014/4/10
 */
public class State {

	private int[][] cBoard;
	private int manDist = 0;

	/**
	 * Constructor for State
	 * @param info the puzzle
	 */
	public State(String[] info) {
		int size = 4;
		this.cBoard = new int[size][size];

		int count = 0;
		for (int r = 0; r < size; r++) {
			for (int c = 0; c < size; c++) {
				String tiledata = info[count++];
				int intform = Integer.parseInt(tiledata);
				cBoard[r][c] = intform;
			}
		}
	}

	/**
	 * Constructor for State
	 * @param board the puzzle
	 */
	public State(int[][] board)
	{
		cBoard = board;

	}

	/**
	 * Getter to return the current board array
	 * @return the curState
	 */
	public int[][] getCurBoard()
	{
		return cBoard;
	}

	/**
	 *Gets the index of the blank tile
	 *@return the index of the blank tile
	 */
	public Point getZero()
	{
		Point zeroIndex = new Point(0, 0);
		for (int r = 0; r < 4; r++)
		{
			for (int c = 0; c < 4; c++)
			{
				if (cBoard[r][c] == 0)
				{
					zeroIndex.setLocation(r, c);
				}
			}
		}
		return zeroIndex;
	}

	/**
	 * Returns the value at index
	 * @param row the row
	 * @param col the col
	 * @return the value value at index
	 */

	public int getPoint(int row, int col)
	{
		return cBoard[row][col];
	}

	/**
	 * Copies the current board
	 * @param state state to copy
	 * @return copy of the state
	 */
	public int[][] copyBoard(int[][] state)
	{
		int[][] ret = new int[4][4];
		for (int r = 0; r < 4; r++)
		{
			for (int c = 0; c < 4; c++)
			{
				ret[r][c] = state[r][c];
			}
		}
		return ret;
	}

	/**
	 * Prints the state
	 */
	public void printState()
	{
		String s = "";
		for (int r = 0; r < 4; r++)
		{
			for (int c = 0; c < 4; c++)
			{
				if (r == (int) getZero().getX() && c == (int) getZero().getY())
				{
					s += "   ";
				}
				else
				{
					s += cBoard[r][c] + " ";
				}
			}
			s += System.getProperty("line.separator");
		}
		System.out.print(s);
	}

	/**
	 *Checks if two States are equal
	 * @param s the State to check with
	 * @return true or fals, if states are equal or not
	 */
	@Override
	public boolean equals(Object s)
	{
		int[][] obj = ((State) s).getCurBoard();
		for (int r = 0; r < 4; r++)
		{
			for (int c = 0; c < 4; c++)
			{
				if (cBoard[r][c] != obj[r][c])
				{
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * The hashcode function
	 * @return the hashcode
	 */

	@Override
	public int hashCode()
	{
		int hash = 0;

		int prime = 1009;
		for (int r = 0; r < 4; r++)
		{
			for (int c = 0; c < 4 ; c++)
			{
				hash += cBoard[r][c] * ((r * prime) * (c * prime));
			}
		}

		return hash;

	}

	/**
	 * Calculates the neighbouring states
	 * @return neighbouring states
	 */

	public ArrayList<State> neighbours()
	{
		ArrayList<State> neighbours = new ArrayList<State>();
		int[][] board = getCurBoard();

		int size = 4;

		Point p = getZero();
		int r = (int) p.getX();
		int c = (int) p.getY();
		int[][] temp = null;
		State newState = null;

		if (r != 0) {
			temp = move(board, "up", r, c);
			newState = new State(temp);
			neighbours.add(newState);
		}


		if (c != size - 1) {
			temp = move(board, "right", r, c);
			newState = new State(temp);
			neighbours.add(newState);
		}

		if (r != size - 1) {
			temp = move(board, "down", r, c);
			newState = new State(temp);
			neighbours.add(newState);
		}

		if (c != 0) {
			temp = move(board, "left", r, c);
			newState = new State(temp);
			neighbours.add(newState);
		}


		return neighbours;
	}

	/**
	 * makes the neighbouring states
	 * @param data board data
	 * @param position	where to move
	 * @param r row
	 * @param c col
	 * @return returns the neighbour state
	 */
	public int[][] move(int[][] data, String position, int r, int c) {
		int[][] after = copyBoard(data);
		int temp = 0;

		if (position.equals("up")) {
			temp = after[r][c];
			after[r][c] = after[r - 1][c];
			after[r - 1][c] = temp;
		}

		if (position.equals("right")) {
			temp = after[r][c];
			after[r][c] = after[r][c + 1];
			after[r][c + 1] = temp;
		}

		if (position.equals("down")) {
			temp = after[r][c];
			after[r][c] = after[r + 1][c];
			after[r + 1][c] = temp;
		}

		if (position.equals("left")) {
			temp = after[r][c];
			after[r][c] = after[r][c - 1];
			after[r][c - 1] = temp;
		}

		return after;
	}

	/**
	 * Calculates the manhattan distance
	 * @return the distance
	 */
	public int getManDist()
	{
		manDist = 0;
		for (int r = 0; r < 4; r++)
		{
			for (int c = 0; c < 4; c++)
			{
				int val = (cBoard[r][c] - 1);

				if (val != -1)
				{
					int horiz = val % 4;
					int vert = val / 4;
					manDist += Math.abs(vert - (r)) + Math.abs(horiz - (c));
				}
			}
		}
		return manDist;
	}


}


