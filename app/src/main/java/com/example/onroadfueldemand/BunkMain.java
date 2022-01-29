package com.example.onroadfueldemand;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseUser;

public class BunkMain extends AppCompatActivity {

    ImageButton logout,profile,update,history, order;
    TextView heading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bunk_main);

        logout = findViewById(R.id.bunkLogout);
        profile = findViewById(R.id.bunkProfile);
        update = findViewById(R.id.bunkUpdate);
        history = findViewById(R.id.bunkMainHistory);
        heading = findViewById(R.id.bunkHeading);
        order = findViewById(R.id.bunkOrder);

        ParseUser user = ParseUser.getCurrentUser();
        heading.setText("HI, "+user.getUsername());
        Intent intent = getIntent();
        String name = intent.getStringExtra("username");

        //profile action code
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(BunkMain.this,ProfileView.class);
                startActivity(intent);


            }
        });

        //Logout action code
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ProgressDialog progressDialog = new ProgressDialog(BunkMain.this);
                progressDialog.show();
                ParseUser.logOutInBackground(e -> {
                    progressDialog.dismiss();
                    if (e == null) {
                        Toast.makeText(getApplicationContext(), "Logged out Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BunkMain.this, Login.class);
                        intent.putExtra("usertype", "ADMIN");
                        startActivity(intent);
                    }
                });
            }
        });

        //update action code
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), BunkUpdate.class);
                startActivity(i);
            }
        });

        //History action code
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), BunkHistory.class);
                startActivity(i);
            }
        });

        //Order action code
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), BunkOrderRequest.class);
                startActivity(i);
            }
        });
    }
}