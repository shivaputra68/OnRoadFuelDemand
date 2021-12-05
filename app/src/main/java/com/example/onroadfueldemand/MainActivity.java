package com.example.onroadfueldemand;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity{

    //Object Declaration
    Button user,admin,bunk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TypeCasting
        user= findViewById(R.id.user);
        admin = findViewById(R.id.admin);
        bunk = findViewById(R.id.bunk);

        //code for users actions
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,login_user.class);
                i.putExtra("key", "user");
                startActivity(i);
            }
        });

        //code for admin actions
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(MainActivity.this,login_user.class);
                i.putExtra("key", "admin");
                startActivity(i);
            }
        });

        //code for bunk actions
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