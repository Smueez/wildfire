package com.example.wildfire.ui.forum;

public class forum {
    public String location;
    public String area;
    public String user;
    public String lat;
    public String lon;
    public Integer time;

    forum(){

    }
    forum(String location, String area, String user, String lat, String lon,Integer time ){
        this.location = location;
        this.area = area;
        this.user = user;
        this.lat = lat;
        this.lon = lon;
        this.time = time;
    }


    public String getLocation() {
        return location;
    }

    public String getArea() {
        return area;
    }

    public Integer gettime() {
        return time;
    }
    public String getUser() {
        return user;
    }

}