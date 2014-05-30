package it.polito.travelguide.app.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

import it.polito.inginformatica.travelguide.app.R;
import it.polito.travelguide.app.adapter.PlacesAdapter;
import it.polito.travelguide.app.model.Place;
import it.polito.travelguide.app.utils.JsonUtils;

/**
 * Created by jessica on 30/05/14.
 */
public class Places extends ActionBarActivity {
    public static final String PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        JsonUtils utils = new JsonUtils(getApplicationContext());
        HashMap<String, ArrayList<Place>> map = utils.getMap();
        ArrayList<Place> places = map.get(settings.getString("category", null));
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new PlacesAdapter(getApplicationContext(), places));
    }

}
