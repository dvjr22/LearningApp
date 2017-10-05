package com.example.valdeslab.learningapp.Kiosk;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.valdeslab.learningapp.R;


public class NavBarFragment extends Fragment {

    private NavBarListener listener;

    public interface NavBarListener {
        // call methods in activity to server sim
    }



    /***********************************************************************************************
     * Required empty constructor
     */
    public NavBarFragment() {}


    public static NavBarFragment newInstance(){
        return new NavBarFragment();
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

        View view = inflater.inflate(R.layout.fragment_nav_bar, container, false);

        return view;
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
        listener = (NavBarListener) context;
    }

}
