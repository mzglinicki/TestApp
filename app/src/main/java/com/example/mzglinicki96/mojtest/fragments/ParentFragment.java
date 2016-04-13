package com.example.mzglinicki96.mojtest.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by mzglinicki.96 on 11.04.2016.
 */
public abstract class ParentFragment extends Fragment {

    protected int layoutId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(layoutId, container, false);
        init(view);
        return view;
    }

    protected abstract void init(View view);
}
