package com.example.onroadfueldemand;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
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
import operations.Fuel;
import operations.OrderAdapter;
import operations.OrderViewAdapter;
import operations.ViewOrder;

public class OrderFuel extends AppCompatActivity implements OrderFuelRecyclerClickListner {

    ArrayList<Fuel> fuel = new ArrayList<>();
    RecyclerView recyclerView;
    ArrayList<String> bunkName, fuelType, petrol_price,diesel_price, bunkDistance, bunkContact;

    double latitude,longitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_fuel);

        recyclerView = findViewById(R.id.recyclerView);


        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
        }
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED)
            {

        }
            Intent intent=getIntent();
            latitude=intent.getDoubleExtra("latitude", 0.0);
            longitude=intent.getDoubleExtra("longitude", 0.0);
            //System.out.println(latitude+" and "+longitude);
            //System.out.println(getDistance(latitude, 13.01865337705787, longitude, 77.49290078248673));
            addValues();
    }

    private double getDistance(double lat1, double lat2, double lon1, double lon2)
    {

        lon1 = Math.toRadians(lon1);
        lon2 = Math.toRadians(lon2);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        // Haversine formula
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2),2);

        double c = 2 * Math.asin(Math.sqrt(a));


        double r = 3956;

        return(c * r);
    }



    //Values adding to the bean class Fuel
    private void setFuelStations(){

        for (int i = 0; i < bunkName.size(); i++) {
            fuel.add(new Fuel(bunkName.get(i),fuelType.get(i),petrol_price.get(i),diesel_price.get(i),bunkDistance.get(i).substring(0, 5),bunkContact.get(i)));
            //fuel.add( new Fuel("HP GAS","petrol","80","56","15","29878979"));
            System.out.println("bunk distance "+bunkDistance.get(i).substring(0, 5));

        }
        OrderAdapter adapter = new OrderAdapter(this, fuel, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        /*fuel.add( new Fuel("Indian Oil","petrol","70","10","12345"));

        fuel.add( new Fuel("Indian Oil","petrol","70","10","154896"));
        fuel.add( new Fuel("HP GAS","petrol","80","18","125436"));
        fuel.add( new Fuel("Indian Oil","petrol","70","10","584967"));
        fuel.add( new Fuel("HP GAS","petrol","80","18","15846"));
        fuel.add( new Fuel("Indian Oil","petrol","70","10","154896"));
        fuel.add( new Fuel("HP GAS","petrol","80","18","225846"));*/
    }

    //Values fetching from the database and adding to the Strings of array
    private void addValues(){
        bunkName = new ArrayList<>();
        fuelType = new ArrayList<>();
        petrol_price = new ArrayList<>();
        diesel_price=new ArrayList<>();
        bunkDistance = new ArrayList<>();
        bunkContact = new ArrayList<>();

        //code to fetch data from database
        System.out.println("fetching the data");
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Fuel");
        query.findInBackground(((objects, e) -> {
            if(e == null){
                for(int i=0;i<objects.size();i++){
                    //calculating the distance by fetching from database
                    //System.out.println(getDistance(latitude, Double.parseDouble(objects.get(i).get("latitude").toString()), longitude, Double.parseDouble(objects.get(i).get("longitude").toString()) ));
                    double d=getDistance(latitude, Double.parseDouble(objects.get(i).get("latitude").toString()), longitude, Double.parseDouble(objects.get(i).get("longitude").toString()) );

                       System.out.println(objects.get(i).get("latitude") + " and " + objects.get(i).get("longitude"));
                       bunkName.add(objects.get(i).get("bunk_name").toString());
                       bunkDistance.add(Double.toString(d));
                       bunkContact.add(objects.get(i).get("contact").toString());
                       petrol_price.add(objects.get(i).get("petrol_price").toString());
                       diesel_price.add(objects.get(i).get("diesel_price").toString());
                       fuelType.add(objects.get(i).get("fuel_type").toString());
                        //System.out.println(objects.get(i).get("fuel_type").toString());
                        System.out.println(Double.toString(d));

                }

            }
            else
            {
                System.out.println(e.getMessage());
            }

            setFuelStations();
        }));

    }


    // Intent passing to OrderDetails Activity with values
    @Override
    public void onItemClick(int position) {

        Intent intent = new Intent(OrderFuel.this, OrderDetails.class);
        intent.putExtra("bunkName",fuel.get(position).getBunkName());
        intent.putExtra("fuelType", fuel.get(position).getFuelType());
        intent.putExtra("petrol_price", fuel.get(position).getPetrol_price());
        intent.putExtra("diesel_price",fuel.get(position).getDiesel_price());
        intent.putExtra("bunkContact", fuel.get(position).getBunkContact());

        startActivity(intent);

    }


}