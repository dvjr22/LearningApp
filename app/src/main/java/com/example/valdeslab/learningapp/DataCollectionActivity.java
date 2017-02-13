package com.example.valdeslab.learningapp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.valdeslab.learningapp.DataManagement.Data;
import com.example.valdeslab.learningapp.DataManagement.DataManager;

public class DataCollectionActivity extends AppCompatActivity implements
    DataCollectionFragment.DataCollectionListener{

    private DataManager dataManager;

    public static Intent newIntent(Context context){
        return new Intent(context, DataCollectionActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_collection);

        dataManager = DataManager.get();

        loadFragment();

    }

    private void loadFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = DataCollectionFragment.newInstance();
        fragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commit();
    }

    public void collectData(String firstName, String lastName, int age){
        Data data = new Data();
        data.setFirst(firstName);
        data.setLast(lastName);
        data.setAge(age);
        Toast.makeText(this, data.dataInfo(), Toast.LENGTH_SHORT).show();
        dataManager.putData(data);

    }

}
