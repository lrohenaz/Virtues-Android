package com.listfist.virtue;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashActivity extends AppCompatActivity {
    private AppPreferences _appPrefs;
    String v="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _appPrefs = new AppPreferences(getApplicationContext());
        final String activeVirtue = _appPrefs.getActiveVirtue();

        setContentView(R.layout.activity_spash);

        Button choose = (Button) findViewById(R.id.chooseBtn);
        Button v1Btn = (Button) findViewById(R.id.v1Btn);
        Button v2Btn = (Button) findViewById(R.id.v2Btn);
        Button v3Btn = (Button) findViewById(R.id.v3Btn);
        Button v4Btn = (Button) findViewById(R.id.v4Btn);
        Button v5Btn = (Button) findViewById(R.id.v5Btn);
        Button v6Btn = (Button) findViewById(R.id.v6Btn);
        Button v7Btn = (Button) findViewById(R.id.v7Btn);
        Button v8Btn = (Button) findViewById(R.id.v8Btn);
        Button v9Btn = (Button) findViewById(R.id.v9Btn);
        Button v10Btn = (Button) findViewById(R.id.v10Btn);
        Button v11Btn = (Button) findViewById(R.id.v11Btn);
        Button v12Btn = (Button) findViewById(R.id.v12Btn);
        Button v13Btn = (Button) findViewById(R.id.v13Btn);
        final TextView vDesc = (TextView) findViewById(R.id.virtueDesc);
        final TextView vTitle = (TextView) findViewById(R.id.virtueTitle);

        Log.d("junk","Active virtue is "+ activeVirtue);
        if(!Objects.equals(activeVirtue, "")) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        else {

            v1Btn.setOnClickListener(new Button.OnClickListener(){
                @Override
                public void onClick(View view) {
                    v="1";
                    vDesc.setText(R.string.v1_desc);
                    vTitle.setText(R.string.v1_title);
                }
            });
            v2Btn.setOnClickListener(new Button.OnClickListener(){
                @Override
                public void onClick(View view) {
                    v="2";
                    vDesc.setText(R.string.v2_desc);
                    vTitle.setText(R.string.v2_title);
                }
            });
            v3Btn.setOnClickListener(new Button.OnClickListener(){
                @Override
                public void onClick(View view) {
                    v="3";
                    vDesc.setText(R.string.v3_desc);
                    vTitle.setText(R.string.v3_title);
                }
            });
            v4Btn.setOnClickListener(new Button.OnClickListener(){
                @Override
                public void onClick(View view) {
                    v="4";
                    vDesc.setText(R.string.v4_desc);
                    vTitle.setText(R.string.v4_title);
                }
            });
            v5Btn.setOnClickListener(new Button.OnClickListener(){
                @Override
                public void onClick(View view) {
                    v="5";
                    vDesc.setText(R.string.v5_desc);
                    vTitle.setText(R.string.v5_title);
                }
            });
            v6Btn.setOnClickListener(new Button.OnClickListener(){
                @Override
                public void onClick(View view) {
                    v="6";
                    vDesc.setText(R.string.v6_desc);
                    vTitle.setText(R.string.v6_title);
                }
            });
            v7Btn.setOnClickListener(new Button.OnClickListener(){
                @Override
                public void onClick(View view) {
                    v="7";
                    vDesc.setText(R.string.v7_desc);
                    vTitle.setText(R.string.v7_title);
                }
            });
            v8Btn.setOnClickListener(new Button.OnClickListener(){
                @Override
                public void onClick(View view) {
                    v="8";
                    vDesc.setText(R.string.v8_desc);
                    vTitle.setText(R.string.v8_title);
                }
            });
            v9Btn.setOnClickListener(new Button.OnClickListener(){
                @Override
                public void onClick(View view) {
                    v="9";
                    vDesc.setText(R.string.v9_desc);
                    vTitle.setText(R.string.v9_title);
                }
            });
            v10Btn.setOnClickListener(new Button.OnClickListener(){
                @Override
                public void onClick(View view) {
                    v="10";
                    vDesc.setText(R.string.v10_desc);
                    vTitle.setText(R.string.v10_title);
                }
            });
            v11Btn.setOnClickListener(new Button.OnClickListener(){
                @Override
                public void onClick(View view) {
                    v="11";
                    vDesc.setText(R.string.v11_desc);
                    vTitle.setText(R.string.v11_title);
                }
            });
            v12Btn.setOnClickListener(new Button.OnClickListener(){
                @Override
                public void onClick(View view) {
                    v="12";
                    vDesc.setText(R.string.v12_desc);
                    vTitle.setText(R.string.v12_title);
                }
            });
            v13Btn.setOnClickListener(new Button.OnClickListener(){
                @Override
                public void onClick(View view) {
                    v="13";
                    vDesc.setText(R.string.v13_desc);
                    vTitle.setText(R.string.v13_title);
                }
            });
            choose.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View view) {
                    _appPrefs.saveActiveVirtue(v);
                    Log.d("junk", "Set active virtue to "+ v);
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });

        }
    }
}
