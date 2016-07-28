
import student.*;
import java.util.Date;
import student.web.*;


// -------------------------------------------------------------------------
/**
 *  WallPosting framwork.
 * 
 *  @author  smb4
 *  @version (2011.04.03)
 */
public class WallPosting
{
    //~ Instance/static variables .............................................
    private String authorName;
    private String message;
    private Date date;

    
    

    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new WallPosting object.
     * @param newAuthor the author of the post
     * @param newMessage the message on the post
     */
    public WallPosting(UserProfile newAuthor, String newMessage)
    {
        authorName = newAuthor.getName();
        message = newMessage;
        date =  new Date();
        
       
    }
    /**
     * Creates a new WallPosting object.
     */
    public WallPosting()
    {        
        // No variable to initialize. 
       
    }

    //~ Methods ...............................................................
    /**
     * An accessor for the date of the post
     * @return the date
     */  
    public Date getDate()
    {
        return date;
    }
    
    /**
     * An accessor for the message of the post
     * @return the meassage
     */  
    public String getMessage()
    {
        return message;
    }
    
    /**
     * An accessor for the author of the post
     * @return the author
     */  
    public UserProfile getAuthor()
    {
        SharedPersistentMap < UserProfile > profiles =
            new SharedPersistentMap < UserProfile > (UserProfile.class);
        return profiles.get(authorName);
    }

    /**
     * An accessor for the post in string format
     * @return the post
     */  
    public String toString()
    {
        
        String r = "<div class=" + '"'  + "body_right_post" + '"' + ">" +
                    "<div class=" + '"' + "body_wall_pic" + '"' + ">" +
                     "<img src=" + '"' + getAuthor().getPictureThumbUrl() +
                     '"' +
                     " width=" + '"' + "50px" + '"' + " height=" + '"' +
                     "50px" + '"' + "</>" + "</div>" +
                     "<div class=" + '"' + "body_wall_name" + '"' + ">" +
                    authorName + "</div>" +
                     "<div class=" + '"' + "body_wall_date" + '"' + ">" +
                     getDate() + "</div>" +
                     "<div class=" + '"' + "body_wall_msg" + '"' + ">" +
                     getMessage() + "</div>" + "</div>";
                   
            
        return r;     
    }
}
