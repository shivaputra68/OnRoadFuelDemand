package com.example.onroadfueldemand;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;

public class TraceOrder extends AppCompatActivity {

    Button cancelButton;
    TextView bunkName, fuelType,status;
    ProgressBar progressBar;

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

        ParseQuery<ParseObject> query = ParseQuery.getQuery("order");
        query.whereEqualTo("customer_name", ParseUser.getCurrentUser().getUsername());
        query.whereNotEqualTo("status", "Delivered");
        query.addAscendingOrder("updateAt");
        query.findInBackground(((objects, e) -> {
            if(e == null){
                bunkName.setText("Name : "+objects.get(0).get("bunk_name").toString());
                fuelType.setText("Fuel : "+objects.get(0).get("fuel_type").toString());
                status.setText("Status : "+objects.get(0).get("status").toString());
            }
        }));

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseQuery<ParseObject> query = ParseQuery.getQuery("order");
                query.whereNotEqualTo("status", "Canceled");
                query.whereEqualTo("customer_name" , ParseUser.getCurrentUser().getUsername());
                query.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        String objectID = "";
                        if(e == null){
                            for(ParseObject object : objects){
                                objectID = object.getObjectId();
                            }
                            ParseQuery<ParseObject> query = ParseQuery.getQuery("order");
                            query.getInBackground(objectID, new GetCallback<ParseObject>() {
                                @Override
                                public void done(ParseObject object, ParseException e) {
                                    object.put("status", "Canceled");
                                    object.saveInBackground(new SaveCallback() {
                                        @Override
                                        public void done(ParseException e) {
                                            Toast.makeText(TraceOrder.this, "Order Has Been Canceld!", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            });
                        }
                    }
                });

            }
        });
    }
}