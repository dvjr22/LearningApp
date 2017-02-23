package com.example.valdeslab.learningapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class FirstNameFragment extends Fragment {

    private static String FIRST = "first_name";

    public static FirstNameFragment newInstance(String first) {
        Bundle args = new Bundle();
        args.putString(FIRST, first);
        FirstNameFragment fragment = new FirstNameFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public FirstNameFragment() {}

    public firstNameListener listener;

    public interface firstNameListener{
        void replaceFragmentTwo(String last);
   }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first_name, container, false);

        TextView textview = (TextView) view.findViewById(R.id.textview_last_name);
        textview.setText(getArguments().getString(FIRST));

        final EditText editText = (EditText) view.findViewById(R.id.edittext_first);

        Button button = (Button) view.findViewById(R.id.button_frag_one);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.replaceFragmentTwo(editText.getText().toString());

            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (firstNameListener) context;
    }

}
