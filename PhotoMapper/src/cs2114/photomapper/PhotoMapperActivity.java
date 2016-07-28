package cs2114.photomapper;

import java.io.IOException;

import student.android.ExifInterface;
import student.android.ImageOverlay;
import student.android.MediaUtils;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

// -------------------------------------------------------------------------
/**
 *  The activity class of the photomapper
 *
 *  @author  Supratim Baruah (smb4)
 *  @version 2012.24.2
 */
public class PhotoMapperActivity extends MapActivity
{
	//~ Instance/static variables .............................................
	private static final int IMAGE_PICKED = 1;
	private ExifInterface exif;
	private String imagePath;
	private float[] latLong;
	private float[] latLong1;
	private MapView mv;
	private TextView coordinates;
	private ImageOverlay overlay;
	private float lat1;
	private float longi1;
	private int lat;
	private int longi;
	private ImageOverlay ol1;
	private PhotoMapper pm;
	private GeoPoint gp;
	private Uri uriP;


	//~ Methods ...............................................................

	// ----------------------------------------------------------

	/**
	 * Gets the model.
	 * @return the model
	 */
	public PhotoMapper getModel()
	{
		return pm;
	}


	/**
	 * Places an overlay on the mapview
	 */
	public void overlay()
	{
		latLong1 = new float[3];
		latLong1[0] = 1000000;

		pm.addLoc(lat1 + "," + longi1);
		lat1 = lat1 * latLong1[0];
		longi1 = longi1 * latLong1[0];

		lat = (int) lat1;
		longi = (int) longi1;
		gp = new GeoPoint(lat, longi);
		mv = (MapView) findViewById(R.id.mapView);
		overlay = new ImageOverlay(imagePath, gp);
		ol1 = overlay;
		mv.getOverlays().add(overlay);
		mv.getController().animateTo(gp);
		mv.postInvalidate();


		overlay.setOnClickListener(new OverLay1());


	}

	/**
	 * Gets the coordinates of the image.
	 * @param imagePath1 the path of the image
	 * @return if the image has a location data or not
	 */
	public boolean getCoordinates(String imagePath1)
	{

		try {
			exif = new ExifInterface(imagePath1);
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		latLong = new float[3];
		exif.getLatLong(latLong);


		if (latLong[0] == 0 && latLong[1] == 0)
		{
			coordinates.setText("Photo does not have location data.");
			return false;
		}
		else
		{
			lat1 = latLong[0];
			longi1 = latLong[1];
			coordinates.setText(latLong[0] + ", " + latLong[1]);
			pm.addToMap(coordinates.getText(), uriP);
			overlay();
			return true;
		}

	}

	/**
	 * add Button.
	 * @param view view of the button
	 */
	public void addButton(View view)
	{
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.setType("image/jpeg");

		startActivityForResult(intent, IMAGE_PICKED);
	}

	/**
	 * view Button.
	 * @param view view of the button
	 */
	public void viewButton(View view)
	{
		if (coordinates.getText().equals("") || coordinates.getText() == null)
		{
			coordinates.setText("Please select or add a photo.");

		}
		else
		{
			Intent intent = new Intent(Intent.ACTION_VIEW);
			intent.setData(pm.getFromMap(coordinates.getText()));

			startActivity(intent);
		}

	}

	/**
	 * remove Button
	 * @param view view of the button
	 */
	public void removeButton(View view)
	{
		if (coordinates.getText().equals("") || ol1 == null)
		{
			coordinates.setText("Please select or add a photo.");
		}
		else
		{
			int i = mv.getOverlays().indexOf(ol1);
			mv.getOverlays().remove(i);
			pm.removeLoc(i);
			coordinates.setText("");
			mv.postInvalidate();
			ol1 = null;
		}
	}

	/**
	 * addButton calls on this method to get uri and image path.
	 * @param requestCode the requested code
	 * @param resultCode the result code
	 * @param data the data
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
			Intent data)
	{
		if (requestCode == IMAGE_PICKED && resultCode == RESULT_OK)
		{
			uriP = data.getData();
			imagePath = MediaUtils.pathForMediaUri(getContentResolver(), uriP);
			getCoordinates(imagePath);
		}


		super.onActivityResult(requestCode, resultCode, data);
	}
	/**
	 * Called when the activity is first created.
	 *
	 * @param savedInstanceState information that was saved the last time the
	 *     activity was suspended
	 */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mv = (MapView) findViewById(R.id.mapView);
		mv.setBuiltInZoomControls(true);
		pm = new PhotoMapper();
		coordinates = (TextView) findViewById(R.id.statusLabel);
		coordinates.setText("");
		ol1 = null;

	}


	// ----------------------------------------------------------
	/**
	 * This method is required by the {@code MapActivity} class. Just return
	 * false.
	 *
	 * @return false
	 */
	@Override
	protected boolean isRouteDisplayed()
	{
		return false;
	}

	/**
	 * Listener for the overlay.
	 */
	private class OverLay1 implements ImageOverlay.OnClickListener
	{
		/**
		 * The method which is invoked when the overlay is clicked
		 */
		@Override
		public void onClick(ImageOverlay ol, MapView mv1) {

			ol1 = ol;
			int i = mv.getOverlays().indexOf(ol);
			coordinates.setText(pm.getLoc(i));
		}
	}

}