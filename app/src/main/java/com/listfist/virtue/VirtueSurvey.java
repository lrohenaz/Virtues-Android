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
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ViewAnimator;

import java.util.Calendar;


public class VirtueSurvey extends AppCompatActivity {
    private static final String TAG = SplashActivity.class.getName();
    private static Button btnNext, btnPrevious, my_button;
    private static ViewAnimator viewAnimator;
    private AppPreferences _appPrefs;
    private SQLiteDatabase database;
    public final static String RECORD_TABLE = "virtueLog"; // name of table
    RatingBar r1;
    RatingBar r2;
    RatingBar r3;
    RatingBar r4;
    RatingBar r5;
    RatingBar r6;
    RatingBar r7;
    RatingBar r8;
    RatingBar r9;
    RatingBar r10;
    RatingBar r11;
    RatingBar r12;
    RatingBar r13;
    Typeface face;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_virtue_survey);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        r1 = (RatingBar) findViewById(R.id.v1Rating);
        r2 = (RatingBar) findViewById(R.id.v2Rating);
        r3 = (RatingBar) findViewById(R.id.v3Rating);
        r4 = (RatingBar) findViewById(R.id.v4Rating);
        r5 = (RatingBar) findViewById(R.id.v5Rating);
        r6 = (RatingBar) findViewById(R.id.v6Rating);
        r7 = (RatingBar) findViewById(R.id.v7Rating);
        r8 = (RatingBar) findViewById(R.id.v8Rating);
        r9 = (RatingBar) findViewById(R.id.v9Rating);
        r10 = (RatingBar) findViewById(R.id.v10Rating);
        r11 = (RatingBar) findViewById(R.id.v11Rating);
        r12 = (RatingBar) findViewById(R.id.v12Rating);
        r13 = (RatingBar) findViewById(R.id.v13Rating);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        face = Typeface.createFromAsset(getAssets(), "OldEurope.ttf");
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(this);
        database = dbHelper.getWritableDatabase();

        _appPrefs = new AppPreferences(getApplicationContext());
        final String activeVirtue = _appPrefs.getActiveVirtue();
        Log.d(TAG, "Active virtue is " + activeVirtue);

        TextView tv = (TextView) findViewById(R.id.nightlyReviewTxt);
        tv.setTypeface(face);
        viewAnimator = (ViewAnimator) findViewById(R.id.viewAnimator);
        btnNext = (Button) findViewById(R.id.nextBtn);
        btnPrevious = (Button) findViewById(R.id.backBtn);

        if (btnPrevious != null) {
            btnPrevious.setAlpha(0);
        }
        TextView msgTv = (TextView) findViewById(R.id.surveyMsg);
        if(equals(getTodaysRecordId(),null)) {
            //msgTv.setText("You have not recorded your virtues today. Click next to begin.");
        }
        else {
            msgTv.setText("You've already recorded your virtues today. You can still click next to change them.");
        }
        // Clear all ratings if last launch of this activity was yesterday or later
        Calendar c = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c2.setTimeInMillis(System.currentTimeMillis());

        long st = _appPrefs.getLastSurveyTime();
        long diff = System.currentTimeMillis() - st;
        Log.d(TAG, "ms since last survey: " + diff);
        c.add(Calendar.MILLISECOND, (int) (-1 * diff));
        if(c.get(Calendar.DAY_OF_WEEK)!=c2.get(Calendar.DAY_OF_WEEK)) {
            Log.d(TAG, "Its a new day! Clear your saved ratings");
            r1.setRating(0);
            r2.setRating(0);
            r3.setRating(0);
            r4.setRating(0);
            r5.setRating(0);
            r6.setRating(0);
            r7.setRating(0);
            r8.setRating(0);
            r9.setRating(0);
            r10.setRating(0);
            r11.setRating(0);
            r12.setRating(0);
            r13.setRating(0);
        }

        _appPrefs.setLastSurveyTime();

        initViewAnimator();
        btnNext.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = null;
                if (equals(btnNext.getText(), "Finish")) {
                    // Finish Clicked
                    Integer[] surveyVals = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
                    for (int x = 0; x < 13; x++) {
                        surveyVals[x] = Integer.parseInt(_appPrefs.getRating(x + 1));
                    }

                    String tid = getTodaysRecordId();
                    if (tid != null) {
                        updateRecord(surveyVals, tid);
                    } else {
                        createRecord(surveyVals);
                    }
                    NavUtils.navigateUpFromSameTask(VirtueSurvey.this);
                } else {
                    viewAnimator.showNext();
                    switch (viewAnimator.getDisplayedChild()) {
                        case 1: // Temperance
                            tv = (TextView) findViewById(R.id.v1HdrTxt);
                            btnPrevious.setAlpha(1);
                            btnPrevious.setEnabled(true);
                            break;
                        case 2:
                            tv = (TextView) findViewById(R.id.v2HdrTxt);
                            break;
                        case 3:
                            tv = (TextView) findViewById(R.id.v3HdrTxt);
                            break;
                        case 4:
                            tv = (TextView) findViewById(R.id.v4HdrTxt);
                            break;
                        case 5:
                            tv = (TextView) findViewById(R.id.v5HdrTxt);
                            break;
                        case 6:
                            tv = (TextView) findViewById(R.id.v6HdrTxt);
                            break;
                        case 7:
                            tv = (TextView) findViewById(R.id.v7HdrTxt);
                            break;
                        case 8:
                            tv = (TextView) findViewById(R.id.v8HdrTxt);
                            break;
                        case 9:
                            tv = (TextView) findViewById(R.id.v9HdrTxt);
                            break;
                        case 10:
                            tv = (TextView) findViewById(R.id.v10HdrTxt);
                            break;
                        case 11:
                            tv = (TextView) findViewById(R.id.v11HdrTxt);
                            break;
                        case 12:
                            tv = (TextView) findViewById(R.id.v12HdrTxt);
                            break;
                        case 13:
                            tv = (TextView) findViewById(R.id.v13HdrTxt);
                            RatingBar rb = (RatingBar) findViewById(R.id.v12Rating);
                            Log.d(TAG, "chastity rating was " + rb.getRating());
                            btnNext.setText(R.string.finishBtnTxt);
                            break;
                    }
                    if (tv != null) {
                        tv.setTypeface(face);
                    }
                    Log.d(TAG, "View Id (n):" + String.valueOf(viewAnimator.getDisplayedChild()));
                }
                if (Integer.parseInt(activeVirtue) == viewAnimator.getDisplayedChild()) {
                    Log.d(TAG, "This is your virtue!!");
                }
            }

            public boolean equals(Object a, Object b) {
                return (a == b) || (a != null && a.equals(b));
            }
        });

        btnPrevious.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewAnimator.showPrevious();
                switch (viewAnimator.getDisplayedChild()) {
                    case 0: // Intro
                        btnNext.setAlpha(1);
                        btnNext.setEnabled(true);
                        btnPrevious.setAlpha(0);
                        btnPrevious.setEnabled(false);
                        break;
                    case 12: // Chastity
                        btnNext.setAlpha(1);
                        btnNext.setEnabled(true);
                        break;
                }
                Log.d(TAG, "View Id (n):" + String.valueOf(viewAnimator.getDisplayedChild()));
            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();
        _appPrefs = new AppPreferences(getApplicationContext());
        r1.setRating(Integer.parseInt(_appPrefs.getRating(1)));
        r2.setRating(Integer.parseInt(_appPrefs.getRating(2)));
        r3.setRating(Integer.parseInt(_appPrefs.getRating(3)));
        r4.setRating(Integer.parseInt(_appPrefs.getRating(4)));
        r5.setRating(Integer.parseInt(_appPrefs.getRating(5)));
        r6.setRating(Integer.parseInt(_appPrefs.getRating(6)));
        r7.setRating(Integer.parseInt(_appPrefs.getRating(7)));
        r8.setRating(Integer.parseInt(_appPrefs.getRating(8)));
        r9.setRating(Integer.parseInt(_appPrefs.getRating(9)));
        r10.setRating(Integer.parseInt(_appPrefs.getRating(10)));
        r11.setRating(Integer.parseInt(_appPrefs.getRating(11)));
        r12.setRating(Integer.parseInt(_appPrefs.getRating(12)));
        r13.setRating(Integer.parseInt(_appPrefs.getRating(13)));
        r1.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                _appPrefs.saveRating(1, (int) rating);
            }
        });
        r2.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                _appPrefs.saveRating(2, (int) rating);
            }
        });
        r3.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                _appPrefs.saveRating(3, (int) rating);
            }
        });
        r4.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                _appPrefs.saveRating(4, (int) rating);
            }
        });
        r5.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                _appPrefs.saveRating(5, (int) rating);
            }
        });
        r6.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                _appPrefs.saveRating(6, (int) rating);
            }
        });
        r7.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                _appPrefs.saveRating(7, (int) rating);
            }
        });
        r8.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                _appPrefs.saveRating(8, (int) rating);
            }
        });
        r9.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                _appPrefs.saveRating(9, (int) rating);
            }
        });
        r10.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                _appPrefs.saveRating(10, (int) rating);
            }
        });
        r11.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                _appPrefs.saveRating(11, (int) rating);
            }
        });
        r12.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                _appPrefs.saveRating(12, (int) rating);
            }
        });
        r13.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                _appPrefs.saveRating(13, (int) rating);
            }
        });
    }

    public static boolean equals(Object a, Object b) {
        return (a == b) || (a != null && a.equals(b));
    }

    private void updateRecord(Integer[] surveyVals, String _id) {
        String strFilter = "_id=" + _id;
        Log.d(TAG, "Updating id " + _id);
        ContentValues values = new ContentValues();
        // Put each value into the database
        for (int x = 0; x < 13; x++) {
            String colName = "v" + (x + 1);
            values.put(colName, surveyVals[x]);
        }
        database.update(RECORD_TABLE, values, strFilter, null);
    }

    private String getTodaysRecordId() {
        String selectQuery = "SELECT * FROM " + RECORD_TABLE + " WHERE dt > date('now','localtime','start of day')";
        Log.d(TAG, selectQuery);
        Cursor cursor = database.rawQuery(selectQuery, null);
        Log.d(TAG, String.valueOf(cursor.getCount()));
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            Log.d(TAG, "found a record " + cursor.getString(1));
            String returntxt = cursor.getString(0);
            cursor.close();
            return returntxt;
        } else {
            cursor.close();
            return null;
        }
    }

    public void createRecord(Integer[] vals) {
        ContentValues values = new ContentValues();
        // Put each value into the database
        for (int x = 0; x < 13; x++) {
            String colName = "v" + (x + 1);
            values.put(colName, vals[x]);
            Log.d(TAG, "Added v" + (x + 1) + " - " + vals[x]);
        }
        database.insert(RECORD_TABLE, null, values);

    }

    void initViewAnimator() {
        //Load animations for in and out for viewanimator
        //final Animation inAnim = AnimationUtils.loadAnimation(this,
        //        android.R.anim.fade_in);
        //final Animation outAnim = AnimationUtils.loadAnimation(this,
        //        android.R.anim.fade_out);

        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
        fadeIn.setStartOffset(400);
        fadeIn.setDuration(200);

        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator()); //and this
        fadeOut.setDuration(100);


        viewAnimator.setInAnimation(fadeIn);
        viewAnimator.setOutAnimation(fadeOut);
    }

}
