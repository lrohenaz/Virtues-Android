package com.listfist.virtue;

import android.app.Activity;
import android.app.Notification;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Parcel;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.widget.RemoteViews;

import java.util.Calendar;

/**
 * Created by Luke on 3/23/2016.
 */
public class AppPreferences {
    private static final String TAG = SplashActivity.class.getName();
    public static final String KEY_PREFS_THEME = "theme";
    public static final String KEY_PREFS_WEEK = "week";
    public static final String KEY_PREFS_TODAYS_RATING = "todays_rating";
    public static final String KEY_PREFS_ACTIVE_VIRTUE = "active_virtue";
    public static final String KEY_PREFS_TIME = "time_key";
    public static final String KEY_PREFS_SURVEY_TIME = "survey_time_key";
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
    public static final String KEY_PREFS_RINGTONE = "notifications_new_message_ringtone";
    public static final String KEY_PREFS_VIBRATE = "notifications_new_message_vibrate";
    public static final String KEY_PREFS_WIDGET_ID = "WidgetId";
    public static final String KEY_PREFS_WIDGET_TEXT_COLOR = "WidgetTextColor";
    public static final String KEY_PREFS_WIDGET_TEXT_SIZE = "WidgetTextSize";

    NotificationReceiver notificationReceiver;

    private SharedPreferences _sharedPrefs;
    private SharedPreferences.Editor _prefsEditor;
    private int weekNo = 1;
    private String changeVirtueTime;
    private Context context;
    private long lastSurveyTime;
    private boolean vibrate;
    private int theme;
    private int widgetTextColor;
    private boolean widgetId;
    private float widgetTextSize;

    public AppPreferences(Context context) {
        notificationReceiver = new NotificationReceiver();
        this.context=context;
        this._sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        this._prefsEditor = _sharedPrefs.edit();
    }

    public String getActiveVirtue() {
        return _sharedPrefs.getString(KEY_PREFS_ACTIVE_VIRTUE, "0");
    }

    public String getActiveVirtueName() {
       String sid = _sharedPrefs.getString(KEY_PREFS_ACTIVE_VIRTUE, "0");
        String name;
        switch(Integer.parseInt(sid)) {
            case 1:
                name = context.getResources().getString(R.string.v1_title);
                break;
            case 2:
                name = context.getResources().getString(R.string.v2_title);
                break;
            case 3:
                name = context.getResources().getString(R.string.v3_title);
                break;
            case 4:
                name = context.getResources().getString(R.string.v4_title);
                break;
            case 5:
                name = context.getResources().getString(R.string.v5_title);
                break;
            case 6:
                name = context.getResources().getString(R.string.v6_title);
                break;
            case 7:
                name = context.getResources().getString(R.string.v7_title);
                break;
            case 8:
                name = context.getResources().getString(R.string.v8_title);
                break;
            case 9:
                name = context.getResources().getString(R.string.v9_title);
                break;
            case 10:
                name = context.getResources().getString(R.string.v10_title);
                break;
            case 11:
                name = context.getResources().getString(R.string.v11_title);
                break;
            case 12:
                name = context.getResources().getString(R.string.v12_title);
                break;
            case 13:
                name = context.getResources().getString(R.string.v13_title);
                break;
            default:
                name = null;
        }
        return name;
    }

    public String getTime() {
        return _sharedPrefs.getString(KEY_PREFS_TIME, "0");
    }

    public void saveActiveVirtue(String text) {
        int awID = _sharedPrefs.getInt("WidgetId", AppWidgetManager.INVALID_APPWIDGET_ID);

        //RemoteViews rv=new RemoteViews(context.getPackageName(),R.layout.virtue_widget);
        //rv.setTextViewText(R.id.appwidget_text, _sharedPrefs.getString(KEY_PREFS_ACTIVE_VIRTUE, "0"));

        //AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        //appWidgetManager.updateAppWidget(awID, rv);

                //awp = new VirtueWidget();
        //ourRemoteView.setFloat(R.id.textview, "setTextSize", 30);
        _prefsEditor.putString(KEY_PREFS_ACTIVE_VIRTUE, text);
        _prefsEditor.commit();
    }

    public void saveTodaysRating(String text) {
        _prefsEditor.putString(KEY_PREFS_TODAYS_RATING, text);
        _prefsEditor.commit();
    }

    public Uri getRingtone() {
        String rt = _sharedPrefs.getString(KEY_PREFS_RINGTONE, "0");
        return Uri.parse(rt);
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
                break;
            case 1:
                _prefsEditor.putString(KEY_PREFS_V1_RATING, String.valueOf(rating));
                break;
            case 2:
                _prefsEditor.putString(KEY_PREFS_V2_RATING, String.valueOf(rating));
                break;
            case 3:
                _prefsEditor.putString(KEY_PREFS_V3_RATING, String.valueOf(rating));
                break;
            case 4:
                _prefsEditor.putString(KEY_PREFS_V4_RATING, String.valueOf(rating));
                break;
            case 5:
                _prefsEditor.putString(KEY_PREFS_V5_RATING, String.valueOf(rating));
                break;
            case 6:
                _prefsEditor.putString(KEY_PREFS_V6_RATING, String.valueOf(rating));
                break;
            case 7:
                _prefsEditor.putString(KEY_PREFS_V7_RATING, String.valueOf(rating));
                break;
            case 8:
                _prefsEditor.putString(KEY_PREFS_V8_RATING, String.valueOf(rating));
                break;
            case 9:
                _prefsEditor.putString(KEY_PREFS_V9_RATING, String.valueOf(rating));
                break;
            case 10:
                _prefsEditor.putString(KEY_PREFS_V10_RATING, String.valueOf(rating));
                break;
            case 11:
                _prefsEditor.putString(KEY_PREFS_V11_RATING, String.valueOf(rating));
                break;
            case 12:
                _prefsEditor.putString(KEY_PREFS_V12_RATING, String.valueOf(rating));
                break;
            case 13:
                _prefsEditor.putString(KEY_PREFS_V13_RATING, String.valueOf(rating));
                break;
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
        int minute = 00;

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

    public int getChangeVirtueDaysLeft() {
        long differenceTime = System.currentTimeMillis() - Long.parseLong(getChangeVirtueTime());
        int days = (int) (differenceTime / (1000*60*60*24));
        int hours = (int) ((differenceTime - (1000*60*60*24*days)) / (1000*60*60));
        int min = (int) (differenceTime - (1000*60*60*24*days) - (1000*60*60*hours)) / (1000*60);
        return days*-1;
    }
    public void setLastSurveyTime() {
         String time = String.valueOf(System.currentTimeMillis());
        _prefsEditor.putString(KEY_PREFS_SURVEY_TIME,time);
        _prefsEditor.commit();
    }
    public long getLastSurveyTime() {
        String st =  _sharedPrefs.getString(KEY_PREFS_SURVEY_TIME, "0");
        lastSurveyTime = Long.parseLong(st);
        return lastSurveyTime;
    }
    public Boolean getVibrate() {
        return  _sharedPrefs.getBoolean(KEY_PREFS_VIBRATE, true);
    }
    public Boolean getTheme() {
        return  _sharedPrefs.getBoolean(KEY_PREFS_THEME, true);
    }
    public void saveWidgetId(int appWidgetId) {
        _prefsEditor.putInt(KEY_PREFS_WIDGET_ID, appWidgetId);
        _prefsEditor.commit();
    }
    public int getWidgetId() {
        return  _sharedPrefs.getInt(KEY_PREFS_WIDGET_ID, 0);
    }
    public int getWidgetTextColor() {
        return  _sharedPrefs.getInt(KEY_PREFS_WIDGET_TEXT_COLOR, 0);
    }
    public void saveWidgetTextColor(int widgetTextColor) {
        _prefsEditor.putInt(KEY_PREFS_WIDGET_TEXT_COLOR, widgetTextColor);
        _prefsEditor.commit();
    }

    public float getWidgetTextSize() {
        return  _sharedPrefs.getFloat(KEY_PREFS_WIDGET_TEXT_SIZE, 42);
    }

    public void saveWidgetTextSize(float size) {
        _prefsEditor.putFloat(KEY_PREFS_WIDGET_TEXT_SIZE, size);
        _prefsEditor.commit();
    }

    public void regenerateReminder() {
        int hour = Integer.parseInt(_sharedPrefs.getString(KEY_PREFS_TIME, "0").split(":")[0]);
        int minute = Integer.parseInt(_sharedPrefs.getString(KEY_PREFS_TIME, "0").split(":")[1]);

        // Create a new calendar and start with the time now
        Calendar nightlyReminder = Calendar.getInstance();
        nightlyReminder.setTimeInMillis(System.currentTimeMillis());

        // Set to default time
        nightlyReminder.set(Calendar.HOUR_OF_DAY, hour);
        nightlyReminder.set(Calendar.MINUTE, minute); // Reminder will trigger at 8am
        nightlyReminder.set(Calendar.SECOND, 0);
        notificationReceiver.setReminder(context, nightlyReminder.getTimeInMillis());
    }
}