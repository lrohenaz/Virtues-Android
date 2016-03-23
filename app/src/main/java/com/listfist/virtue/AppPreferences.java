package com.listfist.virtue;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Luke on 3/23/2016.
 */
public class AppPreferences {
    public static final String KEY_PREFS_ACTIVE_VIRTUE = "active_virtue";
    private static final String APP_SHARED_PREFS = AppPreferences.class.getSimpleName(); //  Name of the file -.xml
    private SharedPreferences _sharedPrefs;
    private SharedPreferences.Editor _prefsEditor;

    public AppPreferences(Context context) {
        this._sharedPrefs = context.getSharedPreferences(APP_SHARED_PREFS, Activity.MODE_PRIVATE);
        this._prefsEditor = _sharedPrefs.edit();
    }

    public String getActiveVirtue() {
        return _sharedPrefs.getString(KEY_PREFS_ACTIVE_VIRTUE, "");
    }

    public void saveActiveVirtue(String text) {
        _prefsEditor.putString(KEY_PREFS_ACTIVE_VIRTUE, text);
        _prefsEditor.commit();
    }
}