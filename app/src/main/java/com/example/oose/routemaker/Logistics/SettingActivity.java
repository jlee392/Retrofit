package com.example.oose.routemaker.Logistics;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
// import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
// import android.widget.Toast;
import android.content.Intent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.example.oose.routemaker.Concrete.User;
import com.firebase.client.AuthData;
import com.firebase.client.ChildEventListener;
import com.example.oose.routemaker.CreateTrip.SelectCityActivity;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.example.oose.routemaker.NewsFeedActivity;
import com.example.oose.routemaker.R;

public class SettingActivity extends AppCompatActivity {

    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private String mActivityTitle;

    public User usr;
    Firebase ref;
    Firebase userRef;
    String userId;
    RadioGroup radioGroup;
    RadioButton radioAgeButton;
    CheckBox museumBox;
    CheckBox artBox;
    CheckBox nightLifeBox;
    CheckBox entertainmentBox;
    CheckBox foodBox;
    CheckBox landmarkBox;
    CheckBox outdoorBox;
    CheckBox shoppingBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        mDrawerList = (ListView)findViewById(R.id.left_drawer_setting);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout_setting);
        mActivityTitle = getTitle().toString();
        addDrawerItems();
        setupDrawer();

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar_default);
        setSupportActionBar(mToolbar);
        if (mToolbar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        Firebase.setAndroidContext(this);
        ref = new Firebase("https://incandescent-fire-1598.firebaseio.com/");
        AuthData authData = ref.getAuth();
        userId = authData.getUid();
        userRef = ref.child("users").child(userId);

        userRef.addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                usr = dataSnapshot.getValue(User.class);
                restoreInformation(usr);
                changeInfo();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }

    /**
     * On click method to change user information (ex. password, ageGroup, preferenceList)
     */
    public void changeInfo() {
        final EditText passwordField = (EditText) findViewById(R.id.password_setting_field);
        final EditText passwordConfirmField = (EditText) findViewById(R.id.password_settingConfirm_field);
        radioGroup = (RadioGroup) findViewById(R.id.ageGroup_setting_radio);

        museumBox = (CheckBox) findViewById(R.id.setting_museum);
        artBox = (CheckBox) findViewById(R.id.setting_art);
        nightLifeBox = (CheckBox) findViewById(R.id.setting_nightlife);
        entertainmentBox = (CheckBox) findViewById(R.id.setting_entertainment);
        foodBox = (CheckBox) findViewById(R.id.setting_food);
        landmarkBox = (CheckBox) findViewById(R.id.setting_landmark);
        outdoorBox = (CheckBox) findViewById(R.id.setting_outdoor);
        shoppingBox = (CheckBox) findViewById(R.id.setting_shopping);

        final Button button = (Button) findViewById(R.id.setting_confirm_button);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioAgeButton = (RadioButton) findViewById(selectedId);
                final List<String> preferenceList = new ArrayList<>();

                final String password = passwordField.getText().toString();
                final String passwordConfirm = passwordConfirmField.getText().toString();
                final String ageGroup = radioAgeButton.getText().toString();

                boolean isChecked = false;
                if (museumBox.isChecked()) {
                    preferenceList.add(museumBox.getText().toString());
                    isChecked = true;
                }
                if (artBox.isChecked()) {
                    preferenceList.add(artBox.getText().toString());
                    isChecked = true;
                }
                if (nightLifeBox.isChecked()) {
                    preferenceList.add("NightLife");
                    isChecked = true;
                }
                if (entertainmentBox.isChecked()) {
                    preferenceList.add(entertainmentBox.getText().toString());
                    isChecked = true;
                }
                if (foodBox.isChecked()) {
                    preferenceList.add(foodBox.getText().toString());
                    isChecked = true;
                }
                if (landmarkBox.isChecked()) {
                    preferenceList.add(landmarkBox.getText().toString());
                    isChecked = true;
                }
                if (outdoorBox.isChecked()) {
                    preferenceList.add(outdoorBox.getText().toString());
                    isChecked = true;
                }
                if (shoppingBox.isChecked()) {
                    preferenceList.add(shoppingBox.getText().toString());
                    isChecked = true;
                }
                //Check if there is at least one preference
                if (isChecked) {
                    //Check if ther is a change in password
                    if (!password.equals("")) {
                        //Check if the confirmation password matches the entered password
                        if (password.equals(passwordConfirm)) {
                            ref.changePassword(usr.email, usr.password, password, new Firebase.ResultHandler() {
                                @Override
                                public void onSuccess() {
                                    User user = new User(usr.email, usr.firstName, usr.lastName, password, ageGroup, preferenceList);
                                    userRef.setValue(user);
                                    Intent intent = new Intent(SettingActivity.this, NewsFeedActivity.class);
                                    startActivity(intent);
                                }

                                @Override
                                public void onError(FirebaseError firebaseError) {
                                }
                            });
                        } else {
                            Toast.makeText(getApplicationContext(), "Password did not match. Please confirm password!", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        User user = new User(usr.email, usr.firstName, usr.lastName, usr.password, ageGroup, preferenceList);
                        userRef.setValue(user);
                        Intent intent = new Intent(SettingActivity.this, NewsFeedActivity.class);
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Please Choose at least one preference!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    private void restoreInformation(User user) {
        TextView userFirstName = (TextView) findViewById(R.id.firstName_setting);
        userFirstName.setText(user.firstName);
        TextView userLastName = (TextView) findViewById(R.id.lastName_setting);
        userLastName.setText(user.lastName);
        TextView userEmail = (TextView) findViewById(R.id.email_setting);
        userEmail.setText(user.email);

        //Check user's original age.
        RadioButton ageButton;
        if (user.ageGroup.equals("20s or Below")) {
            ageButton = (RadioButton) findViewById(R.id.setting_20s);
        } else if (user.ageGroup.equals("30s")) {
            ageButton = (RadioButton) findViewById(R.id.setting_30s);
        } else if (user.ageGroup.equals("40s")) {
            ageButton = (RadioButton) findViewById(R.id.setting_40s);
        } else if (user.ageGroup.equals("50s")) {
            ageButton = (RadioButton) findViewById(R.id.setting_50s);
        } else {
            ageButton = (RadioButton) findViewById(R.id.setting_60s);
        }
        ageButton.setChecked(true);

        //Check user's original preferences
        CheckBox preferenceBox = null;
        for (String s : user.preferenceList) {
            if (s.equals("Museums")) {
                preferenceBox = (CheckBox) findViewById(R.id.setting_museum);
            } else if (s.equals("Food")) {
                preferenceBox = (CheckBox) findViewById(R.id.setting_food);
            } else if (s.equals("Art")) {
                preferenceBox = (CheckBox) findViewById(R.id.setting_art);
            } else if (s.equals("Landmarks")) {
                preferenceBox = (CheckBox) findViewById(R.id.setting_landmark);
            } else if (s.equals("NightLife")) {
                preferenceBox = (CheckBox) findViewById(R.id.setting_nightlife);
            } else if (s.equals("Outdoors")) {
                preferenceBox = (CheckBox) findViewById(R.id.setting_outdoor);
            } else if (s.equals("Entertainment")) {
                preferenceBox = (CheckBox) findViewById(R.id.setting_entertainment);
            } else if (s.equals("Shopping")) {
                preferenceBox = (CheckBox) findViewById(R.id.setting_shopping);
            }
            preferenceBox.setChecked(true);
        }

    }

    private void addDrawerItems() {
//        String[] osArray = { "Android", "iOS", "Windows", "OS X", "Linux" };
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
