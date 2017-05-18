package com.example.valdeslab.learningapp.DataMovement;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.valdeslab.learningapp.R;

public class FragmentExampleActivity extends AppCompatActivity implements
    FirstNameFragment.firstNameListener,
    LastNameFragment.lastNameListener {

    private static String FIRST = "first_name";
    private static String LAST = "last_name";

    public static Intent newIntent(Context context, String first, String last){
        Intent intent = new Intent(context, FragmentExampleActivity.class);
        intent.putExtra(FIRST, first);
        intent.putExtra(LAST, last);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_example);

        String first = getIntent().getStringExtra(FIRST);
        String last = getIntent().getStringExtra(LAST);

        Button button = (Button) findViewById(R.id.button_fun);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(DataCollectionActivity.newIntent(v.getContext()));
            }
        });

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
