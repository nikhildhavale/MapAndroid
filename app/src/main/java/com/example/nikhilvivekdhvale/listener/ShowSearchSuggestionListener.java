package com.example.nikhilvivekdhvale.listener;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.nikhilvivekdhvale.Trip;

public class ShowSearchSuggestionListener implements TextWatcher {
    private ListView suggestionList;
    private EditText editText;
    private Trip trip;

    public ShowSearchSuggestionListener(ListView suggestionList, EditText editText, Trip trip) {
        this.suggestionList = suggestionList;
        this.editText = editText;
        this.trip = trip;
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (charSequence.length() == 0 ){
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) suggestionList.getLayoutParams();
            params.addRule(RelativeLayout.ALIGN_TOP,editText.getId());

        }

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
