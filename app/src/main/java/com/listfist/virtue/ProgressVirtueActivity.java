package com.listfist.virtue;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ProgressVirtueActivity extends AppCompatActivity {
    private static final String TAG = SplashActivity.class.getName();
    private SQLiteDatabase database;
    public final static String RECORD_TABLE = "virtueLog"; // name of table

    public final static String RECORD_ID = "_id"; // id value for record
    public final static String RECORD_TIME = "dt";  // datetime of record
    public final static String RECORD_1 = "v1";
    public final static String RECORD_2 = "v2";
    public final static String RECORD_3 = "v3";
    public final static String RECORD_4 = "v4";
    public final static String RECORD_5 = "v5";
    public final static String RECORD_6 = "v6";
    public final static String RECORD_7 = "v7";
    public final static String RECORD_8 = "v8";
    public final static String RECORD_9 = "v9";
    public final static String RECORD_10 = "v10";
    public final static String RECORD_11 = "v11";
    public final static String RECORD_12 = "v12";
    public final static String RECORD_13 = "v13";
    private Cursor cursor;
    private int color;
    private String datasetTitle;
    Integer vid;
    private AppPreferences _appPrefs;
    Boolean lightTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        _appPrefs = new AppPreferences(getApplicationContext());
        lightTheme =_appPrefs.getTheme();
        if(_appPrefs.getTheme()) {
            setTheme(R.style.AppTheme);
            lightTheme =true;
        }
        else {
            setTheme(R.style.AppThemeDark);
            lightTheme =false;
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_virtue);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent i = getIntent();
        vid = i.getIntExtra("virtue", 0);
        color = i.getIntExtra("color", 0);

        // Set up database
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(this);
        database = dbHelper.getWritableDatabase();

        switch (vid) {
            case 0:
                break;
            case 1:
                datasetTitle = _appPrefs.getCustomTitle(1);
                break;
            case 2:
                datasetTitle = _appPrefs.getCustomTitle(2);
                break;
            case 3:
                datasetTitle = _appPrefs.getCustomTitle(3);
                break;
            case 4:
                datasetTitle = _appPrefs.getCustomTitle(4);
                break;
            case 5:
                datasetTitle = _appPrefs.getCustomTitle(5);
                break;
            case 6:
                datasetTitle = _appPrefs.getCustomTitle(6);
                break;
            case 7:
                datasetTitle = _appPrefs.getCustomTitle(7);
                break;
            case 8:
                datasetTitle = _appPrefs.getCustomTitle(8);
                break;
            case 9:
                datasetTitle = _appPrefs.getCustomTitle(9);
                break;
            case 10:
                datasetTitle = _appPrefs.getCustomTitle(10);
                break;
            case 11:
                datasetTitle = _appPrefs.getCustomTitle(11);
                break;
            case 12:
                datasetTitle = _appPrefs.getCustomTitle(12);
                break;
            case 13:
                datasetTitle = _appPrefs.getCustomTitle(13);
                break;
        }
        getSupportActionBar().setTitle(datasetTitle);
        cursor = selectRecords();
        BarChart chart = (BarChart) findViewById(R.id.bchart);
        BarData data = new BarData(getXAxisValues(), getDataSet());

        if(!lightTheme) {
            chart.setDescriptionColor(Color.DKGRAY);
            chart.getXAxis().setTextColor(getResources().getColor(R.color.colorAccentDark));
            chart.getAxisLeft().setTextColor(getResources().getColor(R.color.colorAccentDark));
            chart.getLegend().setTextColor(getResources().getColor(R.color.colorAccentDark));
        }
        chart.setData(data);
        chart.getAxisLeft().setAxisMaxValue(5);
        chart.getAxisLeft().setAxisMinValue(0);
        chart.getAxisRight().setEnabled(false);
        chart.setDescription("Rating over time");
        chart.animateXY(1500, 1500);


        chart.invalidate();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public Cursor selectRecords() {
        String[] cols = new String[]{RECORD_ID, RECORD_TIME, RECORD_1, RECORD_2, RECORD_3, RECORD_4, RECORD_5, RECORD_6, RECORD_7, RECORD_8, RECORD_9, RECORD_10, RECORD_11, RECORD_12, RECORD_13};
        if (database != null) {
            String selectQuery = "SELECT * FROM " + RECORD_TABLE;
            Log.d(TAG, selectQuery);
            Cursor mCursor = database.rawQuery(selectQuery, null);

            //Cursor mCursor = database.query(true, RECORD_TABLE, cols, null                    , null, null, null, null, null);
            if (mCursor != null) {
                mCursor.moveToFirst();
            }
            return mCursor; // iterate to get each value.
        } else {
            return null;
        }
    }

    private double getMonthAvg(int month) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());
        int thisYear = c.get(Calendar.YEAR); // Note: Months start at 0 not 1

        float avg=0.0f;

        Log.d(TAG,"Current month: "+ month);
        String query = "SELECT * FROM "+ RECORD_TABLE +" WHERE strftime('%m', "+ RECORD_TIME +") = '"+ String.format("%02d", month) +"' AND strftime('%Y', "+ RECORD_TIME +") = '"+ thisYear +"'";

        Cursor curse = database.rawQuery(query, null);
        int ctr=0;
        float accu=0.0f;
        //Cursor mCursor = database.query(true, RECORD_TABLE, cols, null                    , null, null, null, null, null);
        if (curse != null) {

            curse.moveToFirst();

            Log.d(TAG,"Found "+ curse.getCount() +" records for month "+ month);
            for(int x=0;x<curse.getCount();x++) {
                Log.d(TAG,"Record "+ x +": "+ curse.getInt(vid-1+2)); // vid is from extras, 1 based
                int val = curse.getInt(vid-1+2);
                accu = accu + val;
                ctr++;
                avg = accu / ctr;
                curse.moveToNext();
            }
            curse.close();
            Log.d(TAG,"Accu: "+ accu +" ctr "+ ctr +" Avg Score: "+ avg);
        }

        return avg;
    }

    public List<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();
        xAxis.add("JAN");
        xAxis.add("FEB");
        xAxis.add("MAR");
        xAxis.add("APR");
        xAxis.add("MAY");
        xAxis.add("JUN");
        xAxis.add("JUL");
        xAxis.add("AUG");
        xAxis.add("SEPT");
        xAxis.add("OCT");
        xAxis.add("NOV");
        xAxis.add("DEC");
        return xAxis;
    }

    public List<IBarDataSet> getDataSet() {
        // get current month
        // ToDo - make chart end with current month and go back 1 year
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());
        int thisMonth = c.get(Calendar.MONTH)+1; // Note: Months start at 0 not 1

        ArrayList<IBarDataSet> dataSets = null;

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        for(int z=0;z<12;z++) {
            double num = getMonthAvg(z);
            BarEntry v1e1 = new BarEntry((float) num, z-1); // Jan
            valueSet1.add(v1e1);
        }
        Log.d(TAG,"Adding "+ valueSet1.size() +" values");
        BarDataSet barDataSet1 = new BarDataSet(valueSet1, datasetTitle);
        barDataSet1.setColor(color);
        if(!lightTheme) {
            barDataSet1.setValueTextColor(getResources().getColor(R.color.colorAccentDark));
        }
        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        return dataSets;
    }

}
