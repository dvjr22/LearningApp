package com.example.valdeslab.learningapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.valdeslab.learningapp.DataMovement.BasicExampleActivity;
import com.example.valdeslab.learningapp.DataMovement.FragmentExampleActivity;
import com.example.valdeslab.learningapp.RecyclerView.RecyclerViewActivity;

public class NavigationActivity extends AppCompatActivity {

    private Button RecyclerViewButton;
    private Button DataMovementButton;
    private Button BasicActivityButton;
    private Button DataChartsButton;

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

        BasicActivityButton = (Button) findViewById(R.id.basic_example);
        BasicActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(BasicExampleActivity.newIntent(getApplicationContext()));
            }
        });

        DataMovementButton = (Button) findViewById(R.id.fragment_data_example);
        DataMovementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(FragmentExampleActivity.newIntent(getApplicationContext(), null, null));
            }
        });

        RecyclerViewButton = (Button) findViewById(R.id.recycler_view_example);
        RecyclerViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(RecyclerViewActivity.newInstance(getApplicationContext()));
            }
        });

        DataMovementButton = (Button) findViewById(R.id.data_chart_example);
        DataMovementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ChartActivity.newIntent(getApplicationContext()));
            }
        });

    }

}
