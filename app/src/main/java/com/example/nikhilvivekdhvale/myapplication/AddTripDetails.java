package com.example.nikhilvivekdhvale.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.example.nikhilvivekdhvale.Trip;
import com.example.nikhilvivekdhvale.listener.ShowSearchSuggestionListener;

public class AddTripDetails extends AppCompatActivity {
    private EditText startTripAddress;
    private EditText endTripAddress;
    private ListView suggestionList;
    private Trip trip = new Trip();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip_details);
        startTripAddress = findViewById(R.id.startAddressSearch);
        endTripAddress = findViewById(R.id.endAddressSearch);
        suggestionList = findViewById(R.id.searchSuggestion);
        startTripAddress.addTextChangedListener(new ShowSearchSuggestionListener(suggestionList,startTripAddress,trip));
        startTripAddress.addTextChangedListener(new ShowSearchSuggestionListener(suggestionList,startTripAddress,trip));
    }

}
