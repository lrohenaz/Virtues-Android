package com.listfist.virtue;

import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.TextView;

public class HelpActivity extends AppCompatActivity {
    Boolean themeholder;
    private AppPreferences _appPrefs;
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
        setContentView(R.layout.activity_help);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            if(themeholder) {
                actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary))); // set your desired color
            }
            else {
                actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimaryDark))); // set your desired color
            }
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView helptxt = (TextView) findViewById(R.id.helptxt);
        TextView helpTitle = (TextView) findViewById(R.id.helpTitle);
        Typeface osr = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        Typeface osrl = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");
        helptxt.setTypeface(osrl);
        helpTitle.setTypeface(osr);
    }

}
