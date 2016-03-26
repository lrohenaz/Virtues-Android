package com.listfist.virtue;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

/**
 * Created by Zero on 3/23/2016.
 */
public class NotificationService extends IntentService {
    private PendingIntent pendingIntent;
    Notification notification;

    public NotificationService() {
        super("notificationService");
    }
    private static final String TAG = SplashActivity.class.getName();
    @Override
    protected void onHandleIntent(Intent intent) {
        Context context = this.getApplicationContext();
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Bitmap bm = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.example_appwidget_preview),
                getResources().getDimensionPixelSize(android.R.dimen.notification_large_icon_width),
                getResources().getDimensionPixelSize(android.R.dimen.notification_large_icon_height),
                true);
        Intent mIntent = new Intent(this, VirtueSurvey.class);
        Bundle bundle = new Bundle();
        bundle.putString("test", "test");
        mIntent.putExtras(bundle);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        Resources res = this.getResources();
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("Virtues");
        builder.setContentText("It's time to reflect on today's work.");
        builder.setSubText("Click to log your performance.");
        builder.setNumber(101);
        builder.setContentIntent(pendingIntent);
        builder.setTicker("Ticker Value");
        builder.setSmallIcon(R.drawable.example_appwidget_preview);
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
        Log.i(TAG, "Notification sent.");

    }

}
