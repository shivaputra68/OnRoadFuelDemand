package com.example.onroadfueldemand;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class login_user extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);
        TextView registering = findViewById(R.id.registering);
        ConstraintLayout ct= findViewById(R.id.loginActivity);
        TextView tv= findViewById(R.id.registering);
        Button button= findViewById(R.id.login_button);
        TextView heading=findViewById(R.id.heading);


        Intent intent = getIntent();
        String key=intent.getStringExtra("key");
        if(key.equalsIgnoreCase("admin"))
        {
            heading.setText("ADMIN LOGIN");
            ct.removeView(tv);
        }


            registering.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it = new Intent(login_user.this, userregister.class);
                    startActivity(it);
                }
            });

        if (key.equals("bunk"))
        {
            heading.setText("BUNK LOGIN");
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it=new Intent(login_user.this,BunkMain.class);
                    startActivity(it);
                }
            });
        }

        if(key.equals("user"))
        {
            heading.setText("USER LOGIN");
        }

    }
}