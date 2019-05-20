package com.example.nikhilvivekdhvale.listadapter;

import android.content.Context;
import android.location.Address;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.nikhilvivekdhvale.myapplication.R;

import java.util.List;
/** This adapter will be used to populate the suggestion list on getting it from Geocoder*/
public class SuggestionListAdapter extends BaseAdapter implements Filterable {

          private  List<Address> list;
          private  Context context;
          private Filter mFilter;
          private AutoCompleteTextView addressTextView;

    public AutoCompleteTextView getAddressTextView() {
        return addressTextView;
    }

    public SuggestionListAdapter(List<Address> list, Context context, AutoCompleteTextView addressTextView){
            this.list = list;
            this.context = context;
            this.addressTextView = addressTextView;

             }

    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        Address address = list.get(position);
        TextView title;
            if (convertView == null){
                LayoutInflater inflater = LayoutInflater.from(context);
                convertView = inflater.inflate(R.layout.suggestion_list_item,parent,false);



            }
        title = (TextView)convertView.findViewById(R.id.Title);
        title.setText(address.getAddressLine(0));

        return convertView;
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Filter getFilter() {
        if(mFilter == null){
            mFilter = new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence charSequence) {
                    FilterResults results = new FilterResults();
                    results.values = list;
                    results.count = list.size();
                    return results;
                }

                @Override
                protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

                }
            };
        }
        return mFilter;
    }
}
