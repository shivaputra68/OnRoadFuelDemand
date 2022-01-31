package com.example.onroadfueldemand;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;

import operations.TraceOrderBean;

public class TraceOrder extends AppCompatActivity {

    Button cancelButton;
    TextView bunkName, fuelType,status;
    ProgressBar progressBar;
    ArrayList<TraceOrderBean> traceOrder = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trace_order);

        cancelButton = findViewById(R.id.traceOrderCancelButton);
        bunkName = findViewById(R.id.traceOrderBunkName);
        fuelType = findViewById(R.id.traceOrderFuelType);
        status = findViewById(R.id.traceOrderStatus);
        progressBar = findViewById(R.id.traceOrderProgressBar);
        Intent intent = getIntent();

        ParseQuery<ParseObject> order = new ParseQuery<>("orders");
        order.whereContains("customer_name", intent.getStringExtra("customer_name"));
        //order.whereNotContains("status","Delivered");

        order.findInBackground(((objects, e) -> {
            if(e == null){
                try {
                    System.out.println("*****************************************");
                    bunkName.setText("Bunk : "+ order.get("bunk_name").toString());
                    fuelType.setText("Fuel : "+ order.get("fuel_type").toString());
                    status.setText("Status : "+ order.get("status").toString());
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }
            }else{
                System.out.println("++++++++++++++++++++++++++++++++++");
                e.printStackTrace();
            }
        }));

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}