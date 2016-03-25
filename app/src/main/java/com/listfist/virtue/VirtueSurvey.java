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
                    Switch s2 = (Switch) findViewById(R.id.silenceSwitch);
                    Switch s3 = (Switch) findViewById(R.id.orderSwitch);
                    Switch s4 = (Switch) findViewById(R.id.resolutionSwitch);
                    Switch s5 = (Switch) findViewById(R.id.frugalitySwitch);
                    Switch s6 = (Switch) findViewById(R.id.industrySwitch);
                    Switch s7 = (Switch) findViewById(R.id.sinceritySwitch);
                    Switch s8 = (Switch) findViewById(R.id.justiceSwitch);
                    Switch s9 = (Switch) findViewById(R.id.moderationSwitch);
                    Switch s10 = (Switch) findViewById(R.id.cleanlinessSwitch);
                    Switch s11 = (Switch) findViewById(R.id.tranquilitySwitch);
                    Switch s12 = (Switch) findViewById(R.id.chastitySwitch);
                    Switch s13 = (Switch) findViewById(R.id.humilitySwitch);

                    if (r1.getNumStars() > 0) {
                        surveyVals[0] = r1.getNumStars();
                    } else {
                        surveyVals[0] = 0;
                    }
                    if (s2.isChecked()) {
                        surveyVals[1] = 1;
                    } else {
                        surveyVals[1] = 0;
                    }
                    if (s3.isChecked()) {
                        surveyVals[2] = 1;
                    } else {
                        surveyVals[2] = 0;
                    }
                    if (s4.isChecked()) {
                        surveyVals[3] = 1;
                    } else {
                        surveyVals[3] = 0;
                    }
                    if (s5.isChecked()) {
                        surveyVals[4] = 1;
                    } else {
                        surveyVals[4] = 0;
                    }
                    if (s6.isChecked()) {
                        surveyVals[5] = 1;
                    } else {
                        surveyVals[5] = 0;
                    }
                    if (s7.isChecked()) {
                        surveyVals[6] = 1;
                    } else {
                        surveyVals[6] = 0;
                    }
                    if (s8.isChecked()) {
                        surveyVals[7] = 1;
                    } else {
                        surveyVals[7] = 0;
                    }
                    if (s9.isChecked()) {
                        surveyVals[8] = 1;
                    } else {
                        surveyVals[8] = 0;
                    }
                    if (s10.isChecked()) {
                        surveyVals[9] = 1;
                    } else {
                        surveyVals[9] = 0;
                    }
                    if (s11.isChecked()) {
                        surveyVals[10] = 1;
                    } else {
                        surveyVals[10] = 0;
                    }
                    if (s12.isChecked()) {
                        surveyVals[11] = 1;
                    } else {
                        surveyVals[11] = 0;
                    }
                    if (s13.isChecked()) {
                        surveyVals[12] = 1;
                    } else {
                        surveyVals[12] = 0;
                    }
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
                            Switch cs = (Switch) findViewById(R.id.chastitySwitch);
                            Log.d("junk", "chastity switch was " + Boolean.toString(cs.isChecked()));
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
