package com.example.nikhilvivekdhvale.thread;

import android.os.AsyncTask;

import com.example.nikhilvivekdhvale.model.ModesOfTravel;
import com.example.nikhilvivekdhvale.model.Trip;
import com.example.nikhilvivekdhvale.myapplication.MapsActivity;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RouterTask extends AsyncTask<String,Integer, List<List<HashMap>>> {
    MapsActivity mapsActivity;
    Trip trip;

    public RouterTask(MapsActivity mapsActivity, Trip trip) {
        this.mapsActivity = mapsActivity;
        this.trip = trip;
    }
    private  String downloadURL(String urlString) throws IOException

    {
        String data = "";
        InputStream inputStream = null;
        HttpURLConnection urlConnection = null;
        try{
            URL url = new URL(urlString);
            urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.connect();
            inputStream = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer stringBuffer = new StringBuffer();
            String line = "";
            while ((line = br.readLine()) != null){
                stringBuffer.append(line);
                br.close();
            }
            data = stringBuffer.toString();
        }
        catch (Exception e){

        }
        finally {
            inputStream.close();
            urlConnection.disconnect();
        }
    }
    public static  String  getDirectionsURL(Trip trip){
        String startTrip = "origin="+trip.getStartTripCoodinates().latitude + "," + trip.getStartTripCoodinates().longitude ;
        String endTrip = "destination="+ trip.getEndTripCoodinates().latitude + "," + trip.getEndTripCoodinates().longitude;
        String sensor = "sensor=false";
        String  mode = "mode=walking";

        if(trip.getModesOfTravel().equalsIgnoreCase(ModesOfTravel.DRIVING)){
            mode = "mode=driving";
        }
        String parameters = startTrip + "&" + endTrip + "&" + sensor + "&" + mode;
        String output = "json";
        String url = "https://maps.googleapis.com/maps/api/directions" + output + "?" + parameters;
        return  url;
    }
    @Override
    protected List<List<HashMap>> doInBackground(String... strings) {

//        List<List<HashMap>> routes = new ArrayList<>();
//        JSONObject jsonObject;
        String data = "";
        try{
             data = downloadURL(strings[0]);
        }
        catch (Exception e){

        }
        return null;
    }
    public List<List<HashMap>> parseData(){
        return null;
    }
}
