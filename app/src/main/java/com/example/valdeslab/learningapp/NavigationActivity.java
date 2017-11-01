package com.example.valdeslab.learningapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.valdeslab.learningapp.Bluetooth.BluetoothActivity;
import com.example.valdeslab.learningapp.Charts.ChartActivity;
import com.example.valdeslab.learningapp.DataMovement.BasicExampleActivity;
import com.example.valdeslab.learningapp.DataMovement.FragmentExampleActivity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        setItUp();
    }

    /***********************************************************************************************
     *
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


    }

}
