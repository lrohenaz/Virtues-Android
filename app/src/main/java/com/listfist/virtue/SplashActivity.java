package com.listfist.virtue;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashActivity extends AppCompatActivity {
    private static final String TAG = SplashActivity.class.getName();
    private AppPreferences _appPrefs;
    Integer v=0;
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
        v1Btn.setText(_appPrefs.getCustomTitle(1));
        v2Btn.setText(_appPrefs.getCustomTitle(2));
        v3Btn.setText(_appPrefs.getCustomTitle(3));
        v4Btn.setText(_appPrefs.getCustomTitle(4));
        v5Btn.setText(_appPrefs.getCustomTitle(5));
        v6Btn.setText(_appPrefs.getCustomTitle(6));
        v7Btn.setText(_appPrefs.getCustomTitle(7));
        v8Btn.setText(_appPrefs.getCustomTitle(8));
        v9Btn.setText(_appPrefs.getCustomTitle(9));
        v10Btn.setText(_appPrefs.getCustomTitle(10));
        v11Btn.setText(_appPrefs.getCustomTitle(11));
        v12Btn.setText(_appPrefs.getCustomTitle(12));
        v13Btn.setText(_appPrefs.getCustomTitle(13));
        final TextView vDesc = (TextView) findViewById(R.id.virtueDesc);
        final TextView vTitle = (TextView) findViewById(R.id.virtueTitle);
        vTitle.setTypeface(face);

        Log.d(TAG, "Active virtue is " + activeVirtue);

        if(!equals(activeVirtue, "0")) { // 0 means first start - no virtue selected yet
            if(!equals(action, "change_virtue")){
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }

        v1Btn.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View view) {
                v=1;
                vDesc.setText(_appPrefs.getCustomDesc(1));
                vTitle.setText(_appPrefs.getCustomTitle(1));
                removeColors();
                view.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            }
        });
        v2Btn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                v=2;
                vDesc.setText(_appPrefs.getCustomDesc(2));
                vTitle.setText(_appPrefs.getCustomTitle(2));
                removeColors();
                view.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            }
        });
        v3Btn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                v=3;
                vDesc.setText(_appPrefs.getCustomDesc(3));
                vTitle.setText(_appPrefs.getCustomTitle(3));
                removeColors();
                view.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            }
        });
        v4Btn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                v=4;
                vDesc.setText(_appPrefs.getCustomDesc(4));
                vTitle.setText(_appPrefs.getCustomTitle(4));
                removeColors();
                view.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            }
        });
        v5Btn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                v=5;
                vDesc.setText(_appPrefs.getCustomDesc(5));
                vTitle.setText(_appPrefs.getCustomTitle(5));
                removeColors();
                view.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            }
        });
        v6Btn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                v=6;
                vDesc.setText(_appPrefs.getCustomDesc(6));
                vTitle.setText(_appPrefs.getCustomTitle(6));
                removeColors();
                view.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            }
        });
        v7Btn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                v=7;
                vDesc.setText(_appPrefs.getCustomDesc(7));
                vTitle.setText(_appPrefs.getCustomTitle(7));
                removeColors();
                view.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            }
        });
        v8Btn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                v=8;
                vDesc.setText(_appPrefs.getCustomDesc(8));
                vTitle.setText(_appPrefs.getCustomTitle(8));
                removeColors();
                view.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            }
        });
        v9Btn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                v=9;
                vDesc.setText(_appPrefs.getCustomDesc(9));
                vTitle.setText(_appPrefs.getCustomTitle(9));
                removeColors();
                view.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            }
        });
        v10Btn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                v=10;
                vDesc.setText(_appPrefs.getCustomDesc(10));
                vTitle.setText(_appPrefs.getCustomTitle(10));
                removeColors();
                view.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            }
        });
        v11Btn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                v=11;
                vDesc.setText(_appPrefs.getCustomDesc(11));
                vTitle.setText(_appPrefs.getCustomTitle(11));
                removeColors();
                view.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            }
        });
        v12Btn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                v=12;
                vDesc.setText(_appPrefs.getCustomDesc(12));
                vTitle.setText(_appPrefs.getCustomTitle(12));
                removeColors();
                view.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            }
        });
        v13Btn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                v=13;
                vDesc.setText(_appPrefs.getCustomDesc(13));
                vTitle.setText(_appPrefs.getCustomTitle(13));
                removeColors();
                view.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                _appPrefs.saveTodaysRating("0");
            }
        });
        v1Btn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showEditVirtueDialog(1);
                return true; //return true if we handle the result or something?
            }
        });
        v2Btn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showEditVirtueDialog(2);
                return true;
            }
        });
        v3Btn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showEditVirtueDialog(3);
                return true;
            }
        });
        v4Btn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showEditVirtueDialog(4);
                return true;
            }
        });
        v5Btn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showEditVirtueDialog(5);
                return true;
            }
        });
        v6Btn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showEditVirtueDialog(6);
                return true;
            }
        });
        v7Btn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showEditVirtueDialog(7);
                return true;
            }
        });
        v8Btn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showEditVirtueDialog(8);
                return true;
            }
        });
        v9Btn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showEditVirtueDialog(9);
                return true;
            }
        });
        v10Btn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showEditVirtueDialog(10);
                return true;
            }
        });
        v11Btn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showEditVirtueDialog(11);
                return true;
            }
        });
        v12Btn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showEditVirtueDialog(12);
                return true;
            }
        });
        v13Btn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showEditVirtueDialog(13);
                return true;
            }
        });

        choose.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set 'switch virtue' reminder for 1 week from today
                if(v!=0) {
                    _appPrefs.saveTodaysRating("0");

                    Log.d(TAG, "Set active virtue to " + v);
                    if(equals(action, "change_virtue")||equals(0,_appPrefs.getChangeVirtueDaysLeft())){
                        if(!equals(String.valueOf(v),_appPrefs.getActiveVirtue())) {
                            showChangeVirtueDialog();
                        }
                        else {


                            _appPrefs.saveActiveVirtue(String.valueOf(v));
                            _appPrefs.createChangeVirtueReminder();
                            finish();
                        }
                    }
                    else {
                        _appPrefs.saveActiveVirtue(String.valueOf(v));
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    blinkTitle();
                }
            }

            public boolean equals(Object a, Object b) {
                return (a == b) || (a != null && a.equals(b));
            }
        });
    }

    private void showEditVirtueDialog(final int v) {
        final AlertDialog ad;
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SplashActivity.this);
        final LinearLayout ll = new LinearLayout(SplashActivity.this);
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setLayoutParams(llp);

        final EditText newVirtueName = new EditText(SplashActivity.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        newVirtueName.setLayoutParams(lp);

        final EditText def = new EditText(SplashActivity.this);
        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        def.setLayoutParams(lp2);

        newVirtueName.setText(_appPrefs.getCustomTitle(v));
        def.setText(_appPrefs.getCustomDesc(v));


        ll.addView(newVirtueName, 0);
        ll.addView(def, 1);

        // ad = alertDialogBuilder.create();
        alertDialogBuilder.setView(ll);
        alertDialogBuilder.setIcon(R.drawable.ic_build_white_24dp);
        alertDialogBuilder.setMessage("customize virtue");
        alertDialogBuilder.setCancelable(true);
        alertDialogBuilder.setPositiveButton("Save", null);
        alertDialogBuilder.setNegativeButton("Cancel",null);
        alertDialogBuilder.setNeutralButton("Default",null);
        ad = alertDialogBuilder.create();
        ad.setOnShowListener(new DialogInterface.OnShowListener() {

            @Override
            public void onShow(DialogInterface dialog) {
                Button b = ad.getButton(AlertDialog.BUTTON_POSITIVE);
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d(TAG,"save Clicked "+ newVirtueName.getText() +" - "+ def.getText());
                        _appPrefs.saveCustomTitle(v,newVirtueName.getText().toString());
                        _appPrefs.saveCustomDef(v,def.getText().toString());
                        ad.dismiss();
                        Intent intent = getIntent();
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        finish();
                        startActivity(intent);
                    }
                });

                Button negBtn = ad.getButton(AlertDialog.BUTTON_NEGATIVE);
                negBtn.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        Log.d(TAG,"Cancel Clicked");
                        ad.dismiss();
                    }
                });

                Button defaultBtn = ad.getButton(AlertDialog.BUTTON_NEUTRAL);
                defaultBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d(TAG,"Default clicked ");
                        switch(v) {
                            case 1:
                                newVirtueName.setText(getResources().getString(R.string.v1_title));
                                def.setText(getResources().getString(R.string.v1_desc));
                                break;
                            case 2:
                                newVirtueName.setText(getResources().getString(R.string.v2_title));
                                def.setText(getResources().getString(R.string.v2_desc));
                                break;
                            case 3:
                                newVirtueName.setText(getResources().getString(R.string.v3_title));
                                def.setText(getResources().getString(R.string.v3_desc));
                                break;
                            case 4:
                                newVirtueName.setText(getResources().getString(R.string.v4_title));
                                def.setText(getResources().getString(R.string.v4_desc));
                                break;
                            case 5:
                                newVirtueName.setText(getResources().getString(R.string.v5_title));
                                def.setText(getResources().getString(R.string.v5_desc));
                                break;
                            case 6:
                                newVirtueName.setText(getResources().getString(R.string.v6_title));
                                def.setText(getResources().getString(R.string.v6_desc));
                                break;
                            case 7:
                                newVirtueName.setText(getResources().getString(R.string.v7_title));
                                def.setText(getResources().getString(R.string.v7_desc));
                                break;
                            case 8:
                                newVirtueName.setText(getResources().getString(R.string.v8_title));
                                def.setText(getResources().getString(R.string.v8_desc));
                                break;
                            case 9:
                                newVirtueName.setText(getResources().getString(R.string.v9_title));
                                def.setText(getResources().getString(R.string.v9_desc));
                                break;
                            case 10:
                                newVirtueName.setText(getResources().getString(R.string.v10_title));
                                def.setText(getResources().getString(R.string.v10_desc));
                                break;
                            case 11:
                                newVirtueName.setText(getResources().getString(R.string.v11_title));
                                def.setText(getResources().getString(R.string.v11_desc));
                                break;
                            case 12:
                                newVirtueName.setText(getResources().getString(R.string.v12_title));
                                def.setText(getResources().getString(R.string.v12_desc));
                                break;
                            case 13:
                                newVirtueName.setText(getResources().getString(R.string.v13_title));
                                def.setText(getResources().getString(R.string.v13_desc));
                                break;
                        }
                    }
                });
            }
        });
        ad.show();
    }

    private void blinkTitle() {
        TextView tv = (TextView) findViewById(R.id.virtueTitle);
        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(50); //You can manage the time of the blink with this parameter
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(3);
        tv.startAnimation(anim);
    }

    @Override
    public void onResume() {
        super.onResume();
        selectActiveVirtue();
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

    private void showChangeVirtueDialog() {
        new AlertDialog.Builder(this).setMessage("Are you sure you want to change your virtue? Change is due in "+ _appPrefs.getChangeVirtueDaysLeft() +" days")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
                        _appPrefs.saveActiveVirtue(String.valueOf(v));
                        _appPrefs.saveRating(v, 0);
                        _appPrefs.createChangeVirtueReminder();
                        System.exit(0);
                        finish();
                    }
                })
                .setNegativeButton("no", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
                        selectActiveVirtue();
                    }
                })
                .show();
    }

    private void selectActiveVirtue() {
        switch (Integer.parseInt(_appPrefs.getActiveVirtue())) {
            case 1:
                v1Btn.callOnClick();
                break;
            case 2:
                v2Btn.callOnClick();
                break;
            case 3:
                v3Btn.callOnClick();
                break;
            case 4:
                v4Btn.callOnClick();
                break;
            case 5:
                v5Btn.callOnClick();
                break;
            case 6:
                v6Btn.callOnClick();
                break;
            case 7:
                v7Btn.callOnClick();
                break;
            case 8:
                v8Btn.callOnClick();
                break;
            case 9:
                v9Btn.callOnClick();
                break;
            case 10:
                v10Btn.callOnClick();
                break;
            case 11:
                v11Btn.callOnClick();
                break;
            case 12:
                v12Btn.callOnClick();
                break;
            case 13:
                v13Btn.callOnClick();
                break;
        }
    }

    public static boolean equals(Object a, Object b) {
        return (a == b) || (a != null && a.equals(b));
    }
}
