package com.example.onroadfueldemand;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class ProfileView extends AppCompatActivity {


    EditText Name,email,username,contact;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);

        Intent intent = getIntent();
        Name = (EditText) findViewById(R.id.textName);
        email = (EditText) findViewById(R.id.textEmail);
        username = (EditText) findViewById(R.id.textAddress);
        contact = (EditText) findViewById(R.id.textContact);
        back = findViewById(R.id.navigateBack);


        //Action code
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        ParseUser user = ParseUser.getCurrentUser();

        query.whereEqualTo("username",user.getUsername());
        query.getFirstInBackground(new GetCallback<ParseUser>() {
            @Override
            public void done(ParseUser object, ParseException e) {
                if(e == null){
                    Name.setText(object.getString("name"));
                    email.setText(object.getString("email"));
                    username.setText(object.getString("username"));
                    contact.setText(object.getString("contact"));
                }else{
                    Name.setText(""+e);
                }
            }
        });
    }
}