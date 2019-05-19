package com.example.nikhilvivekdhvale.listener;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.nikhilvivekdhvale.Trip;
import com.example.nikhilvivekdhvale.listadapter.SuggestionListAdapter;
import com.example.nikhilvivekdhvale.thread.GetGeoCoderThread;

import java.util.List;

public class ShowSearchSuggestionListener implements TextWatcher {
    private AutoCompleteTextView addressText;
    private Geocoder geocoder;
    private List<Address> addressSuggestion;
    private Activity activity;
    private GetGeoCoderThread geoCoderThread;
    public ShowSearchSuggestionListener( AutoCompleteTextView addressText,Activity activity) {
        this.addressText = addressText;
        this.activity = activity;

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if(charSequence.length() > 2) {
                geoCoderThread = new GetGeoCoderThread(activity,addressText,charSequence.toString());
                geoCoderThread.start();



        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
