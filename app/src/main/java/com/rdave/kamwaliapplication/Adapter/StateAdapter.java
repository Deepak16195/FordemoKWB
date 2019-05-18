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

import com.rdave.kamwaliapplication.Model.GetState;
import com.rdave.kamwaliapplication.R;

import java.util.ArrayList;
import java.util.List;

public class StateAdapter extends ArrayAdapter<GetState.DataEntity> {
    private List<GetState.DataEntity> stateArray;

    public StateAdapter(@NonNull Context context, @NonNull List<GetState.DataEntity> astateArray) {
        super(context, 0, astateArray);
        stateArray = new ArrayList<>(astateArray);
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

        GetState.DataEntity countryItem = getItem(position);

        if (countryItem != null) {
            txt_ID.setText(0+"");
            txt_Name.setText(countryItem.getState());
        }

        return convertView;
    }

    private Filter countryFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<GetState.DataEntity> suggestions = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                suggestions.addAll(stateArray);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (GetState.DataEntity item : stateArray) {
                    if (item.getState().toLowerCase().contains(filterPattern)) {
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
            return ((GetState.DataEntity) resultValue).getState();
        }
    };
}
