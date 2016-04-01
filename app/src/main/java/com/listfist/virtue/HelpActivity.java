package com.listfist.virtue;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.TextView;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView helptxt = (TextView) findViewById(R.id.helptxt);
        TextView helpTitle = (TextView) findViewById(R.id.helpTitle);
        Typeface osr = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        Typeface osrl = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");
        helptxt.setTypeface(osrl);
        helpTitle.setTypeface(osr);
    }

}
