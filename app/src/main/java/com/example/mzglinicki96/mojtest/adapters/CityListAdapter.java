package com.example.mzglinicki96.mojtest.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mzglinicki96.mojtest.R;

/**
 * Created by mzglinicki.96 on 13.04.2016.
 */
public class CityListAdapter extends ArrayAdapter<String> {

    private final String[] cityModel;

    public CityListAdapter(final Context context, final String[] cityModel) {
        super(context, -1, cityModel);
        this.cityModel = cityModel;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        final LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final String city = cityModel[position];

        convertView = inflater.inflate(R.layout.city_list_model, parent, false);
        final TextView cityTextView = (TextView) convertView.findViewById(R.id.city_name_list);

        cityTextView.setText(city);
        return convertView;
    }

    @Override
    public long getItemId(final int position) {
        return super.getItemId(position);
    }

}
