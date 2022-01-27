package com.example.onroadfueldemand;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import Interfaces.OrderFuelRecyclerClickListner;
import operations.Fuel;
import operations.OrderAdapter;

public class OrderFuel extends AppCompatActivity implements OrderFuelRecyclerClickListner {

    ArrayList<Fuel> fuel = new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_fuel);

        recyclerView = findViewById(R.id.recyclerView);
        setFuelStations();
        OrderAdapter adapter = new OrderAdapter(this, fuel, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setFuelStations(){
        fuel.add( new Fuel("Indian Oil","petrol","70 rs","10 km"));
        fuel.add( new Fuel("HP GAS","petrol","80 rs","18 km"));
        fuel.add( new Fuel("Indian Oil","petrol","70 rs","10 km"));
        fuel.add( new Fuel("HP GAS","petrol","80 rs","18 km"));
        fuel.add( new Fuel("Indian Oil","petrol","70 rs","10 km"));
        fuel.add( new Fuel("HP GAS","petrol","80 rs","18 km"));
        fuel.add( new Fuel("Indian Oil","petrol","70 rs","10 km"));
        fuel.add( new Fuel("HP GAS","petrol","80 rs","18 km"));
    }

    @Override
    public void onItemClick(int position) {

        Intent intent = new Intent(OrderFuel.this, OrderDetails.class);
        intent.putExtra("bunkName",fuel.get(position).getBunkName());

        startActivity(intent);

    }
}