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
    private static final int SSX_COUNT = 8;
    private static final int QUESTION_COUNT = 9;

    public static Intent newIntent(Context context){
        return new  Intent(context, KioskActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kiosk);

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

        for (int i = 0; i < ids.size(); i++) {
            Log.i(TAG, "(KioskActivity) ids: " + ids.get(i));
        }

        ArrayList<String> ssx = list.get(1);

        for (int i = 0; i < ssx.size(); i++) {
            Log.i(TAG, "(KioskActivity) ids: " + ssx.get(i));
        }

        if (ids.get(0) < SSX_COUNT) {
            loadSsxFragment(ids, ssx);
        } else {
            loadQuestionsFragment(ids, ssx);
        }

    }

    private void loadSsxFragment(ArrayList<Integer> ids, ArrayList<String> ssx){

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = SsxFragment.newInstance(ids, ssx);
        fragmentManager.beginTransaction().add(R.id.kiosk_container, fragment).commit();
    }

    private void loadQuestionsFragment(ArrayList<Integer> ids, ArrayList<String> questions){

    }
}
