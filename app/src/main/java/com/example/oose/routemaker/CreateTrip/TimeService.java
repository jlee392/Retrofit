package com.example.oose.routemaker.CreateTrip;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;

import com.example.oose.routemaker.Constants;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.sql.Time;
import java.util.Calendar;

public class TimeService extends IntentService {

    public static final String ITEMS = "items";
    public static final String RECEIVER = "receiver";

    public TimeService() {
        super("TimeService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        List<TimeItem> TimeItems = null;
        int index = 5;
        DateFormat start;
        DateFormat end;
        for (int i = 0; i < index; i++) {
            TimeItems.add(new TimeItem(new SimpleDateFormat(), new SimpleDateFormat()));
        }
        Bundle bundle = new Bundle();
        bundle.putSerializable(ITEMS, (Serializable) TimeItems);
        ResultReceiver receiver = intent.getParcelableExtra(RECEIVER);
        receiver.send(0, bundle);
    }

}

