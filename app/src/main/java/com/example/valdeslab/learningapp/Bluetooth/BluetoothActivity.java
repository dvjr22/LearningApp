package com.example.valdeslab.learningapp.Bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHealth;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.valdeslab.learningapp.NavigationActivity;
import com.example.valdeslab.learningapp.R;

import java.util.Iterator;
import java.util.Set;

public class BluetoothActivity extends AppCompatActivity {

    private static final int REQUEST_ENABLE_BT = 0;
    private static final int REQUEST_DISCOVERABLE = 1;

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

        // get all the bluetooth device paired to the device
        Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
/*
        Iterator<BluetoothDevice> it = pairedDevices.iterator();
        while(it.hasNext()) {
            log(it.next().toString());

        }
*/
        // Iterate through set and get paired device name and mac address
        if (pairedDevices.size() > 0) {
            for (BluetoothDevice device : pairedDevices ) {
                log(device.getName());
                log(device.getAddress());

            }

        }

        // register for broadcasts when a device is discovered
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(Receiver, filter);


        // This asks to make device discovera ble for 5 minutes
        // default is 2 min
/*
        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
        startActivityForResult(discoverableIntent, REQUEST_DISCOVERABLE);
*/

    }

    private final BroadcastReceiver Receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Discovery has found a device. Get the Bluetooth device
                // object and its info from the intent
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                String deviceName = device.getName();
                String deviceHardwareAddress = device.getAddress();

                log(deviceName + " " + deviceHardwareAddress);
            }

        }
    };

    /***********************************************************************************************
     *
     * @param log
     */
    private void log(String log) {
        Log.i(TAG, "(BluetoothActivity) + " + log);
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

            case REQUEST_DISCOVERABLE:
                log("Something happened");
                break;

            default:
                startActivity(NavigationActivity.newIntent(this));
                break;
        }

        log(test);
    }

    /***********************************************************************************************
     *
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(Receiver);
    }
}
