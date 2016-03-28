package com.listfist.virtue;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by Zero on 3/23/2016.
 */
public class NotificationReceiver extends BroadcastReceiver {
    final public static String ONE_TIME = "onetime";
    final public static String ACTION = "action";
    final public static String ACTION_NIGHTLY = "nightly";
    final public static String ACTION_CHANGE_VIRTUE = "change_virtue";
    final public static String ACTION_1WEEK = "1time1week";
    private static final String TAG = SplashActivity.class.getName();

        @Override
        public void onReceive(Context context, Intent intent) {
            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "YOUR TAG");
            //Acquire the lock
            wl.acquire();


            //You can do the processing here update the widget/remote views.
            String action=intent.getStringExtra(ACTION);

            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            Bitmap bm = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher),
                    context.getResources().getDimensionPixelSize(android.R.dimen.notification_large_icon_width),
                    context.getResources().getDimensionPixelSize(android.R.dimen.notification_large_icon_height),
                    true);
            if(equals(action, ACTION_NIGHTLY)){
                Intent mIntent = new Intent(context, VirtueSurvey.class);
                Bundle bundle = new Bundle();
                bundle.putString("test", "test");
                mIntent.putExtras(bundle);
                PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
                builder.setContentTitle("Virtues");
                builder.setContentText("It's time to reflect on today's work.");
                builder.setSubText("Click to log your performance.");
                builder.setNumber(102);
                builder.setContentIntent(pendingIntent);
                builder.setTicker("Ticker Value");
                builder.setSmallIcon(R.mipmap.ic_launcher);
                builder.setLargeIcon(bm);
                builder.setAutoCancel(true);
                builder.setSound(soundUri);
                builder.setPriority(Notification.PRIORITY_DEFAULT);
                Notification notification = builder.build();
                notification.flags |= Notification.FLAG_AUTO_CANCEL | Notification.FLAG_SHOW_LIGHTS;
                notification.defaults |= Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE;
                notification.ledARGB = 0xFFFFA500;
                notification.ledOnMS = 800;
                notification.ledOffMS = 1000;
                int NOTIFICATION_ID = 888;
                notificationManager.notify(NOTIFICATION_ID, notification);
            }
            else if(equals(action, ACTION_1WEEK)){
                Intent mIntent = new Intent(context, SplashActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(ACTION, ACTION_CHANGE_VIRTUE);
                mIntent.putExtras(bundle);
                PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
                builder.setContentTitle("Virtues");
                builder.setContentText("Choose a Virtue");
                builder.setSubText("You should change your virtue every 7 days");
                builder.setNumber(101);
                builder.setContentIntent(pendingIntent);
                builder.setTicker("Ticker Value");
                builder.setSmallIcon(R.mipmap.ic_launcher);
                builder.setLargeIcon(bm);
                builder.setAutoCancel(true);
                builder.setSound(soundUri);
                builder.setPriority(Notification.PRIORITY_DEFAULT);
                Notification notification = builder.build();
                notification.flags |= Notification.FLAG_AUTO_CANCEL | Notification.FLAG_SHOW_LIGHTS;
                notification.defaults |= Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE;
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
            intent.putExtra(ACTION, ACTION_NIGHTLY);
            PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
            am.setRepeating(AlarmManager.RTC_WAKEUP, time, AlarmManager.INTERVAL_DAY, pi);
            Log.d(TAG, "Reminder was set");
        }

        public void setOnetimeTimer(Context context, Calendar c){
            AlarmManager am=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(context, NotificationReceiver.class);
            intent.putExtra(ACTION, ACTION_1WEEK);
            PendingIntent pi = PendingIntent.getBroadcast(context, 1, intent, 0);
            am.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pi);
            //String cdate = c.get(Calendar.YEAR)+"-"+c.get(Calendar.MONTH)+"-"+c.get(Calendar.DAY_OF_MONTH) +" (day#:"+  c.get(Calendar.DAY_OF_YEAR)+")";
            //String ctime = c.get(Calendar.HOUR_OF_DAY)+":"+c.get(Calendar.MINUTE)+":"+c.get(Calendar.SECOND);
            //Log.d(TAG, "Virtue will change on "+ cdate +" at "+ ctime);
        }

    public static boolean equals(Object a, Object b) {
        return (a == b) || (a != null && a.equals(b));
    }
}
