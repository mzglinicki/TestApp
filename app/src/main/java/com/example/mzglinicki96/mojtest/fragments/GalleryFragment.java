package com.example.mzglinicki96.mojtest.fragments;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.mzglinicki96.mojtest.R;
import com.example.mzglinicki96.mojtest.adapters.CustomPagerAdapter;
import com.example.mzglinicki96.mojtest.database.DatabaseHelper;
import com.example.mzglinicki96.mojtest.database.ImageModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mzglinicki.96 on 11.04.2016.
 */
public class GalleryFragment extends ParentFragment {

    public static final int REQUEST_CODE = 1;

    private CustomPagerAdapter adapter;
    private ViewPager viewPager;
    private DatabaseHelper databaseHelper;
    private List<Uri> pictureList = null;

    public GalleryFragment() {
        layoutId = R.layout.fragment_gallery;
    }

    @Override
    protected void init(View view) {

        databaseHelper = new DatabaseHelper(getContext());

        viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        adapter = new CustomPagerAdapter(getContext(), getDefaultImage());
        assert viewPager != null;
        viewPager.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        onFloatingActionPressed(fab);
    }

    private List<Uri> getDefaultImage() {

        pictureList = new ArrayList<>();

        List<ImageModel> imageModels = databaseHelper.getImageData();

        if (imageModels.size() != 0) {
            for (ImageModel model : imageModels) {
                Uri uri = Uri.parse(model.getImage());
                pictureList.add(uri);
            }
        } else {
            Uri uriAndroid = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                    "://" + getResources().getResourcePackageName(R.drawable.android)
                    + '/' + getResources().getResourceTypeName(R.drawable.android) + '/' + getResources()
                    .getResourceEntryName(R.drawable.android));

            Uri uriTrain = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE +
                    "://" + getResources().getResourcePackageName(R.drawable.train)
                    + '/' + getResources().getResourceTypeName(R.drawable.train) + '/' + getResources()
                    .getResourceEntryName(R.drawable.train));

            pictureList.add(uriAndroid);
            pictureList.add(uriTrain);
            databaseHelper.insertData(uriAndroid.toString());
            databaseHelper.insertData(uriTrain.toString());
        }
        return pictureList;
    }

    public void onFloatingActionPressed(FloatingActionButton fab) {

        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            Uri uri = data.getData();
            adapter.addImage(uri);
            viewPager.setCurrentItem(adapter.getCount());
            databaseHelper.insertData(uri.toString());
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}



