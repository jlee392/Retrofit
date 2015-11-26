package com.example.oose.routemaker.CreateTrip;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by James on 2015-11-24.
 */
public class TimeItem {

    private DateFormat startTime;
    private DateFormat endTime;

    public TimeItem(DateFormat startTime, DateFormat endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public DateFormat getStartTime() {
        return this.startTime;
    }

    public void setStartTime(DateFormat startTime) {
        this.startTime = startTime;
    }

    public DateFormat getEndTime() {
        return this.endTime;
    }

    public void setEndTime(DateFormat endTime) {
        this.endTime = endTime;
    }

}