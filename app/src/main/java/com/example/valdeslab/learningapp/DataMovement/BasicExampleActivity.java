package com.example.valdeslab.learningapp.DataMovement;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.valdeslab.learningapp.R;

public class BasicExampleActivity extends AppCompatActivity {

    private Button send;
    private Button back;

    private EditText firstName;
    private EditText lastName;

    public static Intent newIntent(Context context){
        return new  Intent(context, BasicExampleActivity.class);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_example);

        send = (Button) findViewById(R.id.button_send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSomething(v);

            }
        });

        back = (Button) findViewById(R.id.button_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSomething(v);
            }
        });

        firstName = (EditText) findViewById(R.id.edittext_first);
        lastName = (EditText) findViewById(R.id.edittext_last);


    }

    public void doSomething(View view){
        String first = firstName.getText().toString();
        String last = lastName.getText().toString();
        startActivity(FragmentExampleActivity.newIntent(this, first, last));

    }

}
