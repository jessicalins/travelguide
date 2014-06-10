package it.polito.travelguide.app.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

import it.polito.travelguide.app.R;
import it.polito.travelguide.app.adapter.PlacesAdapter;
import it.polito.travelguide.app.model.Place;
import it.polito.travelguide.app.utils.JsonUtils;

/**
 * Created by jessica on 30/05/14.
 */
public class PlacesActivity extends Activity {
    public static final String PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);
        final SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        JsonUtils utils = new JsonUtils(getApplicationContext());
        HashMap<String, ArrayList<Place>> map = utils.getMap();
        ArrayList<Place> places = map.get(settings.getString("category", null));
        ListView listView = (ListView) findViewById(R.id.listView);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        listView.setAdapter(new PlacesAdapter(getApplicationContext(), places, metrics));
        listView.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				Intent intent = new Intent();
				intent.setClassName("it.polito.travelguide.app", "it.polito.travelguide.app.activities.PlaceDetailsActivity");
				intent.putExtra("it.polito.travelguide.app.placeCategory", settings.getString("category",  null));
				intent.putExtra("it.polito.travelguide.app.placeId", position);
				startActivity(intent);
			}
        	
        });
    }

}
