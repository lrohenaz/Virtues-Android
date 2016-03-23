package com.listfist.virtue;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Zero on 3/22/2016.
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Virtues";

    private static final int DATABASE_VERSION = 2;

    // Database creation sql statement
    private static final String DATABASE_CREATE =
            "CREATE TABLE IF NOT EXISTS virtueLog( _id INTEGER PRIMARY KEY,dt DATETIME default current_timestamp, v1 INTEGER, v2 INTEGER, v3 integer, v4 INTEGER, v5 INTEGER, v6 INTEGER, v7 INTEGER, v8 INTEGER, v9 INTEGER, v10 INTEGER, v11 INTEGER, v12 INTEGER, v13 INTEGER);";

    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Method is called during creation of the database
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    // Method is called during an upgrade of the database,
    @Override
    public void onUpgrade(SQLiteDatabase database,int oldVersion,int newVersion){
        Log.w(MyDatabaseHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS virtueLog");
        onCreate(database);
    }
}
