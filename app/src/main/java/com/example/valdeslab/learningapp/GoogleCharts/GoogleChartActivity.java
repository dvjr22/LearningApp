package com.example.valdeslab.learningapp.GoogleCharts;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.valdeslab.learningapp.NavigationActivity;
import com.example.valdeslab.learningapp.R;

public class GoogleChartActivity extends AppCompatActivity {


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
    }
}
