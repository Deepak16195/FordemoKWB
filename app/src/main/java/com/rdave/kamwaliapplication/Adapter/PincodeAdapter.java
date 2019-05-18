package com.rdave.kamwaliapplication.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.rdave.kamwaliapplication.Model.GetPincode;
import com.rdave.kamwaliapplication.R;

import java.util.ArrayList;
import java.util.List;

public class PincodeAdapter extends ArrayAdapter<GetPincode.DataEntity> {
    private List<GetPincode.DataEntity> pincodeArray;

    public PincodeAdapter(@NonNull Context context, @NonNull List<GetPincode.DataEntity> apincodeArray) {
        super(context, 0, apincodeArray);
        pincodeArray = new ArrayList<>(apincodeArray);
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return countryFilter;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.categories_row, parent, false
            );
        }

        TextView txt_ID = convertView.findViewById(R.id.txt_ID);
        TextView txt_Name = convertView.findViewById(R.id.txt_Name);

        GetPincode.DataEntity countryItem = getItem(position);

        if (countryItem != null) {
            txt_ID.setText(0+"");
            txt_Name.setText(countryItem.getPinCode());
        }

        return convertView;
    }

    private Filter countryFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<GetPincode.DataEntity> suggestions = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                suggestions.addAll(pincodeArray);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (GetPincode.DataEntity item : pincodeArray) {
                    if (item.getPinCode().toLowerCase().contains(filterPattern)) {
                        suggestions.add(item);
                    }
                }
            }

            results.values = suggestions;
            results.count = suggestions.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            clear();
            addAll((List) results.values);
            notifyDataSetChanged();
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((GetPincode.DataEntity) resultValue).getPinCode();
        }
    };
}
