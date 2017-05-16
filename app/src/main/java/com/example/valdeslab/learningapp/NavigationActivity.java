package com.example.valdeslab.learningapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

public class NavigationActivity extends AppCompatActivity {

    private Button RecyclerViewButton;
    private Button DataMovementButton;
    private Button BasicActivityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        BasicActivityButton = (Button) findViewById(R.id.basic_example);
        BasicActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(BasicExampleActivity.newIntent(getApplicationContext()));
            }
        });

        RecyclerViewButton = (Button) findViewById(R.id.recycler_view_example);
        RecyclerViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(RecyclerViewActivity.newInstance(getApplicationContext()));
            }
        });




    }
}
