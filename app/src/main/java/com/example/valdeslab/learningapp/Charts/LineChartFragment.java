package com.example.valdeslab.learningapp.Charts;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.valdeslab.learningapp.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LineChartFragment extends Fragment {

    public static LineChartFragment newInstance() {
        return new LineChartFragment();
    }

    public LineChartFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_line_chart, container, false);

        LineChart lineChart = (LineChart) view.findViewById(R.id.line_chart);

        List<Entry> data = generateData();

        LineDataSet dataSet = new LineDataSet(data, "Label");

        LineData lineData = new LineData(dataSet);
        lineChart.setData(lineData);
        //lineChart.invalidate();

        return view;
    }


    private List generateData(){

        List<Entry> entries = new ArrayList<>();
        entries.add(new Entry(1, 85));
        entries.add(new Entry(2, 86));
        entries.add(new Entry(3, 84));
        entries.add(new Entry(4, 83));
        entries.add(new Entry(5, 82));
        entries.add(new Entry(6, 81));

        return entries;
    }



}
