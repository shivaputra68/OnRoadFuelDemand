package com.example.onroadfueldemand;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseUser;

public class Register extends AppCompatActivity {

    //objects declaration
    EditText name,address,contact,email,username,password,conf_password,location;
    Button signup;
    Spinner type;
    String userType;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

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
//        userType = type.getSelectedItem().toString();
//
//        if(userType.equalsIgnoreCase("user")){
//            layout.removeView(location);
//        }
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser user = new ParseUser();
                // Set the user's username and password, which can be obtained by a forms
                user.setUsername(username.getText().toString());
                user.setPassword(password.getText().toString());
                user.setEmail(email.getText().toString());
               // user.put("contact number", contact.getText().toString());


                user.signUpInBackground(e -> {
                    if (e == null) {
                        Toast.makeText(Register.this, "signed in", Toast.LENGTH_SHORT).show();
                    } else {
                        ParseUser.logOut();
                        Toast.makeText(Register.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            }
        });


    }
}