package com.example.onroadfueldemand;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class activity_splash extends AppCompatActivity {

    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

            handler = new Handler();
            handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(activity_splash.this,MainActivity.class);
                startActivity(i);
            }
        },5000);
    }
}