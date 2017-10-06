package com.example.valdeslab.learningapp.Kiosk;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.valdeslab.learningapp.R;

import java.util.ArrayList;

public class QuestionFragment extends Fragment {

    private static final String TAG = "trace";

    private static String BUNDLE_IDS = "bundle_ids";
    private static String BUNDLE_QUES = "bundle_ques";

    private TextView question;

    private RadioButton yes;
    private RadioButton no;
    private RadioGroup radioGroup;

    private boolean answer = false;
    private String quesId;


    // Interface and listener
    private QuestionFragmentListener listener;

    public interface QuestionFragmentListener {
        void submitToServerSimulator(String quesId, String answer);
    }

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

        quesId = ids.get(0).toString();

        setUpQues(view, ques.get(0));
        setUpRadioGroup(view);

        return view;
    }

    /***********************************************************************************************
     *
     * @param view
     * @param ques
     */
    private void setUpQues(View view, String ques) {

        question = (TextView) view.findViewById(R.id.textview_question);
        question.setText(ques);
    }

    /***********************************************************************************************
     *
     * @param view
     */
    private void setUpRadioGroup(View view) {

        radioGroup = (RadioGroup) view.findViewById(R.id.question_radiogroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                switch (checkedId) {

                    case R.id.yes:
                        answer = true;
                        break;

                    case R.id.no:
                        answer = false;
                        break;
                }

            }
        });
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
        listener = (QuestionFragmentListener) context;
    }

    /***********************************************************************************************
     *
     */
    @Override
    public void onDestroy() {

        super.onDestroy();
        listener.submitToServerSimulator(quesId, Integer.toString((answer) ? 1 : 0));
    }

}
