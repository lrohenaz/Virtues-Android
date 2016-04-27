package com.listfist.virtue;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.support.design.widget.Snackbar;
import android.support.v4.os.ResultReceiver;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by Zero on 3/23/2016.
 */
public class NotificationReceiver extends BroadcastReceiver {
    // Incoming actions from alarm service
    final public static String ACTION_NIGHTLY = "com.listfist.action.NIGHTLY";
    final public static String ACTION_1WEEK = "com.listfist.action.1TIME1WEEK";
    // Action to send to splash activity
    final public static String ACTION_CHANGE_VIRTUE = "change_virtue";
    private static final String TAG = SplashActivity.class.getName();
    private Context ctxt;

    @Override
    public void onReceive(Context context, Intent intent) {
            ctxt=context;

            // WAKE
            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, TAG);
            wl.acquire();   //Acquire the lock

            // Get shared preferences
            AppPreferences _appPrefs = new AppPreferences(context);
            final Uri activeRingtone = _appPrefs.getRingtone();

            // Get notification manager
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            // Create notification icon
            Bitmap bm = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher),
                    context.getResources().getDimensionPixelSize(android.R.dimen.notification_large_icon_width),
                    context.getResources().getDimensionPixelSize(android.R.dimen.notification_large_icon_height),
                    true);


        // Build & send notifications
        String action=intent.getAction();

        if(action.equals(Intent.ACTION_BOOT_COMPLETED)) {
            // Check to see if todays timer exists, otherwise create it.
            // Check nightly reminder is set, otherwise set it to default time
            // Check if widget needs update
            _appPrefs.regenerateReminder();
            // update widget
            if(_appPrefs.getWidgetId()!=0) {
                VirtueWidget.updateAppWidget(ctxt, AppWidgetManager.getInstance(context),_appPrefs.getWidgetId());
            }
        }

            if(equals(action, ACTION_NIGHTLY)){
                Intent mIntent = new Intent(context, VirtueSurvey.class);
                Bundle bundle = new Bundle();
                mIntent.putExtras(bundle);
                PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                Uri soundUri = _appPrefs.getRingtone();
                Log.d(TAG, "Notifying with " + soundUri.toString());
                NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
                builder.setContentTitle("Virtues");
                builder.setContentText("So how did you do?");
                builder.setSubText("Time to make your journal entry for today...");
                builder.setNumber(102);
                builder.setContentIntent(pendingIntent);
                builder.setTicker("Log your virtues!");
                builder.setSmallIcon(R.drawable.ic_stat_action_assessment);
                builder.setLargeIcon(bm);
                builder.setAutoCancel(true);
                builder.setSound(soundUri);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    builder.setPriority(Notification.PRIORITY_HIGH);
                }
                Notification notification = builder.build();
                notification.flags |= Notification.FLAG_AUTO_CANCEL | Notification.FLAG_SHOW_LIGHTS;
                if(_appPrefs.getVibrate()) {
                    builder.setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 });
                }
                notification.ledARGB = 0xFFFFA500;
                notification.ledOnMS = 800;
                notification.ledOffMS = 1000;
                int NOTIFICATION_ID = 888;
                notificationManager.notify(NOTIFICATION_ID, notification);
            }
            else if(equals(action, ACTION_1WEEK)){
                Intent mIntent = new Intent(context, SplashActivity.class);
                Bundle bundle = new Bundle();
                mIntent.putExtras(bundle);
                mIntent.setAction(ACTION_CHANGE_VIRTUE);
                PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                Uri soundUri = _appPrefs.getRingtone();
                NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
                builder.setContentTitle("Virtues");
                builder.setContentText("Choose a Virtue");
                builder.setSubText("After 7 days of " + _appPrefs.getActiveVirtueName() + " its time to switch it up.");
                builder.setNumber(101);
                builder.setContentIntent(pendingIntent);
                builder.setTicker("Change your virtue!");
                builder.setSmallIcon(R.drawable.ic_stat_social_school);
                builder.setLargeIcon(bm);
                builder.setAutoCancel(true);
                builder.setSound(soundUri);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    builder.setPriority(Notification.PRIORITY_HIGH);
                }
                Notification notification = builder.build();
                notification.flags |= Notification.FLAG_AUTO_CANCEL | Notification.FLAG_SHOW_LIGHTS;
                if(_appPrefs.getVibrate()) {
                    builder.setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 });
                }

                notification.ledARGB = 0xFFFFA500;
                notification.ledOnMS = 800;
                notification.ledOffMS = 1000;
                int NOTIFICATION_ID = 777;
                notificationManager.notify(NOTIFICATION_ID, notification);
            }

            //Release the lock
            wl.release();
        }

        public void setReminder(Context context, long time)
        {
            AlarmManager am=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(context, NotificationReceiver.class);
            intent.setAction(ACTION_NIGHTLY);
            PendingIntent pi = PendingIntent.getBroadcast(context, 2, intent, PendingIntent.FLAG_ONE_SHOT);
            am.setRepeating(AlarmManager.RTC_WAKEUP, time, AlarmManager.INTERVAL_DAY, pi);
            Log.d(TAG, "Reminder was set");
        }

        public void setOnetimeTimer(Context context, Calendar c){
            AlarmManager am=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(context, NotificationReceiver.class);
            intent.setAction(ACTION_1WEEK);
            PendingIntent pi = PendingIntent.getBroadcast(context, 1, intent, PendingIntent.FLAG_ONE_SHOT);
            am.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pi);
        }

    public static boolean equals(Object a, Object b) {
        return (a == b) || (a != null && a.equals(b));
    }
}
