
import student.*;

// -------------------------------------------------------------------------
/**
 *  Tests the ProfileViewer class.
 * 
 *  @author  Suratim Baruah (smb4)
 *  @version 2011.02.13
 */
public class ProfileViewerTest
    extends TestCase
{
    private ProfileViewer faceBook1;
    private FaceBooklet faceBook2;
    private UserProfile entry;

    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new FaceBookletTest test object.
     */
    public ProfileViewerTest()
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
        faceBook1 = new ProfileViewer(); 
        faceBook2 = new FaceBooklet(); 
        
    }


    // ----------------------------------------------------------
    
    
    /**
     * Tests the getprofile() method.
     */
    public void testGetProfile()
    {
        assertEquals(null, faceBook1.getProfile());
    }
    


    
    /**
     * Tests the setProfile() method.
     */
    public void testSetProfile()
    {
        faceBook1.setProfile(null);        
        assertEquals(null, faceBook1.getProfile());
    }
    
    /**
     * This method is to test saveProfile() Method.
     * @param newName of the new profile
     */
    public void createNewProfile(String newName)
    {         
        if (newName != null && !newName.equals(""))
        {
            entry = new UserProfile();   
            entry.setName(newName);
            faceBook1.setProfile(entry);    
        }
    }
    /**
     * Test saveProfile() Method.
     */
    public void testSaveProfile()
    {
        faceBook1.profiles.clear();
        createNewProfile("");
        faceBook1.saveProfile();
        assertEquals(null, faceBook1.getProfile());
        createNewProfile(null);       
        faceBook1.saveProfile();
        assertEquals(null, faceBook1.getProfile());
        createNewProfile("hel");
        faceBook1.saveProfile();
        assertEquals("hel", faceBook1.getProfile().getName());
    }
    /**
     * Tests the loadProfile().
     */
    public void testLoadProfile()
    {
        faceBook1.profiles.clear();
        faceBook2.createNewUser("", "man");
        faceBook1.loadProfile("");
        assertEquals(null, faceBook1.getProfile());
        faceBook1.profiles.clear();
        faceBook2.createNewUser("hel", "");
        faceBook1.loadProfile("hel");
        assertEquals(null, faceBook1.getProfile());
        faceBook1.profiles.clear();
        faceBook2.createNewUser("hel", "man");
        faceBook1.loadProfile("hel");
        assertEquals("man", faceBook1.getProfile().getPassword());
    }
    /**
     * Tests the getInfo and setInfo().
     */
    public void testSetInfo()
    {
        faceBook2.createNewUser("ma", "man");   
        faceBook1.loadProfile("ma");
        faceBook1.setInfo("may", "bay", "hay", "jay", "nay");
        assertEquals("may", faceBook1.getProfile().getEmail());
        assertEquals("bay", faceBook1.getProfile().getStatus());
        assertEquals("hay", faceBook1.getProfile().getPictureUrl());
        assertEquals("nay", faceBook1.getProfile().getPictureThumbUrl());
    }
    /**
     * Tests the getUser()
     */
    public void testGetUser()
    {
        faceBook2.createNewUser("ma", "man"); 
        assertEquals("ma", faceBook2.getUser("ma").getName()); 
        faceBook1.profiles.clear();
        assertEquals(null, faceBook2.getUser(null));
        assertEquals(null, faceBook2.getUser(""));
    }
    /**
     * tests the setSecurityQuestion()
     */
    public void testSetSecurityQuestion()
    {
        faceBook2.createNewUser("na", "man");   
        faceBook1.loadProfile("na");
        faceBook1.setSecurityQuestion("may", "bay");
        assertEquals("may", faceBook1.getProfile().getQuestion());
        assertEquals("bay", faceBook1.getProfile().getAns());
    }
}

