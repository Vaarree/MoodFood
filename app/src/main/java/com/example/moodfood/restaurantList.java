package com.example.moodfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.moodfood.data.Data;

public class restaurantList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);
        ListView restaurants =findViewById(R.id.list_restaurantList_restaurants);
        RestaurantListAdapter adapter = new RestaurantListAdapter(this);
        restaurants.setAdapter(adapter);
        Button findnew = findViewById(R.id.list_restaurantList_findnew);
        findnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (restaurantList.this,activity_moodForm.class);
                startActivity(intent);
                Data.DATA_RESTAURANT.clear();
                finish();
            }
        });

        restaurants.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent toDetail = new Intent(restaurantList.this,RestaurantDetail.class);
                toDetail.putExtra("ID",position);
                startActivity(toDetail);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Data.DATA_RESTAURANT.clear();
        finish();
        super.onBackPressed();
    }
}
