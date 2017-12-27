package com.example.valdeslab.learningapp.Charts;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.valdeslab.learningapp.R;
import com.example.valdeslab.learningapp.Utilities.PatientSimulator;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 */
public class LineChartFragment extends Fragment {

    private static final String BUNDLE_TIME = "bundle_time";
    private static final String BUNDLE_HR = "bundle_hr";

    LineChart lineChart;

    public static LineChartFragment newInstance(ArrayList<Integer> time, ArrayList<Integer> hr) {

        Bundle bundle = new Bundle();

        bundle.putIntegerArrayList(BUNDLE_TIME, time);
        bundle.putIntegerArrayList(BUNDLE_HR, hr);

        LineChartFragment fragment = new LineChartFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    public LineChartFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_line_chart, container, false);

        lineChart = (LineChart) view.findViewById(R.id.line_chart);

        List<Entry> data = generateData(getArguments().getIntegerArrayList(BUNDLE_TIME),
                getArguments().getIntegerArrayList(BUNDLE_HR));

        LineDataSet dataSet = new LineDataSet(data, "Label");

        LineData lineData = new LineData(dataSet);
        lineChart.setData(lineData);
        lineChart.invalidate();

        return view;
    }

    private List generateData(ArrayList<Integer> time, ArrayList<Integer> hr){

        List<Entry> entries = new ArrayList<>();

        for (int i = 0; i < time.size(); i++){
            entries.add(new Entry(time.get(i), hr.get(i)));
        }

        return entries;
    }

}
