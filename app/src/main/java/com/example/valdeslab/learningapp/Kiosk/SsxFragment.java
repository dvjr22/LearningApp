package com.example.valdeslab.learningapp.Kiosk;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.valdeslab.learningapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class SsxFragment extends Fragment {

    private static final String TAG = "trace";

    private static String BUNDLE_IDS = "bundle_ids";
    private static String BUNDLE_SSX = "bundle_ssx";

    // left side
    private LinearLayout group1, group2, group3, group4, group5, group6, group7, group8;
    // right side
    private LinearLayout group9, group10, group11, group12, group13, group14, group15, group16;


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

        ArrayList<Integer> ids = getArguments().getIntegerArrayList(BUNDLE_IDS);
        ArrayList<String> ssx = getArguments().getStringArrayList(BUNDLE_SSX);

        for (int i = 0; i < ids.size(); i++) {
            Log.i(TAG, "(SsxFragment) ids: " + ids.get(0));
            Log.i(TAG, "(SsxFragment) ssx: " + ssx.get(1));
        }

        int visibility = ids.size() + 1;

        ArrayList<LinearLayout> widgetList = new ArrayList<>(Arrays.asList(
                (LinearLayout) view.findViewById(R.id.checkbox_group_1),
                (LinearLayout) view.findViewById(R.id.checkbox_group_2),
                (LinearLayout) view.findViewById(R.id.checkbox_group_3),
                (LinearLayout) view.findViewById(R.id.checkbox_group_4),
                (LinearLayout) view.findViewById(R.id.checkbox_group_5),
                (LinearLayout) view.findViewById(R.id.checkbox_group_6),
                (LinearLayout) view.findViewById(R.id.checkbox_group_7),
                (LinearLayout) view.findViewById(R.id.checkbox_group_8),
                (LinearLayout) view.findViewById(R.id.checkbox_group_9),
                (LinearLayout) view.findViewById(R.id.checkbox_group_10),
                (LinearLayout) view.findViewById(R.id.checkbox_group_11),
                (LinearLayout) view.findViewById(R.id.checkbox_group_12),
                (LinearLayout) view.findViewById(R.id.checkbox_group_13),
                (LinearLayout) view.findViewById(R.id.checkbox_group_14),
                (LinearLayout) view.findViewById(R.id.checkbox_group_15),
                (LinearLayout) view.findViewById(R.id.checkbox_group_16)));

        for(int i = 0; i < widgetList.size(); i++) {
            if (i < visibility) {
                widgetList.get(i).setVisibility(View.VISIBLE);
            } else {
                widgetList.get(i).setVisibility(View.INVISIBLE);
            }
        }

        return view;
    }

}
