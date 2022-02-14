package com.example.onroadfueldemand;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

import Interfaces.OrderFuelRecyclerClickListner;
import operations.BunkOrder;
import operations.BunkOrderAdapter;

public class BunkOrderRequest extends AppCompatActivity implements OrderFuelRecyclerClickListner {

    ArrayList<BunkOrder> bunkOrder = new ArrayList<BunkOrder>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bunk_order_request);

        Intent i = getIntent();
        recyclerView = findViewById(R.id.recyclerView_bunkOrder);
        setOrderDetails();


    }

    private void setOrderDetails() {

        ParseUser user = ParseUser.getCurrentUser();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("order");
        query.whereEqualTo("bunk_name", user.get("name").toString());
        query.whereEqualTo("bunk_contact", user.get("contact").toString());
        query.whereNotEqualTo("status", "Delivered");
        query.whereNotEqualTo("status", "Canceled");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e == null){
                    for(ParseObject object : objects) {
                        if (!object.get("status").toString().equals("Delivered") || !object.get("status").toString().equals("Reject")) {
                            bunkOrder.add(new BunkOrder(object.getObjectId(), object.get("customer_name").toString(), object.get("customer_contact").toString(),
                                    object.get("fuel_type").toString(), object.get("address").toString(), object.get("status").toString(),
                                    object.get("quantity").toString(), object.get("total_amount").toString()));
                        }
                    }
                }
                adapters() ;
            }
        });
    }

    public void adapters(){
        BunkOrderAdapter adapter = new BunkOrderAdapter(this, bunkOrder, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onItemClick(int position) {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("order");
        query.whereEqualTo("objectId", bunkOrder.get(position).getOrderId());
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                String objectId = "";
                for(ParseObject object : objects) {
                    objectId = object.getObjectId();
                }
                ParseQuery<ParseObject> obj = ParseQuery.getQuery("order");
                obj.getInBackground(objectId, new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if (e == null) {
                            System.out.println(object.getObjectId());
                            object.put("status", bunkOrder.get(position).getBunkStatus());
                            object.saveInBackground(new SaveCallback() {
                                @Override
                                public void done(ParseException e) {
                                    if(e == null){
                                        Toast.makeText(BunkOrderRequest.this, "Order Updated", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                         } else {
                            Toast.makeText(BunkOrderRequest.this, " "+e, Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}