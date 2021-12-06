package com.example.onroadfueldemand;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class user_register extends AppCompatActivity {

    //objects declaration
    EditText name,address,contact,email,username,password,conf_password,location;
    Button signup;
    Spinner type;
    String userType;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        //Typecasting
        layout = findViewById(R.id.reg_page_layout);
        name = findViewById(R.id.reg_name);
        address = findViewById(R.id.reg_address);
        contact = findViewById(R.id.reg_contact);
        email = findViewById(R.id.reg_email);
        username = findViewById(R.id.reg_username);
        password = findViewById(R.id.reg_password);
        conf_password = findViewById(R.id.reg_conf_password);
        location = findViewById(R.id.reg_location);
        signup = findViewById(R.id.reg_signup);
        type = findViewById(R.id.reg_spinner);

        //accepting value from spinner
        userType = type.getSelectedItem().toString();

        if(userType.equalsIgnoreCase("user")){
            layout.removeView(location);
        }


    }
}