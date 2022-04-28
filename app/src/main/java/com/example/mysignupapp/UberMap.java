package com.example.mysignupapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.location.ActivityRecognitionClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.Locale;

public class UberMap extends AppCompatActivity
{
    //initialize variables
    FusedLocationProviderClient client;
    SupportMapFragment mapFragment;

    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uber_map);

        //assign variables
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        //initialize FLP
        client = LocationServices.getFusedLocationProviderClient(this);

        //check permissions
        if (ActivityCompat.checkSelfPermission(UberMap.this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED) {

            //When permission granted
            //call method
            getCurrentLocation();

        }


        
    }

    private void getCurrentLocation()
    {

        //Initialize task

        @SuppressLint("MissingPermission")
        Task<Location> task = client.getLastLocation();

        task.addOnSuccessListener(new OnSuccessListener<Location>()
        {
            @Override
            public void onSuccess(Location location)
            {

                if (location != null)
                {
                    //sync map

                    mapFragment.getMapAsync(new OnMapReadyCallback()
                    {
                        @Override
                        public void onMapReady(@NonNull GoogleMap googleMap)
                        {
                            //Initialize latlng
                            LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());
                            //create marker option
                            MarkerOptions options = new  MarkerOptions().position(latLng).title("Find me here");
                            //zoom map
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                            //add marker option on map
                            googleMap.addMarker(options);


                        }
                    });
                }

            }
        });



    }


}