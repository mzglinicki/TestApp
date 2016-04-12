package com.example.mzglinicki96.mojtest.database;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by mzglinicki.96 on 12.04.2016.
 */
public class ImageModel {

    private int id;
    private String image;
    private String name;

    public ImageModel() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bitmap getImage() {

        byte[] imageInByte = image.getBytes();
        Bitmap bmp = BitmapFactory.decodeByteArray(imageInByte,0,imageInByte.length);
        return bmp;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
