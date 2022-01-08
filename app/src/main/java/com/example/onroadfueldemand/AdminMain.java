package com.example.onroadfueldemand;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseUser;

public class AdminMain extends AppCompatActivity {

    //Object Declaration
    ImageButton logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        logout = findViewById(R.id.adminLogout);


        //logout action code
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ProgressDialog progressDialog = new ProgressDialog(AdminMain.this);
                progressDialog.show();
                ParseUser.logOutInBackground(e -> {
                    progressDialog.dismiss();
                    if (e == null) {
                        Toast.makeText(getApplicationContext(), "Logged out Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AdminMain.this, Login.class);
                        intent.putExtra("usertype","ADMIN");
                        startActivity(intent);
                    }
                });
            }
        });
    }
}