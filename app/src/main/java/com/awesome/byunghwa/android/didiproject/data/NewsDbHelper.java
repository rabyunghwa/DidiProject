package com.awesome.byunghwa.android.didiproject.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NewsDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "news.db";
    private static final int DATABASE_VERSION = 1;

    public NewsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // news table
        db.execSQL("CREATE TABLE " + NewsContract.NewsEntry.TABLE_NAME + " ("
                + NewsContract.NewsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NewsContract.NewsEntry.NEWS_ID + " TEXT,"
                + NewsContract.NewsEntry.CHANNEL_ID + " TEXT,"
                + NewsContract.NewsEntry.CHANNEL_NAME + " TEXT,"
                + NewsContract.NewsEntry.DESC + " TEXT,"
                + NewsContract.NewsEntry.LINK + " TEXT,"
                + NewsContract.NewsEntry.NEWS_TITLE + " TEXT,"
                + NewsContract.NewsEntry.PUB_DATE + " TEXT,"
                + NewsContract.NewsEntry.IMAGE_URL+ " TEXT,"
                + NewsContract.NewsEntry.TYPE + " TEXT NOT NULL"
                + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + NewsContract.NewsEntry.TABLE_NAME);
        onCreate(db);
    }
}
