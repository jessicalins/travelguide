package it.polito.travelguide.app.activities;

import java.io.IOException;
import java.io.InputStream;

import it.polito.travelguide.app.R;
import it.polito.travelguide.app.model.Place;
import it.polito.travelguide.app.model.PlacesDataContainer;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PlaceDetailsActivity extends Activity {
	private Place place;
	private LinearLayout layout;

    @Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_place_details);
	    Bundle extras = getIntent().getExtras();
	    String placeCategory = extras.getString("it.polito.travelguide.app.placeCategory");
	    int placeId = extras.getInt("it.polito.travelguide.app.placeId");
	    PlacesDataContainer dataContainer = PlacesDataContainer.newInstance(this);
	    place = dataContainer.getMap().get(placeCategory).get(placeId);
	    TextView textView = (TextView) findViewById(R.id.place_details_description);
	    textView.setText(place.getDescription());
	    setPlacePictures();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main, menu);
	    
	    return super.onCreateOptionsMenu(menu);
	}

	public void getRoute(View view){
		Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
	}
	
	private void setPlacePictures(){
		layout = (LinearLayout) findViewById(R.id.place_details_pictures);
		for(int i=0; i<place.getPictures().size(); i++){
            try {
                ImageView image = new ImageView(this);
                
                DisplayMetrics metrics = new DisplayMetrics();
        		getWindowManager().getDefaultDisplay().getMetrics(metrics);
    			
                image.setImageBitmap(getBitmapFromAsset(place.getPictures().get(i), this, metrics));
                image.setPadding(10,  10,  10, 10);
                layout.addView(image);
            } catch (Exception e) {
                e.printStackTrace();
            }
		}
	}

	private Bitmap getBitmapFromAsset(String file_name, Context context, DisplayMetrics metrics){
    	AssetManager assetManager = context.getAssets();
		InputStream is = null;
		int scaled_size = getScaledSize(metrics);
		try{
			is = assetManager.open(file_name);
		}catch(IOException e){
			Log.e("RoomEditorActivity", "Exception caught: " + e.getMessage());
		}
		Bitmap bitmap = BitmapFactory.decodeStream(is);
		bitmap = Bitmap.createScaledBitmap(bitmap,  (int)(scaled_size), (int)(scaled_size), false);
		
		return bitmap;
    }
	
	private int getScaledSize(DisplayMetrics metrics){
		if(metrics.densityDpi == DisplayMetrics.DENSITY_LOW)
			return (int)96;
		else if(metrics.densityDpi == DisplayMetrics.DENSITY_MEDIUM)
			return (int)192;
		else if(metrics.densityDpi == DisplayMetrics.DENSITY_HIGH)
			return (int)288;
		else if(metrics.densityDpi == DisplayMetrics.DENSITY_XHIGH)
			return (int)384;
		else if(metrics.densityDpi == DisplayMetrics.DENSITY_XXHIGH)
			return (int)576;
		return 0;
	}
	
}
