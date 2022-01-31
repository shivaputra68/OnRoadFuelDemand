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

import java.util.ArrayList;

import Interfaces.OrderFuelRecyclerClickListner;
import operations.Fuel;
import operations.OrderAdapter;

public class OrderFuel extends AppCompatActivity implements OrderFuelRecyclerClickListner, LocationListener {

    ArrayList<Fuel> fuel = new ArrayList<>();
    RecyclerView recyclerView;
    ArrayList<String> bunkName, fuelType, fuelPrice, bunkDistance, bunkContact;
    LocationManager locationManager;
    double latitude,longitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_fuel);

        recyclerView = findViewById(R.id.recyclerView);
        setFuelStations();
        OrderAdapter adapter = new OrderAdapter(this, fuel, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
        }
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED)
            {
                getLocation();

                System.out.println(latitude+" on create"+longitude);
        }


    }

    private double getDistance(double lat1, double lat2, double lon1, double lon2)
    {
        // The math module contains a function
        // named toRadians which converts from
        // degrees to radians.
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

        // Radius of earth in kilometers. Use 3956
        // for miles
        double r = 6371;

        // calculate the result
        return(c * r);
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

    @Override
    public void onLocationChanged(@NonNull Location location) {
        Toast.makeText(this, ""+location.getLatitude()+","+location.getLongitude(), Toast.LENGTH_SHORT).show();
        latitude=location.getLatitude();
        longitude=location.getLongitude();
        System.out.println(latitude+"on location changed "+longitude);
        System.out.println(getDistance(latitude,12.952104,longitude, 77.573527));



    }

    @SuppressLint("MissingPermission")
    private void getLocation() {

        try {
            locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,5,this);
            Log.d(TAG, "getLocation: hello "+locationManager);
            System.out.println(latitude+" getlocation"+longitude);


        }catch (Exception e){
            e.printStackTrace();
        }

    }
}