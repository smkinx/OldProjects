package cs2114.mazesolver;

public class MazeSolver {

	public Maze maze;
	public Coordinate c;
	public LinkedStack<Coordinate> stack;
	public MazeSolver(Maze maze)
	{
		this.maze = maze;
		c = new Coordinate(0, 0);
		stack = new LinkedStack<Coordinate>();
	}
	public String solve()
	{
		if (c.getRow() == maze.size() - 1 &&
				c.getCol() == maze.size() - 1)
		{
			return "win";
		}
		else if (pathFinder())
		{
			solve();
		}
		else if(!pathFinder())
		{
			pathFixer();
		}
		return null;
	}
	
	public boolean pathFinder()
	{
		if (maze.getCell(c.getRow() + 1, c.getCol()) == MazeCell.UNEXPLORED
				&& c.getRow() + 1 < maze.size())
		{
			c = new Coordinate(c.getRow() + 1, c.getCol());
			maze.setCell(c.getRow(), c.getCol(), MazeCell.CURRENT_PATH);
			stack.push(c);
			return true;
		}
		else if (
				maze.getCell(c.getRow(), c.getCol() + 1) == MazeCell.UNEXPLORED
				&& c.getCol() + 1 < maze.size())
		{
			c = new Coordinate(c.getRow(), c.getCol() + 1);
			maze.setCell(c.getRow(), c.getCol(), MazeCell.CURRENT_PATH);
			stack.push(c);
			return true;
		}
		else if (
				maze.getCell(c.getRow() - 1, c.getCol()) == MazeCell.UNEXPLORED
				&& c.getRow() - 1 >= 0)
		{
			c = new Coordinate(c.getRow() - 1, c.getCol());
			maze.setCell(c.getRow(), c.getCol(), MazeCell.CURRENT_PATH);
			stack.push(c);
			return true;
		}
		else if (
				maze.getCell(c.getRow(), c.getCol() - 1) == MazeCell.UNEXPLORED
				&& c.getCol() - 1 >= 0)
		{
			c = new Coordinate(c.getRow(), c.getCol() - 1);
			maze.setCell(c.getRow(), c.getCol(), MazeCell.CURRENT_PATH);
			stack.push(c);
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	public void pathFixer()
	{
		if (!(stack.isEmpty()))
		{
			stack.pop();
			maze.setCell(c.getRow(), c.getCol() - 1, MazeCell.FAILED_PATH);
			c = stack.top();
			solve();
		}
		else 
		{
			solve();
		}
		
		
	}
	
}
