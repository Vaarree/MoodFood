package com.example.moodfood.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.Vector;

public class Data {
    public static Vector<Restaurant> DATA_RESTAURANT = new Vector<>();
    public static boolean sync;

    public static void AddDataRestaurants(Context ctx, String name, String loc, String address, String vibe, String lat, String longitude){
        DBHelper helper = new DBHelper(ctx);
        SQLiteDatabase db = helper.getWritableDatabase();
        String query = String.format("INSERT INTO Restaurant(restaurantName, restaurantLoc, restaurantAddress, restaurantVibe," +
                "restaurantLat, restaurantLong) VALUES ('%s', '%s', '%s', '%s','%s', '%s')", name, loc, address, vibe, lat, longitude);
        db.execSQL(query);

    }
    public static void setDataRestaurants(Context ctx){
        DBHelper helper = new DBHelper(ctx);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor c1 = db.rawQuery("SELECT COUNT (restaurantID) FROM Restaurant", null);
        int count = 0;
        while (c1.moveToNext()) {
            count = c1.getInt(0);
        }c1.close();
        if (count == 0){
            AddDataRestaurants(ctx,"McDonalds", "Bekasi", "Summarecon Bekasi", "Quiet and Peaceful","-6.227725", "106.997582");
            AddDataRestaurants(ctx,"KFC", "Jakarta", "Sarinah", "Fancy and Modern","-6.188102" , "106.823567");
            AddDataRestaurants(ctx,"Ta Wan", "Bekasi", "Summarecon mall bekasi","Fancy and Modern", "-6.225923", "107.001050");
            AddDataRestaurants(ctx, "Ruth Chris Steak House", "Jakarta Selatan", "Sommerset Grand Citra", "Fancy and Modern","-6.224133", "106.824705");
            AddDataRestaurants(ctx, "Hakkasan Jakarta", "Jakarta", "Alila SCBD", "Fancy and Modern","-6.225247", "106.808741");
            AddDataRestaurants(ctx, "Union Jakarta", "Jakarta", "Senayan City", "Quiet and Peaceful","-6.224395", "106.799043");
            AddDataRestaurants(ctx, "Pizza Hut", "Bekasi","Summarecon Mal Bekasi", "Fancy and modern", "-6.225606", "107.000627");
            AddDataRestaurants(ctx, "Fedwell","Jakarta","Senopati","Queit and Peaceful" ,"-6.232610", "106.811010");
            AddDataRestaurants(ctx, "Shitako Takoyaki","Bekasi", "Summarecon mall bekasi","Hot and Smokey", "-6.225923", "107.001050");
            AddDataRestaurants(ctx, "Abuba Steak", "Bekasi", "Summarecon Bekasi", "Queit and Peaceful","-6.228378", "107.001442");
        }

    }public static void AddDataTags(Context ctx, String tagName){
        DBHelper helper = new DBHelper(ctx);
        SQLiteDatabase db = helper.getWritableDatabase();
        String query = String.format("INSERT INTO Tags(tagName" +
                ") VALUES ('%s')", tagName);
        db.execSQL(query);

    }
    public static void setDataTags(Context ctx){
        DBHelper helper = new DBHelper(ctx);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor c1 = db.rawQuery("SELECT COUNT (tagID) FROM Tags", null);
        int count = 0;
        while (c1.moveToNext()) {
            count = c1.getInt(0);
        }c1.close();
        if (count == 0){
            AddDataTags(ctx,"Steaks");
            AddDataTags(ctx,"Salads");
            AddDataTags(ctx,"Fast Foods");
            AddDataTags(ctx,"Pizza and Pasta");
            AddDataTags(ctx,"Healthy Dining");


            }
     }
    public static void AddDataTagDetails(Context ctx, int id, int tagID){
        DBHelper helper = new DBHelper(ctx);
        SQLiteDatabase db = helper.getWritableDatabase();
        String query = String.format("INSERT INTO TagDetails(restaurantID, tagID" +
                ") VALUES ('%s', '%s')", id, tagID);
        db.execSQL(query);

    }
    public static void setDataTagDetails(Context ctx){
        DBHelper helper = new DBHelper(ctx);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor c1 = db.rawQuery("SELECT COUNT (restaurantID) FROM TagDetails", null);
        int count = 0;
        while (c1.moveToNext()) {
            count = c1.getInt(0);
        }c1.close();
        if (count == 0){
            AddDataTagDetails(ctx, 1,3);
            AddDataTagDetails(ctx, 1,2);
            AddDataTagDetails(ctx, 2,3);
            AddDataTagDetails(ctx, 3,5);
            AddDataTagDetails(ctx, 3,2);
            AddDataTagDetails(ctx ,4,1);
            AddDataTagDetails(ctx ,4,2);
            AddDataTagDetails(ctx ,4,5);
            AddDataTagDetails(ctx ,5,3);
            AddDataTagDetails(ctx ,5,5);
            AddDataTagDetails(ctx ,6,4);
            AddDataTagDetails(ctx ,7,3);
            AddDataTagDetails(ctx ,7,4);
            AddDataTagDetails(ctx ,8,2);
            AddDataTagDetails(ctx ,8,3);
            AddDataTagDetails(ctx ,8,5);
            AddDataTagDetails(ctx ,9,3);
            AddDataTagDetails(ctx ,10,1);


        }
    }
    public static void retrieveDatabase(Context ctx, String restaurantLoc, String restaurantVibe, String foodType){
        String SQL_SEARCH_RESTAURANTS;
        //query untuk ngambil data restaurant dari database SQLite, berdasarkan vibe tertentu.
        if (restaurantVibe.equals("I don't care")){
           SQL_SEARCH_RESTAURANTS = "SELECT r.restaurantID, r.restaurantName, r.restaurantLoc, r.restaurantAddress,r.restaurantVibe, r.restaurantLat, r.restaurantLong" +
                    " FROM Restaurant r, TagDetails td, Tags t" +
                    " WHERE r.restaurantLoc LIKE '%"+restaurantLoc+
                    "%' AND r.restaurantID = td.restaurantID AND td.tagID = t.tagID AND t.tagName LIKE '"+foodType+"'";
        }else {
            SQL_SEARCH_RESTAURANTS = "SELECT r.restaurantID, r.restaurantName, r.restaurantLoc, r.restaurantAddress,r.restaurantVibe, r.restaurantLat, r.restaurantLong" +
                    " FROM Restaurant r, TagDetails td, Tags t" +
                    " WHERE r.restaurantLoc LIKE '%" + restaurantLoc + "%' AND r.restaurantVibe LIKE '" + restaurantVibe + "'" +
                    " AND r.restaurantID = td.restaurantID AND td.tagID = t.tagID AND t.tagName LIKE '" + foodType + "'";
        }

        DBHelper helper = new DBHelper(ctx);
        SQLiteDatabase db = helper.getReadableDatabase();
        //cursor c1 = cursor yang diambil dari search restaurant yang match di database
        //cursor c2 = cursor yang diambil dari search tags berdasarkan restaurantID dari cursor c1
        Cursor c1 =db.rawQuery(SQL_SEARCH_RESTAURANTS, null);
        String test = "";
        while(c1.moveToNext()){
            String SQL_RETRIEVE_TAGS = "SELECT group_concat (t.tagName, ', ') FROM Tags t, TagDetails td " +
                    "WHERE t.tagID = td.tagID AND td.restaurantID = '"+c1.getInt(0)+"'" +
                    "GROUP BY td.restaurantID";
            Cursor c2 = db.rawQuery (SQL_RETRIEVE_TAGS, null);
            //add ke vector object bernama 'DATA_RESTAURANT' yang berfungsi sebagai vector utk listview hasil search
            while(c2.moveToNext()){
                DATA_RESTAURANT.add(new Restaurant(c1.getInt(0),c1.getString(1), c1.getString(2)
                        ,c1.getString(3), c1.getString(4),
                        Double.parseDouble(c1.getString(5)), Double.parseDouble(c1.getString(6)), c2.getString(0)));
            }

            c2.close();
            test = c1.getString(4);

        }
        c1.close();

    }

}
