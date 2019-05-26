package com.example.nikhilvivekdhvale.thread;

import android.graphics.Color;
import android.os.AsyncTask;

import com.example.nikhilvivekdhvale.model.ModesOfTravel;
import com.example.nikhilvivekdhvale.model.Trip;
import com.example.nikhilvivekdhvale.myapplication.MapsActivity;
import com.google.android.gms.maps.model.Dot;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class RouterTask extends AsyncTask<String,Integer, ArrayList<List<HashMap<String ,String >>>>  {
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
            }
            br.close();

            data = stringBuffer.toString();
        }
        catch (Exception e){
                System.out.println(e);
        }
        finally {
            inputStream.close();
            urlConnection.disconnect();
        }
        return data;
    }
    public static  String  getDirectionsURL(Trip trip){
        String startTrip = "origin="+trip.getStartTripCoodinates().latitude + "," + trip.getStartTripCoodinates().longitude ;
        String endTrip = "destination="+ trip.getEndTripCoodinates().latitude + "," + trip.getEndTripCoodinates().longitude;
        String sensor = "sensor=false";
        String  mode = "mode=" + trip.getModesOfTravel();

        String key = "key=AIzaSyDlF__bqunVgpc7_WY8v-Y_MnbNAFc8PRc";
        String parameters = startTrip + "&" + endTrip + "&" + sensor + "&" + mode + "&" + key ;
        String output = "json";
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;
        return  url;
    }
    @Override
    protected ArrayList<List<HashMap<String ,String >>>  doInBackground(String... strings) {

        JSONObject jsonObject;
        String data = "";
        try{
             data = downloadURL(strings[0]);
          return parseDirections(data);

        }
        catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
    private ArrayList<List<HashMap<String ,String >>> parseDirections(String  directionJson) throws Exception {

        ArrayList<List<HashMap<String ,String >>> routes = new ArrayList<>();

        JSONObject jsonObject = new JSONObject(directionJson);
        JSONArray jsonRoutes = null;
        JSONArray jsonLegs = null;
        JSONArray jsonSteps = null;
        try{
            jsonRoutes = jsonObject.getJSONArray("routes");
            for (int routeIndex = 0; routeIndex <jsonRoutes.length(); routeIndex++ ){
                jsonLegs = ((JSONObject)jsonRoutes.get(routeIndex)).getJSONArray("legs");
                ArrayList<HashMap<String,String>> path = new ArrayList<HashMap<String,String>>();
                for(int legsIndex = 0 ; legsIndex < jsonLegs.length() ; legsIndex++){
                    jsonSteps = ((JSONObject)jsonLegs.get(legsIndex)).getJSONArray("steps");
                    for(int stepsIndex = 0; stepsIndex <jsonSteps.length(); stepsIndex ++){
                        String polyLine = "";
                        polyLine = (String)((JSONObject)((JSONObject)jsonSteps.get(stepsIndex)).get("polyline")).get("points");
                        ArrayList<LatLng> list = decodePoly(polyLine);
                        for (LatLng latLng : list){
                            HashMap<String,String > latitudeLongitude = new HashMap<String ,String>();
                            latitudeLongitude.put("lat",Double.toString(latLng.latitude));
                            latitudeLongitude.put("lng",Double.toString(latLng.longitude));
                            path.add(latitudeLongitude);
                        }
                    }
                    routes.add(path);
                }
            }

        }
        catch (Exception e){

        }

        return  routes;
    }

    @Override
    protected void onPostExecute(ArrayList<List<HashMap<String, String>>> route) {
        ArrayList points = null;
        PolylineOptions lineOptions = null;
         MarkerOptions markerOptions = new MarkerOptions();

        for (int i = 0; i < route.size(); i++) {
            points = new ArrayList();
            lineOptions = new PolylineOptions();

            List<HashMap<String, String>> path = route.get(i);

            for (int j = 0; j < path.size(); j++) {
                HashMap<String, String> point = path.get(j);

                double lat = Double.parseDouble(point.get("lat"));
                double lng = Double.parseDouble(point.get("lng"));
                LatLng position = new LatLng(lat, lng);

                points.add(position);
            }

            lineOptions.addAll(points);
            lineOptions.width(12);
            lineOptions.color(Color.BLACK);
            lineOptions.geodesic(true);
            if(trip.getModesOfTravel().equalsIgnoreCase(ModesOfTravel.WALKING)){
                lineOptions.pattern(Arrays.<PatternItem>asList(new Dot()));
            }
            mapsActivity.getmMap().addPolyline(lineOptions);

        }

    }

    /**
     * Method to decode polyline points
     * Courtesy : http://jeffreysambells.com/2010/05/27/decoding-polylines-from-google-maps-direction-api-with-java
     * */
    private ArrayList<LatLng> decodePoly(String encoded) {

        ArrayList poly = new ArrayList<LatLng>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((((double) lat / 1E5)),
                    (((double) lng / 1E5)));
            poly.add(p);
        }
        return poly;
    }
}
