package com.example.onroadfueldemand;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.onroadfueldemand.databinding.ActivityRegisterMapBinding;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class Register_map extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityRegisterMapBinding binding;
    String cordinates;
    Button btn_map_confirm;
    Marker m;
    String name,address,contact,email,username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btn_map_confirm=findViewById(R.id.btn_map_confirm);
        binding = ActivityRegisterMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        if(ContextCompat.checkSelfPermission(Register_map.this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(Register_map.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
        }


        //intent from map to register page
        Intent intent = getIntent();
         name=intent.getStringExtra("name");
         address=intent.getStringExtra("address");
         contact= intent.getStringExtra("contact");
         email=intent.getStringExtra("email");
         username=intent.getStringExtra("username");
         password=intent.getStringExtra("password");



    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng bengaluru = new LatLng(12.9716, 77.5946);
        //mMap.addMarker(new MarkerOptions().position(bengaluru).title("Marker in india"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(bengaluru));
        mMap.setMinZoomPreference(10);





        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                //Log.d(TAG, "onMapLongClick: "+latLng.toString());
                if (m!=null) {
                    m.remove();
                }
               m= mMap.addMarker(new MarkerOptions().position(latLng).draggable(true));
                cordinates=latLng.toString();



            }
        });





    }

    public void register (View view)
    {
        if(view.getId()==R.id.btn_map_confirm)
        {
            ParseUser user = new ParseUser();
            user.setUsername(username);
            user.put("name",name);
            user.put("contact",contact);
            user.put("address",address);
            user.put("location", cordinates);
            user.setPassword(password);
            user.setEmail(email);
            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        Toast.makeText(getApplicationContext(), "Successful Sign Up!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),Login.class);
                        intent.putExtra("key", "bunk");

                        startActivity(intent);
                    } else {
                        ParseUser.logOut();
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(),Login.class);
                        intent.putExtra("key", "bunk");
                        startActivity(intent);

                    }
                }
            });
        }
    }
}

