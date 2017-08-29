package com.example.valdeslab.learningapp.Network;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.valdeslab.learningapp.Charts.ChartActivity;
import com.example.valdeslab.learningapp.R;
import com.example.valdeslab.learningapp.Utilities.NetworkTask;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import static android.widget.Toast.makeText;

public class NetworkActivity extends AppCompatActivity {

    private Button start;
    private FileOutputStream outputStream;

    public static Intent newIntent(Context context){
        return new Intent(context, NetworkActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);

        start = (Button) findViewById(R.id.button_start_network);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pingHost(getApplicationContext());
            }
        });
    }

    public void pingHost(Context context) {

        File file = new File(getApplicationContext().getFilesDir(), "iplog.txt");
        Log.i("trace", "(NetworkActivity)" +  file.toString());
        ;

        outputStream = null;

        try {
            outputStream = new FileOutputStream(file);
            outputStream.write("test".getBytes());
            outputStream.close();
        } catch (Exception e) {

        }

        NetworkTask networkTask = new NetworkTask(file, outputStream);
        networkTask.execute();
    }

}
