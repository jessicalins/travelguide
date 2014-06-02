package it.polito.travelguide.app.model;

import it.polito.travelguide.app.utils.JsonUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;

/*
 *  This class implements the Singleton design pattern, and is used as a
 *  data container inside the application.
 *  Using a singleton all the application's data is read only once from persistent
 *  storage, improving the application's speed.
 */

public class PlacesDataContainer {
	private static PlacesDataContainer dataContainer;
	private HashMap<String, ArrayList<Place>> data;
	private JsonUtils jutils;
	
	protected PlacesDataContainer(){
	}
	
	protected PlacesDataContainer(Context context){
		this.jutils = new JsonUtils(context);
		data = jutils.getMap();
	}
	
	public static PlacesDataContainer newInstance(Context context){
		if(dataContainer == null){
			dataContainer = new PlacesDataContainer(context);
		}
		return dataContainer;
	}
	
	public HashMap<String, ArrayList<Place>> getMap(){
		return this.data;
	}
	
	public ArrayList<String> getCategories() {
        ArrayList<String> categories = new ArrayList<String>();
        for(Map.Entry<String, ArrayList<Place>> entry : this.data.entrySet()) {
            categories.add(entry.getKey().toString());
        }
        return categories;
    }
	
}
