package cs2114.mazesolver;

/**
 * The Maze class
 * @author Supratim Baruah
 * @version 2012.9.3
 */
public class Maze extends MazeBase{

	private MazeCell[][] base;
	private int size;
	
	/**
	 * the constructor for the maze class
	 * @param row the height and width.
	 */
	public Maze(int row)
	{
		size = row + 1;
		base = new MazeCell[row][row];
	}
	
	/**
	 * Clears the Maze
	 */
	@Override
	public void clearMaze() {
		for (int r = 0; r < size - 1; r++)
		{
			for (int c = 0; c < size - 1; c++)
			{
				setCell(r, c, MazeCell.UNEXPLORED);
			}
		}
		
	}

	/** 
	 * Gets the cell.
	 */
	@Override
	public MazeCell getCell(int row, int col) {
		
		return base[row][col];
	}

	/**
	 * Clears the Maze keeping just the walls
	 */
	@Override
	public void resetPaths() {
		for (int r = 0; r < size - 1; r++)
		{
			for (int c = 0; c < size - 1; c++)
			{
				if (getCell(r, c) != MazeCell.WALL)
				{
					setCell(r, c, MazeCell.UNEXPLORED);
				}
			}
		}
		
	}

	/**
	 * Sets the cell to the value
	 */
	@Override
	public void setCell(int row, int col, MazeCell value) {

		base[row][col] = value;
		
	}
	
	/**
	 * Returns the height or the width of the maze
	 * @return the height or width
	 */
	@Override
	public int size() {
		return size;
	}

}
