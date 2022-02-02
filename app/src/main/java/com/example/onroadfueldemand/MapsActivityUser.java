package com.example.onroadfueldemand;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.onroadfueldemand.databinding.ActivityMapsUserBinding;

public class MapsActivityUser extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsUserBinding binding;
    Marker m;
    double latitude,longitude;
    Button location;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //to get the permission
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
        }

        //type casting
        location=findViewById(R.id.userlocation);

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
                //cordinates=latLng.toString();
                latitude=latLng.latitude;
                longitude=latLng.longitude;



            }
        });
    }

    public void getLocation(View view)
    {
        if(view.getId()==R.id.userlocation)
        {
            Intent intent= new Intent(this,OrderFuel.class);
            intent.putExtra("latitude", latitude);
            intent.putExtra("longitude",longitude);
            startActivity(intent);
        }
    }
}