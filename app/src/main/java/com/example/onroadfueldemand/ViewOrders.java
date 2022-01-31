package com.example.onroadfueldemand;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import operations.OrderViewAdapter;
import operations.ViewOrder;

public class ViewOrders extends AppCompatActivity {

    ArrayList<ViewOrder> orderHistory = new ArrayList<>();
    RecyclerView recyclerView;
    ArrayList<String> bunkName, fuelType, orderID, amount, orderStatus, orderDate, quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_orders);

        Intent i = getIntent();
        recyclerView = findViewById(R.id.recyclerView_viewOrder);
        setViewOrders();
       // OrderViewAdapter adapter = new OrderViewAdapter(this, orderHistory);
       // recyclerView.setAdapter(adapter);
       // recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setViewOrders() {
        ParseQuery<ParseObject> obj = new ParseQuery<ParseObject>("order");
        obj.whereEqualTo("customer_name", ParseUser.getCurrentUser().getUsername());
        obj.orderByDescending("updateAt");
        obj.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                for(ParseObject object : objects){
                    System.out.println("*******************"+object.get("bunk_name").toString()+"****************");
                    orderHistory.add(new ViewOrder(object.get("bunk_name").toString(), object.get("fuel_type").toString(),
                            object.getObjectId().toString(), object.get("total_amount").toString(),object.get("status").toString(),
                            object.get("date").toString(),object.get("quantity").toString()));
                }
            }
        });

            OrderViewAdapter adapter = new OrderViewAdapter(this, orderHistory);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}