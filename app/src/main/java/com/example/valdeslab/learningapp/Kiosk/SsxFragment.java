package com.example.valdeslab.learningapp.Kiosk;


import android.content.Context;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
    private TextView ssx1, ssx2, ssx3, ssx4, ssx5, ssx6, ssx7, ssx8;
    private CheckBox checkBox1, checkBox2, checkbox3, checkBox4, checkBox5, checkBox6, checkBox7, checkBox8;

    // right side
    private LinearLayout group9, group10, group11, group12, group13, group14, group15, group16;
    private TextView ssx9, ssx10, ssx11, ssx12, ssx13, ssx14, ssx15, ssx16;
    private CheckBox checkBox9, checkBox10, checkBox11, checkBox12, checkBox13, checkBox14, checkBox15, checkBox16;

    // Selection trackers
    Map<Integer, Boolean> selections;
    Map<Integer, Integer> ssxSelections;
    ArrayList<String> submission;

    // Interface and listener
    private SsxFragmentListener listener;

    public interface SsxFragmentListener {
        void submitToServerSimulator(ArrayList<String> ssx, String from);
    }

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

        int visibility = ids.size();

        setUpSelections();
        setUpGroups(view, visibility);
        setUpSsx(view, visibility, ssx);
        setUpCheckBoxes(view, visibility, ids);

        print();

        return view;
    }



    /***********************************************************************************************
     *
     * @param view
     * @param visibility
     */
    public void setUpGroups(View view, int visibility){

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
                (LinearLayout) view.findViewById(R.id.checkbox_group_16))
        );

        for(int i = 0; i < widgetList.size(); i++) {
            if (i < visibility) {
                widgetList.get(i).setVisibility(View.VISIBLE);
            } else {
                widgetList.get(i).setVisibility(View.INVISIBLE);
            }
        }
    }

    /***********************************************************************************************
     *
     * @param view
     * @param visibility
     * @param ssx
     */
    public void setUpSsx(View view, int visibility, ArrayList<String> ssx){

        ArrayList<TextView> textViews = new ArrayList<>(Arrays.asList(
                (TextView) view.findViewById(R.id.ssx_1),
                (TextView) view.findViewById(R.id.ssx_2),
                (TextView) view.findViewById(R.id.ssx_3),
                (TextView) view.findViewById(R.id.ssx_4),
                (TextView) view.findViewById(R.id.ssx_5),
                (TextView) view.findViewById(R.id.ssx_6),
                (TextView) view.findViewById(R.id.ssx_7),
                (TextView) view.findViewById(R.id.ssx_8),
                (TextView) view.findViewById(R.id.ssx_9),
                (TextView) view.findViewById(R.id.ssx_10),
                (TextView) view.findViewById(R.id.ssx_11),
                (TextView) view.findViewById(R.id.ssx_12),
                (TextView) view.findViewById(R.id.ssx_13),
                (TextView) view.findViewById(R.id.ssx_14),
                (TextView) view.findViewById(R.id.ssx_15),
                (TextView) view.findViewById(R.id.ssx_16)
        ));

        for (int i = 0; i < visibility; i++) {
            textViews.get(i).setText(ssx.get(i));
            Log.i(TAG, "(SsxFragment): visibility " + visibility);
            Log.i(TAG, "(SsxFragment): textviews " + textViews.size());
            Log.i(TAG, "(SsxFragment): ssx " + ssx.size());
        }
    }

    /***********************************************************************************************
     *
     * @param view
     * @param visibility
     * @param ids
     */
    public void setUpCheckBoxes(View view, int visibility, final ArrayList<Integer> ids) {

        ArrayList<CheckBox> checkboxes = new ArrayList<>(Arrays.asList(
                (CheckBox) view.findViewById(R.id.checkbox_1),
                (CheckBox) view.findViewById(R.id.checkbox_2),
                (CheckBox) view.findViewById(R.id.checkbox_3),
                (CheckBox) view.findViewById(R.id.checkbox_4),
                (CheckBox) view.findViewById(R.id.checkbox_5),
                (CheckBox) view.findViewById(R.id.checkbox_6),
                (CheckBox) view.findViewById(R.id.checkbox_7),
                (CheckBox) view.findViewById(R.id.checkbox_8),
                (CheckBox) view.findViewById(R.id.checkbox_9),
                (CheckBox) view.findViewById(R.id.checkbox_10),
                (CheckBox) view.findViewById(R.id.checkbox_11),
                (CheckBox) view.findViewById(R.id.checkbox_12),
                (CheckBox) view.findViewById(R.id.checkbox_13),
                (CheckBox) view.findViewById(R.id.checkbox_14),
                (CheckBox) view.findViewById(R.id.checkbox_15),
                (CheckBox) view.findViewById(R.id.checkbox_16)
        ));

        for (int i = 0; i < visibility; i++){

            populate(checkboxes.get(i).getId(), ids.get(i));

            checkboxes.get(i).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        select(buttonView.getId());
                    } else {
                        select(buttonView.getId());
                    }
                    print();
                }
            });
        }
    }

    /***********************************************************************************************
     *
     */
    private void setUpSelections() {

        selections = new HashMap<>();
        ssxSelections = new HashMap<>();
        submission = new ArrayList<>();
    }

    /***********************************************************************************************
     *
     * @param rId
     * @param id
     */
    private void populate(Integer rId, Integer id) {

        selections.put(rId, Boolean.FALSE);
        ssxSelections.put(rId, id);
    }

    /***********************************************************************************************
     *
     * @param rId
     */
    private void select(Integer rId) {

        Boolean ssx = !selections.get(rId);

        selections.put(rId, ssx);

        if (ssx) {
            submission.add(ssxSelections.get(rId).toString());
        } else {
            submission.remove(ssxSelections.get(rId).toString());
        }

    }

    /***********************************************************************************************
     *
     */
    private void print() {

        Log.i(TAG, "(SsxFragment) selections");

        for (Map.Entry<Integer, Boolean> entry : selections.entrySet()) {
            Integer key = entry.getKey();
            Boolean value = entry.getValue();
            Log.i(TAG, "(SsxFragment) " + key.toString() + " " + value);
        }

        Log.i(TAG, "(SsxFragment) ssxSelections");

        for (Map.Entry<Integer, Integer> entry : ssxSelections.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            Log.i(TAG, "(SsxFragment) " + key.toString() + " " + value);
        }

        Log.i(TAG, "(SsxFragment) submission");

        for (int i = 0; i < submission.size(); i++) {
            Log.i(TAG, "(SsxFragment) " + submission.get(i));
        }
    }

    /***********************************************************************************************
     * Android method
     * Called when a fragment is first attached to its context
     *
     * @param context       The context to use
     */
    @Override
    public void onAttach(Context context) {

        super.onAttach(context);
        listener = (SsxFragmentListener) context;
    }

    /***********************************************************************************************
     *
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        listener.submitToServerSimulator(submission, "onDestroy");
    }
}
