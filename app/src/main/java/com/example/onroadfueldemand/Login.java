package com.example.onroadfueldemand;

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
                        login(name,pass);
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
                        login(name,pass);


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
                        login(name,pass);
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

    //On resume code
    @Override
    protected  void onResume() {
        super.onResume();

        Intent i = getIntent();
        heading.setText(i.getStringExtra("usertype"));
    }

    public void login(String username, String password){
        ProgressDialog progress  = new ProgressDialog(this);
        progress.show();
        progress.setMessage("Please wait....");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        ParseUser.logInInBackground(username, password, (parseUser, e) -> {
            progress.dismiss();
            if (parseUser != null) {
                //showAlert("Successful Login", "Welcome back " + username + " !");
                Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
            } else {
                ParseUser.logOut();
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}