
import student.*;

// -------------------------------------------------------------------------
/**
 *  Tests the FaceBooklet class.
 * 
 *  @author  Suratim Baruah (smb4)
 *  @version 2011.03.09
 */
public class FaceBookletTest
    extends TestCase
{
    private FaceBooklet faceBook1;

    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new FaceBookletTest test object.
     */
    public FaceBookletTest()
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
        faceBook1 = new FaceBooklet();
        
        
    }


    // ----------------------------------------------------------
    /**
     * Tests the getCurrentUser) method.
     */
    
    public void testGetCurrentUser()
    {
        faceBook1.createNewUser("hell", "sam"); 
        assertEquals("hell", faceBook1.getCurrentUser().getName());
    }
    
    /**
     * Tests the createNewProfile() method.
     */
    public void testCreateNewProfile()
    {        
        assertFalse(faceBook1.createNewUser(null, "man"));
        faceBook1.profiles.clear();
        assertFalse(faceBook1.createNewUser("", "man"));
        faceBook1.profiles.clear();
        assertFalse(faceBook1.createNewUser("hel", ""));
        faceBook1.profiles.clear();
        assertTrue(faceBook1.createNewUser("hel", "man"));
        assertFalse(faceBook1.createNewUser("hel", "name"));
       
    }
    
    /**
     * Tests the login() method.
     */
    public void testLogin()
    {        
        faceBook1.createNewUser("hells", "man");
        faceBook1.createNewUser("hells1", "man");
        faceBook1.logout();
        faceBook1.login("", "man");
        assertEquals(null, faceBook1.getCurrentUser());
        faceBook1.login("hells", "");
        assertEquals(null, faceBook1.getCurrentUser());        
        faceBook1.login("hells", "mans");
        assertEquals(null, faceBook1.getCurrentUser());
        faceBook1.login("hells", "man");
        assertEquals("hells", faceBook1.getCurrentUser().getName()); 
        faceBook1.profiles.clear();
        faceBook1.logout();
        faceBook1.login("hellman", "man");
        assertEquals(null, faceBook1.getCurrentUser());
    }
    /**
     * Tests the logout() method.
     */
    public void testLogout()
    {
        faceBook1.profiles.clear();
        faceBook1.createNewUser("hells", "man");        
        assertEquals("hells", faceBook1.getCurrentUser().getName()); 
        faceBook1.logout();
        assertEquals(null, faceBook1.getCurrentUser()); 
    }
    
    /**
     * Tests the getMsg and setMsg().
     */
    public void testGetMsg()
    {
        faceBook1.setMsg("hey");
        assertEquals("hey", faceBook1.getMsg());
    }
    /**
     * Tests the currentProfile
     */
    public void testCurrentProfile ()
    {
        faceBook1.profiles.clear();
        faceBook1.createNewUser("hells", "man");
        faceBook1.logout();
        faceBook1.createNewUser("hells1", "man");
        faceBook1.loadProfile("hells");
        faceBook1.currentProfile();
        assertFalse(faceBook1.currentProfile());
        faceBook1.loadProfile("hells1");
        faceBook1.currentProfile();
        assertTrue(faceBook1.currentProfile());
        faceBook1.logout();
        assertFalse(faceBook1.currentProfile());
        
    }
    /**
     * tests out getForgotPass()
     */
    public void testGetForgotPass()
    {
        faceBook1.createNewUser("na", "man");   
        faceBook1.loadProfile("na");
        faceBook1.setSecurityQuestion("may", "bay");
        assertFalse(faceBook1.getForgotPass("", ""));
        assertFalse(faceBook1.getForgotPass("hello", "bello"));
        assertFalse(faceBook1.getForgotPass("na", ""));
        assertFalse(faceBook1.getForgotPass("na", "jay"));
        assertTrue(faceBook1.getForgotPass("na", "bay"));
    }
}
