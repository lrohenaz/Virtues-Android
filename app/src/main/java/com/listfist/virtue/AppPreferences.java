package com.listfist.virtue;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Luke on 3/23/2016.
 */
public class AppPreferences {
    public static final String KEY_PREFS_ACTIVE_VIRTUE = "active_virtue";
    public static final String KEY_PREFS_TIME = "time_key";

    private static final String APP_SHARED_PREFS = MainActivity.class.getSimpleName(); //  Name of the file -.xml

    private SharedPreferences _sharedPrefs;
    private SharedPreferences.Editor _prefsEditor;

    public AppPreferences(Context context) {
        //this._sharedPrefs = context.getSharedPreferences(APP_SHARED_PREFS, Activity.MODE_PRIVATE);
        this._sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        this._prefsEditor = _sharedPrefs.edit();
    }

    public String getActiveVirtue() {
        return _sharedPrefs.getString(KEY_PREFS_ACTIVE_VIRTUE, "");
    }

    public String getTime() {
        return _sharedPrefs.getString(KEY_PREFS_TIME, "0");
    }

    public void saveActiveVirtue(String text) {
        _prefsEditor.putString(KEY_PREFS_ACTIVE_VIRTUE, text);
        _prefsEditor.commit();
    }
}