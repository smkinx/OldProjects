
import student.*;
import student.web.*;


// -------------------------------------------------------------------------
/**
 *  Allows user to connect to group
 * 
 *  @author  smb4
 *  @version 2011.03.5
 */
public class GroupViewer
    extends Group
{
    //~ Instance/static variables .............................................
    /**
     * The map where the user profile are stored with a key
     */ 
    protected SharedPersistentMap < Group > groups;
    private Group group;    
    


    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new GroupViewer object.
     */
    public GroupViewer()
    {
        groups = new SharedPersistentMap < Group > (Group.class);
       
    }


    //~ Methods ...............................................................
     /**
     * An accessor to obtain profile.
     * @return entry that holds profile
     */
    public Group getGroup()
    {
        return group;
    }
    
    
    /**
     * A setter that sets profile.
     * @param newGroup  new UserProfile object.
     */
    public void setGroup(Group newGroup)
    {
        group = newGroup;
    }
    
    /**
     * This method saves the group.
     */
    public void saveGroup()
    {   
        groups.put(group.getName(), group);
    }       
    
    /**
     * This method loads profile with name that is inputed.
     * @param groupName The user name to load.
     * @return true or false depending the used was able to 
     * load the group.
     */
    public boolean loadGroup(String groupName)
    {              
        if  (groupName != null && !groupName.equals("") &&
            groups.get(groupName) != null )
        {              
            group = groups.get(groupName);
            return true;
        }         
        return false;
    }   
    
    /**
     * This method creats a group.
     * @param creater the creater of the group
     * @param groupName the name of the group
     * @param description the description of the group
     * @param pictureUrl the pic url of the group.
     * @return true or false depending if the user was 
     * able to make the group
     */
    public boolean createGroup(String creater, String groupName,
        String description, String pictureUrl)
    {
        if  (groupName != null && !groupName.equals(""))
        {   
            group = new Group(creater, groupName, description, pictureUrl);     
            return true;
        }
        return false;
    }   
    
}

