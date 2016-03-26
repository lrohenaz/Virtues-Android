package com.listfist.virtue;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Luke on 3/23/2016.
 */
public class AppPreferences {
    public static final String KEY_PREFS_WEEK = "week";
    public static final String KEY_PREFS_TODAYS_RATING = "todays_rating";
    public static final String KEY_PREFS_ACTIVE_VIRTUE = "active_virtue";
    public static final String KEY_PREFS_TIME = "time_key";

    private static final String APP_SHARED_PREFS = MainActivity.class.getSimpleName(); //  Name of the file -.xml

    private SharedPreferences _sharedPrefs;
    private SharedPreferences.Editor _prefsEditor;
    private int weekNo = 1;

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
    public void saveTodaysRating(String text) {
        _prefsEditor.putString(KEY_PREFS_TODAYS_RATING, text);
        _prefsEditor.commit();
    }
    public String getTodaysRating() {
        return _sharedPrefs.getString(KEY_PREFS_TODAYS_RATING, "0");
    }

    public String getWeekWord() {
        switch(_sharedPrefs.getInt(KEY_PREFS_WEEK, 1)) {
            case 1:
                return "first";
            case 2:
                return "second";
            case 3:
                return "third";
            case 4:
                return "fourth";
            case 5:
                return "fifth";
            case 6:
                return "sixth";
            case 7:
                return "seventh";
            case 8:
                return "eighth";
            case 9:
                return "ninth";
            case 10:
                return "tenth";
            case 11:
                return "eleventh";
            case 12:
                return "twelveth";
            case 13:
                return "thirteenth";
            default:
                return "first";
        }
    }

    public void createDefaultReminder() {
        _prefsEditor.putString(KEY_PREFS_TIME, "22:0");
        _prefsEditor.commit();
    }
}