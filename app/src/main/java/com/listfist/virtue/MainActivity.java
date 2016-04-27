package com.listfist.virtue;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = SplashActivity.class.getName();
    private AppPreferences _appPrefs;

    // Fonts
    Typeface oldEurope;
    Typeface osr;

    // Themes
    public static final int LIGHT = 1;
    public static final int DARK = 0;
    Boolean themeholder = true;

    // Navigation
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private String mActivityTitle;
    private NavigationView mDrawerNavigation;
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get shared preferences
        _appPrefs = new AppPreferences(getApplicationContext());
        // Apply theme from prefs
        if (_appPrefs.getTheme()) {             // Light Theme
            setTheme(R.style.AppTheme);
            themeholder = true;
        } else {                                // Dark Theme
            setTheme(R.style.AppThemeDark);
            themeholder = false;
        }

        setContentView(R.layout.activity_main);

        // Set up toolbar & navigation drawer
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();
        getSupportActionBar().setTitle("");
        setupDrawer();
        mDrawerNavigation = (NavigationView) findViewById(R.id.nvView);

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            if (themeholder) {
                actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));
                mDrawerNavigation.getHeaderView(0).setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            } else {
                actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimaryDark))); // set your desired color
                mDrawerNavigation.getHeaderView(0).setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
            }
        }
        // Set Home as selected
        mDrawerNavigation.getMenu().getItem(0).setChecked(true);

        mDrawerNavigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                Log.d(TAG, item.getTitle().toString());
                Intent intent;
                switch (item.getItemId()) {
                    case R.id.action_home:
                        break;
                    case R.id.action_help:
                        intent = new Intent(MainActivity.this, HelpActivity.class);
                        MainActivity.this.startActivity(intent);
                        break;
                    case R.id.virtueExpandableList:
                        break;
                    case R.id.action_v1:
                        break;
                    case R.id.action_v2:
                        break;
                    case R.id.action_progress:
                        intent = new Intent(MainActivity.this, ProgressActivity.class);
                        MainActivity.this.startActivity(intent);
                        break;
                    case R.id.action_settings:
                        intent = new Intent(MainActivity.this, SettingsActivity.class);
                        MainActivity.this.startActivity(intent);
                        break;
                    case R.id.action_virtue_group:
                        break;
                }
                mDrawerLayout.closeDrawers();
                return true;
            }


        });

        // ToDo
        // String[] osArray = { "Android", "iOS", "Windows", "OS X", "Linux" };
        //mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
        //mDrawerList.setAdapter(mAdapter);
        // mDrawerList.

        // Journal Button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, VirtueSurvey.class);
                    MainActivity.this.startActivity(intent);
                    Snackbar.make(view, "Journal entry canceled", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
        }

        // Fonts
        oldEurope = Typeface.createFromAsset(getAssets(), "OldEurope.ttf");
        osr = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");

        // Clear all ratings if last launch of this activity was yesterday or later
        Calendar c = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c2.setTimeInMillis(System.currentTimeMillis());

        long st = _appPrefs.getLastSurveyTime();
        long diff = System.currentTimeMillis() - st;
        Log.d(TAG, "ms since last survey: " + diff);
        c.add(Calendar.MILLISECOND, (int) (-1 * diff));
        if (c.get(Calendar.DAY_OF_WEEK) != c2.get(Calendar.DAY_OF_WEEK)) {
            Log.d(TAG, "Its a new day! Clear your saved ratings");
            _appPrefs.saveRating(0, 0);
            _appPrefs.saveRating(1, 0);
            _appPrefs.saveRating(2, 0);
            _appPrefs.saveRating(3, 0);
            _appPrefs.saveRating(4, 0);
            _appPrefs.saveRating(5, 0);
            _appPrefs.saveRating(6, 0);
            _appPrefs.saveRating(7, 0);
            _appPrefs.saveRating(8, 0);
            _appPrefs.saveRating(9, 0);
            _appPrefs.saveRating(10, 0);
            _appPrefs.saveRating(11, 0);
            _appPrefs.saveRating(12, 0);
            _appPrefs.saveRating(13, 0);
        }
    }

    @Override
    public void onResume() {
        // Refresh the theme
        if (themeholder.equals(_appPrefs.getTheme())) {  // No Refresh needed
        } else {                                          // Refresh the view
            finish();
            startActivity(getIntent());
        }
        super.onResume();

        // Check nightly reminder is set, otherwise set it to default time
        String reminderTime = _appPrefs.getTime();
        if (reminderTime.equals("0")) {
            Log.d(TAG, "Nightly Reminder not set");
            _appPrefs.createDefaultReminder();
            reminderTime = _appPrefs.getTime();
            Snackbar.make(this.findViewById(android.R.id.content), "Nightly Reminder set for 10PM: ", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
        Log.d(TAG, "Reminder is " + reminderTime);

        // Check the change virtue time
        String changeVirtueTime = _appPrefs.getChangeVirtueTime();
        if (changeVirtueTime.equals("0")) {
            Log.d(TAG, "Change virtue reminder not set");
            _appPrefs.createChangeVirtueReminder();
            changeVirtueTime = _appPrefs.getChangeVirtueTime();
            Snackbar.make(this.findViewById(android.R.id.content), "Reminder set - 'change virtue' in 7 days: ", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }

        // Set virtue headline
        final String activeVirtue = _appPrefs.getActiveVirtue();
        Log.d(TAG, "Active virtue is " + activeVirtue);
        TextView yourVirtue = (TextView) findViewById(R.id.yourVirtue);
        yourVirtue.setTypeface(oldEurope);
        yourVirtue.setTextColor(Color.rgb(130, 130, 130));
        yourVirtue.setShadowLayer(1.5f, -2, 2, Color.argb(55, 5, 5, 5));
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
        // onClick virtue heading
        yourVirtue.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SplashActivity.class);
                intent.putExtra("action", "change_virtue");
                startActivity(intent);
                // finish();
            }
        });


        // Set goal text
        goaltxt = getResources().getString(R.string.goalTxt);
        goal.setText(goaltxt.replaceAll("\\bTemperance\\b", title));
        goaltxt = goal.getText().toString();
        goal.setText(goaltxt.replaceAll("\\bfirst\\b", _appPrefs.getWeekWord()));

        // Primary rating bar
        RatingBar rb = (RatingBar) findViewById(R.id.ratingBar);
        rb.setRating(Integer.parseInt(_appPrefs.getRating(Integer.parseInt(activeVirtue))));
        rb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                _appPrefs.saveRating(Integer.parseInt(activeVirtue), (int) rating);
                final String todaysRating = _appPrefs.getRating(Integer.parseInt(activeVirtue));
                Log.d(TAG, "saved todays rating " + todaysRating);
            }
        });

        // Message under rating bar
        TextView ratingMsg = (TextView) findViewById(R.id.ratingMsg);
        ratingMsg.setTypeface(osr);

        // Handle theming ratingbar for older devices
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
            // Older versions dont theme the start very nicely, just turn them to solid accent color for the current theme
            if (themeholder) {
                rb.getProgressDrawable().setColorFilter(getResources().getColor(R.color.colorAccentDark), PorterDuff.Mode.SRC_ATOP);
            } else {
                rb.getProgressDrawable().setColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
            }
        }
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {
            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "Create options menu...");
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.legacy_menu, menu);
        return true;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // ToDo - For jumplist navigation
        // if (mDrawerToggle.onOptionsItemSelected(item)) {
        //     return true;
        // }

        Log.d(TAG, "ITEM SELECTED: " + item.toString());
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

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
        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

