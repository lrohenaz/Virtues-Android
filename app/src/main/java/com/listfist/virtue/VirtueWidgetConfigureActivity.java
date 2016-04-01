package com.listfist.virtue;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

/**
 * The configuration screen for the {@link VirtueWidget VirtueWidget} AppWidget.
 */
public class VirtueWidgetConfigureActivity extends Activity {

    private static final String PREFS_NAME = "com.listfist.virtue.VirtueWidget";
    private static final String PREF_PREFIX_KEY = "appwidget_";
    int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
    private AppPreferences _appPrefs;
    private int widgetTextColor;
    EditText mAppWidgetText;

    View.OnClickListener colorPickerBtnClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            final Context context = VirtueWidgetConfigureActivity.this;

            ColorPickerDialogBuilder
                    .with(context)
                    .setTitle("Choose color")
                    .initialColor(widgetTextColor)
                    .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                    .density(12)
                    .setOnColorSelectedListener(new OnColorSelectedListener() {
                        @Override
                        public void onColorSelected(int selectedColor) {
                            //toast("onColorSelected: 0x" + Integer.toHexString(selectedColor));
                            Log.d("TAG", "Color picked: " + Integer.toHexString(selectedColor));
                        }
                    })
                    .setPositiveButton("ok", new ColorPickerClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                            Log.d("TAG", "Ok Button Clicked: " + Integer.toHexString(selectedColor));
                            TextView widgetPreview = (TextView) findViewById(R.id.widgetPreview);
                            widgetPreview.setTextColor(selectedColor);
                            _appPrefs.saveWidgetTextColor(selectedColor);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Log.d("TAG", "Cancel Button Clicked");
                        }
                    })
                    .build()
                    .show();
        }
    };

    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            final Context context = VirtueWidgetConfigureActivity.this;

            // When the button is clicked, store the string locally
            //String widgetText = mAppWidgetText.getText().toString();
            //saveTitlePref(context, mAppWidgetId, widgetText);

            // It is the responsibility of the configuration activity to update the app widget
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            VirtueWidget.updateAppWidget(context, appWidgetManager, mAppWidgetId);

            // Make sure we pass back the original appWidgetId
            Intent resultValue = new Intent();
            resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
            setResult(RESULT_OK, resultValue);
            finish();
        }
    };

    public VirtueWidgetConfigureActivity() {
        super();
    }

    // Write the prefix to the SharedPreferences object for this widget
    static void saveTitlePref(Context context, int appWidgetId, String text) {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_NAME, 0).edit();
        prefs.putString(PREF_PREFIX_KEY + appWidgetId, text);
        prefs.apply();
    }

    // Read the prefix from the SharedPreferences object for this widget.
    // If there is no preference saved, get the default from a resource
    static String loadTitlePref(Context context, int appWidgetId) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        String titleValue = prefs.getString(PREF_PREFIX_KEY + appWidgetId, null);
        if (titleValue != null) {
            return titleValue;
        } else {
            return context.getString(R.string.appwidget_text);
        }
    }

    static void deleteTitlePref(Context context, int appWidgetId) {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_NAME, 0).edit();
        prefs.remove(PREF_PREFIX_KEY + appWidgetId);
        prefs.apply();
    }

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        // Set the result to CANCELED.  This will cause the widget host to cancel
        // out of the widget placement if the user presses the back button.
        setResult(RESULT_CANCELED);

        // Apply the theme from preferences
        _appPrefs = new AppPreferences(getApplicationContext());
        if(_appPrefs.getTheme()) {
            setTheme(R.style.AppTheme);
            widgetTextColor = ContextCompat.getColor(getApplicationContext(), R.color.colorAccent);
        }
        else {
            setTheme(R.style.AppThemeDark);
            widgetTextColor = ContextCompat.getColor(getApplicationContext(), R.color.colorAccentDark);
        }

        setContentView(R.layout.virtue_widget_configure);
        //mAppWidgetText = (ImageButton) findViewById(R.id.appwidget_img);
        findViewById(R.id.add_button).setOnClickListener(mOnClickListener);
        TextView widgetPreview = (TextView) findViewById(R.id.widgetPreview);
        Button pickerBtn = (Button) findViewById(R.id.widgetTextColorPickerBtn);
        if(_appPrefs.getWidgetTextColor()!=0) {
            widgetPreview.setTextColor(_appPrefs.getWidgetTextColor());
        }
        else {
            widgetPreview.setTextColor(widgetTextColor);
        }
        widgetPreview.setTypeface(Typeface.createFromAsset(getApplicationContext().getAssets(), "OldEurope.ttf"));
        pickerBtn.setOnClickListener(colorPickerBtnClickListener);
        if(_appPrefs.getWidgetTextColor()==0)
        _appPrefs.saveWidgetTextColor(widgetTextColor);

        // Find the widget id from the intent.
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            mAppWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        // If this activity was started with an intent without an app widget ID, finish with an error.
        if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
            return;
        }

        //mAppWidgetText.setText(loadTitlePref(VirtueWidgetConfigureActivity.this, mAppWidgetId));
    }
}

