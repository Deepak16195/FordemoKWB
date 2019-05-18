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

import com.rdave.kamwaliapplication.Model.Categories;
import com.rdave.kamwaliapplication.R;

import java.util.ArrayList;
import java.util.List;

public class CategoriesAdapter extends ArrayAdapter<Categories.DataBean> {
    private List<Categories.DataBean> countryListFull;

    public CategoriesAdapter(@NonNull Context context, @NonNull List<Categories.DataBean> countryList) {
        super(context, 0, countryList);
        countryListFull = new ArrayList<>(countryList);
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

        Categories.DataBean countryItem = getItem(position);

        if (countryItem != null) {
            txt_ID.setText(countryItem.getCategoryId()+"");
            txt_Name.setText(countryItem.getCategoryName());
        }

        return convertView;
    }

    private Filter countryFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<Categories.DataBean> suggestions = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                suggestions.addAll(countryListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Categories.DataBean item : countryListFull) {
                    if (item.getCategoryName().toLowerCase().contains(filterPattern)) {
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
            return ((Categories.DataBean) resultValue).getCategoryName();
        }
    };
}
