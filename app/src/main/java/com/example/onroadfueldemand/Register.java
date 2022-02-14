package com.example.onroadfueldemand;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class Register<protecetd> extends AppCompatActivity {

    //objects declaration
    EditText contact,email,username,password,conf_password,name,address;
    Button signup;
    LinearLayout layout;
    CheckBox checkBox;



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
        username = findViewById(R.id.reg_username);
        password = findViewById(R.id.reg_password);
        conf_password = findViewById(R.id.reg_conf_password);
        signup = findViewById(R.id.reg_signup);
        layout = findViewById(R.id.reg_page_layout);
        checkBox=findViewById(R.id.reg_chk);

        //Action code
        switch(usertype){
            case "user":
                signup.setText("Sign up");
                signup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(email.getText().toString().matches("^(.+)@(\\\\S+)$"))
                        {
                            Toast.makeText(Register.this, "Invalid Email Format", Toast.LENGTH_SHORT).show();
                        }
                        else if(contact.getText().toString().matches("^\\d{10}$"))
                        {
                            Toast.makeText(Register.this, "Number should be exactly 10 digits", Toast.LENGTH_SHORT).show();
                        }
                        else if(password.getText().toString().isEmpty()||password.getText().toString().equals(conf_password.getText().toString())==false)
                        {
                            Toast.makeText(Register.this, "Password Mismatch", Toast.LENGTH_SHORT).show();

                        }
                        else if(!password.getText().toString().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+])(?=\\S+$).{8,20}$"))
                        {
                            AlertDialog.Builder ab= new AlertDialog.Builder(Register.this);
                            ab.setTitle("invalid format").setMessage("It Should contain at least 8 characters and at most 20 characters.\n" +
                                    "It Should contain at least one digit.\n" +
                                    "It Should contain at least one upper case alphabet.\n" +
                                    "It Should contain at least one lower case alphabet.\n" +
                                    "It Should contain at least one special character which includes !@#$%&*()-+=^.\n" +
                                    "It should not contain any white space.\n").setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(Register.this, "", Toast.LENGTH_SHORT).show();
                                    password.setText("");
                                    conf_password.setText("");
                                }
                            }).create().show();

                        }
                        else if(!checkBox.isChecked())
                        {
                            Toast.makeText(Register.this, "Please agree to the terms and condition", Toast.LENGTH_SHORT).show();
                        }
                        else {
//

                            ParseUser user = new ParseUser();
                            user.setUsername(username.getText().toString());
                            user.put("name",name.getText().toString());
                            user.put("contact",contact.getText().toString());
                            user.put("address",address.getText().toString());
                            user.setPassword(password.getText().toString());
                            user.setEmail(email.getText().toString());
                            user.signUpInBackground(new SignUpCallback() {
                                @Override
                                public void done(ParseException e) {
                                    if (e == null) {
                                        Toast.makeText(getApplicationContext(), "Successful Sign Up!", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                        startActivity(intent);
                                    } else {
                                        ParseUser.logOut();
                                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }

                    }
                });
                break;
            case "bunk":
                signup.setText("Continue");
                signup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1=new Intent(getApplicationContext(),Register_map.class);
                        if(email.getText().toString().matches("^(.+)@(\\\\S+)$"))
                        {
                            Toast.makeText(Register.this, "Invalid Email Format", Toast.LENGTH_SHORT).show();
                        }
                        else if(contact.getText().toString().matches("^\\d{10}$"))
                        {
                            Toast.makeText(Register.this, "Number should be exactly 10 digits", Toast.LENGTH_SHORT).show();
                        }
                        else if(password.getText().toString().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$"))
                        {
                            AlertDialog.Builder ab= new AlertDialog.Builder(Register.this);
                            ab.setTitle("invalid format").setMessage("It Should contain at least 8 characters and at most 20 characters.\n" +
                                    "It Should contain at least one digit.\n" +
                                    "It Should contain at least one upper case alphabet.\n" +
                                    "It Should contain at least one lower case alphabet.\n" +
                                    "It Should contain at least one special character which includes !@#$%&*()-+=^.\n" +
                                    "It should not contain any white space.\n").setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(Register.this, "", Toast.LENGTH_SHORT).show();
                                    password.setText("");
                                    conf_password.setText("");
                                }
                            });
                        }
                        String address1=address.getText().toString(),name1=name.getText().toString(),contact1=contact.getText().toString(),email1=email.getText().toString(),username1=username.getText().toString(),
                                password1=password.getText().toString();

                        intent1.putExtra("name",name1);
                        intent1.putExtra("address",address1);
                        intent1.putExtra("contact",contact1);
                        intent1.putExtra("email", email1);
                        intent1.putExtra("username", username1);
                        intent1.putExtra("password", password1);
                        startActivity(intent1);


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