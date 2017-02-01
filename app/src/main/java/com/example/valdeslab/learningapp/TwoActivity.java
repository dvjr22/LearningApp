package com.example.valdeslab.learningapp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TwoActivity extends AppCompatActivity implements
    FirstNameFragment.firstNameListener,
    LastNameFragment.lastNameListener {

    private static String FIRST = "first_name";
    private static String LAST = "last_name";

    public static Intent newIntent(Context context, String first, String last){
        Intent intent = new Intent(context, TwoActivity.class);
        intent.putExtra(FIRST, first);
        intent.putExtra(LAST, last);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        String first = getIntent().getStringExtra(FIRST);
        String last = getIntent().getStringExtra(LAST);

        replaceFragmentTwo(last);
        replaceFragmentOne(first);
    }

    public void replaceFragmentTwo(String last){
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment2 = LastNameFragment.newInstance(last);
        fragmentManager.beginTransaction().replace(R.id.fragment_container_two, fragment2).commit();
    }

    public void replaceFragmentOne(String first){
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = FirstNameFragment.newInstance(first);
        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }


}
