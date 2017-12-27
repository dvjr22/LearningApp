package com.example.valdeslab.learningapp.GoogleCharts;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import com.example.valdeslab.learningapp.NavigationActivity;
import com.example.valdeslab.learningapp.R;
import com.example.valdeslab.learningapp.Utilities.PatientSimulator;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GoogleChartActivity extends AppCompatActivity {

    private static final String TAG = "trace";


    /***********************************************************************************************
     *
     * @param context
     * @return
     */
    public static Intent newIntent(Context context) {
        return new  Intent(context, GoogleChartActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_chart);

        /* This will load the webview with google home page
        setContentView(R.layout.webview_layout);
        WebView webView = (WebView) findViewById(R.id.webView1);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://www.google.com/");
        */

        execute();
    }

    /***********************************************************************************************
     *
     */
    private void execute() {

        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                simulateThatPatient();
            }
        }, 0, 2, TimeUnit.SECONDS);

    }

    /***********************************************************************************************
     *
     */
    private void simulateThatPatient(){

        Log.i(TAG, Integer.toString(PatientSimulator.heartRate()));

        PatientSimulator.heartRate();

    }
}
