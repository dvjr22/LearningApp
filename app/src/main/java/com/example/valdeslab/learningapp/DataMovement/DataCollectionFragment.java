package com.example.valdeslab.learningapp.DataMovement;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.valdeslab.learningapp.R;

public class DataCollectionFragment extends Fragment {


    public DataCollectionFragment() {}

    public static DataCollectionFragment newInstance(){
        return new DataCollectionFragment();
    }

    public DataCollectionListener listener;

    public interface DataCollectionListener{
        void collectData(String firstName, String lastName, int age);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data_collection, container, false);

        final EditText firstName = (EditText) view.findViewById(R.id.editText_firstName);
        final EditText lastName = (EditText) view.findViewById(R.id.editText_lastName);
        final EditText age = (EditText) view.findViewById(R.id.editText_age);
        Button submit = (Button) view.findViewById(R.id.button_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.collectData(firstName.getText().toString(), lastName.getText().toString(),
                        Integer.parseInt(age.getText().toString()));
                firstName.setText("");
                lastName.setText("");
                age.setText("");
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        listener = (DataCollectionListener) context;
    }

}
