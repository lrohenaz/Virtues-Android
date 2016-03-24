package com.listfist.virtue;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Switch;
import android.widget.ViewAnimator;

import java.sql.Array;


public class VirtueSurvey extends AppCompatActivity {
    private static Button btnNext, btnPrevious, my_button;
    private static ViewAnimator viewAnimator;
    private MyDatabaseHelper dbHelper;

    private SQLiteDatabase database;
    public final static String RECORD_TABLE="virtueLog"; // name of table
    private AppPreferences _appPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_virtue_survey);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dbHelper = new MyDatabaseHelper(this);
        database = dbHelper.getWritableDatabase();

        _appPrefs = new AppPreferences(getApplicationContext());
        final String activeVirtue = _appPrefs.getActiveVirtue();
        Log.d("junk", "Active virtue is " + activeVirtue);


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

                if (btnNext.getText().equals("Finish")) {
                    // Finish Clicked

                    Integer[] surveyVals = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
                    Switch s1 = (Switch) findViewById(R.id.temperanceSwitch);
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

                    if (s1.isChecked()) {
                        surveyVals[0] = 1;
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
                    if(tid!=null) {
                        updateRecord(surveyVals, tid);
                    }
                    else {
                        createRecord(surveyVals);
                    }

                    Log.d("junk", "whaaaaat... i got the deetz");

                    NavUtils.navigateUpFromSameTask(VirtueSurvey.this);

                } else {
                    viewAnimator.showNext();
                    switch (viewAnimator.getDisplayedChild()) {
                        case 1: // Temperance
                            btnPrevious.setAlpha(1);
                            break;
                        case 13: // Humility
                            Switch cs = (Switch) findViewById(R.id.chastitySwitch);
                            Log.d("junk", "chastity switch was " + Boolean.toString(cs.isChecked()));
                            btnNext.setText("Finish");
                            break;
                    }
                    Log.d("junk", "View Id (n):" + String.valueOf(viewAnimator.getDisplayedChild()));

                }
                if(Integer.parseInt(activeVirtue)==viewAnimator.getDisplayedChild()) {
                    Log.d("junk","This is your virtue!!");
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
        ContentValues values = new ContentValues();
        // Put each value into the database
        for(int x=0;x<13;x++) {
            String colName = "v" + (x + 1);
            values.put(colName, surveyVals[x]);
            Log.d("junk", "pdated v" + (x + 1) + " - " + surveyVals[x]);
        }
       // database.update(RECORD_TABLE, null, values);
        String strFilter = "_id=" + _id;
        database.update(RECORD_TABLE,values,strFilter,null);
    }

    private String getTodaysRecordId() {
        Cursor cursor = database.query(true, RECORD_TABLE, new String[]{"dt"}, "dt=date('now')",null,null,null,null,null);
        if (cursor.getCount()>0) {
            cursor.moveToFirst();
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
