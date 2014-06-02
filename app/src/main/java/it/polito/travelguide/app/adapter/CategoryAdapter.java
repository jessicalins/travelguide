package it.polito.travelguide.app.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
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
import it.polito.travelguide.app.utils.JsonUtils;


/**
 * Created by jessica on 30/05/14.
 */
public class CategoryAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private HashMap<String, ArrayList<Place>> map;

    public CategoryAdapter(Context context, HashMap<String, ArrayList<Place>> map) {
        this.mInflater = LayoutInflater.from(context);
        this.map = map;
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

        // get input stream
        InputStream inputStream = null;
        try {
            inputStream = convertView.getContext().getAssets().open(map.get(categories.get(position)).get(0).getImagePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // load image as Drawable
        Drawable d = Drawable.createFromStream(inputStream, null);
        // set image to ImageView
        holder.image.setImageDrawable(d);

        return convertView;
    }

    static class ViewHolder {
        TextView imageName;
        ImageView image;
    }
}
