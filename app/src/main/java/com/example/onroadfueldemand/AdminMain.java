package com.example.onroadfueldemand;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseUser;

public class AdminMain extends AppCompatActivity {

    //Object Declaration
    ImageButton logout,profile,bunkRequest,home;
    TextView heading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        logout = findViewById(R.id.adminLogout);
        profile = findViewById(R.id.adminProfile);
        heading = findViewById(R.id.adminHeading);
        home = findViewById(R.id.adminHome);
        bunkRequest = findViewById(R.id.adminBunkRequest);

        ParseUser user = ParseUser.getCurrentUser();
        heading.setText("HI, "+user.getUsername());
        Intent intent = getIntent();
        String name = intent.getStringExtra("username");

        //profile action code
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AdminMain.this,ProfileView.class);
                startActivity(intent);
            }
        });

        //logout action code
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ProgressDialog progressDialog = new ProgressDialog(AdminMain.this);
                progressDialog.show();
                ParseUser.logOutInBackground(e -> {
                    progressDialog.dismiss();
                    if (e == null) {
                        Toast.makeText(getApplicationContext(), "Logged out Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AdminMain.this, Login.class);
                        intent.putExtra("key","ADMIN");
                        startActivity(intent);
                    }
                });
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.back4app.com/#"));
                startActivity(intent);
            }
        });

        bunkRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminMain.this, AdminBunkRequest.class);
                startActivity(intent);
            }
        });
    }
}