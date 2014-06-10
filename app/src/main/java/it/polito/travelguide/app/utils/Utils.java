package it.polito.travelguide.app.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by jessica on 10/06/14.
 */
public class Utils {
    public static Bitmap getBitmapFromAsset(String file_name, Context context, DisplayMetrics metrics){
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

    public static int getScaledSize(DisplayMetrics metrics){
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
