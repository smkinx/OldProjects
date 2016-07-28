package cs2114.photomapper;


import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;




/**
 *  The activity class test of the photo mapper
 *
 *  @author  Supratim Baruah (smb4)
 *  @version 2012.24.2
 */
public class PhotoMapperActivityTest
	extends student.AndroidTestCase<PhotoMapperActivity>
{

	/**
	 * the constructor
	 */
	public PhotoMapperActivityTest() {
		super(PhotoMapperActivity.class);

	}


	private MapView mapView;
	private PhotoMapper pm;
	private PhotoMapperActivity pma;
	private Button addButton;
	private Button viewButton;
	private Button removeButton;
	private TextView coordinate;

	/**
	 * setup for the tests.
	 */
	public void setUp()
	{
		pma = new PhotoMapperActivity();
		pm = getActivity().getModel();
		mapView = getView(MapView.class, R.id.mapView);
		addButton = getView(Button.class, R.id.addPhoto);
		viewButton = getView(Button.class, R.id.viewPhoto);
		removeButton = getView(Button.class, R.id.removePhoto);
		coordinate = getView(TextView.class, R.id.statusLabel);

	}
	/**
	 * Centers the map on the specified coordinates and then clicks the middle
	 * pixel, to simulate clicking on a particular geographic location on the
	 * map.
	 *
	 * @param latitude the latitude to click
	 * @param longitude the longitude to click
	 */
	private void clickCoordinates(float latitude, float longitude)
	{
	    mapView.getController().animateTo(new GeoPoint(
	        (int) (latitude * 1000000), (int) (longitude * 1000000)));

	    click(mapView, mapView.getWidth() / 2, mapView.getHeight() / 2);
	}

	/**
	 * test for the add button
	 */
	public void testAddButton()
	{
		click(addButton);
		prepareToSelectMediaInChooser("photomapper4.jpg");

		click(addButton);

		assertEquals("Photo does not have location data.",
				coordinate.getText());

		prepareToSelectMediaInChooser("photomapper1.jpg");

		click(addButton);

		assertEquals("37.217,-80.40217", pm.getLoc(0));

	}

	/**
	 * test for the view button
	 */
	public void testViewButton()
	{
		click(viewButton);
		assertEquals("Please select or add a photo.", coordinate.getText());

		prepareToSelectMediaInChooser("photomapper1.jpg");

		click(addButton);

		assertEquals("37.217,-80.40217", pm.getLoc(0));
		float lat = (float) 37.217;
		float longi = (float) -80.40217;
		clickCoordinates(lat, longi);
		assertEquals("37.217,-80.40217", pm.getLoc(0));
		prepareForUpcomingActivity(Intent.ACTION_VIEW);
		click(viewButton);
		assertEquals("37.217,-80.40217", coordinate.getText());

	}

	/**
	 * test for the remove button
	 */
	public void testRemoveButton()
	{

		click(removeButton);

		assertEquals("Please select or add a photo.", coordinate.getText());

		prepareToSelectMediaInChooser("photomapper1.jpg");

		click(addButton);

		assertEquals("37.217, -80.40217", coordinate.getText());

		click(removeButton);
		assertEquals("", coordinate.getText());
	}
	/**
	 * test for the exception from exifInterface
	 */
	public void testException()
	{

		Exception occurred = null;
		try
		{
			pma.getCoordinates("/amd/hello.jpg");
		}
		catch (Exception exception)
		{
			occurred = exception;
		}
		assertNotNull(occurred);
	}
}
