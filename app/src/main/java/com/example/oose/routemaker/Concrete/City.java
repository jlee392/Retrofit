package com.example.oose.routemaker.Concrete;

public class City {

    public String cityCode;
    public String cityName;
    public String state;
    public double latitude;
    public double longitude;

    public City() {

    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

}
