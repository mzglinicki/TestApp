package com.example.mzglinicki96.mojtest.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.mzglinicki96.mojtest.R;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by mzglinicki.96 on 12.04.2016.
 */
public class CustomPagerAdapter extends PagerAdapter {

    private List<Uri> pictureList = null;
    private final Context context;

    public CustomPagerAdapter(final Context context, final List<Uri> pictureList) {
        this.context = context;
        this.pictureList = pictureList;
    }

    @Override
    public int getCount() {
        return pictureList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        final LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View itemView = layoutInflater.inflate(R.layout.swipe_layout, container, false);
        final ImageView imageView = (ImageView) itemView.findViewById(R.id.image_to_show);

        final InputStream stream;
        try {
            stream = context.getContentResolver().openInputStream(pictureList.get(position));

            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;

            imageView.setImageBitmap(BitmapFactory.decodeStream(stream, null, options));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);
    }

    public void addImage(Uri path) {
        pictureList.add(path);
        notifyDataSetChanged();
    }
}
