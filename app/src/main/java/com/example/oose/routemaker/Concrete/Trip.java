package com.example.oose.routemaker.Concrete;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user01 on 2015-11-23.
 */
public class Trip {

    public String tripId;
    public String cityCode;
    public DateFormat startDate;
    public DateFormat endDate;
    public List<String> dayIds;
    public double totalCost;

    public Trip(int tripCount) {
        this.tripId = "t" + (tripCount+1);
        this.dayIds = new ArrayList<String>();
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }
}
