package com.listfist.virtue;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ViewAnimator;



public class VirtueSurvey extends AppCompatActivity {
    private static Button btnNext, btnPrevious, my_button;
    private static ViewAnimator viewAnimator;
    private MyDatabaseHelper dbHelper;

    private SQLiteDatabase database;
    public final static String RECORD_TABLE="virtueLog"; // name of table
    private AppPreferences _appPrefs;
    Typeface face;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_virtue_survey);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        face= Typeface.createFromAsset(getAssets(), "OldEurope.ttf");
        dbHelper = new MyDatabaseHelper(this);
        database = dbHelper.getWritableDatabase();

        _appPrefs = new AppPreferences(getApplicationContext());
        final String activeVirtue = _appPrefs.getActiveVirtue();
        Log.d("junk", "Active virtue is " + activeVirtue);

        TextView tv = (TextView) findViewById(R.id.nightlyReviewTxt);
        tv.setTypeface(face);
        viewAnimator = (ViewAnimator) findViewById(R.id.viewAnimator);
        btnNext = (Button) findViewById(R.id.nextBtn);
        btnPrevious = (Button) findViewById(R.id.backBtn);
        if (btnPrevious != null) {
            btnPrevious.setAlpha(0);
        }
        initViewAnimator();

        btnNext.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv;
                if (btnNext.getText().equals("Finish")) {
                    // Finish Clicked

                    Integer[] surveyVals = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
                    RatingBar r1 = (RatingBar) findViewById(R.id.v1Rating);
                    RatingBar r2 = (RatingBar) findViewById(R.id.v2Rating);
                    RatingBar r3 = (RatingBar) findViewById(R.id.v3Rating);
                    RatingBar r4 = (RatingBar) findViewById(R.id.v4Rating);
                    RatingBar r5 = (RatingBar) findViewById(R.id.v5Rating);
                    RatingBar r6 = (RatingBar) findViewById(R.id.v6Rating);
                    RatingBar r7 = (RatingBar) findViewById(R.id.v7Rating);
                    RatingBar r8 = (RatingBar) findViewById(R.id.v8Rating);
                    RatingBar r9 = (RatingBar) findViewById(R.id.v9Rating);
                    RatingBar r10 = (RatingBar) findViewById(R.id.v10Rating);
                    RatingBar r11 = (RatingBar) findViewById(R.id.v11Rating);
                    RatingBar r12 = (RatingBar) findViewById(R.id.v12Rating);
                    RatingBar r13 = (RatingBar) findViewById(R.id.v12Rating);

                    surveyVals[0] = (int) r1.getRating();
                    surveyVals[1] = (int) r2.getRating();
                    surveyVals[2] = (int) r3.getRating();
                    surveyVals[3] = (int) r4.getRating();
                    surveyVals[4] = (int) r5.getRating();
                    surveyVals[5] = (int) r6.getRating();
                    surveyVals[6] = (int) r7.getRating();
                    surveyVals[7] = (int) r8.getRating();
                    surveyVals[8] = (int) r9.getRating();
                    surveyVals[9] = (int) r10.getRating();
                    surveyVals[10] = (int) r11.getRating();
                    surveyVals[11] = (int) r12.getRating();
                    surveyVals[12] = (int) r13.getRating();

                    String tid = getTodaysRecordId();
                    if (tid != null) {
                        updateRecord(surveyVals, tid);
                    } else {
                        createRecord(surveyVals);
                    }

                    Log.d("junk", "whaaaaat... i got the deetz");

                    NavUtils.navigateUpFromSameTask(VirtueSurvey.this);

                } else {
                    viewAnimator.showNext();
                    switch (viewAnimator.getDisplayedChild()) {

                        case 1: // Temperance
                            tv = (TextView) findViewById(R.id.v1HdrTxt);
                            tv.setTypeface(face);
                            btnPrevious.setAlpha(1);
                            break;
                        case 2:
                            tv = (TextView) findViewById(R.id.v2HdrTxt);
                            tv.setTypeface(face);
                            break;
                        case 13: // Humility
                            RatingBar rb = (RatingBar) findViewById(R.id.v12Rating);
                            Log.d("junk", "chastity rating was " + rb.getRating());
                            btnNext.setText("Finish");
                            break;
                    }
                    Log.d("junk", "View Id (n):" + String.valueOf(viewAnimator.getDisplayedChild()));

                }
                if (Integer.parseInt(activeVirtue) == viewAnimator.getDisplayedChild()) {
                    Log.d("junk", "This is your virtue!!");

                }

            }
        });

        btnPrevious.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewAnimator.showPrevious();
                switch (viewAnimator.getDisplayedChild()) {
                    case 0: // Intro
                        btnNext.setAlpha(1);
                        btnPrevious.setAlpha(0);
                        break;
                    case 12: // Chastity
                        btnNext.setAlpha(1);
                        break;
                }
                Log.d("junk", "View Id (n):" + String.valueOf(viewAnimator.getDisplayedChild()));
            }
        });

    }

    private void updateRecord(Integer[] surveyVals, String _id) {
        String strFilter = "_id=" + _id;
        Log.d("junk","Updating id "+ _id);
        ContentValues values = new ContentValues();
        // Put each value into the database
        for(int x=0;x<13;x++) {
            String colName = "v" + (x + 1);
            values.put(colName, surveyVals[x]);
        }
        database.update(RECORD_TABLE, values, strFilter, null);
    }

    private String getTodaysRecordId() {
       // String selectQuery = "SELECT * FROM "+ RECORD_TABLE +" WHERE dt = (select max(dt) from "+ RECORD_TABLE +" WHERE dt < DATETIME('now') AND dt >= datetime('now', '-1 days'))";
        //String selectQuery = "SELECT * FROM "+ RECORD_TABLE +" WHERE date(datetime(dt / 1000 , 'unixepoch')) = date('now')";
        String selectQuery = "SELECT * FROM "+ RECORD_TABLE +" WHERE dt > date('now','localtime','start of day')";
        Log.d("junk",selectQuery);
        Cursor cursor = database.rawQuery(selectQuery, null);
        Log.d("junk", String.valueOf(cursor.getCount()));
        if (cursor.getCount()>0) {
            cursor.moveToFirst();
            Log.d("junk","found a record "+ cursor.getString(1));
            return cursor.getString(0);
        }
        else {
            return null;
        }
    }

    public void createRecord(Integer[] vals){
        ContentValues values = new ContentValues();
        // Put each value into the database
        for(int x=0;x<13;x++) {
            String colName = "v" + (x + 1);
            values.put(colName, vals[x]);
            Log.d("junk", "Added v" + (x + 1) + " - " + vals[x]);
        }
        database.insert(RECORD_TABLE, null, values);

    }

    void initViewAnimator() {
        //Load animations for in and out for viewanimator
        final Animation inAnim = AnimationUtils.loadAnimation(this,
                android.R.anim.fade_in);
        final Animation outAnim = AnimationUtils.loadAnimation(this,
                android.R.anim.fade_out);

        viewAnimator.setInAnimation(inAnim);
        viewAnimator.setOutAnimation(outAnim);
    }

}
