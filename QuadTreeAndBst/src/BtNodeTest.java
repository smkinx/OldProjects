
import junit.framework.TestCase;
/**
 *  The test class for city class
 *
 *  @author  Supratim Baruah (smb4)
 *  @version 2014/02/5
 */
public class BtNodeTest
    extends TestCase
{

    private BtNode b1;
    private BtNode b2;
    private BtNode b3;
    private BtNode b4;
    private BtNode b5;
    private City c4;


    /**
    * Sets up the new object.
    */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void setUp() throws Exception
    {
        City c1 = new City("Portsmouth", 95535, 5, 6);
        City c2 = new City("r", 1, 7, 8);
        c4 = new City("d", 4, 9, 10);
        b1 = new BtNode("Portsmouth", c1);
        b2 = new BtNode("r", c1);
        b3 = new BtNode("a", c4);
        b4 = new BtNode("r", c2);
        b5 = new BtNode("r", c1);
    }

    /**
     * Tests all the set functions.
     */
    @SuppressWarnings("unchecked")
    public void testSets()
    {
        assertEquals("d 4 9 10", b3.getVal().toString());
        assertEquals("Portsmouth", ((City) b1.getVal()).getName());
        assertEquals("Portsmouth",  b1.getKey());
        b1.setRight(b2);
        b1.setLeft(b3);
        b1.setKey("p");
        assertEquals("r", b1.right().getKey());
        assertEquals("a", b1.left().getKey());
        assertEquals("p", b1.getKey());
        b1.setVal(c4);
        assertEquals(4, ((City) b1.getVal()).getPopulation());
        assertEquals(0, b1.compare(b2, b5));
        assertEquals(1, b1.compare(b2, b4));
        assertEquals(-1, b1.compare(b2, b3));
        assertEquals(1, b1.compare(b3, b2));

    }

}
