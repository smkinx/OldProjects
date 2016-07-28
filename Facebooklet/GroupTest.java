
import student.*;

// -------------------------------------------------------------------------
/**
 *  Tests the method in group
 * 
 *  @author  smb4
 *  @version 2011.03.5
 */
public class GroupTest
    extends TestCase
{
    private Group group;
    private FaceBooklet book;

    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new GroupTest test object.
     */
    public GroupTest()
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
        group = new Group("may", "bay", "say", "jay");
        book = new FaceBooklet();
    }


    // ----------------------------------------------------------
    /**
     * Tests all the getters.
     */
    public void testGetters()
    {
        assertEquals ("may", group.getCreater());
        assertEquals ("bay", group.getName());
        assertEquals ("jay", group.getPictureUrl());
        assertEquals ("say", group.getDescription());
    }

    /**
     * Tests postToWall(), getWall() and getWallPostings().
     */
    public void testGetWall()
    {
        book.createNewUser("sam1", "bar");
        book.login("sam1", "bar");
        group.postToWall(book.getCurrentUser() , "hell");
        assertTrue(0 < group.getWall().size());
        group.postToWall(book.getCurrentUser() , "hell");
        group.postToWall(book.getCurrentUser() , "hell");
        group.postToWall(book.getCurrentUser() , "hell");
        group.postToWall(book.getCurrentUser() , "hell");
        group.postToWall(book.getCurrentUser() , "hell");
        group.postToWall(book.getCurrentUser() , "hell");
        group.postToWall(book.getCurrentUser() , "hell");
        group.postToWall(book.getCurrentUser() , "hell");
        group.postToWall(book.getCurrentUser() , "hell");
        group.postToWall(book.getCurrentUser() , "hell");
        group.postToWall(book.getCurrentUser() , "hell");
        group.postToWall(book.getCurrentUser() , "hell");
        group.postToWall(book.getCurrentUser() , "hell");
        group.postToWall(book.getCurrentUser() , "hell");
        group.postToWall(book.getCurrentUser() , "hell");
        group.postToWall(book.getCurrentUser() , "hell");
        group.postToWall(book.getCurrentUser() , "hell");
        group.postToWall(book.getCurrentUser() , "hell");
        group.postToWall(book.getCurrentUser() , "hell");
        group.postToWall(book.getCurrentUser() , "hell");
        assertEquals(20, group.getWall().size());
        assertTrue(group.getWallPostings().length() > 0);
    }

    /**
     * Tests joinGroup() and getMembers().
     */
    public void testJoinGroup()
    {
        book.createNewUser("sam2", "bar");
        book.createNewUser("sam3", "bar");
        group.joinGroup("sam2");
        group.joinGroup("sam2");
        group.joinGroup("sam3");
        assertTrue(group.getMembers().size() > 0);
    }

}
