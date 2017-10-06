package com.example.valdeslab.learningapp.Kiosk;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.valdeslab.learningapp.DataMovement.BasicExampleActivity;
import com.example.valdeslab.learningapp.R;
import com.example.valdeslab.learningapp.Utilities.ServerSimulator;

import java.util.ArrayList;

public class KioskActivity extends AppCompatActivity  implements
        NavBarFragment.NavBarListener,
        SsxFragment.SsxFragmentListener,
        QuestionFragment.QuestionFragmentListener{

    private static final String TAG = "trace";
    private static final int SSX_COUNT = 8;
    private static final int QUESTION_COUNT = 9;

    public static Intent newIntent(Context context) {
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
        ArrayList<String> ssx = list.get(1);

        if (ids.get(0) < SSX_COUNT) {
            loadSsxFragment(ids, ssx);
        } else {
            loadQuestionsFragment(ids, ssx);
        }
    }

    /***********************************************************************************************
     *
     */
    private void loadSsxFragment(ArrayList<Integer> ids, ArrayList<String> ssx){

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = SsxFragment.newInstance(ids, ssx);
        fragmentManager.beginTransaction().replace(R.id.kiosk_container, fragment).commit();
    }

    /***********************************************************************************************
     *
     */
    private void loadQuestionsFragment(ArrayList<Integer> ids, ArrayList<String> questions){

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = QuestionFragment.newInstance(ids, questions);
        fragmentManager.beginTransaction().replace(R.id.kiosk_container, fragment).commit();

    }

    /***********************************************************************************************
     *
     * @param submission
     * @param from
     */
    public void submitToServerSimulator(ArrayList<String> submission, String from){

        Toast.makeText(this, "Data sent to server", Toast.LENGTH_SHORT).show();

        for (int i = 0; i < submission.size(); i++) {
            Log.i(TAG, "(KioskActivity) " + submission.get(i));
        }
    }

    /***********************************************************************************************
     *
     * @param quesId
     * @param answer
     */
    public void submitToServerSimulator(String quesId, String answer) {

        Toast.makeText(this, "Data sent to server", Toast.LENGTH_SHORT).show();

        Log.i(TAG, "(KioskActivity) " + quesId + " " + answer);
    }

}
