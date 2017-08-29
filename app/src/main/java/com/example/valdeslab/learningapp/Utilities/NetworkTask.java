package com.example.valdeslab.learningapp.Utilities;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/***************************************************************************************************
 * Class to handle network communication
 */
public class NetworkTask extends AsyncTask <Void, Void, Void> {

    private Context context;
    private File file;
    private FileOutputStream outputStream;


    public NetworkTask(File file, FileOutputStream outputStream) {
        this.file = file;
        this.outputStream = outputStream;
    }

    /***********************************************************************************************
     * Android method
     * Runs on the UI thread before doInBackground()
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    /***********************************************************************************************
     * Android method
     * Performs a computation on a background thread.
     * Can be used to call publishProgress() to display results on UI
     *
     * Using to test
     */
    @Override
    @TargetApi(20)
    protected Void doInBackground(Void... params) {

        int count = 0;
        int success = 0;
        int failure = 0;

        Log.i("trace", "(NetworkTask) doInBackground()");

        while (true) {
            try {
                String ipAddress = "129.252.131.78";
                InetAddress inet = InetAddress.getByName(ipAddress);
                System.out.println("Sending Ping Request to " + ipAddress);
                if (inet.isReachable(5000)){
                    success++;
                    System.out.println(ipAddress + " is reachable.");
                } else {
                    failure++;
                    System.out.println(ipAddress + " NOT reachable.");
                }
            } catch ( Exception e ) {
                System.out.println("Exception:" + e.getMessage());
            }

            try {
                outputStream.write("test".getBytes());
                Log.i("trace", "(NetworkTask) should have written to file");
            } catch (Exception e) {
                Log.i("trace", "(NetworkTask) Failure writing to file");
            }

            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                Log.i("trace", "(NetworkTask) no pause");
            }

            count++;

            if (count > 10) break;

        }

        try {
            outputStream.close();
        } catch (Exception e) {

        }


        return null;
    }

    /***********************************************************************************************
     * Runs on UI after doInBackground.
     * Specified result is the value returned by doInBackground()
     */
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }



}
