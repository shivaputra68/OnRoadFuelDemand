package com.example.onroadfueldemand;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
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
        BunkOrderAdapter adapter = new BunkOrderAdapter(this, bunkOrder, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setOrderDetails() {
        bunkOrder.add(new BunkOrder("1234","shiva","88848","petrol","Bidar",
                "pending","12","100"));
        bunkOrder.add(new BunkOrder("1234","shiva","88848","petrol","bidar",
                "pending","12","100"));
        bunkOrder.add(new BunkOrder("1234","shiva","88848","petrol","bidar",
                "pending","12","100"));
        bunkOrder.add(new BunkOrder("1234","shiva","88848","petrol","bidar",
                "pending","12","100"));
    }

    @Override
    public void onItemClick(int position) {

    }
}