package it.polito.travelguide.app.activities;

import it.polito.travelguide.app.R;
import it.polito.travelguide.app.model.Place;
import it.polito.travelguide.app.model.PlacesDataContainer;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

public class PlaceDetailsActivity extends Activity {
	private Place place;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_place_details);
	    Bundle extras = getIntent().getExtras();
	    String placeCategory = extras.getString("it.polito.travelguide.app.placeCategory");
	    int placeId = extras.getInt("it.polito.travelguide.app.placeId");
	    PlacesDataContainer dataContainer = PlacesDataContainer.newInstance(this);
	    place = dataContainer.getMap().get(placeCategory).get(placeId);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main, menu);
	    
	    return super.onCreateOptionsMenu(menu);
	}

	public void getRoute(View view){
		//TODO start Google Maps Activity
	}
	
}
