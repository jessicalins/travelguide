package it.polito.travelguide.app.utils;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import it.polito.travelguide.app.model.Place;

/**
 * Created by jessica on 30/05/14.
 */
public class JsonUtils {
    private Context context;

    public JsonUtils(Context context) {
        this.context = context;
    }

    public HashMap<String, ArrayList<Place>> getMap() {
        HashMap<String, ArrayList<Place>> map = new HashMap<String, ArrayList<Place>>();
        String json = getStringJson();
        try {
            JSONObject stringJson = new JSONObject(json); // string to jsonObject
            JSONArray products = stringJson.getJSONArray("places"); // array with query results

            for (int i = 0; i < products.length(); i++) {
                JSONObject result = products.getJSONObject(i);
                String set = (String) result.get("category");
                Place place = createPlace(result);

                if (set.equals("Coffees")) {
                    check("Coffees", place, map);

                } else if (set.equals("Palaces")) {
                    check("Palaces", place, map);

                } else if (set.equals("Squares")) {
                    check("Squares", place, map);

                } else if (set.equals("Churches")) {
                    check("Churches", place, map);

                } else if (set.equals("Parks")) {
                    check("Parks", place, map);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("EXCEPTION", "<json> - " + e.toString());
        }
        return map;
    }

    private void check(String key, Place product, HashMap<String, ArrayList<Place>> map) {
        if(!map.containsKey(key)) {
            ArrayList<Place> array = new ArrayList<Place>();
            array.add(product);
            map.put(key, array);
        } else {
            map.get(key).add(product);
        }
    }

    public ArrayList<Place> getAllPlaces() {
        ArrayList<Place> allPlaces = new ArrayList<Place>();
        String json = getStringJson();
        try {
            JSONObject stringJson = new JSONObject(json); // string to jsonObject
            JSONArray places = stringJson.getJSONArray("places"); // array with query results
            for (int i = 0; i < places.length(); i++) {
                JSONObject result = places.getJSONObject(i);
                Place place = createPlace(result);
                allPlaces.add(place);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("EXCEPTION", "<json> - " + e.toString());
        }
        return allPlaces;
    }

    public ArrayList<String> getCategories(HashMap<String, ArrayList<Place>> map) {
        ArrayList<String> categories = new ArrayList<String>();
        for(Map.Entry entry : map.entrySet()) {
            categories.add(entry.getKey().toString());
        }
        return categories;
    }

    private Place createPlace(JSONObject result) {
        Place place = new Place();
        try {
            place.setName(result.getString("name"));
            place.setDescription(result.getString("description"));
            place.setImagePath(result.getString("imagePath"));
            place.setCategory(result.getString("category"));
            place.setLat(result.getString("lat"));
            place.setLg(result.getString("long"));
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("EXCEPTION", "<json> - " + e.toString());
        }
        return place;
    }

    private String getStringJson() {
        // Reading text file from assets folder
        StringBuffer sb = new StringBuffer();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(context.getAssets().
                    open("places.json")));
            String temp;
            while ((temp = br.readLine()) != null)
                sb.append(temp);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close(); // stop reading
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return sb.toString();
    }
}
