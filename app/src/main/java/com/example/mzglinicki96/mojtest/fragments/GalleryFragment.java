package com.example.mzglinicki96.mojtest.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mzglinicki96.mojtest.R;
import com.example.mzglinicki96.mojtest.adapters.CustomPagerAdapter;
import com.example.mzglinicki96.mojtest.database.DatabaseHelper;
import com.example.mzglinicki96.mojtest.database.ImageModel;

import java.util.List;

/**
 * Created by mzglinicki.96 on 11.04.2016.
 */
public class GalleryFragment extends ParentFragment {

    public GalleryFragment() {
        layoutId = R.layout.fragment_gallery;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(layoutId, container, false);

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        CustomPagerAdapter adapter = new CustomPagerAdapter(getContext());
        assert viewPager != null;
        viewPager.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        onFloatingActionPressed(fab);

        return view;
    }

    public void onFloatingActionPressed(FloatingActionButton fab) {

        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, R.string.non_active, Snackbar.LENGTH_SHORT).show();
            }
        });
    }
}



