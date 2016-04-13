package com.example.mzglinicki96.mojtest.fragments;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.mzglinicki96.mojtest.R;
import com.example.mzglinicki96.mojtest.adapters.CityListAdapter;

/**
 * Created by mzglinicki.96 on 13.04.2016.
 */
public class CityFragment extends ParentFragment {

    public CityFragment() {
        layoutId = R.layout.fragment_city;
    }

    protected void init(View view) {

        String[] cities = getResources().getStringArray(R.array.cities);
        ListView listView = (ListView) view.findViewById(R.id.listView);
        ListAdapter listAdapter = new CityListAdapter(getContext(), cities);
        listView.setAdapter(listAdapter);
        setOnItemListener(listView, cities);
    }

    public void setOnItemListener(ListView listView, final String[] cities) {

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                ParentFragment fragment = new WeatherFragment();

                Bundle bundle = new Bundle();
                bundle.putString("city", cities[position]);
                fragment.setArguments(bundle);

                transaction.replace(R.id.place_holder, fragment);
                transaction.commit();
            }
        });
    }
}
