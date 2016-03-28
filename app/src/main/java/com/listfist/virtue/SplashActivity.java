package com.listfist.virtue;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashActivity extends AppCompatActivity {
    private static final String TAG = SplashActivity.class.getName();
    private AppPreferences _appPrefs;
    String v="";
    private String action;
    Typeface face;
    Button choose;
    Button v1Btn;
    Button v2Btn;
    Button v3Btn;
    Button v4Btn;
    Button v5Btn;
    Button v6Btn;
    Button v7Btn;
    Button v8Btn;
    Button v9Btn;
    Button v10Btn;
    Button v11Btn;
    Button v12Btn;
    Button v13Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _appPrefs = new AppPreferences(getApplicationContext());
        final String activeVirtue = _appPrefs.getActiveVirtue();
        Intent i = getIntent();
        action = i.getStringExtra("action");
        setContentView(R.layout.activity_spash);
        face= Typeface.createFromAsset(getAssets(), "OldEurope.ttf");
        choose = (Button) findViewById(R.id.chooseBtn);
        v1Btn = (Button) findViewById(R.id.v1Btn);
        v2Btn = (Button) findViewById(R.id.v2Btn);
        v3Btn = (Button) findViewById(R.id.v3Btn);
        v4Btn = (Button) findViewById(R.id.v4Btn);
        v5Btn = (Button) findViewById(R.id.v5Btn);
        v6Btn = (Button) findViewById(R.id.v6Btn);
        v7Btn = (Button) findViewById(R.id.v7Btn);
        v8Btn = (Button) findViewById(R.id.v8Btn);
        v9Btn = (Button) findViewById(R.id.v9Btn);
        v10Btn = (Button) findViewById(R.id.v10Btn);
        v11Btn = (Button) findViewById(R.id.v11Btn);
        v12Btn = (Button) findViewById(R.id.v12Btn);
        v13Btn = (Button) findViewById(R.id.v13Btn);
        final TextView vDesc = (TextView) findViewById(R.id.virtueDesc);
        final TextView vTitle = (TextView) findViewById(R.id.virtueTitle);
        vTitle.setTypeface(face);

        Log.d(TAG,"Active virtue is "+ activeVirtue);
        if(!equals(activeVirtue, "")) {
            if(!equals(action, "change_virtue")){
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }

            v1Btn.setOnClickListener(new Button.OnClickListener(){

                @Override
                public void onClick(View view) {
                    v="1";
                    vDesc.setText(R.string.v1_desc);
                    vTitle.setText(R.string.v1_title);
                    removeColors();
                    view.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    _appPrefs.saveTodaysRating("0");
                }
            });
            v2Btn.setOnClickListener(new Button.OnClickListener(){
                @Override
                public void onClick(View view) {
                    v="2";
                    vDesc.setText(R.string.v2_desc);
                    vTitle.setText(R.string.v2_title);
                    removeColors();
                    view.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    _appPrefs.saveTodaysRating("0");
                }
            });
            v3Btn.setOnClickListener(new Button.OnClickListener(){
                @Override
                public void onClick(View view) {
                    v="3";
                    vDesc.setText(R.string.v3_desc);
                    vTitle.setText(R.string.v3_title);
                    removeColors();
                    view.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    _appPrefs.saveTodaysRating("0");
                }
            });
            v4Btn.setOnClickListener(new Button.OnClickListener(){
                @Override
                public void onClick(View view) {
                    v="4";
                    vDesc.setText(R.string.v4_desc);
                    vTitle.setText(R.string.v4_title);
                    removeColors();
                    view.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    _appPrefs.saveTodaysRating("0");
                }
            });
            v5Btn.setOnClickListener(new Button.OnClickListener(){
                @Override
                public void onClick(View view) {
                    v="5";
                    vDesc.setText(R.string.v5_desc);
                    vTitle.setText(R.string.v5_title);
                    removeColors();
                    view.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    _appPrefs.saveTodaysRating("0");
                }
            });
            v6Btn.setOnClickListener(new Button.OnClickListener(){
                @Override
                public void onClick(View view) {
                    v="6";
                    vDesc.setText(R.string.v6_desc);
                    vTitle.setText(R.string.v6_title);
                    removeColors();
                    view.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    _appPrefs.saveTodaysRating("0");
                }
            });
            v7Btn.setOnClickListener(new Button.OnClickListener(){
                @Override
                public void onClick(View view) {
                    v="7";
                    vDesc.setText(R.string.v7_desc);
                    vTitle.setText(R.string.v7_title);
                    removeColors();
                    view.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    _appPrefs.saveTodaysRating("0");
                }
            });
            v8Btn.setOnClickListener(new Button.OnClickListener(){
                @Override
                public void onClick(View view) {
                    v="8";
                    vDesc.setText(R.string.v8_desc);
                    vTitle.setText(R.string.v8_title);
                    removeColors();
                    view.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    _appPrefs.saveTodaysRating("0");
                }
            });
            v9Btn.setOnClickListener(new Button.OnClickListener(){
                @Override
                public void onClick(View view) {
                    v="9";
                    vDesc.setText(R.string.v9_desc);
                    vTitle.setText(R.string.v9_title);
                    removeColors();
                    view.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    _appPrefs.saveTodaysRating("0");
                }
            });
            v10Btn.setOnClickListener(new Button.OnClickListener(){
                @Override
                public void onClick(View view) {
                    v="10";
                    vDesc.setText(R.string.v10_desc);
                    vTitle.setText(R.string.v10_title);
                    removeColors();
                    view.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    _appPrefs.saveTodaysRating("0");
                }
            });
            v11Btn.setOnClickListener(new Button.OnClickListener(){
                @Override
                public void onClick(View view) {
                    v="11";
                    vDesc.setText(R.string.v11_desc);
                    vTitle.setText(R.string.v11_title);
                    removeColors();
                    view.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    _appPrefs.saveTodaysRating("0");
                }
            });
            v12Btn.setOnClickListener(new Button.OnClickListener(){
                @Override
                public void onClick(View view) {
                    v="12";
                    vDesc.setText(R.string.v12_desc);
                    vTitle.setText(R.string.v12_title);
                    removeColors();
                    view.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    _appPrefs.saveTodaysRating("0");
                }
            });
            v13Btn.setOnClickListener(new Button.OnClickListener(){
                @Override
                public void onClick(View view) {
                    v="13";
                    vDesc.setText(R.string.v13_desc);
                    vTitle.setText(R.string.v13_title);
                    removeColors();
                    view.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    _appPrefs.saveTodaysRating("0");
                }
            });
            choose.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Set 'switch virtue' reminder for 1 week from today

                    _appPrefs.saveActiveVirtue(v);
                    Log.d(TAG, "Set active virtue to " + v);
                    if(equals(action, "change_virtue")){
                        _appPrefs.createChangeVirtueReminder();
                        finish();
                    }
                    else {
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }

                public boolean equals(Object a, Object b) {
                    return (a == b) || (a != null && a.equals(b));
                }
            });


    }

    private void removeColors() {
        int stdColor = getResources().getColor(R.color.colorPrimaryDark);
        v1Btn.setBackgroundColor(stdColor);
        v2Btn.setBackgroundColor(stdColor);
        v3Btn.setBackgroundColor(stdColor);
        v4Btn.setBackgroundColor(stdColor);
        v5Btn.setBackgroundColor(stdColor);
        v6Btn.setBackgroundColor(stdColor);
        v7Btn.setBackgroundColor(stdColor);
        v8Btn.setBackgroundColor(stdColor);
        v9Btn.setBackgroundColor(stdColor);
        v10Btn.setBackgroundColor(stdColor);
        v11Btn.setBackgroundColor(stdColor);
        v12Btn.setBackgroundColor(stdColor);
        v13Btn.setBackgroundColor(stdColor);
    }

    public static boolean equals(Object a, Object b) {
        return (a == b) || (a != null && a.equals(b));
    }
}
