package com.example.moodfood.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static String DB_NAME = "MoodFoodDB";
    //add value DB_VERSION +1 kalo mau ada perubahan di database
    public static int DB_VERSION = 8;

    public static String SQL_CREATE_RESTAURANT_TABLE =
            "CREATE TABLE IF NOT EXISTS Restaurant(" +
                    "restaurantID integer primary key autoincrement," +
                    "restaurantName text NOT NULL," +
                    "restaurantLoc text NOT NULL, " +
                    "restaurantAddress text NOT NULL, " +
                    "restaurantVibe text NOT NULL, " +
                    "restaurantLat text NOT NULL, " +
                    "restaurantLong text NOT NULL " +
                    ")";
    public static String SQL_CREATE_TAGS_TABLE =
            "CREATE TABLE IF NOT EXISTS Tags(" +
                    "tagID integer primary key autoincrement," +
                    "tagName text NOT NULL" +
                    ")";
    public static String SQL_CREATE_TAGDETAILS_TABLE =
            "CREATE TABLE IF NOT EXISTS TagDetails(" +
                    "restaurantID NOT NULL REFERENCES Restaurant(restaurantID)," +
                    "tagID NOT NULL REFERENCES Tags(tagID)" +
                    ")";
    public static String SQL_DROP_RESTAURANT_TABLE = "DROP TABLE IF EXISTS Restaurant";
    public static String SQL_DROP_TAGS_TABLE = "DROP TABLE IF EXISTS Tags";
    public static String SQL_DROP_TAGDETAILS_TABLE = "DROP TABLE IF EXISTS TagDetails";


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        refreshTables(db);
    }

    public DBHelper(Context ctx){
        super(ctx, DB_NAME, null, DB_VERSION);
    }
    public static void createFirstTables(SQLiteDatabase db){
        db.execSQL(SQL_CREATE_RESTAURANT_TABLE);
        db.execSQL(SQL_CREATE_TAGS_TABLE);
        db.execSQL(SQL_CREATE_TAGDETAILS_TABLE);
    }
    public static void refreshTables(SQLiteDatabase db){
        db.execSQL(SQL_DROP_RESTAURANT_TABLE);
        db.execSQL(SQL_DROP_TAGS_TABLE);
        db.execSQL(SQL_DROP_TAGDETAILS_TABLE);

        db.execSQL(SQL_CREATE_RESTAURANT_TABLE);
        db.execSQL(SQL_CREATE_TAGS_TABLE);
        db.execSQL(SQL_CREATE_TAGDETAILS_TABLE);


    }

}
