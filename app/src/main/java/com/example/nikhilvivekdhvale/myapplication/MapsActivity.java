package com.example.nikhilvivekdhvale.myapplication;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.example.nikhilvivekdhvale.model.ModesOfTravel;
import com.example.nikhilvivekdhvale.model.Trip;
import com.example.nikhilvivekdhvale.thread.RouterTask;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Dot;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Arrays;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Trip trip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        trip = (Trip) getIntent().getSerializableExtra(getResources().getString(R.string.TripSelected));

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
        mMap.addMarker(new MarkerOptions().position(trip.getStartTripCoodinates()).title(trip.getStartAddressName()));
        mMap.addMarker(new MarkerOptions().position(trip.getEndTripCoodinates()).title(trip.getEndAddressName()));

//        PolylineOptions polylineOptions = new PolylineOptions();
//        polylineOptions.add(trip.getStartTripCoodinates());
//        polylineOptions.add(trip.getEndTripCoodinates());
//        if(trip.getModesOfTravel().equalsIgnoreCase(ModesOfTravel.WALKING)){
//            polylineOptions.pattern(Arrays.<PatternItem>asList(new Dot()));
//        }
//        googleMap.addPolyline(polylineOptions);

        LatLngBounds bounds = new LatLngBounds(trip.getStartTripCoodinates(),trip.getEndTripCoodinates());
        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds,0));
        RouterTask routerTask = new RouterTask(this,trip);
        routerTask.execute(RouterTask.getDirectionsURL(trip));
    }
}
