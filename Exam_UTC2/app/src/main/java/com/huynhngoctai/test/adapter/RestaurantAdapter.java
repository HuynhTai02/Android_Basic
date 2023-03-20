package com.huynhngoctai.test.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.huynhngoctai.test.R;
import com.huynhngoctai.test.model.Restaurant;

import java.util.List;

public class RestaurantAdapter extends BaseAdapter {
    Activity context;
    int item_layout;
    List<Restaurant> restaurantList;

    public RestaurantAdapter(Activity context, int item_layout, List<Restaurant> lunches) {
        this.context = context;
        this.item_layout = item_layout;
        this.restaurantList = lunches;
    }

    @Override
    public int getCount() {
        return restaurantList.size();
    }

    @Override
    public Object getItem(int i) {
        return restaurantList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(item_layout, null);
            holder.imgPhoto = view.findViewById(R.id.img_food);
            holder.txtAddress = view.findViewById(R.id.tv_address);
            holder.txtPlaceName = view.findViewById(R.id.tx_namefood);
            holder.txtRatingValue = view.findViewById(R.id.tv_ratingvalue);
            holder.txtRatingCount = view.findViewById(R.id.tv_ratingcount);
            holder.txtDishName = view.findViewById(R.id.tv_dishname);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        Restaurant restaurant = restaurantList.get(i);
        holder.imgPhoto.setImageResource(restaurant.getIdPhoto());
        holder.txtPlaceName.setText(restaurant.getPlaceName());
        holder.txtAddress.setText(restaurant.getAddress());
        holder.txtRatingValue.setText(String.valueOf(restaurant.getRatingValue()));
        holder.txtRatingCount.setText(restaurant.getRatingCount());
        holder.txtDishName.setText(restaurant.getDishName());


        return view;
    }

    public static class ViewHolder {
        ImageView imgPhoto;
        TextView txtPlaceName, txtRatingValue, txtRatingCount, txtAddress, txtDishName;
    }

}

