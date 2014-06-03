package it.polito.travelguide.app.activities;

import java.io.IOException;
import java.io.InputStream;

import it.polito.travelguide.app.R;
import it.polito.travelguide.app.model.Place;
import it.polito.travelguide.app.model.PlacesDataContainer;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
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
			InputStream inputStream = null;
            try {
                inputStream = getAssets().open(place.getPictures().get(i));
                Drawable d = Drawable.createFromStream(inputStream, null);
                ImageView image = new ImageView(this);
    			image.setImageResource(R.layout.picture);
                image.setImageDrawable(d);
                image.setPadding(10,  10,  10, 10);
                layout.addView(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
		}
	}
	
}
