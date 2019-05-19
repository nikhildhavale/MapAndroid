package com.example.nikhilvivekdhvale.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.nikhilvivekdhvale.Trip;

import java.util.ArrayList;


public class ListOfTrips extends AppCompatActivity implements View.OnClickListener , AdapterView.OnItemClickListener {
    FloatingActionButton floatingButton;
    ListView tripList;
    ArrayList<Trip> tripArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_trips);
        floatingButton = findViewById(R.id.addTrip);
        floatingButton.setOnClickListener(this);
        tripList = findViewById(R.id.tripList);
        tripList.setOnItemClickListener(this);


    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Trip trip = tripArrayList.get(i);
        Intent intent = new Intent(this,MapsActivity.class);


        intent.putExtra(getResources().getString(R.string.TripSelected),trip);
        startActivity(intent);

    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(this,AddTripDetails.class);
        startActivity(intent);

    }
}
