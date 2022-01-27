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
    ArrayList<String> bunkName, fuelType, fuelPrice, bunkDistance, bunkContact;

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

    //Values adding to the bean class Fuel
    private void setFuelStations(){
       // addValues();
        fuel.add( new Fuel("Indian Oil","petrol","70","10","12345"));
        fuel.add( new Fuel("HP GAS","petrol","80","18","159486"));
        fuel.add( new Fuel("Indian Oil","petrol","70","10","154896"));
        fuel.add( new Fuel("HP GAS","petrol","80","18","125436"));
        fuel.add( new Fuel("Indian Oil","petrol","70","10","584967"));
        fuel.add( new Fuel("HP GAS","petrol","80","18","15846"));
        fuel.add( new Fuel("Indian Oil","petrol","70","10","154896"));
        fuel.add( new Fuel("HP GAS","petrol","80","18","225846"));
    }

    //Values fetching from the database and adding to the Strings of array
    private void addValues(){
        bunkName = new ArrayList<>();
        fuelType = new ArrayList<>();
        fuelPrice = new ArrayList<>();
        bunkDistance = new ArrayList<>();
        bunkContact = new ArrayList<>();

        //code to fetch data from database

    }


    // Intent passing to OrderDetails Activity with values
    @Override
    public void onItemClick(int position) {

        Intent intent = new Intent(OrderFuel.this, OrderDetails.class);
        intent.putExtra("bunkName",fuel.get(position).getBunkName());
        intent.putExtra("fuelType", fuel.get(position).getFuelType());
        intent.putExtra("fuelPrice", fuel.get(position).getPrice());
        intent.putExtra("bunkContact", fuel.get(position).getBunkContact());

        startActivity(intent);

    }
}