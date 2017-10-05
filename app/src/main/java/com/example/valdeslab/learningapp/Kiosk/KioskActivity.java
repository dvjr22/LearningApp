package com.example.valdeslab.learningapp.Kiosk;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.valdeslab.learningapp.DataMovement.BasicExampleActivity;
import com.example.valdeslab.learningapp.R;
import com.example.valdeslab.learningapp.Utilities.ServerSimulator;

import java.util.ArrayList;

public class KioskActivity extends AppCompatActivity  implements
        NavBarFragment.NavBarListener{

    private static final String TAG = "trace";

    public static Intent newIntent(Context context){
        return new  Intent(context, KioskActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kiosk);

        callServer();

        loadNavBar();
    }

    /***********************************************************************************************
     *
     */
    private void loadNavBar(){

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = NavBarFragment.newInstance();
        fragmentManager.beginTransaction().add(R.id.nav_bar_container, fragment).commit();

    }

    /***********************************************************************************************
     *
     */
    public void callServer(){

        ArrayList<ArrayList> list = ServerSimulator.request();

        ArrayList<Integer> ids = list.get(0);

        ArrayList<String> ssx = list.get(1);


    }
}
