package com.example.valdeslab.learningapp.Bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothHealth;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.valdeslab.learningapp.NavigationActivity;
import com.example.valdeslab.learningapp.R;

public class BluetoothActivity extends AppCompatActivity {

    private static final int REQUEST_ENABLE_BT = 0;

    private static final String TAG = "trace";

    private BluetoothHealth bluetoothHealth;

    public static Intent newIntent(Context context) {
        return new  Intent(context, BluetoothActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            // Device doesn't support bluetooth
        }

        // check if bluetooth is enabled
        if (!bluetoothAdapter.isEnabled()) {
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, REQUEST_ENABLE_BT);
        }

    }

    /***********************************************************************************************
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String test = "request: " + requestCode + " result " + resultCode;

        switch(requestCode) {
            case REQUEST_ENABLE_BT:

                if (resultCode < 0); // Do something when request is denied
                break;

            default:
                startActivity(NavigationActivity.newIntent(this));
                break;
        }

        log(test);

    }

    private void log(String log) {
        Log.i(TAG, "(BluetoothActivity) + " + log);
    }
}
