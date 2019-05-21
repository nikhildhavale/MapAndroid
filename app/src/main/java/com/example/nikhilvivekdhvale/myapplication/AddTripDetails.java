package com.example.nikhilvivekdhvale.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;

import com.example.nikhilvivekdhvale.model.ModesOfTravel;
import com.example.nikhilvivekdhvale.model.Trip;
import com.example.nikhilvivekdhvale.listadapter.SuggestionListAdapter;
import com.example.nikhilvivekdhvale.listener.ShowSearchSuggestionListener;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

public class AddTripDetails extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {
    private AutoCompleteTextView startTripAddress;
    private AutoCompleteTextView endTripAddress;
    private ListView suggestionList;
    private Trip trip = new Trip();
    private Button saveButton;
    private AlertDialog alertDialog  = null;
    private RadioButton walkingRadioButton;
    private RadioButton drivingRadioButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip_details);
        startTripAddress = findViewById(R.id.startAddressSearch);
        endTripAddress = findViewById(R.id.endAddressSearch);
        setupAutoCompleteTextView(startTripAddress);
        setupAutoCompleteTextView(endTripAddress);
        saveButton = (Button)findViewById(R.id.saveButton);
        saveButton.setOnClickListener(this);
        walkingRadioButton = findViewById(R.id.walking);
        drivingRadioButton = findViewById(R.id.driving);
        walkingRadioButton.setOnClickListener(this);
        drivingRadioButton.setOnClickListener(this);

    }
    void setupAutoCompleteTextView(AutoCompleteTextView textView){
        textView.addTextChangedListener(new ShowSearchSuggestionListener(textView,this));
        textView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
       SuggestionListAdapter adapter = (SuggestionListAdapter) adapterView.getAdapter();
        Address address =(Address) adapter.getItem(i);
        LatLng latLng = new LatLng(address.getLatitude(),address.getLongitude());
        switch (adapter.getAddressTextView().getId()){
            case  R.id.startAddressSearch:
                trip.setStartAddressName(address.getAddressLine(0));
                trip.setStartTripCoodinates(latLng);
            case  R.id.endAddressSearch:
                trip.setEndAddressName(address.getAddressLine(0));
                trip.setEndTripCoodinates(latLng);

        }
        adapter.getAddressTextView().setText(address.getAddressLine(0));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.saveButton:
            saveTrip();
            break;
            case R.id.walking:
            trip.setModesOfTravel(ModesOfTravel.WALKING);
            break;
            case R.id.driving:
            trip.setModesOfTravel(ModesOfTravel.DRIVING);
            break;

        }

    }
    void saveTrip(){
        if(!trip.isTripDataCompleted()){

            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);

            alertBuilder.setMessage(getResources().getString(R.string.TripIncomplete));
            alertBuilder.setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    alertDialog.cancel();
                }
            });
            alertDialog = alertBuilder.create();
            alertDialog.show();
        }
        else {
            Intent returnIntent = getIntent();
            returnIntent.putExtra(getResources().getString(R.string.tripDataSaved),trip);
            setResult(0,returnIntent);
            finish();

        }
    }
}
