
import student.*;
import student.web.*;


// -------------------------------------------------------------------------
/**
 *  Class used to connect the user with the program, using a web browser.
 * 
 *  @author  Suratim Baruah (smb4)
 *  @version 2011.03.09
 */
public class FaceBooklet
    extends ProfileViewer
{
    //~ Instance/static variables .............................................
    private SessionPersistentMap <UserProfile> currentUser;
    private UserProfile user;
    private UserProfile entry;
    private String msg;
    ProfileViewer p = new ProfileViewer();
    
    
    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new FaceBooklet object.
     */
    public FaceBooklet()
    {
        super();
        currentUser = new SessionPersistentMap <UserProfile> 
        (UserProfile.class); 
        if ( getCurrentUser() != null)
        {
            setProfile(getCurrentUser());
        }       
        
        
    }


    //~ Methods ...............................................................
    /**
     * Message that the user gets.
     * @return the message
     */
    public String getMsg()
    {
        return msg;
    }
    
    /**
     * Message sets the message.
     * @param newMsg the new message to display.
     */
    public void setMsg(String newMsg)
    {
        msg = newMsg;
        
    }
    
    /**
     * An accessor for current user's profile.
     * @return the current user's profile
     */
    public UserProfile getCurrentUser()
    {        
        user = currentUser.get("user");
        
        return user;
    }

    
    
    /**
     * Meathod decides the user can login or not.
     * @param userName the user name.
     * @param password the password
     * @return decides if the user may or may not login.
     */
    public boolean login(String userName, String password)
    {        
        if ((userName != null && !userName.equals("")) && (password != null &&
            !password.equals("")))
        {        
            
            currentUser.remove("user");       
            if (profiles.get(userName) != null)
            {
                String pass = profiles.get(userName).getPassword();
                if (pass.equals(password))
                {
                         
                    user = profiles.get(userName);
                    currentUser.put("user", 
                        profiles.get(userName));
                    WebUtilities.showWebPage("home.zhtml");
                    setMsg("<div id =" + '"' + "msg" + '"' +                
                        ">Welcome! You have successfully logged in." + 
                        "</div>") ;
                    return true;
                    
                }
                else
                {
                    setMsg("<div id =" + '"' + "msg" + '"' +                
                        ">User Name and Password does not match" + 
                        "</div>");                 
                    return false;
                }
            }
            else
            {
                setMsg("<div id =" + '"' + "msg" + '"' +                
                    ">No such user exists" + 
                    "</div>");
                return false;
            }
        }
        else
        {
            setMsg("<div id =" + '"' + "msg" + '"' +                
                    ">UserName or Password is left blank" + 
                    "</div>");
            return false;
        }    
    }
  
    /**
     * Method decides if user is able to create a profile.
     * @param newName the user name
     * @param newPass the password
     * @return decides if the user may or may not create a new profile.
     */
    public boolean createNewUser(String newName, String newPass)
    {   
        if ((newName != null && !newName.equals("")) && (newPass != null &&
             !newPass.equals("")))
        {   
            
            if (profiles.get(newName) == null) 
            {
                setMsg("<div id =" + '"' + "msg" + '"' +                
                     ">Welcome! You have successfully created a new profile." +
                     "</div>");
                entry = new UserProfile();   
                entry.setName(newName);
                entry.setPassword(newPass);
                profiles.put(entry.getName(), entry);     
                currentUser.put("user", 
                        profiles.get(newName));
                WebUtilities.showWebPage("securityquestion.zhtml");                              
                return true;
            }
            else
            {
                setMsg("<div id =" + '"' + "msg" + '"' +                
                    ">UserName is already taken" + 
                    "</div>");
                return false;
            }
        }
        else
        {
            setMsg("<div id =" + '"' + "msg" + '"' +                
                    ">UserName or Password is left blank" + 
                    "</div>");
            return false;
        }
    }   
    
    /**
     * Logs out the user from the current session.
     */
    public void logout()
    {
        currentUser.remove("user");     
    }
    
    /**
     * Checks if the user is viewing his profile.
     * @return true if he is, false if he is not
     */
    public boolean currentProfile()
    {
        if (getCurrentUser() != null)
        {
            if (getProfile().getName().equals(getCurrentUser().getName()))
            {            
                return true;
            }
            else
            {
                return false;
            }
        }   
        return false;        
    }
    
    /**
     * Checks if the user's answer is right .
     * @return true if the answer is right, false if it's not
     * @param ans the answer of thw question
     * @param userName the user who forgot his password
     */
    public boolean getForgotPass(String userName, String ans)
    {
        if ((userName != null && !userName.equals("")))
        {                    
            currentUser.remove("user");       
            if (profiles.get(userName) != null)
            {
                if (!ans.equals("") && ans != null)
                {
                    if (ans.equals(profiles.get(userName).getAns()))
                    {
                        setMsg("<div id =" + '"' + "msg" + '"' +                
                            ">Password:" 
                            + profiles.get(userName).getPassword() 
                            + "</div>"); 
                        return true;
                    }
                    else
                    {
                        setMsg("<div id =" + '"' + "msg" + '"' +                
                            ">Answer is wrong." + 
                            "</div>");
                        return false;
                    }
                }
                else
                {
                    setMsg("<div id =" + '"' + "msg" + '"' +                
                        ">Question not answered." + 
                        "</div>");
                    return false;
                }
            }
            else
            {
                setMsg("<div id =" + '"' + "msg" + '"' +                
                    ">No such user exists." + 
                    "</div>");
                return false;
            }
        }
        else
        {
            setMsg("<div id =" + '"' + "msg" + '"' +                
                    ">UserName not entered." +
                    "</div>");
            return false;
        }      

    }

}

