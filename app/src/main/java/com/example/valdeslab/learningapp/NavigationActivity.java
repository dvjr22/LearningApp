package com.example.valdeslab.learningapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.valdeslab.learningapp.Bluetooth.BluetoothActivity;
import com.example.valdeslab.learningapp.Charts.ChartActivity;
import com.example.valdeslab.learningapp.Charts.ChartTwoActivity;
import com.example.valdeslab.learningapp.DataMovement.BasicExampleActivity;
import com.example.valdeslab.learningapp.DataMovement.FragmentExampleActivity;
import com.example.valdeslab.learningapp.GoogleCharts.GoogleChartActivity;
import com.example.valdeslab.learningapp.Kiosk.KioskActivity;
import com.example.valdeslab.learningapp.Network.NetworkActivity;
import com.example.valdeslab.learningapp.RecyclerView.RecyclerViewActivity;

public class NavigationActivity extends AppCompatActivity{

    private Button recyclerViewButton;
    private Button dataMovementButton;
    private Button basicActivityButton;
    private Button dataChartsButton;
    private Button networkTestButton;
    private Button kioskButton;
    private Button bluetoothButton;
    //private Button googleChartButton; // Deactivate b/c abandonded this idea
    private Button pulseOximeterButton;

    /***********************************************************************************************
     *
     * @param context
     * @return
     */
    public static Intent newIntent(Context context) {
        return new  Intent(context, NavigationActivity.class);
    }

    /***********************************************************************************************
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        setItUp();
    }

    /***********************************************************************************************
     * Set up buttons
     */
    private void setItUp(){

        basicActivityButton = (Button) findViewById(R.id.basic_example);
        basicActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(BasicExampleActivity.newIntent(getApplicationContext()));
            }
        });

        dataMovementButton = (Button) findViewById(R.id.fragment_data_example);
        dataMovementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(FragmentExampleActivity.newIntent(getApplicationContext(), null, null));
            }
        });

        recyclerViewButton = (Button) findViewById(R.id.recycler_view_example);
        recyclerViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(RecyclerViewActivity.newInstance(getApplicationContext()));
            }
        });

        dataChartsButton = (Button) findViewById(R.id.data_chart_example);
        dataChartsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ChartActivity.newIntent(getApplicationContext()));
            }
        });

        networkTestButton = (Button) findViewById(R.id.network_example);
        networkTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(NetworkActivity.newIntent(getApplicationContext()));
            }
        });

        kioskButton = (Button) findViewById(R.id.kiosk_button);
        kioskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(KioskActivity.newIntent(getApplicationContext()));
            }
        });

        bluetoothButton = (Button) findViewById(R.id.bluetooth_button);
        bluetoothButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(BluetoothActivity.newIntent(getApplicationContext()));
            }
        });

        pulseOximeterButton = (Button) findViewById(R.id.pulse_button);
        pulseOximeterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ChartTwoActivity.newIntent(getApplicationContext()));
            }
        });
/*
        googleChartButton = (Button) findViewById(R.id.google_chart_button);
        googleChartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(GoogleChartActivity.newIntent(getApplicationContext()));
            }
        });
*/


    }

}
