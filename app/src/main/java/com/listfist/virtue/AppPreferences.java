package com.listfist.virtue;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by Luke on 3/23/2016.
 */
public class AppPreferences {
    private static final String TAG = SplashActivity.class.getName();
    public static final String KEY_PREFS_WEEK = "week";
    public static final String KEY_PREFS_TODAYS_RATING = "todays_rating";
    public static final String KEY_PREFS_ACTIVE_VIRTUE = "active_virtue";
    public static final String KEY_PREFS_TIME = "time_key";
    public static final String KEY_PREFS_CHANGE_VIRTUE_TIME = "time_change_virtue";
    public static final String KEY_PREFS_V1_RATING = "v1";
    public static final String KEY_PREFS_V2_RATING = "v2";
    public static final String KEY_PREFS_V3_RATING = "v3";
    public static final String KEY_PREFS_V4_RATING = "v4";
    public static final String KEY_PREFS_V5_RATING = "v5";
    public static final String KEY_PREFS_V6_RATING = "v6";
    public static final String KEY_PREFS_V7_RATING = "v7";
    public static final String KEY_PREFS_V8_RATING = "v8";
    public static final String KEY_PREFS_V9_RATING = "v9";
    public static final String KEY_PREFS_V10_RATING = "v10";
    public static final String KEY_PREFS_V11_RATING = "v11";
    public static final String KEY_PREFS_V12_RATING = "v12";
    public static final String KEY_PREFS_V13_RATING = "v13";

    NotificationReceiver notificationReceiver;

    private static final String APP_SHARED_PREFS = MainActivity.class.getSimpleName(); //  Name of the file -.xml

    private SharedPreferences _sharedPrefs;
    private SharedPreferences.Editor _prefsEditor;
    private int weekNo = 1;
    private String changeVirtueTime;
    private Context context;

    public AppPreferences(Context context) {
        notificationReceiver = new NotificationReceiver();
        this.context=context;
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

    public String getRating(int id) {
        switch (id) {
            case 0:
                return _sharedPrefs.getString(KEY_PREFS_TODAYS_RATING, "0");
            case 1:
                return _sharedPrefs.getString(KEY_PREFS_V1_RATING, "0");
            case 2:
                return _sharedPrefs.getString(KEY_PREFS_V2_RATING, "0");
            case 3:
                return _sharedPrefs.getString(KEY_PREFS_V3_RATING, "0");
            case 4:
                return _sharedPrefs.getString(KEY_PREFS_V4_RATING, "0");
            case 5:
                return _sharedPrefs.getString(KEY_PREFS_V5_RATING, "0");
            case 6:
                return _sharedPrefs.getString(KEY_PREFS_V6_RATING, "0");
            case 7:
                return _sharedPrefs.getString(KEY_PREFS_V7_RATING, "0");
            case 8:
                return _sharedPrefs.getString(KEY_PREFS_V8_RATING, "0");
            case 9:
                return _sharedPrefs.getString(KEY_PREFS_V9_RATING, "0");
            case 10:
                return _sharedPrefs.getString(KEY_PREFS_V10_RATING, "0");
            case 11:
                return _sharedPrefs.getString(KEY_PREFS_V11_RATING, "0");
            case 12:
                return _sharedPrefs.getString(KEY_PREFS_V12_RATING, "0");
            case 13:
                return _sharedPrefs.getString(KEY_PREFS_V13_RATING, "0");
            default:
                return _sharedPrefs.getString(KEY_PREFS_TODAYS_RATING, "0");
        }
    }

    public void saveRating(int id, int rating) {
        switch (id) {
            case 0:
                _prefsEditor.putString(KEY_PREFS_TODAYS_RATING, String.valueOf(rating));
            case 1:
                _prefsEditor.putString(KEY_PREFS_V1_RATING, String.valueOf(rating));
            case 2:
                _prefsEditor.putString(KEY_PREFS_V2_RATING, String.valueOf(rating));
            case 3:
                _prefsEditor.putString(KEY_PREFS_V3_RATING, String.valueOf(rating));
            case 4:
                _prefsEditor.putString(KEY_PREFS_V4_RATING, String.valueOf(rating));
            case 5:
                _prefsEditor.putString(KEY_PREFS_V5_RATING, String.valueOf(rating));
            case 6:
                _prefsEditor.putString(KEY_PREFS_V6_RATING, String.valueOf(rating));
            case 7:
                _prefsEditor.putString(KEY_PREFS_V7_RATING, String.valueOf(rating));
            case 8:
                _prefsEditor.putString(KEY_PREFS_V8_RATING, String.valueOf(rating));
            case 9:
                _prefsEditor.putString(KEY_PREFS_V9_RATING, String.valueOf(rating));
            case 10:
                _prefsEditor.putString(KEY_PREFS_V10_RATING, String.valueOf(rating));
            case 11:
                _prefsEditor.putString(KEY_PREFS_V11_RATING, String.valueOf(rating));
            case 12:
                _prefsEditor.putString(KEY_PREFS_V12_RATING, String.valueOf(rating));
            case 13:
                _prefsEditor.putString(KEY_PREFS_V13_RATING, String.valueOf(rating));
        }

        _prefsEditor.commit();
    }

    public String getWeekWord() {
        switch (_sharedPrefs.getInt(KEY_PREFS_WEEK, 1)) {
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
    public void createChangeVirtueReminder() {
        // Create a new calendar
        Calendar weeklyReminder = Calendar.getInstance();
        weeklyReminder.setTimeInMillis(System.currentTimeMillis());
        Log.d(TAG, "it thinks today is " + weeklyReminder.get(Calendar.DAY_OF_YEAR));
        // Advance it by 1 week
        weeklyReminder.set(Calendar.HOUR_OF_DAY, 8); // Reminder will trigger at 8am
        weeklyReminder.set(Calendar.MINUTE, 0);
        weeklyReminder.set(Calendar.SECOND, 0);
        weeklyReminder.add(Calendar.DAY_OF_YEAR, 7); // Add 7 days
        //weeklyReminder.add(Calendar.MINUTE, 1); // Add 1 minute (use instead of adding 7 days for testing)
        Log.d(TAG, "Virtue will change on day #" + weeklyReminder.get(Calendar.DAY_OF_YEAR) + "/365");
        notificationReceiver.setOnetimeTimer(context, weeklyReminder);
        _prefsEditor.putString(KEY_PREFS_CHANGE_VIRTUE_TIME, String.valueOf(weeklyReminder.getTimeInMillis()));
        _prefsEditor.commit();
    }

    public void createDefaultReminder() {
        // Default reminder time 10PM
        int hour = 22;
        int minute = 0;

        // Create a new calendar and start with the time now
        Calendar nightlyReminder = Calendar.getInstance();
        nightlyReminder.setTimeInMillis(System.currentTimeMillis());
        // Set to default time
        nightlyReminder.set(Calendar.HOUR_OF_DAY, hour);
        nightlyReminder.set(Calendar.MINUTE, minute); // Reminder will trigger at 8am
        nightlyReminder.set(Calendar.SECOND, 0);
        notificationReceiver.setReminder(context, nightlyReminder.getTimeInMillis());

        // Save to shared preferences
        String prefTime = nightlyReminder.get(Calendar.HOUR_OF_DAY)+":"+nightlyReminder.get(Calendar.MINUTE);
        _prefsEditor.putString(KEY_PREFS_TIME, prefTime);
        _prefsEditor.commit();
    }

    public String getChangeVirtueTime() {
        return _sharedPrefs.getString(KEY_PREFS_CHANGE_VIRTUE_TIME, "0");
    }
}