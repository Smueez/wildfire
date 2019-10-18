package com.example.wildfire;

public class Users {
    String name,email,pass;
    float lat,lng;
    Users(){}
    Users(String name, String email, String pass, float lat, float lng){
        this.email = email;
        this.name = name;
        this.pass = pass;
        this.lat = lat;
        this.lng = lng;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public String getPass() {
        return pass;
    }
}
