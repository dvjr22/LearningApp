package com.example.valdeslab.learningapp.Charts;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.valdeslab.learningapp.R;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ChartTwoActivity extends AppCompatActivity {

    //int[] time = {0,1,2,3,4,5,6};
    //int[] hr = {85,86,87,85,84,86,88};

    ArrayList<Integer> time = new ArrayList<>();
    ArrayList<Integer> hr = new ArrayList<>();

    int count = 0;
    int heart = 85;

    public static Intent newIntent(Context context){
        return new Intent(context, ChartTwoActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_two);

        execute();
    }

    private void loadLineChart(ArrayList<Integer> time, ArrayList<Integer> hr){

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = LineChartFragment.newInstance(time, hr);
        fragmentManager.beginTransaction().
                replace(R.id.chart_two_activity_fragment_container, fragment).commit();
    }


    /***********************************************************************************************
     *
     */
    private void execute() {

        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                incrementData();
                loadLineChart(time, hr);
            }
        }, 0, 500, TimeUnit.MILLISECONDS);

    }

    private void incrementData(){
        time.add(count);
        hr.add(heart);

        count++;

        if (count % 2 == 0) {
            heart++;
        } else {
            heart--;
        }

        Log.i("trace", Integer.toString(heart) + " " + Integer.toString(count));
    }

}
