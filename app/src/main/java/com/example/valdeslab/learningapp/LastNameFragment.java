package com.example.valdeslab.learningapp;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LastNameFragment extends Fragment {

    private static String LAST = "last_name";

    public lastNameListener listener;

    public interface lastNameListener{
        void replaceFragmentOne(String first);
    }

    public LastNameFragment() {}

    public static LastNameFragment newInstance(String last) {

        Bundle args = new Bundle();
        args.putString(LAST, last);

        LastNameFragment fragment = new LastNameFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_last_name, container, false);

        TextView textview = (TextView) view.findViewById(R.id.textview_last_name);
        textview.setText(getArguments().getString(LAST));

        final EditText editText = (EditText) view.findViewById(R.id.edittext_last);


        Button button = (Button) view.findViewById(R.id.button_frag_two);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.replaceFragmentOne(editText.getText().toString());
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (lastNameListener) context;
    }

}
