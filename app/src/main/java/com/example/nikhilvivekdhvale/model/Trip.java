package com.example.nikhilvivekdhvale.model;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;


/** This class is model to represent trip details*
 *
 */
public class Trip implements Serializable {
        private  String startAddressName;
        private  String endAddressName;
        private double startLatitude;
        private double startLongitude;
        private double endLatitude;
        private double endLongitude;
        private String modesOfTravel;

    public String getStartAddressName() {
        return startAddressName;
    }

    public void setStartAddressName(String startAddressName) {
        this.startAddressName = startAddressName;
    }

    public String getEndAddressName() {
        return endAddressName;
    }

    public void setEndAddressName(String endAddressName) {
        this.endAddressName = endAddressName;
    }

    public String getModesOfTravel() {
        return modesOfTravel;
         }

        public void setModesOfTravel(String modesOfTravel) {
        this.modesOfTravel = modesOfTravel;
        }

        public Trip() {
            endLatitude = Double.NaN;
            endLongitude = Double.NaN;
            startLongitude = Double.NaN;
            startLatitude = Double.NaN;
        }



        public LatLng getStartTripCoodinates() {
            return new LatLng(startLatitude,startLongitude);
        }

        public LatLng getEndTripCoodinates() {
            return new LatLng(endLatitude,endLongitude);
        }



        public void setStartTripCoodinates(LatLng startTripCoodinates) {
            this.startLatitude = startTripCoodinates.latitude;
            this.startLongitude = startTripCoodinates.longitude;
        }

        public void setEndTripCoodinates(LatLng endTripCoodinates) {
            this.endLatitude = endTripCoodinates.latitude;
            this.endLongitude = endTripCoodinates.longitude;
        }
        public boolean isTripDataCompleted(){
            return startLatitude != Double.NaN && startLongitude != Double.NaN && endLatitude != Double.NaN && endLongitude != Double.NaN && startAddressName != null && endAddressName != null && modesOfTravel !=null;
        }

    }

