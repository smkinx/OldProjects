
import student.*;

// -------------------------------------------------------------------------
/**
 *  Tests the method in groupviewer
 * 
 *  @author  smb4
 *  @version 2011.03.5
 */
public class GroupViewerTest
    extends TestCase
{
    //~ Constructor ...........................................................
    private GroupViewer gro;

    private Group gro2;
    // ----------------------------------------------------------
    /**
     * Creates a new GroupViewerTest test object.
     */
    public GroupViewerTest()
    {
        // The constructor is usually empty in unit tests, since it runs
        // once for the whole class, not once for each test method.
        // Per-test initialization should be placed in setUp() instead.
    }


    //~ Methods ...............................................................

    // ----------------------------------------------------------
    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
    public void setUp()
    {
        gro = new GroupViewer();
        gro2 = new Group ("fay", "hay", "nay", "lay");
    }


    // ----------------------------------------------------------
    /**
     * Tests out every method in the class
     */
    public void testGetGroup()
    {
        assertFalse(gro.createGroup("may", "", "say", "jay"));
        gro.createGroup("may", "bay", "say", "jay");
        gro.saveGroup();

        gro.loadGroup("bay");
        assertFalse(gro.loadGroup(""));
        assertEquals("bay", gro.getGroup().getName());
        
        gro.setGroup(gro2);
        assertEquals("hay", gro.getGroup().getName());
        
    }

}
