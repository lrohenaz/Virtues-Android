package com.listfist.virtue;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
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


public class VirtueSurvey extends AppCompatActivity implements
        GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener{
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
    private GestureDetectorCompat mDetector;
    Boolean themeholder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        _appPrefs = new AppPreferences(getApplicationContext());
        themeholder=_appPrefs.getTheme();
        if(_appPrefs.getTheme()) {
            setTheme(R.style.AppTheme);
            themeholder=true;
        }
        else {
            setTheme(R.style.AppThemeDark);
            themeholder=false;
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_virtue_survey);
        // Set up toolbar & navigation drawer
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            if(themeholder) {
                actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));
            }
            else {
                actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimaryDark)));
            }
        }
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
        btnNext.setText("Begin");
        btnPrevious = (Button) findViewById(R.id.backBtn);

        if (btnPrevious != null) {
            btnPrevious.setAlpha(0);
        }
        TextView msgTv = (TextView) findViewById(R.id.surveyMsg);
        Typeface osr = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        msgTv.setTypeface(osr);
        if(equals(getTodaysRecordId(),null)) {
            //msgTv.setText("You have not recorded your virtues today. Click next to begin.");
        }
        else {
            msgTv.setText("You've already recorded your virtues today. Continuing will update your responses.");
        }


        _appPrefs.setLastSurveyTime();

        initViewAnimator();
        btnNext.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = null;
                TextView def = null;
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
                            def = (TextView) findViewById(R.id.v1DescTxt);
                            tv.setText(_appPrefs.getCustomTitle(1));
                            def.setText(_appPrefs.getCustomDesc(1));
                            btnPrevious.setAlpha(1);
                            btnPrevious.setEnabled(true);
                            btnNext.setText("Next");
                            break;
                        case 2:
                            tv = (TextView) findViewById(R.id.v2HdrTxt);
                            def = (TextView) findViewById(R.id.v2DescTxt);
                            tv.setText(_appPrefs.getCustomTitle(2));
                            def.setText(_appPrefs.getCustomDesc(2));
                            break;
                        case 3:
                            tv = (TextView) findViewById(R.id.v3HdrTxt);
                            def = (TextView) findViewById(R.id.v3DescTxt);
                            tv.setText(_appPrefs.getCustomTitle(3));
                            def.setText(_appPrefs.getCustomDesc(3));
                            break;
                        case 4:
                            tv = (TextView) findViewById(R.id.v4HdrTxt);
                            def = (TextView) findViewById(R.id.v4DescTxt);
                            tv.setText(_appPrefs.getCustomTitle(4));
                            def.setText(_appPrefs.getCustomDesc(4));
                            break;
                        case 5:
                            tv = (TextView) findViewById(R.id.v5HdrTxt);
                            def = (TextView) findViewById(R.id.v5DescTxt);
                            tv.setText(_appPrefs.getCustomTitle(5));
                            def.setText(_appPrefs.getCustomDesc(5));
                            break;
                        case 6:
                            tv = (TextView) findViewById(R.id.v6HdrTxt);
                            def = (TextView) findViewById(R.id.v6DescTxt);
                            tv.setText(_appPrefs.getCustomTitle(6));
                            def.setText(_appPrefs.getCustomDesc(6));
                            break;
                        case 7:
                            tv = (TextView) findViewById(R.id.v7HdrTxt);
                            def = (TextView) findViewById(R.id.v7DescTxt);
                            tv.setText(_appPrefs.getCustomTitle(7));
                            def.setText(_appPrefs.getCustomDesc(7));
                            break;
                        case 8:
                            tv = (TextView) findViewById(R.id.v8HdrTxt);
                            def = (TextView) findViewById(R.id.v8DescTxt);
                            tv.setText(_appPrefs.getCustomTitle(8));
                            def.setText(_appPrefs.getCustomDesc(8));
                            break;
                        case 9:
                            tv = (TextView) findViewById(R.id.v9HdrTxt);
                            def = (TextView) findViewById(R.id.v9DescTxt);
                            tv.setText(_appPrefs.getCustomTitle(9));
                            def.setText(_appPrefs.getCustomDesc(9));
                            break;
                        case 10:
                            tv = (TextView) findViewById(R.id.v10HdrTxt);
                            def = (TextView) findViewById(R.id.v10DescTxt);
                            tv.setText(_appPrefs.getCustomTitle(10));
                            def.setText(_appPrefs.getCustomDesc(10));
                            break;
                        case 11:
                            tv = (TextView) findViewById(R.id.v11HdrTxt);
                            def = (TextView) findViewById(R.id.v11DescTxt);
                            tv.setText(_appPrefs.getCustomTitle(11));
                            def.setText(_appPrefs.getCustomDesc(11));
                            break;
                        case 12:
                            tv = (TextView) findViewById(R.id.v12HdrTxt);
                            def = (TextView) findViewById(R.id.v12DescTxt);
                            tv.setText(_appPrefs.getCustomTitle(12));
                            def.setText(_appPrefs.getCustomDesc(12));
                            break;
                        case 13:
                            tv = (TextView) findViewById(R.id.v13HdrTxt);
                            def = (TextView) findViewById(R.id.v13DescTxt);
                            tv.setText(_appPrefs.getCustomTitle(13));
                            def.setText(_appPrefs.getCustomDesc(13));
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
                        btnNext.setText("Begin");
                        break;
                }
                Log.d(TAG, "View Id (n):" + String.valueOf(viewAnimator.getDisplayedChild()));
            }
        });


        // Instantiate the gesture detector with the
        // application context and an implementation of
        // GestureDetector.OnGestureListener
        mDetector = new GestureDetectorCompat(this,this);
        // Set the gesture detector as the double tap
        // listener.
        mDetector.setOnDoubleTapListener(this);
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
        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
        fadeIn.setStartOffset(200);
        fadeIn.setDuration(100);

        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator()); //and this
        fadeOut.setDuration(50);

        viewAnimator.setInAnimation(fadeIn);
        viewAnimator.setOutAnimation(fadeOut);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.mDetector.onTouchEvent(event);
        // Be sure to call the superclass implementation
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent event) {
        Log.d(TAG,"onDown: " + event.toString());
        return true;
    }
    private static final int SWIPE_DISTANCE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2,
                           float velocityX, float velocityY) {
        float distanceX = e2.getX() - e1.getX();
        float distanceY = e2.getY() - e1.getY();
        if (Math.abs(distanceX) > Math.abs(distanceY)
                && Math.abs(distanceX) > SWIPE_DISTANCE_THRESHOLD
                && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
            if (distanceX > 0)
                btnPrevious.callOnClick();
            else
                btnNext.callOnClick();
            return true;
        }
        return false;
    };

    @Override
    public void onLongPress(MotionEvent event) {
        Log.d(TAG, "onLongPress: " + event.toString());
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {
        Log.d(TAG, "onScroll: " + e1.toString()+e2.toString());
        return true;
    }

    @Override
    public void onShowPress(MotionEvent event) {
        Log.d(TAG, "onShowPress: " + event.toString());
    }

    @Override
    public boolean onSingleTapUp(MotionEvent event) {
        Log.d(TAG, "onSingleTapUp: " + event.toString());
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent event) {
        Log.d(TAG, "onDoubleTap: " + event.toString());
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent event) {
        Log.d(TAG, "onDoubleTapEvent: " + event.toString());
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent event) {
        Log.d(TAG, "onSingleTapConfirmed: " + event.toString());
        return true;
    }

}
