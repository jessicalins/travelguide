package it.polito.travelguide.app.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

import it.polito.travelguide.app.R;
import it.polito.travelguide.app.adapter.CategoryAdapter;
import it.polito.travelguide.app.model.Place;
import it.polito.travelguide.app.model.PlacesDataContainer;
import it.polito.travelguide.app.utils.JsonUtils;


public class MainActivity extends Activity {
    private  HashMap<String, ArrayList<Place>> map;
    private JsonUtils utils;
    private ListView list;
    public static final String PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        utils = new JsonUtils(getApplicationContext());
        
        PlacesDataContainer dataContainer = PlacesDataContainer.newInstance(this);
        map = dataContainer.getMap();
        
        list = (ListView) findViewById(R.id.listView);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        list.setAdapter(new CategoryAdapter(getApplicationContext(), map, metrics));
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String categorySelected = utils.getCategories(map).get(i);
                SharedPreferences settings = getApplicationContext().getSharedPreferences(PREFS_NAME, 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("category", categorySelected);
                editor.commit();
                Intent intent = new Intent(MainActivity.this, PlacesActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
