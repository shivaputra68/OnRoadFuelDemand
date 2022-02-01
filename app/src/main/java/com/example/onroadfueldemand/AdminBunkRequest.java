package com.example.onroadfueldemand;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import java.util.ArrayList;
import java.util.List;

import Interfaces.OrderFuelRecyclerClickListner;
import operations.AdminBunkAdapter;
import operations.AdminBunkVerify;

public class AdminBunkRequest extends AppCompatActivity implements OrderFuelRecyclerClickListner {

    ArrayList<AdminBunkVerify> adminOrderVerifies = new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_bunk_request);

        Intent i = getIntent();
        recyclerView = findViewById(R.id.adminRecyclerView);
        setBunkRequest();
        OrderFuelRecyclerClickListner recyclerClickListner;
        AdminBunkAdapter adapter = new AdminBunkAdapter(this, adminOrderVerifies, this);
        System.out.println("****************************************************");
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setBunkRequest() {

        ParseQuery<ParseUser> obj = ParseUser.getQuery();
        obj.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                for(ParseUser object : objects){
                    System.out.println(object.getUsername());
                }
                AdminBunkAdapter adapter = new AdminBunkAdapter(this, adminOrderVerifies, this);
                System.out.println("****************************************************");
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
            }
        });

    }

    @Override
    public void onItemClick(int position) {

        ParseObject object = new ParseObject("Fuel");
        object.put("bunk_name", adminOrderVerifies.get(position).getBunkName());
        object.put("owner", adminOrderVerifies.get(position).getOwnerName());
        object.put("contact", adminOrderVerifies.get(position).getBunkContact());
        object.put("address", adminOrderVerifies.get(position).getBunkAddress());
        object.put("status", adminOrderVerifies.get(position).getStatus());
        object.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null){
                    Toast.makeText(AdminBunkRequest.this, "Status Updated", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Status not updated", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}