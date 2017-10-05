package com.example.valdeslab.learningapp.Kiosk;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.valdeslab.learningapp.R;

import java.util.ArrayList;

public class QuestionFragment extends Fragment {

    private static final String TAG = "trace";

    private static String BUNDLE_IDS = "bundle_ids";
    private static String BUNDLE_QUES = "bundle_ques";

    private TextView question;

    public QuestionFragment() {}

    /***********************************************************************************************
     *
     * @param ids
     * @param ques
     * @return
     */
    public static QuestionFragment newInstance(ArrayList<Integer> ids, ArrayList<String> ques){

        QuestionFragment fragment = new QuestionFragment();

        Bundle bundle = new Bundle();
        bundle.putIntegerArrayList(BUNDLE_IDS, ids);
        bundle.putStringArrayList(BUNDLE_QUES, ques);
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

        View view = inflater.inflate(R.layout.fragment_question, container, false);

        ArrayList<Integer> ids = getArguments().getIntegerArrayList(BUNDLE_IDS);
        ArrayList<String> ques = getArguments().getStringArrayList(BUNDLE_QUES);

        for (int i = 0; i < ids.size(); i++) {
            Log.i(TAG, "(QuesFragment) ids: " + ids.get(i));
            Log.i(TAG, "(QuesFragment) ssx: " + ques.get(i));
        }

        question = (TextView) view.findViewById(R.id.textview_question);
        question.setText(ques.get(0));

        return view;
    }

}
