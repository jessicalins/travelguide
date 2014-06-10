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
import java.util.HashMap;

import it.polito.travelguide.app.R;
import it.polito.travelguide.app.model.Place;
import it.polito.travelguide.app.model.PlacesDataContainer;
import it.polito.travelguide.app.utils.JsonUtils;
import it.polito.travelguide.app.utils.Utils;


/**
 * Created by jessica on 30/05/14.
 */
public class CategoryAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private HashMap<String, ArrayList<Place>> map;
    private DisplayMetrics metrics;
    private Context context;

    public CategoryAdapter(Context context, HashMap<String, ArrayList<Place>> map, DisplayMetrics metrics) {
        this.mInflater = LayoutInflater.from(context);
        this.map = map;
        this.metrics = metrics;
        this.context = context;
    }

    @Override
    public int getCount() {
        return map.size();
    }

    @Override
    public Object getItem(int position) {
        return map.get(position);
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
        JsonUtils utils = new JsonUtils(viewGroup.getContext());
        ArrayList<String> categories = utils.getCategories(map);
        holder.imageName.setText(categories.get(position));
        Bitmap img = Utils.getBitmapFromAsset(map.get(categories.get(position)).get(0).getImagePath(), context, metrics);
        // set image to ImageView
        holder.image.setImageBitmap(img);

        return convertView;
    }

    static class ViewHolder {
        TextView imageName;
        ImageView image;
    }
}
