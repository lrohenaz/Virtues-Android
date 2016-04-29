package com.listfist.virtue;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class VirtueSurveyHistory extends AppCompatActivity {
    private static final String TAG = SplashActivity.class.getName();
    private BarChart mChart;
    Boolean lightTheme;
    private AppPreferences _appPrefs;

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
        setContentView(R.layout.activity_virtue_survey_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("History");

        mChart = (HorizontalBarChart) findViewById(R.id.historyChart);
        mChart.setDrawGridBackground(false);
        mChart.setDescription("");

        // scaling can now only be done on x- and y-axis separately
        mChart.setPinchZoom(false);
        mChart.setDragEnabled(true);
        mChart.setDrawBarShadow(false);
        mChart.setDrawValueAboveBar(false);

        // Left = top - disabled
        mChart.getAxisLeft().setEnabled(false);
        // right = Bottom
        mChart.getAxisRight().setAxisMaxValue(100);
        mChart.getAxisRight().setAxisMinValue(0);
        mChart.getAxisRight().setDrawGridLines(false);
        mChart.getAxisRight().setDrawZeroLine(true);
        mChart.getAxisRight().setTextSize(9f);
        mChart.getAxisRight().setShowOnlyMinMax(true);

        // Vertical Axis
        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); // Date Label Position. Bottom = left
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setTextSize(9f);

        mChart.setVisibleYRangeMaximum(4, YAxis.AxisDependency.RIGHT); // X-Axis

        Legend l = mChart.getLegend();
        l.setFormSize(8f);
        l.setFormToTextSpace(4f);
        l.setXEntrySpace(6f);
        l.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);
        l.setWordWrapEnabled(true);
        if(lightTheme) {

        }
        else {
            l.setTextColor(getResources().getColor(R.color.colorAccentDark));
            xAxis.setTextColor(getResources().getColor(R.color.colorAccentDark)); // y axis
            mChart.getAxisRight().setTextColor(getResources().getColor(R.color.colorAccentDark)); // x-axis
        }

        // IMPORTANT: When using negative values in stacked bars, always make sure the negative values are in the array first
        ArrayList<BarEntry> yValues = new ArrayList<BarEntry>();

        DatabaseRecordsHandler dbr = new DatabaseRecordsHandler(getApplicationContext());
        // First value in this set must be oldest
        Cursor crsr = dbr.getCursor();
        String[] xVals = new String[crsr.getCount()];
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());
        crsr.moveToLast(); // Start with newest record

        for(int x=0;x<crsr.getCount();x++) { // For each record
            String[] parts;
            parts = crsr.getString(1).split(" "); // Default format 2016-04-23 22:30:49
            String recorddate = parts[0];
            String[] dateParts = recorddate.split("-");
            String year = dateParts[0];
            String month = dateParts[1];
            String day = dateParts[2];
            Log.d(TAG,"Year: "+ year +" Month: "+ month);

            c.set(Calendar.YEAR,Integer.parseInt(year));
            c.set(Calendar.MONTH,Integer.parseInt(month)-1); // 0 based
            c.set(Calendar.DAY_OF_MONTH,Integer.parseInt(day));

            Date date = c.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("M/dd");
            String dateString = sdf.format(date);
            xVals[crsr.getCount()-(x+1)] = dateString;
            yValues.add(new BarEntry(new float[]{crsr.getInt(2),crsr.getInt(3),crsr.getInt(4),crsr.getInt(5),crsr.getInt(6),crsr.getInt(7),crsr.getInt(8),crsr.getInt(9),crsr.getInt(10),crsr.getInt(11),crsr.getInt(12),crsr.getInt(13),crsr.getInt(14)}, crsr.getCount()-(x+1)));
            crsr.moveToPrevious();
        }

        int stacksize = 13;
        BarDataSet set = new BarDataSet(yValues, "History");
        set.setValueFormatter(new MyValueFormatter());
        set.setValueTextSize(7f);
        set.setBarSpacePercent(40f);
        set.setColors(getColors(stacksize));
        String[] sl = new String[stacksize];
        for(int x=0;x<stacksize;x++) {
            sl[x] = _appPrefs.getCustomTitle(x+1);
        }
        set.setStackLabels(sl);
        BarData data = new BarData(xVals, set);
        mChart.setData(data);
        mChart.invalidate();
    }

    private ArrayList<Integer> getColors(int stacksize) {
        ArrayList<Integer> colors = new ArrayList<Integer>(stacksize);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);
        for (int c : ColorTemplate.PASTEL_COLORS)
            if(colors.size()<stacksize) {
                colors.add(c);
            }

            if(colors.size()<stacksize) {
                colors.add(ColorTemplate.getHoloBlue());
            }
        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            if(colors.size()<stacksize) {
                colors.add(c);
            }
        return colors;
    }
}
