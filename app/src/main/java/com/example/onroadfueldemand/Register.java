package com.example.onroadfueldemand;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class Register<protecetd> extends AppCompatActivity {

    //objects declaration
    EditText map_reg,contact,email,username,password,conf_password,name,address;
    Button signup;
    LinearLayout layout;
    String lat_long;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Intent from Login page
        Intent intent = getIntent();
        String usertype = intent.getStringExtra("usertype");

        //Object TypeCasting
        name = findViewById(R.id.reg_name);
        address = findViewById(R.id.reg_address);
        contact = findViewById(R.id.reg_contact);
        email = findViewById(R.id.reg_email);
        //map_reg = findViewById(R.id.reg_location);
        username = findViewById(R.id.reg_username);
        password = findViewById(R.id.reg_password);
        conf_password = findViewById(R.id.reg_conf_password);
        signup = findViewById(R.id.reg_signup);
        layout = findViewById(R.id.reg_page_layout);

        //Action code
        switch(usertype){
            case "user":
                signup.setText("Sign up");
                signup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Register(name.getText().toString(), address.getText().toString(), contact.getText().toString()
                                , email.getText().toString(), username.getText().toString(), password.getText().toString());
                    }
                });
                break;
            case "bunk":
                signup.setText("Continue");
                signup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1=new Intent(getApplicationContext(),Register_map.class);
                        String address1=address.getText().toString(),name1=name.getText().toString(),contact1=contact.getText().toString(),email1=email.getText().toString(),username1=username.getText().toString(),
                        password1=password.getText().toString();

                        intent1.putExtra("name",name1);
                        intent1.putExtra("address",address1);
                        intent1.putExtra("contact",contact1);
                        intent1.putExtra("email", email1);
                        intent1.putExtra("username", username1);
                        intent1.putExtra("password", password1);
                        startActivity(intent1);

                        Register(name.getText().toString(),address.getText().toString(),contact.getText().toString(),email.getText().toString()
                        ,username.getText().toString(),password.getText().toString());
                    }
                });
                break;
            default:
                break;
        }
    }

    public void Register(String name,String address,String contact,String email,String username,String password ){
        //Back4App Parser
        ParseUser user = new ParseUser();
        user.setUsername(username);
        user.put("name",name);
        user.put("contact",contact);
        user.put("address",address);
        user.setPassword(password);
        user.setEmail(email);
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Toast.makeText(getApplicationContext(), "Successful Sign Up!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),Register_map.class);
                    startActivity(intent);
                } else {
                    ParseUser.logOut();
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}