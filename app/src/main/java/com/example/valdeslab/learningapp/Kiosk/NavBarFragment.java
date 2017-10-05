package com.example.valdeslab.learningapp.Kiosk;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.valdeslab.learningapp.R;


public class NavBarFragment extends Fragment {

    private TextView back, next, submit;

    private NavBarListener listener;

    public interface NavBarListener {
        void callServer();
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

        back = (TextView) view.findViewById(R.id.nav_back);
        setOnClick();

        next = (TextView) view.findViewById(R.id.nav_next);

        submit = (TextView) view.findViewById(R.id.nav_submit);

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

    private void setOnClick(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Test", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
