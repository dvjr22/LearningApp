package com.example.valdeslab.learningapp.Network;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.valdeslab.learningapp.Charts.ChartActivity;
import com.example.valdeslab.learningapp.R;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import static android.widget.Toast.makeText;

public class NetworkActivity extends AppCompatActivity {

    private Button start;

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
                pingHost(getApplicationContext(), "test", 80, 60);
            }
        });

    }

    @TargetApi(20)
    public static boolean pingHost(Context context, String host, int port, int timeout) {

        Toast _true = makeText(context, "true", Toast.LENGTH_SHORT);
        Toast _false = makeText(context, "false", Toast.LENGTH_SHORT);

        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(host, port), timeout);
            _true.show();
            return true;
        } catch (IOException e) {
            _false.show();
            return false; // Either timeout or unreachable or failed DNS lookup.
        }
    }
}
