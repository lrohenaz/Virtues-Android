package com.listfist.virtue;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RatingBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private AppPreferences _appPrefs;
    Typeface face;
    private static final String TAG = SplashActivity.class.getName();

    public static final int LIGHT = 1;
    public static final int DARK = 0;
    Boolean themeholder = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "Let there be light...");
        super.onCreate(savedInstanceState);
        _appPrefs = new AppPreferences(getApplicationContext());
        if(_appPrefs.getTheme()) {
            setTheme(R.style.AppTheme);
            themeholder=true;
        }
        else {
            setTheme(R.style.AppThemeDark);
            themeholder=false;
        }

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        face = Typeface.createFromAsset(getAssets(), "OldEurope.ttf");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, VirtueSurvey.class);
                    MainActivity.this.startActivity(intent);
                    Snackbar.make(view, "Review Canceled", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
        }
    }

    @Override
    public void onResume() {
        if(themeholder.equals(_appPrefs.getTheme())) {
            // No Refresh needed

        }
        else {
            // Refresh the view
            //recreate();
            finish();
            startActivity(getIntent());
        }
        super.onResume();
        _appPrefs = new AppPreferences(getApplicationContext());
        final String activeVirtue = _appPrefs.getActiveVirtue();
        String reminderTime = _appPrefs.getTime();
        if (reminderTime.equals("0")) {
            Log.d(TAG, "Nightly Reminder not set");
            _appPrefs.createDefaultReminder();
            reminderTime = _appPrefs.getTime();
            Snackbar.make(this.findViewById(android.R.id.content), "Nightly Reminder set for 10PM: ", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

        }
        Log.d(TAG, "Reminder is " + reminderTime);

        String changeVirtueTime = _appPrefs.getChangeVirtueTime();
        if (changeVirtueTime.equals("0")) {
            Log.d(TAG, "Change virtue reminder not set");
            _appPrefs.createChangeVirtueReminder();
            changeVirtueTime = _appPrefs.getChangeVirtueTime();
            Snackbar.make(this.findViewById(android.R.id.content), "Reminder set - 'change virtue' in 7 days: ", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        // _appPrefs.
        Log.d(TAG, "Active virtue is " + activeVirtue);
        TextView yourVirtue = (TextView) findViewById(R.id.yourVirtue);
        yourVirtue.setTypeface(face);
        yourVirtue.setTextColor(Color.rgb(130,130,130));
        yourVirtue.setShadowLayer(1.5f, -2, 2, Color.argb(55,5,5,5));
        TextView goal = (TextView) findViewById(R.id.goalTxt);
        String goaltxt;
        String title = "";
        switch (activeVirtue) {
            case "1":
                title = getResources().getString(R.string.v1_title);
                break;
            case "2":
                title = getResources().getString(R.string.v2_title);
                break;
            case "3":
                title = getResources().getString(R.string.v3_title);
                break;
            case "4":
                title = getResources().getString(R.string.v4_title);
                break;
            case "5":
                title = getResources().getString(R.string.v5_title);
                break;
            case "6":
                title = getResources().getString(R.string.v6_title);
                break;
            case "7":
                title = getResources().getString(R.string.v7_title);
                break;
            case "8":
                title = getResources().getString(R.string.v8_title);
                break;
            case "9":
                title = getResources().getString(R.string.v9_title);
                break;
            case "10":
                title = getResources().getString(R.string.v10_title);
                break;
            case "11":
                title = getResources().getString(R.string.v11_title);
                break;
            case "12":
                title = getResources().getString(R.string.v12_title);
                break;
            case "13":
                title = getResources().getString(R.string.v13_title);
                break;
        }
        yourVirtue.setText(title);
        goaltxt = getResources().getString(R.string.goalTxt);
        goal.setText(goaltxt.replaceAll("\\bTemperance\\b", title));
        goaltxt = goal.getText().toString();
        goal.setText(goaltxt.replaceAll("\\bfirst\\b", _appPrefs.getWeekWord()));
        TextView ratingMsg = (TextView) findViewById(R.id.ratingMsg);
        Typeface osr = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        ratingMsg.setTypeface(osr);

        RatingBar rb = (RatingBar) findViewById(R.id.ratingBar);
        rb.setRating(Integer.parseInt(_appPrefs.getRating(Integer.parseInt(activeVirtue))));
        Log.d(TAG, "rating: " + (int) rb.getRating());
        rb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                _appPrefs.saveRating(Integer.parseInt(activeVirtue), (int) rating);
                final String todaysRating = _appPrefs.getRating(Integer.parseInt(activeVirtue));
                Log.d(TAG, "saved todays rating " + todaysRating);
            }
        });


        //stars.getDrawable(2).setColorFilter(getResources().getColor(R.color.colorAccentDark), PorterDuff.Mode.SRC_ATOP); //selected
        //stars.getDrawable(1).setColorFilter(getResources().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.SRC_ATOP); //partially selected
        //stars.getDrawable(0).setColorFilter(getResources().getColor(R.color.colorSecondaryDark), PorterDuff.Mode.SRC_ATOP); //not selected
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

        }
        else {
            // Older versions dont theme the start very nicely, just turn them to solid accent color for the current theme
            if(themeholder) {
                rb.getProgressDrawable().setColorFilter(getResources().getColor(R.color.colorAccentDark), PorterDuff.Mode.SRC_ATOP);
            }
            else {
                rb.getProgressDrawable().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
            }
        }

        if (yourVirtue != null) {
            yourVirtue.setOnClickListener(new TextView.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, SplashActivity.class);
                    intent.putExtra("action", "change_virtue");
                    startActivity(intent);
                    // finish();
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "Create options menu...");
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "Menu Item Selected...");
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            MainActivity.this.startActivity(intent);
            return true;
        }
        if (id == R.id.action_progress) {
            Intent intent = new Intent(MainActivity.this, ProgressActivity.class);
            MainActivity.this.startActivity(intent);
            return true;
        }
        if (id == R.id.action_help) {
            Intent intent = new Intent(MainActivity.this, HelpActivity.class);
            MainActivity.this.startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}

