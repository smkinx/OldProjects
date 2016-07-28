import student.*;
import student.web.*;


// -------------------------------------------------------------------------
/**
 *  Class used to connect the user with the program, using a web browser.
 * 
 *  @author  Suratim Baruah (smb4)
 *  @version 2011.02.13
 */
public class ProfileViewer

{
    //~ Instance/static variables .............................................
    /**
     * The map where the user profile are stored with a key
     */ 
    protected SharedPersistentMap < UserProfile > profiles;
    
    private UserProfile entry;    
    private UserProfile user;    
    ProfileViewer e = new FaceBooklet();
    
    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new FaceBooklet object.
     */
    public ProfileViewer()
    {
        profiles = new SharedPersistentMap < UserProfile > (UserProfile.class);
    }


    //~ Methods ...............................................................
    /**
     * An accessor to obtain profile.
     * @return entry that holds profile
     */
    public UserProfile getProfile()
    {
        return entry;
    }
    
    
    /**
     * A setter that sets profile.
     * @param newEntry  new UserProfile object.
     */
    public void setProfile(UserProfile newEntry)
    {
        entry = newEntry;
    }

    /**
     * This method set the information from the profile.
     * @param newPass The password of the user
     * @param newEmail The email of the user
     * @param newStatus The status of the user
     * @param newPictureUrl The picture URL of the user    
     * @param newThumb The thumb picture URL of the user   
     */
    public void setInfo(String newEmail,
    String newStatus, String newPictureUrl, String newPass, String newThumb)
    {
        getProfile();
        entry.setEmail(newEmail);
        entry.setStatus(newStatus);
        entry.setPictureUrl(newPictureUrl);
        entry.setPassword(newPass);
        entry.setPictureThumbUrl(newThumb);
        
    }
    /**
     * This method saves the profile.
     */
    public void saveProfile()
    {   
        if (entry != null && !entry.getName().equals(""))
        {
            profiles.put(entry.getName(), entry);
        }  

    }       
    
    /**
     * This method loads profile with name that is inputed.
     * @param name The user name to load.
     */
    public void loadProfile(String name)
    {              
        if  (name != null && !name.equals("") && profiles.get(name) != null )
        {              
            entry = profiles.get(name);
        }
         
    }
    
    /**
     * This method loads profile and return the user Profile.
     * @param name The user name to load.
     * @return returns user profile
     */
    public UserProfile getUser(String name)
    {
        if  (name != null && !name.equals("") && profiles.get(name) != null )
        {              
            user = profiles.get(name);
        }
        else 
        {              
            user = null;
        }
        return user;
    }
    
    /**
     * This method to set the question and answer to 
     * get back the password.
     * @param newQuestion The question.
     * @param newAns the answer.
     */
    public void setSecurityQuestion(String newQuestion, String newAns)
    {
        getProfile();
        entry.setQuestion(newQuestion);
        entry.setAns(newAns);
    }

}