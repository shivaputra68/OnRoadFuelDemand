package com.example.onroadfueldemand;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;

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
        //adminOrderVerifies.add(new AdminBunkVerify("sindol","sindol group", "7353483019", "A, Circle Bidar","Pending"));
        AdminBunkAdapter adapter = new AdminBunkAdapter(this, adminOrderVerifies, this);
        System.out.println("****************************************************");
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setBunkRequest() {

       /* adminOrderVerifies.add(new AdminBunkVerify("sindol","sindol group", "7353483019", "A, Circle Bidar","Pending"));
        adminOrderVerifies.add(new AdminBunkVerify("sindol","sindol group", "7353483019", "A, Circle Bidar","Pending"));
        adminOrderVerifies.add(new AdminBunkVerify("sindol","sindol group", "7353483019", "A, Circle Bidar","Pending"));
        AdminBunkAdapter adapter = new AdminBunkAdapter(this, adminOrderVerifies, this);
        System.out.println("****************************************************");
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));*/
        //ParseQuery<ParseUser> query = ParseUser.getQuery();
        //ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("user");
        //ParseUser.getQuery().whereNotEqualTo("latitude", null);

        ParseUser.getQuery().findInBackground(((objects, e) -> {
            if( e == null){
                for(int i=0;i<objects.size();i++){
                    adminOrderVerifies.add(new AdminBunkVerify(objects.get(i).getUsername(), objects.get(i).get("name").toString(),
                            objects.get(i).get("contact").toString(),objects.get(i).get("address").toString(),"Pending"));
                }
                AdminBunkAdapter adapter = new AdminBunkAdapter(this, adminOrderVerifies, this);
                System.out.println("****************************************************");
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
            }else{

            }
        }));



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