package com.example.moodfood.data;

public class Restaurant {
    public int restaurantId;
    public String restaurantName;
    public String restaurantLoc;
    public String restaurantAddress;
    public String restaurantVibe;
    public double restaurantLat;
    public double restaurantLong;
    public String tags;

    public Restaurant(int restaurantId,String restaurantName, String restaurantLoc, String restaurantAddress, String restaurantVibe, double restaurantLat, double restaurantLong, String tags) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.restaurantLoc = restaurantLoc;
        this.restaurantAddress = restaurantAddress;
        this.restaurantVibe = restaurantVibe;
        this.restaurantLat = restaurantLat;
        this.restaurantLong = restaurantLong;
        this.tags = tags;
    }
}

