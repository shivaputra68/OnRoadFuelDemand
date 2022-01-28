package com.example.onroadfueldemand;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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

        setValues();

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    protected void setValues(){
        traceOrder.add(new TraceOrderBean("indian oil","petrol","Accepted"));
        bunkName.setText("Bunk : "+traceOrder.get(0).getBunkName());
        fuelType.setText("Fuel : "+traceOrder.get(0).getFuelType());
        status.setText("Status : "+traceOrder.get(0).getStatus());
    }
}