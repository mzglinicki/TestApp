package com.example.mzglinicki96.mojtest.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.mzglinicki96.mojtest.R;
import com.example.mzglinicki96.mojtest.database.ImageModel;

import java.util.List;

/**
 * Created by mzglinicki.96 on 12.04.2016.
 */
public class CustomPagerAdapter extends PagerAdapter {

    private int[] defaultImages = {R.drawable.train, R.drawable.android};
    private Context context;
    private LayoutInflater layoutInflater;

    public CustomPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return defaultImages.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return (view == (RelativeLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = layoutInflater.inflate(R.layout.swipe_layout, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.image_to_show);

        imageView.setImageResource(defaultImages[position]);
        container.addView(itemView);

        return itemView;
    }
}
