package com.listfist.virtue;

import android.content.Context;
import android.content.res.TypedArray;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by Zero on 3/23/2016.
 */
public class TimePreference extends DialogPreference {
    private int lastHour=0;
    private int lastMinute=0;
    private TimePicker picker=null;

    NotificationReceiver notificationReceiver;
    Calendar alarmStartTime = Calendar.getInstance();

    public static int getHour(String time) {
        String[] pieces=time.split(":");

        return(Integer.parseInt(pieces[0]));
    }

    public static int getMinute(String time) {
        String[] pieces=time.split(":");

        return(Integer.parseInt(pieces[1]));
    }

    public TimePreference(Context ctxt, AttributeSet attrs) {
        super(ctxt, attrs);

        setPositiveButtonText("Set");
        setNegativeButtonText("Cancel");
        notificationReceiver = new NotificationReceiver();
    }

    @Override
    protected View onCreateDialogView() {
        picker=new TimePicker(getContext());

        return(picker);
    }

    @Override
    protected void onBindDialogView(View v) {
        super.onBindDialogView(v);

        picker.setCurrentHour(lastHour);
        picker.setCurrentMinute(lastMinute);
    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        super.onDialogClosed(positiveResult);

        if (positiveResult) {
            lastHour=picker.getCurrentHour();
            lastMinute=picker.getCurrentMinute();

            String time=String.valueOf(lastHour)+":"+String.valueOf(lastMinute);

            String notificationHour = String.valueOf(getHour(time));
            String notificationMinute = String.valueOf(getMinute(time));

            //alarmStartTime.setTimeZone(TimeZone.getDefault());
            Calendar now = Calendar.getInstance();
            alarmStartTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(notificationHour));
            alarmStartTime.set(Calendar.MINUTE, Integer.parseInt(notificationMinute));
            alarmStartTime.set(Calendar.SECOND, 0);

            if (now.after(alarmStartTime)) {
                Log.d("junk", "Added a day");
                alarmStartTime.add(Calendar.DATE, 1);
            }
            notificationReceiver.setReminder(getContext(), alarmStartTime.getTimeInMillis());
            Log.d("junk", "Notification set for everyday " + alarmStartTime.getTime());

            if (callChangeListener(time)) {
                persistString(time);
            }
        }
    }

    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        return(a.getString(index));
    }

    @Override
    protected void onSetInitialValue(boolean restoreValue, Object defaultValue) {
        String time=null;

        if (restoreValue) {
            if (defaultValue==null) {
                time=getPersistedString("00:00");
            }
            else {
                time=getPersistedString(defaultValue.toString());
            }
        }
        else {
            time=defaultValue.toString();
        }

        lastHour=getHour(time);
        lastMinute=getMinute(time);
    }
}