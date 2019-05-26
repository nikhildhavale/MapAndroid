package com.example.nikhilvivekdhvale.listadapter;

import android.content.Context;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.nikhilvivekdhvale.model.Trip;
import com.example.nikhilvivekdhvale.myapplication.R;

import java.util.ArrayList;

public class TripsSavedListAdapter extends BaseAdapter {
    private ArrayList<Trip> tripArrayList;
    private Context context;

    public TripsSavedListAdapter(ArrayList<Trip> tripArrayList, Context context) {
        this.tripArrayList = tripArrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return tripArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return tripArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView title;
        Trip trip = tripArrayList.get(i);
        if (view == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.trip_list_item,viewGroup,false);


        }
        TextView startAddress = view.findViewById(R.id.startAddress);
        TextView endAddress = view.findViewById(R.id.endAddres);
        TextView modesOfTransport = view.findViewById(R.id.modeOfTransport);
        TextView distance = view.findViewById(R.id.distance);
        String startAddressString = context.getResources().getString(R.string.Start) + trip.getStartAddressName();
        startAddress.setText(startAddressString);
        String endAddressString =  context.getResources().getString(R.string.End) + trip.getEndAddressName();
        endAddress.setText(endAddressString);
        float[] results = new float[1];
        Location.distanceBetween(trip.getStartTripCoodinates().latitude,trip.getStartTripCoodinates().longitude,trip.getEndTripCoodinates().latitude,trip.getEndTripCoodinates().longitude,results);
                /// Distance is not travelling distance but distance between two coorindates. Setting of directions api will solve this problem.
                String distanceString = "Distance:"+ results[0]/1000 + "km";
                distance.setText(distanceString);




        modesOfTransport.setText(trip.getModesOfTravel());
        return view;
    }
}
