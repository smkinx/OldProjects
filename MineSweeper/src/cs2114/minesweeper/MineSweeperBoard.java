package cs2114.minesweeper;

import java.util.Random;


/**
 * The MinesSweeper's model
 * @author Supratim Baruah (smb4)
 * @version 2012. 2. 6.
 */
public class MineSweeperBoard extends MineSweeperBoardBase
{

	private MineSweeperCell [][] board;
	private int numRow;
	private int numCol;
	
	/**
	 * The constructor for the class
	 * @param col the number of columns on the board
	 * @param row the number of rows on the board
	 * @param mines the number of mines on the board
	 */
	public MineSweeperBoard(int col, int row, int mines)
	{
		numCol = col;
		numRow = row;
		board = new MineSweeperCell[col][row];
		
		for (int r = 0; r < row; r++)
		{
			for (int c = 0; c < col; c++)
			{
				setCell(c, r, MineSweeperCell.COVERED_CELL);
			}
		}
		placeMines(mines);
	}

	/**
	 * Places the mines on the board
	 * @param mines the number of mines to place
	 * @return true when all the mines are placed.
	 * @param mines
	 * @return
	 */
	public boolean placeMines(int mines)
	{
		Random rand = new Random();
		int mRow = rand.nextInt(height());
		int mCol = rand.nextInt(width());
		
		if (mines == 0)
		{
			return true;
		}
		else if (getCell(mCol, mRow) == MineSweeperCell.COVERED_CELL)
		{
			setCell(mCol, mRow, MineSweeperCell.MINE);
			return placeMines(mines - 1);
		}
		return placeMines(mines);
		
		
	}
	/**
	 * Flags the cell.
	 * @param row the row of the cell the user clicked on.
	 * @param col the column of the cell the user clicked on
	 */
	public void flagCell(int col, int row) 
	{
		if (getCell(col, row) == MineSweeperCell.FLAGGED_MINE)
		{
			setCell(col, row, MineSweeperCell.MINE);
		}		
		else if (getCell(col, row) == MineSweeperCell.FLAG)
		{
			setCell(col, row, MineSweeperCell.COVERED_CELL);
		}
		else if (getCell(col, row) == MineSweeperCell.COVERED_CELL)
		{
			setCell(col, row, MineSweeperCell.FLAG);
		}
		else
		{
			setCell(col, row, MineSweeperCell.FLAGGED_MINE);
		}
	}

	/**
	 * Gets the value of the cell.
	 * @param row the row of the cell the user clicked on.
	 * @param col the column of the cell the user clicked on
	 * @return the value of the cell or invalid cell.
	 */
	public MineSweeperCell getCell(int col, int row) 
	{
		if (col > width() || col < 0 || row > height() || row < 0)
		{
			return MineSweeperCell.INVALID_CELL;
		}
		return board[col][row];
	}

	/**
	 * The height of the board.
	 * @return the height of the board.
	 */
	public int height() 
	{
		return numRow;
	}

	/**
	 * Checks if the user lost the game.
	 * @return true if the current game has been lost and false otherwise
	 */
	public boolean isGameLost() 
	{

		for (int r = 0; r < height(); r++)
		{
			for (int c = 0; c < width(); c++)
			{
				if (getCell(c, r) == MineSweeperCell.UNCOVERED_MINE)
				{
					return true;
				}
				
			}
		
		}
		return false;
	}

	/**
	 * Checks if the user won the game.
	 * @return true if the current game has been won and false otherwise.
	 */
	public boolean isGameWon() 
	{
		for (int r = 0; r < height(); r++)
		{
			for (int c = 0; c < width(); c++)
			{
				if (getCell(c, r) == MineSweeperCell.COVERED_CELL ||
						getCell(c, r) == MineSweeperCell.FLAG ||
						getCell(c, r) == MineSweeperCell.MINE ||
						getCell(c, r) == MineSweeperCell.UNCOVERED_MINE)
				{
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Checks the number of minese next to the cell user clicked on.
	 * @param row the row of the cell the user clicked on.
	 * @param col the column of the cell the user clicked on
	 * @return the number of mines adjacent to the specified cell.
	 */
	public int numberOfAdjacentMines(int col, int row) 
	{
		int adMines = 0;
        for (int rMine = row - 1; rMine <= row + 1; rMine++)
        {
            for (int cMine = col - 1; cMine <= col + 1; cMine++)
            {

                if ((0 <= rMine && rMine < height() && 0 <= cMine
                 	&& cMine < width()) && (
                 			getCell(cMine, rMine) ==
                    	MineSweeperCell.UNCOVERED_MINE
                       	|| getCell(cMine, rMine) ==
                       			MineSweeperCell.MINE
                       	|| getCell(cMine, rMine) ==
                       			MineSweeperCell.FLAGGED_MINE))
                {

                    adMines++;
                }

            }
        }
		return adMines;
	}

	/**
	 * Reveals the whole board.
	 */
	public void revealBoard() 
	{
		for (int r = 0; r < height(); r++)
		{
			for (int c = 0; c < width(); c++)
			{
				if (getCell(c, r) == MineSweeperCell.FLAG)
				{
					int adMines = numberOfAdjacentMines(c, r);
					setCell(c, r, MineSweeperCell.adjacentTo(adMines));
				}
				if (getCell(c, r) == MineSweeperCell.FLAGGED_MINE)
				{
					setCell(c, r, MineSweeperCell.MINE);
				}
				uncoverCell(c, r);
				
				
			}
		}
		
	}

	/**
	 * Sets the Cell to the value.
	 * @param row the row of the cell the user clicked on.
	 * @param col the column of the cell the user clicked on
	 * @param value the value of the cell.
	 */
	protected void setCell(int col, int row, MineSweeperCell value) 
	{
		board[col][row] = value;		
	}

	/**
	 * Uncovers the Cell.
	 * @param row the row of the cell the user clicked on.
	 * @param col the column of the cell the user clicked on
	 */
	public void uncoverCell(int col, int row) 
	{		
		if (!(getCell(col, row) == MineSweeperCell.FLAG) &&
				!(getCell(col, row) == MineSweeperCell.FLAGGED_MINE))
		{
			if (getCell(col, row) == MineSweeperCell.COVERED_CELL)
			{
				int adMines = numberOfAdjacentMines(col, row);
				setCell(col, row, MineSweeperCell.adjacentTo(adMines));
			}
			else if (getCell(col, row) == MineSweeperCell.MINE)
			{
				setCell(col, row, MineSweeperCell.UNCOVERED_MINE);
			}
		}

	
	}

	/**
	 * Gets the width of the board.
	 * @return the width of the board
	 */
	public int width() 
	{
		return numCol;
	}


}
