package com.example.tourmate.mostafijur.tourmate;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.google.android.gms.location.FusedLocationProviderClient;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeviceLocationActivity extends AppCompatActivity {

    private static final int REQUEST_LOCATION_CODE = 1;
    private FusedLocationProviderClient client;
    private TextView locTV;
    private Geocoder geocoder;
    private List<Address> addresses = new ArrayList<>();

   /* private LocationRequest locationRequest;*/
    /*private LocationCallback callback = new LocationCallback(){
        @Override
        public void onLocationResult(LocationResult locationResult) {
            for(Location location : locationResult.getLocations()){
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                locTV.setText(String.valueOf(latitude)+", "+
                        String.valueOf(longitude));
            }
        }
    };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_location);

        locTV = findViewById(R.id.lastLocTV);
        geocoder = new Geocoder(this);

        client = LocationServices.getFusedLocationProviderClient(this);
        if (checkLocationPermission()){
            getDeviceLastLocation();
        }
    }

    private boolean checkLocationPermission() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_CODE);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_LOCATION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getDeviceLastLocation();
//               getLocationUpdates();
            }
        }
    }

    /*private LocationRequest getLocationRequest(){
        locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(2000);
        locationRequest.setFastestInterval(1000);
        return locationRequest;
    }*/
    private void getDeviceLastLocation() {
        if (checkLocationPermission()) {
            client.getLastLocation()
                    .addOnSuccessListener(new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location == null) {
                                return;
                            }
                            double latitude = location.getLatitude();
                            double longitude = location.getLongitude();
                            try {
                                addresses = geocoder.getFromLocation(latitude, longitude, 1);
                                String addressline = addresses.get(0).getAddressLine(0);
                                String city = addresses.get(0).getLocality();
                                String zip = addresses.get(0).getPostalCode();
                                String country = addresses.get(0).getCountryName();
                                locTV.setText(addressline + "\n" + city + " - " + zip + "\n" + country);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                    /*locTV.setText(String.valueOf(latitude)+", "+
                    String.valueOf(longitude));*/
                        }
                    });
        }

}


    /*private void getLocationUpdates(){
        if(checkLocationPermission()){
            client.requestLocationUpdates(getLocationRequest(), callback,null);
        }
    }*/

    /*@Override
    protected void onResume() {
        super.onResume();
        if(checkLocationPermission()){
            getDeviceLastLocation();
            getLocationUpdates();
        }
    }*/

    /*@Override
    protected void onStop() {
        super.onStop();
        client.removeLocationUpdates(callback);
    }*/


    }
