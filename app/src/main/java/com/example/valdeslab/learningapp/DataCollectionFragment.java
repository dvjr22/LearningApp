package com.example.valdeslab.learningapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DataCollectionFragment extends Fragment {


    public DataCollectionFragment() {}

    public static DataCollectionFragment newInstance(){
        return new DataCollectionFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_data_collection, container, false);
    }

}
