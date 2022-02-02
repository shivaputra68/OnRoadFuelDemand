package com.example.onroadfueldemand;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
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
        bunkOrder.add(new BunkOrder("1234","shiva1999","8884859355","petrol","bidar","pending",
                "2","100"));
        bunkOrder.add(new BunkOrder("1234","shiva1999","8884859355","petrol","bidar","pending",
                "2","100"));
        BunkOrderAdapter adapter = new BunkOrderAdapter(this, bunkOrder, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void setOrderDetails() {

        ParseUser user = ParseUser.getCurrentUser();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("order");
        query.whereEqualTo("bunk_name", user.get("name").toString());
        System.out.println("printing bunk name from user table");
        System.out.println(user.get("name"));
        query.whereNotEqualTo("status", "Delivered");
        query.whereEqualTo("bunk_contact", user.get("contact").toString());
        query.findInBackground(((objects, e) -> {
            if(e == null){
                for(int i=0;i<objects.size();i++){
                    bunkOrder.add(new BunkOrder(objects.get(i).getObjectId(), objects.get(i).get("customer_name").toString(),
                            objects.get(i).get("customer_contact").toString(), objects.get(i).get("fuel_type").toString(),
                            objects.get(i).get("address").toString(), objects.get(i).get("status").toString(), objects.get(i).get("quantity").toString(),
                            objects.get(i).get("total_amount").toString()));
                }
                BunkOrderAdapter adapter = new BunkOrderAdapter(this, bunkOrder, this);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
            }
        }));

    }

    @Override
    public void onItemClick(int position) {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("order");
        query.whereEqualTo("objectID", bunkOrder.get(position).getOrderId());
        query.findInBackground(((objects, e) -> {
            String objectId = "";
            for(int i=0;i<objects.size();i++) {
                objectId = objects.get(i).getObjectId();
            }
            ParseQuery<ParseObject> obj = ParseQuery.getQuery("order");
            obj.getInBackground(objectId, new GetCallback<ParseObject>() {
                @Override
                public void done(ParseObject object, ParseException e) {
                    if (e == null) {
                        object.put("status", bunkOrder.get(position).getBunkStatus());
                        Toast.makeText(BunkOrderRequest.this, "Status updated", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(BunkOrderRequest.this, "Status updating failed"+e, Toast.LENGTH_LONG).show();
                    }
                }
            });
        }));
    }
}