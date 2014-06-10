package it.polito.travelguide.app.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import it.polito.travelguide.app.R;
import it.polito.travelguide.app.model.Place;
import it.polito.travelguide.app.utils.Utils;

/**
 * Created by jessica on 30/05/14.
 */
public class PlacesAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private ArrayList<Place> places;
    private DisplayMetrics metrics;
    private Context context;

    public PlacesAdapter(Context context, ArrayList<Place> places, DisplayMetrics metrics) {
        this.mInflater = LayoutInflater.from(context);
        this.places = places;
        this.metrics = metrics;
        this.context = context;
    }

    @Override
    public int getCount() {
        return places.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.row, null);
            holder = new ViewHolder();
            holder.imageName = (TextView) convertView.findViewById(R.id.title);
            holder.image = (ImageView) convertView.findViewById(R.id.list_image);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (position < places.size()) {
            holder.imageName.setText(places.get(position).getName());
            Bitmap img = Utils.getBitmapFromAsset(places.get(position).getImagePath(), context, metrics);
            // set image to ImageView
            holder.image.setImageBitmap(img);
        }
        return convertView;
    }

    static class ViewHolder {
        TextView imageName;
        ImageView image;
    }
}
