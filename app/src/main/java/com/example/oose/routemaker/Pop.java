package com.example.oose.routemaker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oose.routemaker.Concrete.User;
import com.example.oose.routemaker.Logistics.SettingActivity;
import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Created by Hyun Joon on 11/24/2015.
 */
public class Pop extends Activity{

    String password;
    Firebase userRef;
    String userId;
    User user;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popupwindow);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .5), (int) (height * .38));

        final EditText password_popup_field  = (EditText) findViewById(R.id.password_popup_field);

        Firebase ref = new Firebase("https://incandescent-fire-1598.firebaseio.com/");
        ref = new Firebase("https://incandescent-fire-1598.firebaseio.com/");
        AuthData authData = ref.getAuth();
        userId = authData.getUid();
        userRef = ref.child("users").child(userId);

        Button button = (Button) findViewById(R.id.popup_enter_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final String popup_password = password_popup_field.getText().toString();
                userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        user = dataSnapshot.getValue(User.class);
                        if (popup_password.equals(user.password)) {
                            Intent intent = new Intent(Pop.this, SettingActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "Please check your password!", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onCancelled(FirebaseError firebaseError) {
                    }
                });
            }
        });
    }
}
