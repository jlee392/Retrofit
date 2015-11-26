package com.example.oose.routemaker.Logistics;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.oose.routemaker.Concrete.User;
import com.example.oose.routemaker.NewsFeedActivity;
import com.example.oose.routemaker.R;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    Firebase ref;
    Firebase usersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Firebase.setAndroidContext(this);
        ref = new Firebase("https://incandescent-fire-1598.firebaseio.com/");

        usersRef = ref.child("users");

        // Attach an listener to read the data at our posts reference

        final Button button = (Button) findViewById(R.id.button_signup);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        final EditText login_email_field = (EditText) findViewById(R.id.email_login_field);
        final EditText login_password_field = (EditText) findViewById(R.id.password_login_field);
        final Button button2 = (Button) findViewById(R.id.button_login);

        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final String login_email = login_email_field.getText().toString();
                final String login_password = login_password_field.getText().toString();
                ref.authWithPassword(login_email, login_password, new Firebase.AuthResultHandler() {
                    @Override
                    public void onAuthenticated(AuthData authData) {
                        Intent intent = new Intent(MainActivity.this, NewsFeedActivity.class);
                        startActivity(intent);
                    }
                    @Override
                    public void onAuthenticationError(FirebaseError firebaseError) {
                        Toast.makeText(getApplicationContext(), "Log In Failed!", Toast.LENGTH_LONG).show();
                        // there was an error
                    }
                });
            }
        });


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
        return super.onOptionsItemSelected(item);
    }
}
