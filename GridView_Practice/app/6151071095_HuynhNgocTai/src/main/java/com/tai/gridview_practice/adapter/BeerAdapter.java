package com.tai.gridview_practice.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tai.gridview_practice.R;
import com.tai.gridview_practice.model.Beer;

import java.util.ArrayList;

public class BeerAdapter extends BaseAdapter {
    Activity context;
    int item_layout;
    ArrayList<Beer> beers;

    public BeerAdapter(Activity context, int item_layout, ArrayList<Beer> beers) {
        this.context = context;
        this.item_layout = item_layout;
        this.beers = beers;
    }

    @Override
    public int getCount() {
        return beers.size();
    }

    @Override
    public Object getItem(int position) {
        return beers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Link views
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(item_layout, null);

            viewHolder.imvThumb = convertView.findViewById(R.id.imvthump);
            viewHolder.txtName = convertView.findViewById(R.id.txt_beer_name);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //Binding data
        Beer beer = beers.get(position);
        viewHolder.imvThumb.setImageResource(beer.getThumbBeer());
        viewHolder.txtName.setText(beer.getNameBeer());

        return convertView;
    }

    public static class ViewHolder {
        ImageView imvThumb;
        TextView txtName;
    }
}