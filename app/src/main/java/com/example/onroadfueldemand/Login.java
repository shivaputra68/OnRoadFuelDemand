package com.example.onroadfueldemand;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseUser;

public class Login extends AppCompatActivity {

    //object declaration
    Button login,signup;
    LinearLayout layout;
    TextView heading,option;
    EditText username,password;
   // @SuppressLint("SetTextI18n")
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //TypeCasting
        signup= findViewById(R.id.btn_signup);
        login= findViewById(R.id.btn_login);
        layout = findViewById(R.id.loginActivity);
        heading=findViewById(R.id.heading);
        option = findViewById(R.id.option);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);


        //Actions to be carried out
        //defining intent to receive the values
        Intent intent = getIntent();
        String key=intent.getStringExtra("key");

        switch(key){
            //ADMIN related code
            case "admin":
                heading.setText("ADMIN LOGIN");
                layout.removeView(signup);
                layout.removeView(option);
                login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = username.getText().toString();
                        String pass = password.getText().toString();
                        ProgressDialog progress  = new ProgressDialog(Login.this);
                        progress.show();
                        progress.setMessage("Please wait....");
                        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        ParseUser.logInInBackground(name, pass, (parseUser, e) -> {
                            progress.dismiss();
                            if (parseUser != null) {
                                Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                                Intent adminIntent = new Intent(Login.this, AdminMain.class);
                                startActivity(adminIntent);
                            } else {
                                ParseUser.logOut();
                                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
                break;
            case "user":
                //USER related code
                heading.setText("USER LOGIN");
                login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = username.getText().toString();
                        String pass = password.getText().toString();
                        ProgressDialog progress  = new ProgressDialog(Login.this);
                        progress.show();
                        progress.setMessage("Please wait....");
                        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        ParseUser.logInInBackground(name, pass, (parseUser, e) -> {
                            progress.dismiss();
                            if (parseUser != null) {
                                Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                                Intent userIntent = new Intent(Login.this,UserMain.class);
                                userIntent.putExtra("username",name);
                                startActivity(userIntent);
                            } else {
                                ParseUser.logOut();
                                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });

                signup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(Login.this, Register.class);
                        intent.putExtra("usertype",key);
                        startActivity(intent);
                    }
                });
                break;
            case "bunk":
                heading.setText("BUNK LOGIN");
                login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = username.getText().toString();
                        String pass = password.getText().toString();
                        ProgressDialog progress  = new ProgressDialog(Login.this);
                        progress.show();
                        progress.setMessage("Please wait....");
                        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        ParseUser.logInInBackground(name, pass, (parseUser, e) -> {
                            progress.dismiss();
                            if (parseUser != null) {
                                Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                                Intent bunkIntent = new Intent(Login.this,BunkMain.class);
                                startActivity(bunkIntent);
                            } else {
                                ParseUser.logOut();
                                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                });
                signup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Login.this, Register.class);
                        intent.putExtra("usertype", key);
                        startActivity(intent);
                    }
                });
                break;
            default:
                break;
        }
    }
}