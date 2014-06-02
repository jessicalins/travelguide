package it.polito.travelguide.app.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.util.Log;

/**
 * Created by jessica on 30/05/14.
 */
public class Place {
    private String name;
    private String description;
    private String imagePath;
    private String category;
    private String lat;
    private String lg;
    
    public Place() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public void setLg(String lg) {
        this.lg = lg;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public String getCategory() {
        return this.category;
    }

    public String getLat() {
        return this.lat;
    }

    public String getLg() {
        return this.lg;
    }
    
    public Bitmap getMainIcon(Context context, DisplayMetrics metrics){
    	return getBitmapFromAsset(imagePath, context, metrics);
    }
    
    public ArrayList<Bitmap> getImages(){
    	//TODO need to implement this method
    	return null;
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
			return (int)48;
		else if(metrics.densityDpi == DisplayMetrics.DENSITY_MEDIUM)
			return (int)96;
		else if(metrics.densityDpi == DisplayMetrics.DENSITY_HIGH)
			return (int)144;
		else if(metrics.densityDpi == DisplayMetrics.DENSITY_XHIGH)
			return (int)192;
		else if(metrics.densityDpi == DisplayMetrics.DENSITY_XXHIGH)
			return (int)288;
		return 0;
	}
    
}
