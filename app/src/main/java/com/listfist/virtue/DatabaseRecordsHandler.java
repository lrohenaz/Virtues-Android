package com.listfist.virtue;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Zero on 4/29/2016.
 */
public class DatabaseRecordsHandler {
    private static final String TAG = SplashActivity.class.getName();
    private SQLiteDatabase database;

    public final static String RECORD_TABLE="virtueLog"; // name of table

    public final static String RECORD_ID="_id"; // id value for record
    public final static String RECORD_TIME="dt";  // datetime of record
    public final static String RECORD_1="v1";
    public final static String RECORD_2="v2";
    public final static String RECORD_3="v3";
    public final static String RECORD_4="v4";
    public final static String RECORD_5="v5";
    public final static String RECORD_6="v6";
    public final static String RECORD_7="v7";
    public final static String RECORD_8="v8";
    public final static String RECORD_9="v9";
    public final static String RECORD_10="v10";
    public final static String RECORD_11="v11";
    public final static String RECORD_12="v12";
    public final static String RECORD_13="v13";

    DatabaseRecordsHandler(Context context) {
        // Set up database
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(context);
        database = dbHelper.getWritableDatabase();

    }

    Cursor getCursor() {
        if(database!=null) {
            String selectQuery = "SELECT _id, dt, v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13 FROM "+ RECORD_TABLE;
            Cursor mCursor = database.rawQuery(selectQuery, null);
            Log.d(TAG,"Query: "+ selectQuery);
            //Cursor mCursor = database.query(true, RECORD_TABLE, cols, null                    , null, null, null, null, null);
            if (mCursor != null) {
                mCursor.moveToFirst();
            }
            return mCursor; // iterate to get each value.
        }
        else {
            return null;
        }
    }
}
