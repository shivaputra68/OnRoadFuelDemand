package com.example.onroadfueldemand;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class login_user extends AppCompatActivity {

    //object declaration
    Button login,signup;
    LinearLayout layout;
    TextView heading,option;
    EditText username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

        //TypeCasting
        signup= findViewById(R.id.btn_signup);
        layout = findViewById(R.id.loginActivity);
        login= findViewById(R.id.btn_login);
        heading=findViewById(R.id.heading);
        option = findViewById(R.id.option);

        //Actions tro be carried out
        //defining intent to receive the values
        Intent intent = getIntent();
        String key=intent.getStringExtra("key");

        switch(key){
            case "admin":
                heading.setText("ADMIN LOGIN");
                layout.removeView(signup);
                layout.removeView(option);
                login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = username.getText().toString();
                        String pass = password.getText().toString();
                        boolean result = Login_method(key,name,pass);
                        if(result){
                            Toast.makeText(getApplicationContext(), "Login Successfully!", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "Invalid username/password", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                break;
            case "user":
                heading.setText("USER LOGIN");
                login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = username.getText().toString();
                        String pass = password.getText().toString();
                        boolean result = Login_method(key,name,pass);
                        if(result){
                            Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_LONG).show();
                        }
                    }
                });

                signup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(login_user.this,user_register.class);
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
                        boolean result = Login_method(key,name,pass);
                        if(result){
                            Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_LONG).show();
                        }
                    }
                });

                signup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(login_user.this,login_user.class);
                        startActivity(intent);
                    }
                });
                break;
            default:
                break;
        }
    }
    public boolean Login_method(String key,String username,String password){
            return true;
    }
}