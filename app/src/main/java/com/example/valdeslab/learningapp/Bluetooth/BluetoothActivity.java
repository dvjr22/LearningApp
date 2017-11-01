package com.example.valdeslab.learningapp.Bluetooth;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.valdeslab.learningapp.R;

public class BluetoothActivity extends AppCompatActivity {

    public static Intent newIntent(Context context) {
        return new  Intent(context, BluetoothActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
    }
}
