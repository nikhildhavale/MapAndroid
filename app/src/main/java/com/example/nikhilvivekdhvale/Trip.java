package com.example.nikhilvivekdhvale;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

/** This class is model to represent trip details*/
public class Trip implements Serializable {
    private  String startTripName;
    private  String endTripName;
    private LatLng startTripCoodinates;
    private LatLng endTripCoodinates;

    public Trip() {
    }

    public String getStartTripName() {
        return startTripName;
    }

    public String getEndTripName() {
        return endTripName;
    }

    public LatLng getStartTripCoodinates() {
        return startTripCoodinates;
    }

    public LatLng getEndTripCoodinates() {
        return endTripCoodinates;
    }

    public void setStartTripName(String startTripName) {
        this.startTripName = startTripName;
    }

    public void setEndTripName(String endTripName) {
        this.endTripName = endTripName;
    }

    public void setStartTripCoodinates(LatLng startTripCoodinates) {
        this.startTripCoodinates = startTripCoodinates;
    }

    public void setEndTripCoodinates(LatLng endTripCoodinates) {
        this.endTripCoodinates = endTripCoodinates;
    }
    public boolean isTripDataCompleted(){
        return startTripCoodinates != null && endTripCoodinates != null && startTripName != null && endTripName != null;
    }

}
