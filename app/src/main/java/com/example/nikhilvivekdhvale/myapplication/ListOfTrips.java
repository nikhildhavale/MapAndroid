package com.example.nikhilvivekdhvale.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.nikhilvivekdhvale.listadapter.TripsSavedListAdapter;
import com.example.nikhilvivekdhvale.model.ModesOfTravel;
import com.example.nikhilvivekdhvale.model.Trip;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;


public class ListOfTrips extends AppCompatActivity implements View.OnClickListener , AdapterView.OnItemClickListener {
    FloatingActionButton floatingButton;
    ListView tripListView;
    ArrayList<Trip> tripArrayList = new  ArrayList<Trip>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_trips);
        floatingButton = findViewById(R.id.addTrip);
        floatingButton.setOnClickListener(this);
        tripListView = findViewById(R.id.tripList);
        tripListView.setOnItemClickListener(this);
        addSampleTrip();

    }
    void addSampleTrip(){
        Trip trip = new Trip();
        trip.setModesOfTravel(ModesOfTravel.DRIVING);
        trip.setStartAddressName("Pune, Maharashtra, India");
        trip.setStartTripCoodinates(new LatLng(18.520430299999997,72.8776559));
        trip.setEndAddressName("Mumbai, Maharashtra, India");
        trip.setEndTripCoodinates(new LatLng(19.0759837,73.8567437));
        addTripInList(trip);
        trip = new Trip();
        trip.setModesOfTravel(ModesOfTravel.WALKING);
        trip.setStartAddressName("Pune, Maharashtra, India");
        trip.setStartTripCoodinates(new LatLng(18.520430299999997,72.8776559));
        trip.setEndAddressName("Mumbai, Maharashtra, India");
        trip.setEndTripCoodinates(new LatLng(19.0759837,73.8567437));
        addTripInList(trip);
    }
    void addTripInList(Trip trip){
        tripArrayList.add(trip);
        tripListView.setAdapter(new TripsSavedListAdapter(tripArrayList,this));

    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Trip trip = tripArrayList.get(i);
        Intent intent = new Intent(this,MapsActivity.class);


        intent.putExtra(getResources().getString(R.string.TripSelected),trip);
        startActivity(intent);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    //    super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(data != null){
               Trip trip = (Trip) data.getSerializableExtra(getResources().getString(R.string.tripDataSaved));
                addTripInList(trip);

            }
        }

    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(this,AddTripDetails.class);
        startActivityForResult(intent,RESULT_OK);
    }

}
