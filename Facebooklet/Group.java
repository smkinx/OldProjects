
import student.*;
import java.util.ArrayList;

// -------------------------------------------------------------------------
/**
 *  Group framework
 * 
 *  @author  smb4
 *  @version 2011.03.5
 */
public class Group
{
    //~ Instance/static variables .............................................
    // The url to the picture of the group.
    private String pictureUrl;
    // The members of the group
    private ArrayList<String> members;
    // The Discription of the Group
    private String description;

    private String name;
    private String creater;
    private ArrayList<WallPosting> wall;
    private WallPosting wallPost;

    
    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new Group object.
     * @param createrName the creater of the group
     * @param groupName the name of the group
     * @param description1 the description of the group
     * @param pictureUrl1 the pic url of the group.
     */
    public Group(String createrName, String groupName, String description1,
        String pictureUrl1)
    {
        creater = createrName;
        name = groupName;
        description = description1;
        pictureUrl = pictureUrl1;
        members = new ArrayList<String> ();
        wall = new ArrayList<WallPosting>(20);
    }
    /**
     * Creates a new Group object.
     */
    public Group()
    {
        creater = null;
        name = null;
        description = null;
        pictureUrl = null;
        members = new ArrayList<String> ();
        wall = new ArrayList<WallPosting>(20);
    }



    //~ Methods ...............................................................
    /**
     * An accessor for the creater of the group.
     * @return this user's name
     */
    public String getCreater() 
    {
        return creater; 
    }

    // ---------------------------------------------------------- 

    /**
     * An accessor for this user's group.
     * @return this user's Group
     */
    public String getName() 
    {
        return name; 
    }
    
    /**
     * An accessor for the url of the picture.
     * @return this picture's url
     */
    public String getPictureUrl() 
    {
        return pictureUrl; 
    }
    /**
     * An accessor for the discription of the group.
     * @return this picture's url
     */
    public String getDescription() 
    {
        return description; 
    }


    // ---------------------------------------------------------- 
    
    /**
     * An accessor for the friends of the user.
     * @return this user's friends
     */    
    
    public ArrayList<String> getMembers() 
    {
        return members;
    }
    
    /**
     * Adds new friends to the user's list.
     * @param newMember new friend of the user
     */
    public void joinGroup(String newMember) 
    {
        if (!getMembers().contains(newMember))
        {
            members.add(newMember);
        }
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

