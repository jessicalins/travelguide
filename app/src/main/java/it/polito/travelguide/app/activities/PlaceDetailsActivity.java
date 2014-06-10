package it.polito.travelguide.app.activities;

import it.polito.travelguide.app.R;
import it.polito.travelguide.app.model.Place;
import it.polito.travelguide.app.model.PlacesDataContainer;
import it.polito.travelguide.app.utils.Utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PlaceDetailsActivity extends Activity {
	private Place place;
	private LinearLayout layout;
	private String placeCategory;
	private int placeId;

    @Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_place_details);
	    Bundle extras = getIntent().getExtras();
	    placeCategory = extras.getString("it.polito.travelguide.app.placeCategory");
	    placeId = extras.getInt("it.polito.travelguide.app.placeId");
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
		
		intent.putExtra("it.polito.travelguide.app.placeCategory", placeCategory);
		intent.putExtra("it.polito.travelguide.app.placeId", placeId);
		
        startActivity(intent);
	}
	
	private void setPlacePictures(){
		layout = (LinearLayout) findViewById(R.id.place_details_pictures);
		for(int i=0; i<place.getPictures().size(); i++){
            try {
                ImageView image = new ImageView(this);
                
                DisplayMetrics metrics = new DisplayMetrics();
        		getWindowManager().getDefaultDisplay().getMetrics(metrics);
    			
                image.setImageBitmap(Utils.getBitmapFromAsset(place.getPictures().get(i), this, metrics));
                image.setPadding(10,  10,  10, 10);
                layout.addView(image);
            } catch (Exception e) {
                e.printStackTrace();
            }
		}
	}
}
