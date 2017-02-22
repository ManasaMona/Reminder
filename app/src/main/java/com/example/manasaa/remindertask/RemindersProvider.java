package com.example.manasaa.remindertask;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by manasa.a on 21-02-2017.
 */

public class RemindersProvider  extends ContentProvider{
    private static final String TAG="RemindersProviderclass";
    private static final String PROVIDER_NAME = "androidcontentproviderdemo.androidcontentprovider.reminders";
    private static final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME + "/reminders");
    private static final int REMINDERS = 1;
    private MySQLiteHelper database=null;
    private static final UriMatcher uriMatcher = getUriMatcher();
    private static UriMatcher getUriMatcher() {
        Log.d(TAG,"called UriMatcher getUriMatcher()");
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "reminders", REMINDERS);
        return uriMatcher;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        Log.d(TAG,"getType(Uri uri)");
        switch (uriMatcher.match(uri)) {
            case REMINDERS://list of items
                return "vnd.android.cursor.dir/vnd.com.androidcontentproviderdemo.androidcontentprovider.reminders";
        }
        return null;
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        database = new MySQLiteHelper(context);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return database.getReminders(null, projection, selection, selectionArgs, sortOrder);
    }


    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        throw new UnsupportedOperationException("Cannot make changes on the data");
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException("Cannot make changes on the data");
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException("Cannot make changes on the data");
    }
}
