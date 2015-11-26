package com.example.oose.routemaker.Concrete;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public class User implements Serializable {

    public String email;
    public String firstName;
    public String lastName;
    public String password;
    public String ageGroup;
    public String currentlyEditingTripId;
    public List<String> preferenceList;
    public List<String> currentTripIds;
    public List<String> pastTripIds;

    public User() {
        initialize();
    }

    public User(String email, String firstName, String lastName, String password, String ageGroup, List<String> preferenceList) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.ageGroup = ageGroup;
        this.preferenceList = preferenceList;
    }

    public void initialize() {
        this.currentTripIds = new ArrayList<>();
        this.pastTripIds = new ArrayList<>();
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }
    public void setpreferenceList(List<String> list) {
        this.preferenceList = list;
    }

    public void addTrip(String tripId) {
        this.currentTripIds.add(tripId);
    }

    public void setCurrentlyEditingTripId(String currentlyEditingTripId) {
        this.currentlyEditingTripId = currentlyEditingTripId;
    }

    public void resetCurrentlyEditingTripId(){
        this.currentlyEditingTripId = "";
    }

    public void moveCurrTrip() {
        //Move all outdated trips to past trip list
    }


}
