package com.example.onroadfueldemand;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.parse.FindCallback;
import com.parse.GetCallback;
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

    }

    private void setBunkRequest() {

        ParseQuery<ParseUser> obj = ParseUser.getQuery();
        obj.whereNotEqualTo("latitude", null);
        obj.whereNotEqualTo("longitude", null);
        obj.whereNotEqualTo("status", "Pending");
        obj.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                if (!objects.isEmpty() && e == null) {
                    for (ParseUser object : objects) {

                            adminOrderVerifies.add(new AdminBunkVerify(object.get("name").toString(), object.getUsername(), object.get("contact").toString(),
                                    object.get("address").toString(), object.get("status").toString(), object.get("latitude").toString(), object.get("longitude").toString()));
                        }
                    setAdapter();
                }else{
                    AlertDialog.Builder alert = new AlertDialog.Builder(AdminBunkRequest.this);
                    alert.setMessage("No Requests Found");
                    alert.setTitle("Alert Message");
                    alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(AdminBunkRequest.this, AdminMain.class);
                            startActivity(intent);
                        }
                    });
                    alert.show();
                }
            }
        });
    }

    public void setAdapter() {
        AdminBunkAdapter adapter = new AdminBunkAdapter(AdminBunkRequest.this, adminOrderVerifies, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(AdminBunkRequest.this));
    }

    @Override
    public void onItemClick(int position) {

        ParseObject object = new ParseObject("Fuel");
        object.put("bunk_name", adminOrderVerifies.get(position).getBunkName());
        object.put("owner", adminOrderVerifies.get(position).getOwnerName());
        object.put("contact", adminOrderVerifies.get(position).getBunkContact());
        object.put("address", adminOrderVerifies.get(position).getBunkAddress());
        object.put("status", adminOrderVerifies.get(position).getStatus());
        object.put("latitude", adminOrderVerifies.get(position).getLatitude());
        object.put("longitude", adminOrderVerifies.get(position).getLongitude());
        object.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null){
                    Toast.makeText(AdminBunkRequest.this, "Status Updated", Toast.LENGTH_SHORT).show();
                    ParseQuery<ParseUser> query = ParseUser.getQuery();
                    query.whereEqualTo("name", adminOrderVerifies.get(position).getBunkName() );
                    query.findInBackground(((objects, e1) -> {
                        String objectId = "";
                        for(int i=0;i<objects.size();i++){
                            objectId=objects.get(i).getObjectId();
                        }
                        ParseQuery<ParseUser> query1 = ParseUser.getQuery();
                        query1.getInBackground(objectId, new GetCallback<ParseUser>() {
                            @Override
                            public void done(ParseUser object, ParseException e) {
                                object.put("status", adminOrderVerifies.get(position).getStatus());
                                object.saveInBackground(new SaveCallback() {
                                    @Override
                                    public void done(ParseException e) {
                                        System.out.println("************"+adminOrderVerifies.get(position).getStatus());
                                    }
                                });
                            }
                        });
                    }));
                }else{
                    Toast.makeText(getApplicationContext(), "Status not updated"+e, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}