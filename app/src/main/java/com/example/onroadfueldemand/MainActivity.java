package com.example.onroadfueldemand;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button user= findViewById(R.id.user);
        Button admin = findViewById(R.id.admin),bunk = findViewById(R.id.bunk);
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,login_user.class);
                i.putExtra("key", "user");
                startActivity(i);
            }
        });
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(MainActivity.this,login_user.class);
                i.putExtra("key", "admin");
                startActivity(i);
            }
        });
        bunk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i =new Intent(MainActivity.this,login_user.class);
                i.putExtra("key", "bunk");
                startActivity(i);

            }
        });

    }
}