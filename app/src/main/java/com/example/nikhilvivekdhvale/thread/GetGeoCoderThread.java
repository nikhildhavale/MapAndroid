package com.example.nikhilvivekdhvale.thread;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.widget.AutoCompleteTextView;

import com.example.nikhilvivekdhvale.listadapter.SuggestionListAdapter;

import java.util.List;

public class GetGeoCoderThread extends  Thread {
    private Activity activity;
    private AutoCompleteTextView addressText;
    private String text;
    public GetGeoCoderThread(Activity activity,AutoCompleteTextView addressText,String text){
        this.activity = activity;
        this.addressText = addressText;
        this.text = text;
    }

    @Override
    public void run() {
        super.run();
        Geocoder geocoder = new Geocoder(addressText.getContext());
        try{
            final List<Address>  addressSuggestion=  geocoder.getFromLocationName(text,10);
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    SuggestionListAdapter adapter = new SuggestionListAdapter(addressSuggestion,addressText.getContext(),addressText);
                    addressText.setAdapter(adapter);
                }
            });
        }
        catch (Exception e){

        }




    }
}
