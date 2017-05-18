package com.example.valdeslab.learningapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class PieChartFrag extends Fragment {

    private PieChart mChart;

    public static PieChartFrag newInstance() {
        return new PieChartFrag();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_simple_pie, container, false);

        mChart = (PieChart) v.findViewById(R.id.pieChart1);
        mChart.getDescription().setEnabled(false);

        //Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Light.ttf");

        //mChart.setCenterTextTypeface(tf);
        mChart.setCenterText(generateCenterText());
        mChart.setCenterTextSize(10f);
        //mChart.setCenterTextTypeface(tf);

        // radius of the center hole in percent of maximum radius
        mChart.setHoleRadius(45f);
        mChart.setTransparentCircleRadius(50f);

        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);

        List<PieEntry> entries = new ArrayList<>();

        // All entries add up to 100
        entries.add(new PieEntry(18.5f, "Green"));
        entries.add(new PieEntry(26.7f, "Yellow"));
        entries.add(new PieEntry(24.0f, "Red"));
        entries.add(new PieEntry(30.8f, "Blue"));

        PieDataSet set = new PieDataSet(entries, "Favorite Colors Results");

        // Colors need to be in the order of the entries to correspond
        set.setColors(new int[] {
                R.color.colorGreen,
                R.color.colorYellow,
                R.color.colorRed,
                R.color.colorPrimaryDark
        }, getContext());
        PieData data = new PieData(set);
        mChart.setData(data);
        //mChart.invalidate();

        return v;

    }

    /***********************************************************************************************
     * Setting the center text
     *
     * @return      The String to be displayed in the center
     */
    private SpannableString generateCenterText() {
        SpannableString s = new SpannableString("Colors\n Because I like them");
        s.setSpan(new RelativeSizeSpan(2f), 0, 8, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 8, s.length(), 0);
        return s;
    }

}
