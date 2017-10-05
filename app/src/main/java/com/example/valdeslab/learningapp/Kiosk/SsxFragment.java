package com.example.valdeslab.learningapp.Kiosk;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.valdeslab.learningapp.R;

import java.util.ArrayList;


public class SsxFragment extends Fragment {

    private static String BUNDLE_IDS = "bundle_ids";
    private static String BUNDLE_SSX = "bundle_ssx";


    public SsxFragment() {}

    /***********************************************************************************************
     *
     * @param ids
     * @param ssx
     * @return
     */
    public static SsxFragment newInstance(ArrayList<Integer> ids, ArrayList<String> ssx){

        SsxFragment fragment = new SsxFragment();

        Bundle bundle = new Bundle();
        bundle.putIntegerArrayList(BUNDLE_IDS, ids);
        bundle.putStringArrayList(BUNDLE_SSX, ssx);
        fragment.setArguments(bundle);

        return fragment;
    }

    /***********************************************************************************************
     * Android method
     * Called to have the fragment instantiate its user interface view
     *
     * @param inflater              The LayoutInflater object that can be used to inflate any views
     * @param container             The parent view that the fragment's UI should be attached to
     * @param savedInstanceState    Bundle containing the data most recently supplied
     * @return                      The View of the Fragment UI, or null
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ssx, container, false);

        return view;
    }

}
