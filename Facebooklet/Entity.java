import student.*;
import java.util.ArrayList;


// -------------------------------------------------------------------------
/**
 *  Stores and shows the name and the picture url of the user.
 * 
 *  @author  smb4
 *  @version 2011.01.29
 */
public class Entity
{
    //~ Instance/static variables .............................................
    // The name of the user.
    private String name;
    // The url to the picture of the user.
    private String pictureUrl;
    // The friends of the user.
    private ArrayList<String> friends;
    private ArrayList<String> groups;



    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new Entity object.
     */
    public Entity()
    {
        
        name = null;
        pictureUrl = null;
        friends = new ArrayList<String> ();
        groups = new ArrayList<String> ();
        
                
    }

    //~ Methods ...............................................................
    /**
     * An accessor for this user's name.
     * @return this user's name
     */
    public String getName() 
    {
        return name; 
    }
    // ---------------------------------------------------------- 
    /**
     * Change this user's name.
     * @param newName The new name of the user
     */
    public void setName(String newName) 
    {
        name = newName; 
    }
        /**
     * An accessor for the url of the picture.
     * @return this picture's url
     */
    public String getPictureUrl() 
    {
        return pictureUrl; 
    }
    // ---------------------------------------------------------- 
    /**
     * Changes the url of the picture of the user.
     * @param newPictureUrl The new url to the picture of the user
     */
    public void setPictureUrl(String newPictureUrl) 
    {
        pictureUrl = newPictureUrl; 
    }
    /**
     * An accessor for the friends of the user.
     * @return this user's friends
     */    
    
    public ArrayList<String> getFriends() 
    {
        return friends;
    }
    
    /**
     * Adds new friends to the user's list.
     * @param newFriend new friend of the user
     */
    public void addFriend(String newFriend) 
    {
        if (!getFriends().contains(newFriend))
        {
            friends.add(newFriend);
        }
    }
    /**
     * An accessor for the group of the user.
     * @return this user's groups
     */    
    
    public ArrayList<String> getGroups() 
    {
        return groups;
    }
    /**
     * Adds new groups to the user's list.
     * @param newGroup new friend of the user
     */
    public void addGroup(String newGroup) 
    {
        if (!getGroups().contains(newGroup))
        {
            groups.add(newGroup);
        }
    }
}