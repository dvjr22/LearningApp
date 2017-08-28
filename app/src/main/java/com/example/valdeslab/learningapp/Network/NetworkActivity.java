package com.example.valdeslab.learningapp.Network;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.valdeslab.learningapp.Charts.ChartActivity;
import com.example.valdeslab.learningapp.R;

public class NetworkActivity extends AppCompatActivity {

    public static Intent newIntent(Context context){
        return new Intent(context, NetworkActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);

    }
}
