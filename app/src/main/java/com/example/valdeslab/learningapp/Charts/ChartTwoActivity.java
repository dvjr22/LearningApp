package com.example.valdeslab.learningapp.Charts;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.valdeslab.learningapp.R;

public class ChartTwoActivity extends AppCompatActivity {

    public static Intent newIntent(Context context){
        return new Intent(context, ChartTwoActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_two);

        loadLineChart();
    }

    private void loadLineChart(){

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = LineChartFragment.newInstance();
        fragmentManager.beginTransaction().
                add(R.id.chart_two_activity_fragment_container, fragment).commit();

    }
}
