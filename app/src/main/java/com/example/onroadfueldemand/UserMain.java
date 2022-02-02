package com.example.onroadfueldemand;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseUser;

public class UserMain extends AppCompatActivity {

    ImageButton logout,profile,orderFuel,viewOrders,orderStatus;
    TextView heading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);

        logout = findViewById(R.id.userLogout);
        profile = findViewById(R.id.userProfile);
        heading = findViewById(R.id.userHeading);
        orderFuel = findViewById(R.id.userOrderFuel);
        viewOrders = findViewById(R.id.viewOrders);
        orderStatus = findViewById(R.id.orderStatus);

        ParseUser user = ParseUser.getCurrentUser();
        heading.setText("HI, "+user.getUsername());
        Intent intent = getIntent();

        //profile action code
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(UserMain.this,ProfileView.class);
                startActivity(intent);


            }
        });

        //Logout action code
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ProgressDialog progressDialog = new ProgressDialog(UserMain.this);
                progressDialog.show();
                ParseUser.logOutInBackground(e -> {
                    progressDialog.dismiss();
                    if (e == null) {
                        Toast.makeText(getApplicationContext(), "Logged out Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(UserMain.this, Login.class);
                        intent.putExtra("key", "user");
                        startActivity(intent);
                    }
                });
            }
        });

        //Order Fuel action code
        orderFuel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent  = new Intent(UserMain.this, OrderFuel.class);
                startActivity(intent);
            }
        });

        orderStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(UserMain.this, TraceOrder.class);
                intent.putExtra("customer_name", user.getUsername());
                startActivity(intent);
            }
        });

        //View orders action code
        viewOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(UserMain.this,ViewOrders.class);
                startActivity(intent);
            }
        });
    }
}