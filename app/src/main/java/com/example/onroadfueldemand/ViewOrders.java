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

    RecyclerView recyclerView_viewOrder;
    ArrayList<ViewOrder> orderHistory;
    OrderViewAdapter adapter;
    String[] bunkName,fuelType,price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_orders);

        Intent i = getIntent();

        recyclerView_viewOrder = findViewById(R.id.recyclerView_viewOrder);
        recyclerView_viewOrder.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_viewOrder.setHasFixedSize(true);

        orderHistory = new ArrayList<ViewOrder>();
        adapter = new OrderViewAdapter(this,orderHistory);
        recyclerView_viewOrder.setAdapter(adapter);

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
        getData();

    }

    private void getData() {

        for(int i=0;i<bunkName.length;i++){
            ViewOrder history = new ViewOrder(bunkName[i],fuelType[i],"hi",price[i],"pending","22-10-2022" );
            orderHistory.add(history);
        }
        adapter.notifyDataSetChanged();
    }
}