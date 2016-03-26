package com.listfist.virtue;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ProgressActivity extends AppCompatActivity {
    private static final String TAG = SplashActivity.class.getName();
    private SQLiteDatabase database;
    public final static String RECORD_TABLE="virtueLog"; // name of table

    public final static String RECORD_ID="_id"; // id value for record
    public final static String RECORD_TIME="dt";  // datetime of record
    public final static String RECORD_1="v1";
    public final static String RECORD_2="v2";
    public final static String RECORD_3="v3";
    public final static String RECORD_4="v4";
    public final static String RECORD_5="v5";
    public final static String RECORD_6="v6";
    public final static String RECORD_7="v7";
    public final static String RECORD_8="v8";
    public final static String RECORD_9="v9";
    public final static String RECORD_10="v10";
    public final static String RECORD_11="v11";
    public final static String RECORD_12="v12";
    public final static String RECORD_13="v13";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new AlertDialog.Builder(ProgressActivity.this)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setTitle("Clearing Progress")
                            .setMessage("Are you sure you want to delete all of your records? This cannot be undone.")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    clearProgress();
                                    updateList();
                                    View contentView = findViewById(android.R.id.content);
                                            Snackbar.make(contentView, "Cleared progress. Ahh a fresh start.", Snackbar.LENGTH_LONG)
                                            .setAction("Action", null).show();
                                }

                            })
                            .setNegativeButton("No", null)
                            .show();


                }
            });
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Set up database
        MyDatabaseHelper dbHelper = new MyDatabaseHelper(this);
        database = dbHelper.getWritableDatabase();

        updateList();
    }
    public Cursor selectRecords() {
        String[] cols = new String[] {RECORD_ID, RECORD_TIME, RECORD_1, RECORD_2, RECORD_3, RECORD_4, RECORD_5, RECORD_6, RECORD_7, RECORD_8, RECORD_9, RECORD_10, RECORD_11, RECORD_12, RECORD_13};
        if(database!=null) {
            String selectQuery = "SELECT _id, strftime('%w', dt), v1, v2, v3, v4, v5, v6, v7, v8, v9, v10, v11, v12, v13 FROM "+ RECORD_TABLE;
            Log.d(TAG,selectQuery);
            Cursor mCursor = database.rawQuery(selectQuery, null);

            //Cursor mCursor = database.query(true, RECORD_TABLE, cols, null                    , null, null, null, null, null);
            if (mCursor != null) {
                mCursor.moveToFirst();
            }
            return mCursor; // iterate to get each value.
        }
        else {
            return null;
        }
    }
    private void clearProgress() {
        //
        LinearLayout container = (LinearLayout) findViewById(R.id.records);
        container.removeAllViews();
        database.delete(RECORD_TABLE, null, null);
        updateList();
    }
    private void updateList() {
        // Get the view to stick out data
        LinearLayout container = (LinearLayout) findViewById(R.id.records);
        Cursor mCursor = selectRecords();
        if(mCursor!=null) {
            for(int i=0;i<mCursor.getCount();i++) {
                // Create a new horizontal layout
                LinearLayout LL = new LinearLayout(this);
                LL.setOrientation(LinearLayout.HORIZONTAL);
                LL.setPadding(20,20,20,20);
                ViewGroup.LayoutParams LLParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                LL.setLayoutParams(LLParams);

                TextView dummyView = new TextView(this);
                ViewGroup.LayoutParams dummyParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dummyView.setLayoutParams(dummyParams);

                Log.d(TAG, "Record " + i + 1 + "/" + mCursor.getCount() + " Date: " + mCursor.getString(1));
                Log.d(TAG, "virtue 2: " + mCursor.getInt(3) + " v2 type: " + mCursor.getInt(3));
                StringBuilder sb = new StringBuilder();

                for(int x=1;x<15;x++) { //id is at 0, skip it
                    // 0=id 1=datetime 2=virtue 1... 15=virtue 13
                    if(x>1) {
                        sb.append(mCursor.getInt(x)+"\t\t");
                        Log.d(TAG, "column " + mCursor.getColumnName(x) + " - " + mCursor.getInt(x));
                    }
                    else {
                        if(x==1) { // Append date
                            switch(mCursor.getInt(x)) {
                                case 0:
                                    sb.append("Sun");
                                    break;
                                case 1:
                                    sb.append("Mon");
                                    break;
                                case 2:
                                    sb.append("Tue");
                                    break;
                                case 3:
                                    sb.append("Wed");
                                    break;
                                case 4:
                                    sb.append("Thu");
                                    break;
                                case 5:
                                    sb.append("Fri");
                                    break;
                                case 6:
                                    sb.append("Sat");
                                    break;
                            }
                            sb.append("\t\t\t\t\t\t\t");

                        }
                        Log.d(TAG, "column " + mCursor.getColumnName(x) + " - " + mCursor.getString(x) + " - or - " + mCursor.getInt(x));
                    }
                }
                dummyView.setText(sb);
                LL.addView(dummyView);
                if (container != null) {
                    container.addView(LL);
                }
                mCursor.moveToNext();
            }
        }
    }
}
