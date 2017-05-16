package com.example.valdeslab.learningapp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class RecyclerViewActivity extends AppCompatActivity {

    public static Intent newInstance(Context context){
        return new Intent(context, RecyclerViewActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        loadFragment();
    }

    private void loadFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = RecyclerViewFragment.newInstance();
        fragmentManager.beginTransaction().
                add(R.id.container_recycler_view_activity, fragment).commit();
    }
}
