package com.example.moodfood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.moodfood.data.Data;
import com.example.moodfood.data.Restaurant;

import java.util.Vector;

public class RestaurantListAdapter extends BaseAdapter {
    private Context ctx;

    public RestaurantListAdapter(Context ctx) {
        this.ctx = ctx;


    }

    @Override
    public int getCount() {
        return Data.DATA_RESTAURANT.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Restaurant r = Data.DATA_RESTAURANT.get(position);

        LayoutInflater inflater =LayoutInflater.from(ctx);
        View restaurants =inflater.inflate(R.layout.adapter_view_restaurantlist, null,false);

        //initialize components
        TextView restName = restaurants.findViewById(R.id.textView_restaurantList_name);
        TextView restAddress = restaurants.findViewById(R.id.textView_restaurantList_address);
        TextView restLocation = restaurants.findViewById(R.id.textView_restaurantList_location);
        TextView restTags = restaurants.findViewById(R.id.textView_restaurantList_tags);
        TextView restVibe = restaurants.findViewById(R.id.textView_restaurantList_vibe);

        try{
            //idActual -1 karena id yang asli mulai dari 0
            int idActual = position;
            restName.setText(Data.DATA_RESTAURANT.get(idActual).restaurantName);
            restAddress.setText(Data.DATA_RESTAURANT.get(idActual).restaurantAddress);
            restLocation.setText(Data.DATA_RESTAURANT.get(idActual).restaurantLoc);
            restTags.setText(Data.DATA_RESTAURANT.get(idActual).tags);
            restVibe.setText(Data.DATA_RESTAURANT.get(idActual).restaurantVibe);


        }catch (NumberFormatException e){

    }

        return restaurants;
    }
}
