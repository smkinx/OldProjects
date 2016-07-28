
import student.*;

// -------------------------------------------------------------------------
/**
 *  Provides test cases for UserProfile.
 * 
 *  @author  smb4
 *  @version 2011.01.29
 */
public class UserProfileTest
    extends TestCase
{
    private UserProfile userProf1;
    private FaceBooklet book;

    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new UserProfileTest test object.
     */
    public UserProfileTest()
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
        userProf1 = new UserProfile();
        book = new FaceBooklet();
    }


    // ----------------------------------------------------------
    /**
     * Tests the setter and getter of the Email.
     */
    public void testEmail()
    {
        userProf1.setEmail("vt@vt.edu");
        assertEquals("vt@vt.edu", userProf1.getEmail());
    }
    /**
     * Tests the setter and getter for the Thumbnail Picture.
     */
    public void testThumbUrl()
    {
        userProf1.setPictureThumbUrl("https://vt.edu/10.png");
        assertEquals("https://vt.edu/10.png", userProf1.getPictureThumbUrl());
    }
    /**
     * Tests the setter and getter for the password.
     */
    public void testPassword()
    {
        userProf1.setPassword("pass");
        assertEquals("pass", userProf1.getPassword());
    }
    /**
     * Tests the setter and getter for the Status.
     */
    public void testStatus()
    {
        userProf1.setStatus("sta");
        assertEquals("sta", userProf1.getStatus());
    }
    /**
     * Tests postToWall() and getWall().
     */
    public void testGetWall()
    {
        book.createNewUser("sam1", "bar");
        userProf1.postToWall(book.getCurrentUser() , "hell");
        assertTrue(0 < userProf1.getWall().size());
        userProf1.postToWall(book.getCurrentUser() , "hell");
        userProf1.postToWall(book.getCurrentUser() , "hell");
        userProf1.postToWall(book.getCurrentUser() , "hell");
        userProf1.postToWall(book.getCurrentUser() , "hell");
        userProf1.postToWall(book.getCurrentUser() , "hell");
        userProf1.postToWall(book.getCurrentUser() , "hell");
        userProf1.postToWall(book.getCurrentUser() , "hell");
        userProf1.postToWall(book.getCurrentUser() , "hell");
        userProf1.postToWall(book.getCurrentUser() , "hell");
        userProf1.postToWall(book.getCurrentUser() , "hell");
        userProf1.postToWall(book.getCurrentUser() , "hell");
        userProf1.postToWall(book.getCurrentUser() , "hell");
        userProf1.postToWall(book.getCurrentUser() , "hell");
        userProf1.postToWall(book.getCurrentUser() , "hell");
        userProf1.postToWall(book.getCurrentUser() , "hell");
        userProf1.postToWall(book.getCurrentUser() , "hell");
        userProf1.postToWall(book.getCurrentUser() , "hell");
        userProf1.postToWall(book.getCurrentUser() , "hell");
        userProf1.postToWall(book.getCurrentUser() , "hell");
        userProf1.postToWall(book.getCurrentUser() , "hell");
        assertEquals(20, userProf1.getWall().size());
    }
    /**
     * Tests getWallPostimgs().
     */
    public void testGetWallPostings()
    {
        book.createNewUser("sam2", "bar");
        userProf1.postToWall(book.getCurrentUser() , "hell");
        userProf1.postToWall(book.getCurrentUser() , "hell2");
        assertTrue(userProf1.getWallPostings().length() > 0);
    }

    /**
     * Tests the setter and getter of the Ans.
     */
    public void testAns()
    {
        userProf1.setAns("vt@vt.edu");
        assertEquals("vt@vt.edu", userProf1.getAns());
    }
    /**
     * Tests the setter and getter of the Question.
     */
    public void testQuestion()
    {
        userProf1.setQuestion("vt@vt.edu");
        assertEquals("vt@vt.edu", userProf1.getQuestion());
    }
}



