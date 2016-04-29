package com.example.mzglinicki96.mojtest.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mzglinicki.96 on 12.04.2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "image.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "image_table";

    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_IMAGE = "IMAGE";

    private static final String DATABASE_CREATE = "create table "
            + TABLE_NAME
            + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_IMAGE + " text "
            + ")";

    public DatabaseHelper(final Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String image) {

        SQLiteDatabase database = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_IMAGE, image);

        database.insert(TABLE_NAME, null, contentValues);
        database.close();
        return true;
    }

    public Cursor getAllData() {

        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("select * from " + TABLE_NAME, null);
    }

    public List<ImageModel> getImageData() {

        final List<ImageModel> imageModelList = new ArrayList<>();
        final Cursor result = getAllData();

        while (result.moveToNext()) {
            int id = result.getInt(result.getColumnIndexOrThrow(COLUMN_ID));
            String image = result.getString(result.getColumnIndexOrThrow(COLUMN_IMAGE));

            ImageModel imageModel = new ImageModel();

            imageModel.setId(id);
            imageModel.setImage(image);
            imageModelList.add(imageModel);
        }
        result.close();
        return imageModelList;
    }


}
