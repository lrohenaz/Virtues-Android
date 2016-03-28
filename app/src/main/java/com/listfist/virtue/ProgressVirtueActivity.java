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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_virtue);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent i = getIntent();
        Integer vid = i.getIntExtra("virtue", 0);
        color = i.getIntExtra("color", 0);

        switch (vid) {
            case 0:
                break;
            case 1:
                datasetTitle = getResources().getString(R.string.v1_title);
                break;
            case 2:
                datasetTitle = getResources().getString(R.string.v2_title);
                break;
            case 3:
                datasetTitle = getResources().getString(R.string.v3_title);
                break;
            case 4:
                datasetTitle = getResources().getString(R.string.v4_title);
                break;
            case 5:
                datasetTitle = getResources().getString(R.string.v5_title);
                break;
            case 6:
                datasetTitle = getResources().getString(R.string.v6_title);
                break;
            case 7:
                datasetTitle = getResources().getString(R.string.v7_title);
                break;
            case 8:
                datasetTitle = getResources().getString(R.string.v8_title);
                break;
            case 9:
                datasetTitle = getResources().getString(R.string.v9_title);
                break;
            case 10:
                datasetTitle = getResources().getString(R.string.v10_title);
                break;
            case 11:
                datasetTitle = getResources().getString(R.string.v11_title);
                break;
            case 12:
                datasetTitle = getResources().getString(R.string.v12_title);
                break;
            case 13:
                datasetTitle = getResources().getString(R.string.v13_title);
                break;
        }
        getSupportActionBar().setTitle(datasetTitle);
        cursor = selectRecords();
        BarChart chart = (BarChart) findViewById(R.id.bchart);
        BarData data = new BarData(getXAxisValues(), getDataSet());
        chart.setData(data);
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

    private void updateList() {
        // Get the view to stick out data
        LinearLayout container = (LinearLayout) findViewById(R.id.records);
        Cursor mCursor = selectRecords();
        if (mCursor != null) {
            for (int i = 0; i < mCursor.getCount(); i++) {
                // Create a new horizontal layout
                LinearLayout LL = new LinearLayout(this);
                LL.setOrientation(LinearLayout.HORIZONTAL);
                LL.setPadding(20, 20, 20, 20);
                ViewGroup.LayoutParams LLParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                LL.setLayoutParams(LLParams);

                TextView dummyView = new TextView(this);
                ViewGroup.LayoutParams dummyParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dummyView.setLayoutParams(dummyParams);

                Log.d(TAG, "Record " + i + 1 + "/" + mCursor.getCount() + " Date: " + mCursor.getString(1));
                Log.d(TAG, "virtue 2: " + mCursor.getInt(3) + " v2 type: " + mCursor.getInt(3));
                StringBuilder sb = new StringBuilder();

                for (int x = 1; x < 15; x++) { //id is at 0, skip it
                    // 0=id 1=datetime 2=virtue 1... 15=virtue 13
                    if (x > 1) {
                        sb.append(mCursor.getInt(x) + "\t\t");
                        Log.d(TAG, "column " + mCursor.getColumnName(x) + " - " + mCursor.getInt(x));
                    } else {
                        if (x == 1) { // Append date
                            switch (mCursor.getInt(x)) {
                                case 0:
                                    sb.append("Sun");
                                    break;
                                case 1:
                                    sb.append("Mon");
                                    break;
                                case 2:
                                    sb.append("Tue");
                                    break;
                                case 3:
                                    sb.append("Wed");
                                    break;
                                case 4:
                                    sb.append("Thu");
                                    break;
                                case 5:
                                    sb.append("Fri");
                                    break;
                                case 6:
                                    sb.append("Sat");
                                    break;
                            }
                            sb.append("\t\t\t\t\t\t\t");

                        }
                        Log.d(TAG, "column " + mCursor.getColumnName(x) + " - " + mCursor.getString(x) + " - or - " + mCursor.getInt(x));
                    }
                }
                dummyView.setText(sb);
                LL.addView(dummyView);
                if (container != null) {
                    container.addView(LL);
                }
                mCursor.moveToNext();
            }
        }
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
        ArrayList<IBarDataSet> dataSets = null;

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        BarEntry v1e1 = new BarEntry(2, 0); // Jan
        valueSet1.add(v1e1);
        BarEntry v1e2 = new BarEntry(1, 1); // Feb
        valueSet1.add(v1e2);
        BarEntry v1e3 = new BarEntry(0, 2); // Mar
        valueSet1.add(v1e3);
        BarEntry v1e4 = new BarEntry(2, 3); // Apr
        valueSet1.add(v1e4);
        BarEntry v1e5 = new BarEntry(1, 4); // May
        valueSet1.add(v1e5);
        BarEntry v1e6 = new BarEntry(4, 5); // Jun
        valueSet1.add(v1e6);
        BarEntry v1e7 = new BarEntry(3, 6); // Jul
        valueSet1.add(v1e7);
        BarEntry v1e8 = new BarEntry(3, 7); // Aug
        valueSet1.add(v1e8);
        BarEntry v1e9 = new BarEntry(2, 8); // Sept
        valueSet1.add(v1e9);
        BarEntry v1e10 = new BarEntry(3, 9); // Oct
        valueSet1.add(v1e10);
        BarEntry v1e11 = new BarEntry(4, 10); // Nov
        valueSet1.add(v1e11);
        BarEntry v1e12 = new BarEntry(5, 11); // Dec
        valueSet1.add(v1e12);


        BarDataSet barDataSet1 = new BarDataSet(valueSet1, datasetTitle);
        barDataSet1.setColor(color);


        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        return dataSets;
    }

}
