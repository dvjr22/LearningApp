package com.example.valdeslab.learningapp.Charts;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.valdeslab.learningapp.R;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ChartTwoActivity extends AppCompatActivity {

    ArrayList<Integer> time = new ArrayList<>();
    ArrayList<Integer> hr = new ArrayList<>();

    int count = 0;
    int heart = 85;

    int critical = 1;

    /***********************************************************************************************
     *
     */
    public static Intent newIntent(Context context){
        return new Intent(context, ChartTwoActivity.class);
    }

    /***********************************************************************************************
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_two);

        execute();

        /* This doesn't work.
        try{
            engage();
        }catch (Exception e){
            e.printStackTrace();
        }
        */
    }

    /***********************************************************************************************
     *
     */
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
                //checkCriticalHeart(hr.get(hr.size()-1)); //This crashes the app
                loadLineChart(time, hr);
            }
        }, 0, 1000, TimeUnit.MILLISECONDS); // 1 seconds

    }

    /***********************************************************************************************
     *
     */
    private void incrementData(){

        if (count % 30 == 0 && critical % 2 == 0) {
            heart = 121;
            critical++;
        } else if (count % 30 == 0 && critical % 2 != 0){
            heart = 85;
            critical++;
        }

        time.add(count);
        hr.add(heart);

        count++;

        if (count % 2 == 0) {
            heart++;
        } else {
            heart--;
        }


        if (time.size() > 9) {
            time = new ArrayList<>(time.subList(1, 10));
            hr = new ArrayList<>(hr.subList(1, 10));

            for (int i = 0; i < time.size(); i++){
                Log.i("trace", "In time: " + time.get(i));
            }
        }

        Log.i("trace", Integer.toString(heart) + " " + Integer.toString(count));
    }

    /***********************************************************************************************
     *
     */
    private void checkCriticalHeart(int lastReading){

        Log.i("trace", "checkCritical called");

        if (lastReading > 119) Toast.makeText(this, "Send that critical message", Toast.LENGTH_SHORT).show();

    }

    /***********************************************************************************************
     *
     */
    private void engage() throws InterruptedException{

        while(true){
            incrementData();
            //checkCriticalHeart(hr.get(hr.size()-1));
            loadLineChart(time, hr);
            Thread.sleep(1000); // 1 second
            Log.i("trace", "running");
        }
    }

    /***********************************************************************************************
     *
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //todo stop runnable here - dvj 12/28/17
    }

}
