package com.example.valdeslab.learningapp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DataCollectionActivity extends AppCompatActivity {

    public static Intent newIntent(Context context){
        return new Intent(context, DataCollectionActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_collection);

        loadFragment();

    }

    private void loadFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = DataCollectionFragment.newInstance();
        fragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commit();
    }

}
