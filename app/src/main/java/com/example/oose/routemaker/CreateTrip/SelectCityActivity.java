package com.example.oose.routemaker.CreateTrip;

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
import android.widget.ListView;
import com.example.oose.routemaker.Concrete.City;
import com.example.oose.routemaker.Logistics.MainActivity;
import com.example.oose.routemaker.Logistics.SettingActivity;
import com.example.oose.routemaker.NewsFeedActivity;
import com.example.oose.routemaker.R;

import java.util.ArrayList;
import java.util.List;

public class SelectCityActivity extends AppCompatActivity {

    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;
    private static String cityName;
    List<Button> buttons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_city);
        mDrawerList = (ListView)findViewById(R.id.left_drawer_selectCity);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout_selectCity);
        mActivityTitle = getTitle().toString();

        addDrawerItems();
        setupDrawer();

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar_default);
        setSupportActionBar(mToolbar);
        if (mToolbar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        //City buttons
        final Button newyork = (Button) findViewById(R.id.button_city_newyork);
        newyork.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (v.isSelected()) {
                    v.setSelected(false);
                } else {
                    setAllOthersFalse();
                    v.setSelected(true);
                    cityName = "New York";
                }
            }
        });

        final Button dc = (Button) findViewById(R.id.button_city_dc);
        dc.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (v.isSelected()) {
                    v.setSelected(false);
                } else {
                    setAllOthersFalse();
                    v.setSelected(true);
                    cityName = "Washington DC";
                }
            }
        });

        final Button boston = (Button) findViewById(R.id.button_city_boston);
        boston.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (v.isSelected()) {
                    v.setSelected(false);
                } else {
                    setAllOthersFalse();
                    v.setSelected(true);
                    cityName = "Boston";
                }
            }
        });

        final Button sanfrancisco = (Button) findViewById(R.id.button_city_sanfrancisco);
        sanfrancisco.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (v.isSelected()) {
                    v.setSelected(false);
                } else {
                    setAllOthersFalse();
                    v.setSelected(true);
                    cityName = "San Francisco";
                }
            }
        });

        final Button losangeles = (Button) findViewById(R.id.button_city_losangeles);
        losangeles.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (v.isSelected()) {
                    v.setSelected(false);
                } else {
                    setAllOthersFalse();
                    v.setSelected(true);
                    cityName = "Los Angeles";
                }
            }
        });

        final Button chicago = (Button) findViewById(R.id.button_city_chicago);
        chicago.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (v.isSelected()) {
                    v.setSelected(false);
                } else {
                    setAllOthersFalse();
                    v.setSelected(true);
                    cityName = "Chicago";
                }
            }
        });

        final Button seattle = (Button) findViewById(R.id.button_city_seattle);
        seattle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (v.isSelected()) {
                    v.setSelected(false);
                } else {
                    setAllOthersFalse();
                    v.setSelected(true);
                    cityName = "Seattle";
                }
            }
        });

        final Button baltimore = (Button) findViewById(R.id.button_city_baltimore);
        baltimore.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (v.isSelected()) {
                    v.setSelected(false);
                } else {
                    setAllOthersFalse();
                    v.setSelected(true);
                    cityName = "Baltimore";
                }
            }
        });

        final Button miami = (Button) findViewById(R.id.button_city_miami);
        miami.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (v.isSelected()) {
                    v.setSelected(false);
                } else {
                    setAllOthersFalse();
                    v.setSelected(true);
                    cityName = "Miami";
                }
            }
        });

        final Button continueButton = (Button) findViewById(R.id.button_continue);
        continueButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(SelectCityActivity.this, SelectDateTimeActivity.class);
                //Some code here to pass extra information with intent
                startActivity(intent);
            }
        });

        buttons.add(newyork);
        buttons.add(boston);
        buttons.add(losangeles);
        buttons.add(miami);
        buttons.add(dc);
        buttons.add(chicago);
        buttons.add(seattle);
        buttons.add(baltimore);
        buttons.add(sanfrancisco);

    }

    private void setAllOthersFalse() {
        for (Button b : buttons) {
            b.setSelected(false);
        }
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

//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this, "Time for an upgrade!", Toast.LENGTH_SHORT).show();
//            }

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
//        if (id == R.id.action_settings) {
//            return true;
//        }

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