package com.example.onroadfueldemand;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import operations.*;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseUser;

public class Register extends AppCompatActivity {

    //objects declaration
    EditText map_reg,contact,email,username,password,conf_password;
    Button signup;
    LinearLayout layout;
    String latlong;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Typecasting
        layout = findViewById(R.id.reg_page_layout);
        //name = findViewById(R.id.reg_name);
        //address = findViewById(R.id.reg_address);
        contact = findViewById(R.id.reg_contact);
        email = findViewById(R.id.reg_email);
        username = findViewById(R.id.reg_username);
        password = findViewById(R.id.reg_password);
        conf_password = findViewById(R.id.reg_conf_password);
        //location = findViewById(R.id.reg_location);
        signup = findViewById(R.id.reg_signup);
        map_reg=findViewById(R.id.reg_map);
        Intent in=getIntent();
        String usertype=in.getStringExtra("usertype");
        if(usertype=="user")
        {
            Registration registration=new Registration();
            signup.setText("Signup");

            registration.registerUser(username.getText().toString(),password.getText().toString(),email.getText().toString());
            Intent intent= new Intent(Register.this,Login.class);

        }
        else
        {
            signup.setText("Continue");
            Registration registration=new Registration();
            registration.registerUser(username.getText().toString(),password.getText().toString(),email.getText().toString());
            Intent intent = new Intent(Register.this,Register_map.class);




        }


        map_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(Register.this,Register_map.class);
                startActivity(i);


            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}