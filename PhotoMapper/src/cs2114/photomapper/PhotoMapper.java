package cs2114.photomapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

import android.net.Uri;

/**
 *  The model class of the photomapper
 *
 *  @author  Supratim Baruah (smb4)
 *  @version 2012.24.2
 */
public class PhotoMapper extends Observable {

	private ArrayList<CharSequence> location;
	private HashMap<CharSequence, Uri> photos;

	/**
	 * The constructor
	 */

	public PhotoMapper()
	{
		location = new ArrayList<CharSequence>();
		photos = new HashMap<CharSequence, Uri> ();

	}

	/**
	 * Adds the location of the picture in a list.
	 * @param obj the location
	 */
	public void addLoc(CharSequence obj)
	{
		location.add(obj);
	}

	/**
	 * gets the location from the list.
	 * @param i the index of the location.
	 * @return the location
	 */
	public CharSequence getLoc(int i)
	{
		return location.get(i);
	}

	/**
	 * removes the location from the list.
	 * @param i the index of the location
	 */
	public void removeLoc(int i)
	{
		location.remove(i);
	}

	/**
	 * Gets the location list
	 * @return the location list
	 */
	public ArrayList<CharSequence> getLocList()
	{
		return location;
	}

	/**
	 * Adds the loc and uri of the picture to a hash map
	 * @param loc the location
	 * @param uriP the uri
	 */
	public void addToMap(CharSequence loc, Uri uriP)
	{
		photos.put(loc, uriP);
	}

	/**
	 * gets the uri using the location
	 * @param loc the location
	 * @return the uri
	 */
	public Uri getFromMap(CharSequence loc)
	{
		return photos.get(loc);
	}

}
