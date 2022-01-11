package com.example.onroadfueldemand;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import operations.Fuel;
import operations.OrderAdapter;

public class OrderFuel extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Fuel> fuelDetails;
    OrderAdapter adapter;
    String[] bunkName,fuelType,price,distance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_fuel);

        Intent i = getIntent();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        fuelDetails = new ArrayList<>();
        adapter = new OrderAdapter(this,fuelDetails);
        recyclerView.setAdapter(adapter);

        bunkName = new String[]{
                "Indian Oil",
                "HP GAS",
                "Reliance","Indian Oil","HP Gas","Oil India",
                "Reliance","Indian Oil","HP Gas","Oil India",
                "Reliance","Indian Oil","HP Gas","Oil India"
        };

        fuelType = new String[]{
                "Petrol",
                "Diesel",
                "Petrol","Gas","Diesel","GAS",
                "Petrol","Gas","Diesel","GAS",
                "Petrol","Gas","Diesel","GAS"
        };

        price = new String[]{
                "100",
                "200",
                "100","200","100","1000",
                "100","200","100","1000",
                "100","200","100","1000"
        };

        distance = new String[]{
                "12",
                "13",
                "12", "20","10","20",
                "12", "20","10","20",
                "12", "20","10","20"
        };
        getData();
    }



    private void getData() {

        for(int i=0;i<bunkName.length;i++){
            Fuel fuel = new Fuel(bunkName[i],fuelType[i],price[i],distance[i] );
            fuelDetails.add(fuel);
        }
        adapter.notifyDataSetChanged();
    }
}