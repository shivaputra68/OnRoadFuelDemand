package com.example.onroadfueldemand;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

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
        OrderViewAdapter adapter = new OrderViewAdapter(this, orderHistory);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setViewOrders() {
        orderHistory.add(new ViewOrder("Indian Oid","petrol","12345","100",
                "delivers","12/10/1999","3"));

        orderHistory.add(new ViewOrder("Indian Oid","petrol","12345","100",
                "delivers","12/10/1999","3"));
        orderHistory.add(new ViewOrder("Indian Oid","petrol","12345","100",
                "delivers","12/10/1999","3"));
        orderHistory.add(new ViewOrder("Indian Oid","petrol","12345","100",
                "delivers","12/10/1999","3"));
    }

    public void add(){

    }
}