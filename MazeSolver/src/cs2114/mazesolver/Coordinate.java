package cs2114.mazesolver;


/**
 *  The Coordinates class to store points
 *
 *  @author  Supratim Baruah (smb4)
 *  @version 2011.11.1
 */
public class Coordinate {

	private int row;
	private int col;
	/**
	 * the constructor
	 * @param row the row
	 * @param col the col
	 */
	public Coordinate(int row, int col)
	{
		this.row = row;
		this.col = col;
	}

	/**
	 * gets the row
	 * @return the row
	 */
	public int getRow()
	{
		return row;

	}

	/**
	 * gets the col
	 * @return the col
	 */
	public int getCol()
	{
		return col;
	}


}
