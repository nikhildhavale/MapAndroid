package com.example.nikhilvivekdhvale.listadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.nikhilvivekdhvale.model.Trip;
import com.example.nikhilvivekdhvale.myapplication.R;

import java.util.ArrayList;

public class TripsSavedListAdapter extends BaseAdapter {
    ArrayList<Trip> tripArrayList;
    Context context;

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
        TextView startAddress = (TextView)view.findViewById(R.id.startAddress);
        TextView endAddress = (TextView)view.findViewById(R.id.endAddres);
        TextView modesOfTransport = (TextView)view.findViewById(R.id.modeOfTransport);
        startAddress.setText(context.getResources().getString(R.string.Start) + trip.getStartAddressName());
        endAddress.setText(context.getResources().getString(R.string.End) + trip.getEndAddressName());
        modesOfTransport.setText(trip.getModesOfTravel());
        return view;
    }
}
