package cs2114.photomapper;

import student.TestCase;

/**
 *  The test for the photomapper class
 *
 *  @author  Supratim Baruah (smb4)
 *  @version 2012.24.2
 */
public class PhotoMapperTest extends TestCase {

	private PhotoMapper pm;

	/**
	 * the constructor
	 */
	public PhotoMapperTest()
	{
		pm = new PhotoMapper();
	}
	/**
	 * test for the addloc()
	 */
	public void testAddLoc()
	{
		pm.addLoc("10, 10");
		assertEquals("10, 10", pm.getLoc(0));
	}
	/**
	 * test for the addToMap()
	 */
	public void testAddToMap()
	{
		pm.addToMap("1, 1", null);
		assertEquals(null, pm.getFromMap("1, 1"));
	}
	/**
	 * test for the getLocList
	 */
	public void testGetLocList()
	{
		pm.addLoc("10");
		assertEquals(1, pm.getLocList().size());
	}

}
