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
import android.widget.Toast;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Zero on 3/23/2016.
 */
public class NotificationReceiver extends BroadcastReceiver {
        final public static String ONE_TIME = "onetime";
    private static final String TAG = SplashActivity.class.getName();
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG,"OnReceive");
            //Intent notificationService = new Intent(context, NotificationService.class);

            //notificationService.setData((Uri.parse("custom://" + System.currentTimeMillis())));
            //context.startService(notificationService);

            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "YOUR TAG");
            //Acquire the lock
            wl.acquire();

            //You can do the processing here update the widget/remote views.
            Bundle extras = intent.getExtras();
            StringBuilder msgStr = new StringBuilder();

            if(extras != null && extras.getBoolean(ONE_TIME, Boolean.FALSE)){
                msgStr.append("One time Timer : ");
            }
            Format formatter = new SimpleDateFormat("hh:mm:ss a", Locale.getDefault());
            msgStr.append(formatter.format(new Date()));

            Toast.makeText(context, msgStr, Toast.LENGTH_LONG).show();

            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            //Bitmap bm = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.example_appwidget_preview),
            //        getResources().getDimensionPixelSize(android.R.dimen.notification_large_icon_width),
            //        getResources().getDimensionPixelSize(android.R.dimen.notification_large_icon_height),
             //       true);
            Intent mIntent = new Intent(context, VirtueSurvey.class);
            Bundle bundle = new Bundle();
            bundle.putString("test", "test");
            mIntent.putExtras(bundle);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            //Resources res = this.getResources();
            Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
            builder.setContentTitle("Virtues");
            builder.setContentText("It's time to reflect on today's work.");
            builder.setSubText("Click to log your performance.");
            builder.setNumber(101);
            builder.setContentIntent(pendingIntent);
            builder.setTicker("Ticker Value");
            builder.setSmallIcon(R.drawable.example_appwidget_preview);
            //builder.setLargeIcon(bm);
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
            Log.i(TAG, "Notification sent.");

            //Release the lock
            wl.release();
        }

        public void setReminder(Context context, long time)
        {
            AlarmManager am=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(context, NotificationReceiver.class);
            intent.putExtra(ONE_TIME, Boolean.FALSE);
            PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
            //After after 30 seconds
            am.setRepeating(AlarmManager.RTC_WAKEUP, time, AlarmManager.INTERVAL_DAY, pi);
        }

        public void setOnetimeTimer(Context context){
            AlarmManager am=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(context, NotificationReceiver.class);
            intent.putExtra(ONE_TIME, Boolean.TRUE);
            PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
            am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), pi);
        }
}
