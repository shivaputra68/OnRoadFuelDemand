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

import com.parse.ParseUser;

public class Login extends AppCompatActivity {

    //object declaration
    Button login,signup;
    LinearLayout layout;
    TextView heading,option;
    EditText username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //TypeCasting
        signup= findViewById(R.id.btn_signup);
        layout = findViewById(R.id.loginActivity);
        login= findViewById(R.id.btn_login);
        heading=findViewById(R.id.heading);
        option = findViewById(R.id.option);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);

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
                        ParseUser.logInInBackground(name, pass,(user,e)->{
                           if(user!=null)
                           {
                               if(name.contains("nirmal@admin")|| name.contains("shivaputra@admin"))
                               {
                                   Intent i= new Intent(Login.this,AdminMain.class);
                                   startActivity(i);
                               }
                               else
                               {
                                   Toast.makeText(Login.this, "Invalid AdminName and Password", Toast.LENGTH_SHORT).show();
                               }
                           }
                           else {
                               Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                           }

                        });
                        //boolean result = Login_method(key,name,pass);
//                        if(result){
//                            Intent i = new Intent(getApplicationContext(),ls.class);
//                            startActivity(i);
//                            Toast.makeText(getApplicationContext(), "Login Successfully!", Toast.LENGTH_LONG).show();
//                        }else{
//                            Toast.makeText(getApplicationContext(), "Invalid username/password", Toast.LENGTH_LONG).show();
//                        }
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
                        ParseUser.logInInBackground(name, pass,(user,e)->{
                            if(user!=null)
                            {
                                Toast.makeText(getApplicationContext(), "login sucessful", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(Login.this, UserMain.class);
                                startActivity(i);
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

//                        boolean result = Login_method(key,name,pass);
//                        if(result){
//                            Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_LONG).show();
//                        }else{
//                            Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_LONG).show();
//                        }
                    }
                });

                signup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Login.this, Register.class);
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
//                        boolean result = Login_method(key,name,pass);
//                        if(result){
//                            Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_LONG).show();
//                        }else{
//                            Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_LONG).show();
//                        }
                    }
                });

                signup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Login.this, Register.class);
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