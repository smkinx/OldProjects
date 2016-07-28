package cs2114.minesweeper;
import student.TestCase;



/**
 * // -------------------------------------------------------------------------
/**
 *  Test case for MineSweeperBoard class.
 *
 *  @author  Supratim Baruah (smb4)
 *  @version 2012. 2. 7.
 */
public class MineSweeperBoardTest extends TestCase
{

    private MineSweeperBoard board; //the mine sweeper board
    private MineSweeperBoard board2;
    /**
     * Sets up the board.
     */
    public MineSweeperBoardTest()
    {
        board = new MineSweeperBoard(4, 4, 2);
    }

    /**
     * This method is used to compare boards.
     * @param theBoard the board.
     * @param expected the expected board.
     */
    public void assertBoard(MineSweeperBoard theBoard, String... expected)
    {
        MineSweeperBoard expectedBoard =
            new MineSweeperBoard(expected.length, expected[0].length(), 0);
        expectedBoard.loadBoardState(expected);
        assertEquals(expectedBoard, theBoard);
        // uses equals() from MineSweeperBoardBase
    }

    // ----------------------------------------------------------

    /**
    * Tests the setCell() method.
    */
    public void testSetCell()
    {
        // board is declared as part of the test fixture, and
        // is initialized to be 4x4
        board.loadBoardState("    ",
                             "OOOO",
                             "O++O",
                             "OOOO");

        board.setCell(1, 2, MineSweeperCell.FLAGGED_MINE);

        assertBoard(board, "    ",
                           "OOOO",
                           "OM+O",
                           "OOOO");
    }

    /**
     * Tests the flagCell() method.
     */
    public void testFlagCell()
    {

        board.loadBoardState("    ",
                             "OOOO",
                             "O++O",
                             "OOOO");

        board.flagCell(1, 2);
        assertBoard(board, "    ",
                           "OOOO",
                           "OM+O",
                           "OOOO");
        board.flagCell(0, 1);
        assertBoard(board, "    ",
                           "FOOO",
                           "OM+O",
                           "OOOO");
        board.flagCell(0, 1);
        assertBoard(board, "    ",
                           "OOOO",
                           "OM+O",
                           "OOOO");
        board.flagCell(1, 2);
        assertBoard(board, "    ",
                           "OOOO",
                           "O++O",
                           "OOOO");
    }

    /**
     * Tests the getCell() method.
     */
	public void testGetCell()
    {
        board.loadBoardState("OOOO",
                             "OOOO",
                             "O++O",
                             "OOOO");

        assertEquals(MineSweeperCell.MINE, board.getCell(2, 2));
        assertEquals(MineSweeperCell.INVALID_CELL, board.getCell(6, 6));


    }
	/**
	 * Tests the uncoverCell() method and the numberOfAdjacentMines().
	 */
	public void testUncoverCell()
    {

        board.loadBoardState("OOOO",
                             "FOOO",
                             "O+MO",
                             "OOOO");

        board.uncoverCell(1, 2);
        board.uncoverCell(0, 1);

        assertBoard(board, "OOOO",
                           "FOOO",
                           "O*MO",
                           "OOOO");

    }
	/**
	 * Tests the revealBoard() method.
	 */
	public void testRevealBoard()
	{
		board.loadBoardState("    ",
                "OOOO",
                "O++O",
                "OOOO");

		board.revealBoard();
		assertBoard(board, "    ",
                			"1221",
                			"1**1",
                			"1221");
		board.loadBoardState("    ",
                "OOOO",
                "OMMO",
                "OOOO");

		board.revealBoard();
		assertBoard(board, "    ",
                			"1221",
                			"1**1",
                			"1221");
		board.loadBoardState("    ",
                "OOOO",
                "OFMO",
                "OOOO");

		board.revealBoard();
		assertBoard(board, "    ",
                			" 111",
                			" 1*1",
                			" 111");


	}
	/**
	 * Tests the uncoverCell() method and the numberOfAdjacentMines().
	 */
	public void  testNumberOfAdjacentMines()
	{
		board.loadBoardState("    ",
                "OOOO",
                "O++O",
                "OOOO");
		board.numberOfAdjacentMines(1, 1);
		board.uncoverCell(1, 1);
		assertBoard(board, "    ",
    			"O2OO",
    			"O++O",
    			"OOOO");

	}

	/**
	 * Tests the isGameLost() method.
	 */
	public void testIsGameLost()
	{
		board.loadBoardState("    ",
    			"1221",
    			"1**1",
    			"1221");

		assertEquals(true, board.isGameLost());

		board.loadBoardState("    ",
    			"1221",
    			"1++1",
    			"1221");

		assertEquals(false, board.isGameLost());
	}

	/**
	 * Tests the isGameWon() and isAllUncovered() method.
	 */
	public void testIsGameWon()
	{
		board.loadBoardState("    ",
    			"1221",
    			"1MM1",
    			"1221");

		assertEquals(true, board.isGameWon());
		board.loadBoardState("    ",
    			"1221",
    			"1*M1",
    			"1221");
		assertEquals(false, board.isGameWon());
		board.loadBoardState("    ",
    			"O221",
    			"1MM1",
    			"1221");

		assertEquals(false, board.isGameWon());
		board.loadBoardState("    ",
    			"F221",
    			"1MM1",
    			"1221");

		assertEquals(false, board.isGameWon());
	}
	/**
	 * Tests the width() and height() method.
	 */
	public void testNumberOfColumns()
	{
		board.loadBoardState("    ",
    			"1221",
    			"1MM1",
    			"1221");
		assertEquals(4, board.width());
		assertEquals(4, board.height());
	}

	/**
	 * Tests the placeMines() method.
	 */
	public void testSameMines()
	{
		board2 = new MineSweeperBoard(4, 4, 1);
		assertEquals(true, board2.placeMines(1));
	}




}