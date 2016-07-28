package cs2114.mazesolver;

import junit.framework.TestCase;

public class MazeTest extends TestCase {

	/**
	 * The Test for Maze class
	 * @author Supratim Baruah
	 * @version 2012.9.3
	 */
	private Maze maze;
	
	/**
	 * sets up the new maze
	 */
	public void setUp()
	{
		maze = new Maze(4);
	}
	
	/**
	 * Tests every method in the maze class.
	 */
	public void tests()
	{
		assertEquals(5, maze.size());
		maze.setCell(0, 0, MazeCell.CURRENT_PATH);
		maze.setCell(1, 1, MazeCell.WALL);
		assertEquals(MazeCell.CURRENT_PATH, maze.getCell(0, 0));
		assertEquals(MazeCell.WALL, maze.getCell(1, 1));
		maze.resetPaths();
		assertEquals(MazeCell.UNEXPLORED, maze.getCell(0, 0));
		assertEquals(MazeCell.WALL, maze.getCell(1, 1));
		maze.clearMaze();
		assertEquals(MazeCell.UNEXPLORED, maze.getCell(1, 1));
	}
	
	
}
