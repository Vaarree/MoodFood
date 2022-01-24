package com.example.moodfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;

import com.example.moodfood.data.DBHelper;
import com.example.moodfood.data.Data;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        DBHelper.createFirstTables(db);
        Data.setDataRestaurants(this);
        Data.setDataTags(this);
        Data.setDataTagDetails(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent splash = new Intent(MainActivity.this, activity_moodForm.class);

                startActivity(splash);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}
