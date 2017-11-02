package com.example.valdeslab.learningapp.Bluetooth;


import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.valdeslab.learningapp.NavigationActivity;
import com.example.valdeslab.learningapp.R;
import com.example.valdeslab.learningapp.Utilities.BluetoothHDPService;

import java.util.Iterator;
import java.util.Set;

public class BluetoothActivity extends AppCompatActivity {

    private static final String TAG = "trace";

    // Use the appropriate IEEE 11073 data types based on the devices used.
    // Below are some examples. Refer to relevant Bluetooth HDP specifications
    // for detail.
    // 0x1007 - blood pressure meter
    // 0x1008 - body thermometer
    // 0x100F - body weight scale
    private static final int HEALTH_PROFILE_SOURCE_DATA_TYPE = 0x1004;

    // ActivityResult variables
    private static final int REQUEST_ENABLE_BT = 1;
    private static final int REQUEST_DISCOVERABLE = 2;

    private TextView mConnectIndicator;
    private ImageView mDataIndicator;
    private TextView mStatusMessage;

    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothDevice[] mAllBondedDevices;
    private BluetoothDevice mDevice;
    private int mDeviceIndex = 0;
    private Resources mRes;
    private Messenger mHealthService;
    private boolean mHealthServiceBound;

    // myturnnow
    private TextView heartRate;
    private TextView oxigen;

    // version number
    private TextView mVersion;

    public static Intent newIntent(Context context) {
        return new  Intent(context, BluetoothActivity.class);
    }

    // Handles events sent by {@link BluetoothHDPService}.
    private Handler mIncomingHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                // Application registration complete.
                case BluetoothHDPService.STATUS_HEALTH_APP_REG:
                    mStatusMessage.setText(String.format(
                            mRes.getString(R.string.status_reg), msg.arg1));
                    break;
                // Application unregistration complete.
                case BluetoothHDPService.STATUS_HEALTH_APP_UNREG:
                    mStatusMessage.setText(String.format(
                            mRes.getString(R.string.status_unreg), msg.arg1));
                    break;
                // Reading data from HDP device.
                case BluetoothHDPService.STATUS_READ_DATA:
                    mStatusMessage.setText(mRes.getString(R.string.read_data));
                    mDataIndicator.setImageLevel(1);
                    break;
                // Finish reading data from HDP device.
                case BluetoothHDPService.STATUS_READ_DATA_DONE:
                    mStatusMessage.setText(mRes.getString(R.string.read_data_done));
                    mDataIndicator.setImageLevel(0);
                    break;
                // Channel creation complete. Some devices will automatically
                // establish
                // connection.
                case BluetoothHDPService.STATUS_CREATE_CHANNEL:
                    Log.d(TAG, "STATUS_CREATE_CHANNEl enabled");
                    mStatusMessage.setText(String.format(
                            mRes.getString(R.string.status_create_channel),
                            msg.arg1));
                    mConnectIndicator.setText(R.string.connected);
                    break;
                // Channel destroy complete. This happens when either the device
                // disconnects or
                // there is extended inactivity.
                case BluetoothHDPService.STATUS_DESTROY_CHANNEL:
                    mStatusMessage.setText(String.format(
                            mRes.getString(R.string.status_destroy_channel),
                            msg.arg1));
                    mConnectIndicator.setText(R.string.disconnected);
                    break;
                case BluetoothHDPService.RECEIVED_O2:
                    oxigen.setText("" + msg.arg1);

                    break;
                case BluetoothHDPService.RECEIVED_HEART_RATE:
                    heartRate.setText("" + msg.arg1);

                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    };

    private final Messenger mMessenger = new Messenger(mIncomingHandler);

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (mBluetoothAdapter == null) {
            Toast.makeText(this, R.string.bluetooth_not_available,
                    Toast.LENGTH_LONG).show();;
            finish();
            return;
        }


        //setContentView(R.layout.activity_bluetooth);
        setContentView(R.layout.activity_bhdp);
        mConnectIndicator = (TextView) findViewById(R.id.connect_ind);
        mDataIndicator = (ImageView) findViewById(R.id.data_ind);
        mRes = getResources();
        mHealthServiceBound = false;

        heartRate= (TextView) findViewById(R.id.heart_rate);
        oxigen= (TextView) findViewById(R.id.sp02);

        mVersion = (TextView) findViewById(R.id.Version);
        // mSys.setText("blah");

        try {
            String versionName = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
            mVersion.setText("V " + versionName);
        } catch (NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Initiates application registration through {@link
        // BluetoothHDPService}.
        Button registerAppButton = (Button) findViewById(R.id.button_register_app);
        registerAppButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sendMessage(BluetoothHDPService.MSG_REG_HEALTH_APP,
                        HEALTH_PROFILE_SOURCE_DATA_TYPE);
            }
        });


        // Initiates application unregistration through {@link
        // BluetoothHDPService}.
        Button unregisterAppButton = (Button) findViewById(R.id.button_unregister_app);
        unregisterAppButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sendMessage(BluetoothHDPService.MSG_UNREG_HEALTH_APP, 0);
            }
        });

        // get all the bluetooth device paired to the device
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();

        // Iterate through set and get paired device name and mac address
        if (pairedDevices.size() > 0) {
            for (BluetoothDevice device : pairedDevices ) {
                log(device.getName());
                log(device.getAddress());

            }
        }
/*
        Iterator<BluetoothDevice> it = pairedDevices.iterator();
        while(it.hasNext()) {
            log(it.next().toString());

        }
*/
/*
        // register for broadcasts when a device is discovered
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(Receiver, filter);
*/
        // This asks to make device discoverable for 5 minutes
        // default is 2 min
/*
        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
        startActivityForResult(discoverableIntent, REQUEST_DISCOVERABLE);
*/
    }
/*
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
*/
    /***********************************************************************************************
     *
     * @param log
     */
    private void log(String log) {
        Log.i(TAG, "(BluetoothActivity) + " + log);
    }

    // Sets up communication with {@link BluetoothHDPService}.
    private ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName name, IBinder service) {
            mHealthServiceBound = true;
            Message msg = Message.obtain(null,
                    BluetoothHDPService.MSG_REG_CLIENT);
            msg.replyTo = mMessenger;
            mHealthService = new Messenger(service);
            try {
                mHealthService.send(msg);
            } catch (RemoteException e) {
                Log.w(TAG, "Unable to register client to service.");
                e.printStackTrace();
            }
        }

        public void onServiceDisconnected(ComponentName name) {
            mHealthService = null;
            mHealthServiceBound = false;
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHealthServiceBound)
            unbindService(mConnection);
        // unregisterReceiver(mReceiver);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // If Bluetooth is not on, request that it be enabled.
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableIntent = new Intent(
                    BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
        } else {
            initialize();
        }
    }

    /***********************************************************************************************
     * Ensures user has turned on Bluetooth on the Android device
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String test = "request: " + requestCode + " result " + resultCode;

        switch (requestCode) {
            case REQUEST_ENABLE_BT:
                if (resultCode == Activity.RESULT_OK) {
                    // initialize();
                } else {
                    finish();
                    return;
                }
        }

        log(test);
    }

    /**
     * Used by {@link SelectDeviceDialogFragment} to record the bonded Bluetooth
     * device selected by the user.
     *
     * @param position
     *            Position of the bonded Bluetooth device in the array.
     */
    public void setDevice(int position) {
        mDevice = this.mAllBondedDevices[position];
        mDeviceIndex = position;
    }

    private void connectChannel() {
        sendMessageWithDevice(BluetoothHDPService.MSG_CONNECT_CHANNEL);
    }

    private void disconnectChannel() {
        sendMessageWithDevice(BluetoothHDPService.MSG_DISCONNECT_CHANNEL);
    }

    private void initialize() {
        // Starts health service.
        Intent intent = new Intent(this, BluetoothHDPService.class);
        startService(intent);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    // Intent filter and broadcast receive to handle Bluetooth on event.
    private IntentFilter initIntentFilter() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        return filter;
    }

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)) {
                if (intent.getIntExtra(BluetoothAdapter.EXTRA_STATE,
                        BluetoothAdapter.ERROR) == BluetoothAdapter.STATE_ON) {
                    initialize();
                }
            }
        }
    };

    // Sends a message to {@link BluetoothHDPService}.
    private void sendMessage(int what, int value) {
        if (mHealthService == null) {
            Log.d(TAG, "Health Service not connected.");
            return;
        }

        try {
            mHealthService.send(Message.obtain(null, what, value, 0));
        } catch (RemoteException e) {
            Log.w(TAG, "Unable to reach service.");
            e.printStackTrace();
        }
    }

    // Sends an update message, along with an HDP BluetoothDevice object, to
    // {@link BluetoothHDPService}. The BluetoothDevice object is needed by the
    // channel creation
    // method.
    private void sendMessageWithDevice(int what) {
        if (mHealthService == null) {
            Log.d(TAG, "Health Service not connected.");
            return;
        }

        try {
            mHealthService.send(Message.obtain(null, what, mDevice));
        } catch (RemoteException e) {
            Log.w(TAG, "Unable to reach service.");
            e.printStackTrace();
        }
    }

    /**
     * Dialog to display a list of bonded Bluetooth devices for user to select
     * from. This is needed only for channel connection initiated from the
     * application.
     */
    public static class SelectDeviceDialogFragment extends DialogFragment {

        public static SelectDeviceDialogFragment newInstance(String[] names,
                                                             int position) {
            SelectDeviceDialogFragment frag = new SelectDeviceDialogFragment();
            Bundle args = new Bundle();
            args.putStringArray("names", names);
            args.putInt("position", position);
            frag.setArguments(args);
            return frag;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            String[] deviceNames = getArguments().getStringArray("names");
            int position = getArguments().getInt("position", -1);
            if (position == -1)
                position = 0;
            return new AlertDialog.Builder(getActivity())
                    .setTitle(R.string.select_device)
                    .setPositiveButton(R.string.ok,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    ((BluetoothActivity) getActivity())
                                            .connectChannel();
                                }
                            })
                    .setSingleChoiceItems(deviceNames, position,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    ((BluetoothActivity) getActivity())
                                            .setDevice(which);
                                }
                            }).create();
        }
    }


}
