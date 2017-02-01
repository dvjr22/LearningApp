package com.example.valdeslab.learningapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button send;
    private Button back;

    private EditText firstName;
    private EditText lastName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        startActivity(TwoActivity.newIntent(this, first, last));

    }

}
