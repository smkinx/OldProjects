
import student.*;

// -------------------------------------------------------------------------
/**
 *  Provides test cases for Entity.
 * 
 *  @author  smb4
 *  @version 2011.01.29
 */
public class EntityTest
    extends TestCase
{
    private Entity entity1;

    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new EntityTest test object.
     */
    public EntityTest()
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
        entity1 = new Entity();
    }


    // ----------------------------------------------------------
    /**
     * Tests the setter and getter of the Name.
     */

    public void testName()
    {
        entity1.setName("bebbie");
        assertEquals("bebbie", entity1.getName());
    }
    /**
     * Tests the setter and getter of the PictureUrl.
     */

    public void testPictureUrl()
    {
        entity1.setPictureUrl("https://vt.edu/10.png");
        assertEquals("https://vt.edu/10.png", entity1.getPictureUrl());
    }
    
    /**
     * Tests the getFriends() and addFriend() method.
     */
    public void testGetFriends()
    {
        entity1.addFriend("hey");
        entity1.addFriend("hey");
        assertTrue(0 < entity1.getFriends().size());        
    }
    /**
     * Tests the getGroups() and addGroup() method.
     */
    public void testGetGroups()
    {
        entity1.addGroup("hey");
        entity1.addGroup("hey");
        assertTrue(0 < entity1.getGroups().size());        
    }

}



