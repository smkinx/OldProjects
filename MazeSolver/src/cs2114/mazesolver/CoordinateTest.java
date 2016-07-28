package cs2114.mazesolver;

import junit.framework.TestCase;
/**
 *  The test class for Coordinates class
 *
 *  @author  Supratim Baruah (smb4)
 *  @version 2011.11.1
 */
public class CoordinateTest extends TestCase {
	private Coordinate cord;
	/**
	 * Create a new test class
	 */
	public CoordinateTest()
	{
		// The constructor is usually empty in unit tests, since it runs
		// once for the whole class, not once for each test method.
		// Per-test initialization should be placed in setUp() instead.
	}


	//~ Public methods ........................................................

	// ----------------------------------------------------------
	/**
	 * Creates two brand new, empty sets for each test method.
	 */
	public void setUp()
	{
		cord = new Coordinate(1, 1);

	}

	/**
	 * tests the getrow and getcol.
	 */
	public void testGetXY()
	{
		assertEquals(1, cord.getCol());
		assertEquals(1, cord.getRow());
	}

}
