package com.example.nikhilvivekdhvale;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.example.nikhilvivekdhvale.model.Trip;

public class TrackTripDetails extends Service {
    Trip trip = new Trip();
    public TrackTripDetails() {

    }

    @Override
    public IBinder onBind(Intent intent) {

        return  null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

}
