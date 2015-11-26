package com.example.oose.routemaker.CreateTrip;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by user01 on 2015-11-24.
 */
public class SelectSitesSitesActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState == null) {
            SelectSitesSitesFragment sites = new SelectSitesSitesFragment();

            sites.setArguments(getIntent().getExtras());
            getFragmentManager().beginTransaction().add(android.R.id.content, sites).commit();
        }
    }
}
