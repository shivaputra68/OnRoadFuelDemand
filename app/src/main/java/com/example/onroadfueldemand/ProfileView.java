package com.example.onroadfueldemand;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class ProfileView extends AppCompatActivity {


    EditText Name,email,username,contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);

        Name = findViewById(R.id.textName);
        email = findViewById(R.id.textEmail);
        username = findViewById(R.id.textAddress);
        contact = findViewById(R.id.textContact);

        Intent intent = getIntent();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("user");
        ParseUser name = ParseUser.getCurrentUser();

        query.whereEqualTo("user",name.getUsername());
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if(e == null){
                    Name.setText(object.getString("name"));
                    email.setText(object.getString("email"));
                    username.setText(object.getString("username"));
                    contact.setText(object.getString("contact"));
                }else{
                    Name.setText("Mapping not found");
                }
            }
        });
    }
}