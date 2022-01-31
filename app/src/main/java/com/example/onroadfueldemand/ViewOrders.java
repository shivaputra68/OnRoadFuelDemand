package com.example.onroadfueldemand;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import java.util.ArrayList;
import operations.OrderViewAdapter;
import operations.ViewOrder;

public class ViewOrders extends AppCompatActivity {

    ArrayList<ViewOrder> orderHistory = new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_orders);

        Intent i = getIntent();
        recyclerView = findViewById(R.id.recyclerView_viewOrder);
        setViewOrders();

    }

    private void setViewOrders() {

        ParseQuery<ParseObject> obj = ParseQuery.getQuery("order");
        obj.findInBackground(((objects, e) -> {
            for(int i=0;i< objects.size();i++){
                System.out.println("*******************"+objects.get(i).get("bunk_name").toString()+"****************");
                orderHistory.add(new ViewOrder(objects.get(i).get("bunk_name").toString(), objects.get(i).get("fuel_type").toString(),
                        objects.get(i).getObjectId().toString(), objects.get(i).get("total_amount").toString(),objects.get(i).get("status").toString(),
                        objects.get(i).get("date").toString(),objects.get(i).get("quantity").toString()));
            }

            OrderViewAdapter adapter = new OrderViewAdapter(this, orderHistory);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }));
    }
}