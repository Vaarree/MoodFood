package com.example.moodfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.moodfood.data.Data;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class RestaurantDetail extends AppCompatActivity implements OnMapReadyCallback  {

    GoogleMap mapAPI;
    SupportMapFragment mapFragment;
    TextView rName,rVibe,rAddress,rLoc;

    int rId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);

        Intent getID = getIntent();
        rId = getID.getIntExtra("ID",0);

        rName = findViewById(R.id.detail_txt_restaurantName);
        rVibe = findViewById(R.id.detail_txt_vibedt);
        rAddress = findViewById(R.id.detail_txt_addressdt);
        rLoc = findViewById(R.id.detail_txt_locationdt);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.detail_fragment_mapAPI);
        mapFragment.getMapAsync(this);

        rName.setText(Data.DATA_RESTAURANT.get(rId).restaurantName);
        rVibe.setText(Data.DATA_RESTAURANT.get(rId).restaurantVibe);
        rAddress.setText(Data.DATA_RESTAURANT.get(rId).restaurantAddress);
        rLoc.setText(Data.DATA_RESTAURANT.get(rId).restaurantLoc);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapAPI = googleMap;
        LatLng relatlong = new LatLng(Data.DATA_RESTAURANT.get(rId).restaurantLat, Data.DATA_RESTAURANT.get(rId).restaurantLong);
        mapAPI.addMarker(new MarkerOptions().position(relatlong).title(Data.DATA_RESTAURANT.get(rId).restaurantName));
        mapAPI.moveCamera(CameraUpdateFactory.newLatLng(relatlong));
        mapAPI.setMinZoomPreference(14.0f);
        mapAPI.setMaxZoomPreference(28.0f);
    }
}
