
import junit.framework.TestCase;
/**
 *  The test class for city class
 *
 *  @author  Supratim Baruah (smb4)
 *  @version 2014/02/5
 */
public class CityTest
    extends TestCase
{
    private City c1;
    /**
    * Sets up the new object.
    */
    public void setUp() throws Exception {

        c1 = new City("Portsmouth", 95535, 5, 6);

    }

    /**
     * Teats all the get functions.
     */
    public void testGets()
    {
        assertEquals("Portsmouth", c1.getName());
        assertEquals(95535, c1.getPopulation());
        assertEquals( 5, c1.getX());
        assertEquals("Portsmouth 95535 5 6", c1.toString());
    }

    /**
     * Tests all the set functions.
     */
    public void testSets()
    {
        c1.setName("p");
        c1.setX(7);
        c1.setY(7);
        c1.setPopulation(1);
        assertEquals("p 1 7 7", c1.toString());
    }



}
