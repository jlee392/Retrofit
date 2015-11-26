package com.example.oose.routemaker.CreateTrip;

import android.app.DatePickerDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;

import com.example.oose.routemaker.Logistics.SettingActivity;
import com.example.oose.routemaker.NewsFeedActivity;
import com.example.oose.routemaker.R;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;


public class SelectDateTimeActivity extends AppCompatActivity {

    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;

    DateFormat fmDateAndTime_start = DateFormat.getDateTimeInstance();
    DateFormat fmDateAndTime_end = DateFormat.getDateTimeInstance();
    TextView dateAndtimeLabel_start;
    TextView dateAndtimeLabel_end;
    Calendar dateAndtime_start = Calendar.getInstance();
    Calendar dateAndtime_end = Calendar.getInstance();
    long diff;

    DatePickerDialog.OnDateSetListener d1 = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet (DatePicker view, int year, int month, int day) {
            dateAndtime_start.set(Calendar.YEAR, year);
            dateAndtime_start.set(Calendar.MONTH, month);
            dateAndtime_start.set(Calendar.DAY_OF_MONTH, day);
            updateLabel_start();
        }
    };
    DatePickerDialog.OnDateSetListener d2 = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet (DatePicker view, int year, int month, int day) {
            dateAndtime_end.set(Calendar.YEAR, year);
            dateAndtime_end.set(Calendar.MONTH, month);
            dateAndtime_end.set(Calendar.DAY_OF_MONTH, day);
            updateLabel_end();

            diff = ((dateAndtime_end.getTimeInMillis() - dateAndtime_start.getTimeInMillis()) / (1000 * 60 * 60 * 24)) + 1;
            dynamicallyAdd(diff);
        }
    };

    private void updateLabel_start() {
        dateAndtimeLabel_start.setText(fmDateAndTime_start.format(dateAndtime_start.getTime()));
    }

    private void updateLabel_end() {
        dateAndtimeLabel_end.setText(fmDateAndTime_end.format(dateAndtime_end.getTime()));
    }

    private void dynamicallyAdd(long diff) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            SelectTimeFragment st = new SelectTimeFragment();
            fragmentTransaction.replace(R.id.fragment_container_time, st, null);
            fragmentTransaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_date_time);

        mDrawerList = (ListView)findViewById(R.id.left_drawer_select_date_time);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout_select_date_time);
        mActivityTitle = getTitle().toString();
        addDrawerItems();
        setupDrawer();
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar_default);
        setSupportActionBar(mToolbar);
        if (mToolbar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        Button startBtn = (Button) findViewById(R.id.startDate_button);
        startBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new DatePickerDialog(SelectDateTimeActivity.this, d1, dateAndtime_start.get(Calendar.YEAR),
                        dateAndtime_start.get(Calendar.MONTH), dateAndtime_start.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        Button endBtn = (Button) findViewById(R.id.endDate_button);
        endBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new DatePickerDialog(SelectDateTimeActivity.this, d2, dateAndtime_end.get(Calendar.YEAR),
                        dateAndtime_end.get(Calendar.MONTH), dateAndtime_end.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        dateAndtimeLabel_start = (TextView) findViewById(R.id.startDate_text);
        dateAndtimeLabel_end = (TextView) findViewById(R.id.endDate_text);

        final Button continueButton = (Button) findViewById(R.id.button_continue_date_time);
        continueButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(SelectDateTimeActivity.this, SelectSitesActivity.class);
                //Some code here to pass extra information with intent
                startActivity(intent);
            }
        });
    }

    private void addDrawerItems() {
        String[] osArray = getResources().getStringArray(R.array.drawer_array);
        ArrayAdapter<String> mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                selectItem(position);
                mDrawerLayout.closeDrawer(mDrawerList);
            }
/*
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Time for an upgrade!", Toast.LENGTH_SHORT).show();
            }
*/
        });
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
//                getSupportActionBar().setTitle("Navigation!");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
//                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
/*        if (id == R.id.action_settings) {
            return true;
        }*/

        // Activate the navigation drawer toggle
        if (!mDrawerToggle.onOptionsItemSelected(item)) {
            return super.onOptionsItemSelected(item);
        }
        return true;

    }

    public void selectItem(int position) {
        Intent intent;
        switch(position) {
            case 0:
                intent = new Intent(this, NewsFeedActivity.class);
                break;
            case 1:
                intent = new Intent(this, SelectCityActivity.class);
                break;
            case 4:
                intent = new Intent(this, SettingActivity.class);
                break;
            default :
                intent = new Intent(this, NewsFeedActivity.class); // Activity_0 as default
                break;
        }
        startActivity(intent);
    }
}
