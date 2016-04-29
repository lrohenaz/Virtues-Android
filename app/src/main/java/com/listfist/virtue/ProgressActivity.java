package com.listfist.virtue;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class ProgressActivity extends AppCompatActivity implements OnChartValueSelectedListener {
    private static final String TAG = SplashActivity.class.getName();
    private SQLiteDatabase database;
    public final static String RECORD_TABLE="virtueLog"; // name of table
    private Cursor cursor;


    private PieChart mChart;

    private Typeface tf;
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
        setContentView(R.layout.activity_progress);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            if(lightTheme) {
                actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary))); // set your desired color

            }
            else {
                actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimaryDark))); // set your desired color
            }
        }


        // Set up database
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(this);
        database = dbHelper.getWritableDatabase();

        cursor = selectRecords();
        mChart = (PieChart) findViewById(R.id.chart1);
        mChart.setUsePercentValues(false);
        mChart.setDescription("");
        mChart.setExtraOffsets(5, 10, 5, 5);

        mChart.setDragDecelerationFrictionCoef(0.95f);

        // Fonts
        tf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        mChart.setCenterTextTypeface(Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf"));

        mChart.setDrawHoleEnabled(true);
        if(lightTheme) {
            mChart.setHoleColor(Color.WHITE);
            mChart.setTransparentCircleColor(Color.WHITE);
        }
        else {
            mChart.setHoleColor(R.color.black_overlay);
            mChart.setTransparentCircleColor(R.color.colorAccentDark);
        }
        mChart.setCenterText(generateCenterSpannableText());
        mChart.setTransparentCircleAlpha(110);
        mChart.setHoleRadius(58f);
        mChart.setTransparentCircleRadius(61f);

        mChart.setDrawCenterText(true);

        mChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        mChart.setRotationEnabled(true);
        mChart.setHighlightPerTapEnabled(true);

        // add a selection listener
        mChart.setOnChartValueSelectedListener(this);

        setData(13, 65); // 5 stars * 13 virtues = 65 TOTAL PIE

        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);

        // Legend
        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);
        l.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);
        l.setWordWrapEnabled(true);
        if(lightTheme) {

        }
        else {
            l.setTextColor(getResources().getColor(R.color.colorAccentDark));
        }
        // customize display:
        mChart.setDrawSliceText(false);
        for (IDataSet<?> set : mChart.getData().getDataSets())
            set.setDrawValues(!set.isDrawValuesEnabled());

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pie, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                break;
            case R.id.actionHistory:
                Intent intent = new Intent(ProgressActivity.this,VirtueSurveyHistory.class);
                ProgressActivity.this.startActivity(intent);
                return true;
            case R.id.actionClearProgress: {
                //for (IDataSet<?> set : mChart.getData().getDataSets())
                //    set.setDrawValues(!set.isDrawValuesEnabled());
                clearProgress();
                View contentView = findViewById(android.R.id.content);
                Snackbar.make(contentView, "Cleared progress. Ahh a fresh start.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;
            }

        }
        return true;
    }
    public Cursor selectRecords() {
        //String[] cols = new String[] {RECORD_ID, RECORD_TIME, RECORD_1, RECORD_2, RECORD_3, RECORD_4, RECORD_5, RECORD_6, RECORD_7, RECORD_8, RECORD_9, RECORD_10, RECORD_11, RECORD_12, RECORD_13};
        if(database!=null) {
            String selectQuery = "SELECT _id, strftime('%w', dt), v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13 FROM "+ RECORD_TABLE;
            Cursor mCursor = database.rawQuery(selectQuery, null);
            Log.d(TAG,"Query: "+ selectQuery);
            //Cursor mCursor = database.query(true, RECORD_TABLE, cols, null                    , null, null, null, null, null);
            if (mCursor != null) {
                mCursor.moveToFirst();
            }
            return mCursor; // iterate to get each value.
        }
        else {
            return null;
        }
    }

    private void clearProgress() {
        database.delete(RECORD_TABLE, null, null);
        mChart.clear();
       // mChart.removeAllViews();
       // mChart.invalidate();
    }


    private void setData(int count, float range) {
        float mult = range;
        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
        // IMPORTANT: In a PieChart, no values (Entry) should have the same
        // xIndex (even if from different DataSets), since no values can be
        // drawn above each other.
        //for (int i = 0; i < count + 1; i++) {
        //    yVals1.add(new Entry((float) (Math.random() * mult) + mult / 5, i));
        //}

        ArrayList<String> xVals = new ArrayList<String>();

        if(cursor!=null&&cursor.getCount()>0) {
            Log.d(TAG,"We've got "+ cursor.getCount() +" records and "+ cursor.getColumnCount() +" columns");
            // For each record, compute a new average for each characteristic and put into a new array

            // Use that new array to populate the pie chart based on the averages
            // A query to omit the records from n days old back would be good
            ArrayList<Integer> avgs = new ArrayList<Integer>();

            // Loop through each record
            for(int x=0;x<cursor.getCount();x++) {
                // Loop through each field
                if(x==0) {   // If this is the first record just put the first values in as the average
                    for(int p=0;p<13;p++) {
                        avgs.add(cursor.getInt(p+2));
                    }
                }
                else {
                    for(int p=0;p<13;p++) {
                        avgs.set(p, ((cursor.getInt(p+2) + avgs.get(p)) / (x + 1)));
                    }
                }
                cursor.moveToNext();
            }

            // Loop through averages array and populate the chart data
            Log.d(TAG,"We've got "+ count +" columns to add");
            for (int i = 0; i < count; i++) {
                Log.d(TAG,"Adding "+ avgs.get(i));
                yVals1.add(new Entry((float) avgs.get(i)+1, i));
            }
        }
        else {
            Log.d(TAG,"Nothing to show");
            View contentView = findViewById(android.R.id.content);
            Snackbar.make(contentView, "Record some data, come back, and we'll see what we can do for ya.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }

        //if(mCursor!=null) {
            for (int i = 1; i <= 13; i++) {
                switch(i) {
                    case 1:
                        xVals.add(_appPrefs.getCustomTitle(1));
                        break;
                    case 2:
                        xVals.add(_appPrefs.getCustomTitle(2));
                        break;
                    case 3:
                        xVals.add(_appPrefs.getCustomTitle(3));
                        break;
                    case 4:
                        xVals.add(_appPrefs.getCustomTitle(4));
                        break;
                    case 5:
                        xVals.add(_appPrefs.getCustomTitle(5));
                        break;
                    case 6:
                        xVals.add(_appPrefs.getCustomTitle(6));
                        break;
                    case 7:
                        xVals.add(_appPrefs.getCustomTitle(7));
                        break;
                    case 8:
                        xVals.add(_appPrefs.getCustomTitle(8));
                        break;
                    case 9:
                        xVals.add(_appPrefs.getCustomTitle(9));
                        break;
                    case 10:
                        xVals.add(_appPrefs.getCustomTitle(10));
                        break;
                    case 11:
                        xVals.add(_appPrefs.getCustomTitle(11));
                        break;
                    case 12:
                        xVals.add(_appPrefs.getCustomTitle(12));
                        break;
                    case 13:
                        xVals.add(_appPrefs.getCustomTitle(13));
                        break;
                }

            }
        //}
        //for (int i = 0; i < count + 1; i++)
            //xVals.add(mParties[i % mParties.length]);

        PieDataSet dataSet = new PieDataSet(yVals1, "Virtues");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());
        mChart.setData(data);

        // undo all highlights
        mChart.highlightValues(null);

        mChart.invalidate();
    }

    private SpannableString generateCenterSpannableText() {
        SpannableString s = new SpannableString("Virtues\nSized by Average Score");
        s.setSpan(new RelativeSizeSpan(1.9f), 0, 7, 0);
        s.setSpan(new StyleSpan(Typeface.NORMAL), 7, s.length(), 0);


        if(lightTheme) {
            s.setSpan(new ForegroundColorSpan(Color.GRAY), 7, s.length(), 0);
        }
        else {
            s.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorAccentDark)), 0, 7, 0);
            s.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorAccentDark)), 7, s.length(), 0);
        }
        return s;
    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

        if (e == null)
            return;
        Log.i("VAL SELECTED",
                "Value: " + e.getVal() + ", xIndex: " + e.getXIndex()
                        + ", DataSet index: " + dataSetIndex);
        Intent intent = new Intent(ProgressActivity.this, ProgressVirtueActivity.class);
        intent.putExtra("virtue", e.getXIndex()+1);
        intent.putExtra("color", mChart.getData().getColors()[e.getXIndex()]);
        startActivity(intent);
    }

    @Override
    public void onNothingSelected() {
        Log.i("PieChart", "nothing selected");
    }

}
