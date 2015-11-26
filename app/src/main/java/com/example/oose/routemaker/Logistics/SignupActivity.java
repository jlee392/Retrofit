package com.example.oose.routemaker.Logistics;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.oose.routemaker.Concrete.User;
import com.example.oose.routemaker.R;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class SignupActivity extends AppCompatActivity {

    Firebase myFirebaseRef;
    RadioGroup radioGroup;
    RadioButton radioAgeButton;
    Firebase usersRef;
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
        setContentView(R.layout.activity_signup);
        Firebase.setAndroidContext(this);
        myFirebaseRef = new Firebase("https://incandescent-fire-1598.firebaseio.com/");
        usersRef = myFirebaseRef.child("users");
        signUp();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_default);
        setSupportActionBar(toolbar);

    }


    /**
     * On click method to sign up a new user.
     */
    public void signUp() {
        final EditText firstNameField = (EditText) findViewById(R.id.first_name_field);
        final EditText lastNameField = (EditText) findViewById(R.id.last_name_field);
        final EditText emailField = (EditText) findViewById(R.id.email_field);
        final EditText passwordField = (EditText) findViewById(R.id.password_field);
        final EditText passwordField_confirm = (EditText) findViewById(R.id.password_field_confirm);
        radioGroup = (RadioGroup) findViewById(R.id.age_group_radio);

        museumBox = (CheckBox) findViewById(R.id.preference_museum);
        artBox = (CheckBox) findViewById(R.id.preference_art);
        nightLifeBox = (CheckBox) findViewById(R.id.preference_nightlife);
        entertainmentBox = (CheckBox) findViewById(R.id.preference_entertainment);
        foodBox = (CheckBox) findViewById(R.id.preference_food);
        landmarkBox = (CheckBox) findViewById(R.id.preference_landmark);
        outdoorBox = (CheckBox) findViewById(R.id.preference_outdoor);
        shoppingBox = (CheckBox) findViewById(R.id.preference_shopping);

        final Button button = (Button) findViewById(R.id.register_button);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioAgeButton = (RadioButton) findViewById(selectedId);
                final List<String> preferenceList = new ArrayList<>();

                final String firstName = firstNameField.getText().toString();
                final String lastName = lastNameField.getText().toString();
                final String email = emailField.getText().toString();
                final String password = passwordField.getText().toString();
                final String password_confirm = passwordField_confirm.getText().toString();
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

                if(isChecked) {
                    if (password.equals(password_confirm)) {
                        myFirebaseRef.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
                            @Override
                            public void onSuccess(Map<String, Object> result) {
                                User user = new User(email, firstName, lastName, password, ageGroup, preferenceList);
                                usersRef.child(result.get("uid").toString()).setValue(user);

                                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
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
                    Toast.makeText(getApplicationContext(), "Please Choose at least one preference!", Toast.LENGTH_LONG).show();
                }

            }
        });
    }



//    @Override
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

        return super.onOptionsItemSelected(item);
    }
}
