package com.example.oose.routemaker.CreateTrip;

import android.app.Activity;
import android.os.Bundle;

public class SelectTimeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState == null) {
            SelectTimeFragment times = new SelectTimeFragment();

            times.setArguments(getIntent().getExtras());
            getFragmentManager().beginTransaction().add(android.R.id.content, times).commit();
        }
    }
}
