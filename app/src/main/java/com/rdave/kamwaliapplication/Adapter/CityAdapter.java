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

import com.rdave.kamwaliapplication.Model.GetCity;
import com.rdave.kamwaliapplication.R;

import java.util.ArrayList;
import java.util.List;

public class CityAdapter extends ArrayAdapter<GetCity.DataEntity> {
    private List<GetCity.DataEntity> cityArray;

    public CityAdapter(@NonNull Context context, @NonNull List<GetCity.DataEntity> acityArray) {
        super(context, 0, acityArray);
        cityArray = new ArrayList<>(acityArray);
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

        GetCity.DataEntity countryItem = getItem(position);

        if (countryItem != null) {
            txt_ID.setText(0+"");
            txt_Name.setText(countryItem.getCity());
        }

        return convertView;
    }

    private Filter countryFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<GetCity.DataEntity> suggestions = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                suggestions.addAll(cityArray);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (GetCity.DataEntity item : cityArray) {
                    if (item.getCity().toLowerCase().contains(filterPattern)) {
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
            return ((GetCity.DataEntity) resultValue).getCity();
        }
    };
}
