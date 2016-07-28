
import student.*;


// -------------------------------------------------------------------------
/**
 *  WallPosting framwork test.
 * 
 *  @author  smb4
 *  @version (2011.04.03)
 */
public class WallPostingTest
    extends TestCase
{
    //~ Constructor ...........................................................

    private WallPosting wallPos1;
    private FaceBooklet book;
    private WallPosting wall;

    
    // ----------------------------------------------------------
    /**
     * Creates a new WallPostingTest test object.
     */
    public WallPostingTest()
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
        book = new FaceBooklet();
        wall = new WallPosting();
    }


    // ----------------------------------------------------------

    /**
     * Test for the toString(), getAutrhor(), getDate(), and getMessage()
     */
    public void testtoString()
    {
        book.createNewUser("sam2", "bar");        
        wallPos1 = new WallPosting(book.getCurrentUser(), "hel");
        assertEquals("<div class=" + '"'  + "body_right_post" + '"' + ">" +
                    "<div class=" + '"' + "body_wall_pic" + '"' + ">" +
                     "<img src=" + '"' + 
                     wallPos1.getAuthor().getPictureThumbUrl() + '"' +
                     " width=" + '"' + "50px" + '"' + " height=" + '"' + 
                     "50px" +  '"' +
                     "</>" + "</div>" +
                     "<div class=" + '"' + "body_wall_name" + '"' + ">sam2" +
                     "</div>" +
                     "<div class=" + '"' + "body_wall_date" + '"' + ">" +
                     wallPos1.getDate() + "</div>" +
                     "<div class=" + '"' + "body_wall_msg" + '"' + ">" +
                     "hel" + "</div>" + "</div>" , wallPos1.toString());
        
    }
    /**
     * Test for the wallPosting() contructor
     */
    public void testGetMessage()
    {
        assertEquals( null, wall.getMessage());
    }
}
