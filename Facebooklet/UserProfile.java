import student.*;
import java.util.ArrayList;


// -------------------------------------------------------------------------
/**
 *  Stores and shows the email, password, and the thumbnail url of the user.
 * 
 *  @author  smb4
 *  @version 2011.01.29
 */
public class UserProfile
    extends Entity
{
    //~ Instance/static variables .............................................
    // The password of the user.
    private String password;
    // The email of the user.
    private String email;
    // The picture of the thumbnail.
    private String pictureThumbUrl;
    // The status of the user.
    private String status;
   
    private ArrayList<WallPosting> wall;
    
    private WallPosting wallPost;
    
    private String question;
    private String ans;
   


    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new UserProfile object.
     */
    public UserProfile()
    {
        super();
        email = null;
        status = null;       
        password = null;
        wall = new ArrayList<WallPosting>(20);
        
        
    }


    //~ Methods ...............................................................
    /**
     * An accessor for this user's password.
     * @return the user's password
     */
    public String getPassword() 
    {
        return password; 
    }
    // ---------------------------------------------------------- 
    /**
     * Change the user's password.
     * @param newPassword The new password of the user
     */
    public void setPassword(String newPassword) 
    {
        password = newPassword; 
    }
    /**
     * An accessor for the email of the user.
     * @return this user's email
     */
    public String getEmail() 
    {
        return email; 
    }
    // ---------------------------------------------------------- 
    /**
     * Changes the email of the user.
     * @param newEmail The new email of the user.
     */
    public void setEmail(String newEmail) 
    {
        email = newEmail; 
    }
    /**
     * An accessor for the url of the thumbnail picture.
     * @return this thumbnail picture's url
     */
    public String getPictureThumbUrl() 
    {
        return pictureThumbUrl; 
    }
    // ---------------------------------------------------------- 
    /**
     * Changes the url of the thumbnail picture of the user.
     * @param newPictureThumbUrl The new url of the thumbnail picture.
     */
    public void setPictureThumbUrl(String newPictureThumbUrl) 
    {
        pictureThumbUrl = newPictureThumbUrl; 
    }
    /**
     * An accessor for the status.
     * @return this status of the user.
     */
    public String getStatus() 
    {
        return status; 
    }
    // ---------------------------------------------------------- 
    /**
     * Changes the status of the user.
     * @param newStatus The new status of the user.
     */
    public void setStatus(String newStatus) 
    {
        status = newStatus; 
    }
    /**
     * An accessor for the questionj.
     * @return this question of the user.
     */
    public String getQuestion() 
    {
        return question; 
    }
    // ---------------------------------------------------------- 
    /**
     * Changes the answer of the user.
     * @param newAns The new answer of the user.
     */
    public void setAns(String newAns) 
    {
        ans = newAns; 
    }
    /**
     * An accessor for the answer.
     * @return this answer of the user.
     */
    public String getAns() 
    {
        return ans; 
    }
    // ---------------------------------------------------------- 
    /**
     * Changes the question of the user.
     * @param newQuestion The new question of the user.
     */
    public void setQuestion(String newQuestion) 
    {
        question = newQuestion; 
    }
    
    /**
     * An accessor for the wall array list.
     * @return the array list of the wall
     */    
    public ArrayList<WallPosting> getWall()
    {
        return wall;
    }
    
    /**
     * An accessor for the wall  list.
     * @return the wall
     */  
    public String getWallPostings()
    {
        int i = 0;
        String wallPostings = "";
        while (i < wall.size() && i < 20)
        {
            wallPostings = wallPostings +  wall.get(i).toString();
            i++;
        }
        return wallPostings;
    }
    /**
     * A methos to post on the wall
     * @param author the write of the post
     * @param post the message
     */  
    public void postToWall(UserProfile author, String post)
    {    
        wallPost = new WallPosting(author , post);
        wall.add(0, wallPost); 
        if (wall.size() > 20)
        {
            wall.remove(20);
        }
        
    }

}
