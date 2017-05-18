package com.example.valdeslab.learningapp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.valdeslab.learningapp.RecyclerView.RecyclerViewFragment;

public class ChartActivity extends AppCompatActivity {

    public static Intent newIntent(Context context){
        return new Intent(context, ChartActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        loadChart();

    }

    private void loadChart(){

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = PieChartFrag.newInstance();
        fragmentManager.beginTransaction().
                add(R.id.chart_activity_fragment_container, fragment).commit();


    }
}
