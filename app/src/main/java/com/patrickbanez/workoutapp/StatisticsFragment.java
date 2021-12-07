package com.patrickbanez.workoutapp;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class StatisticsFragment extends Fragment {

    private View view;

    public StatisticsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        String[] graphDropdown = getResources().getStringArray(R.array.graphTypes);
        ArrayAdapter arrayAdapter = new ArrayAdapter(requireContext(), R.layout.dropdown_item, graphDropdown);
        AutoCompleteTextView autoComplete = view.findViewById(R.id.autoCompleteTextView);
        autoComplete.setAdapter(arrayAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_statistics, container, false);

        TextView workoutsComplete = (TextView) view.findViewById(R.id.workoutsComplete);
        TextView workoutDuration = (TextView) view.findViewById(R.id.workoutDuration);
        TextView averageDuration = (TextView) view.findViewById(R.id.averageDuration);

        //Here is where the stats would be set from the global stat holder
        //String wComplete = "Total Workouts Completed: " + ; //to change
        //String wDuration = "Total Workout Duration: " + ; //to change
        //String avgDuration = "Average Workout Duration: " + ; //to change
        //workoutsComplete.setText(wComplete);
        //workoutDuration.setText(wDuration);
        //averageDuration.setText(avgDuration);

        //This is where you would change the x and y axis titles
        //Will have to account for changing of dropdown box value
        String xAxisT = ""; //to change
        String yAxisT = ""; //to change
        TextView xAxisTitle = (TextView) view.findViewById(R.id.xAxisTitle);
        TextView yAxisTitle = (TextView) view.findViewById(R.id.yAxisTitle);
        //xAxisTitle.setText(xAxisT);
        //yAxisTitle.setText(yAxisT);

        //This is where the charts data is created/added
        LineChart chart = (LineChart) view.findViewById(R.id.LineChart);
        chart.setNoDataText("Error: No Data Is Found");
        chart.setGridBackgroundColor(Color.DKGRAY);
        chart.setDrawBorders(true);
        chart.setBorderWidth(3f);
        chart.setBorderColor(Color.DKGRAY);
        chart.setExtraBottomOffset(6f);
        chart.setDragEnabled(false);
        chart.setScaleEnabled(false);

        ArrayList<Entry> yValues = new ArrayList<>();

        //This is where data would be set for the charts from the global stat holder
        //for now it will just use fake data
        yValues.add(new Entry(0,14f));
        yValues.add(new Entry(1,32f));
        yValues.add(new Entry(2,65f));
        yValues.add(new Entry(3,45f));
        yValues.add(new Entry(4,52f));
        yValues.add(new Entry(5,24f));
        yValues.add(new Entry(6,90f));

        LineDataSet set1 = new LineDataSet(yValues, "Workout Duration");

        set1.setFillAlpha(110);
        set1.setColor(Color.DKGRAY);
        set1.setLineWidth(5f);
        set1.setValueTextSize(0f);
        set1.setCircleRadius(5f);
        set1.setCircleColor(Color.BLACK);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        LineData data = new LineData(dataSets);

        Description description = chart.getDescription();
        description.setEnabled(false);

        Legend legend = chart.getLegend();
        legend.setEnabled(false);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGridColor(Color.DKGRAY);
        xAxis.setAxisLineColor(Color.DKGRAY);
        xAxis.setDrawGridLines(true);
        xAxis.setDrawAxisLine(true);
        xAxis.setGridLineWidth(3f);
        xAxis.setTextSize(16f);

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setGridColor(Color.DKGRAY);
        leftAxis.setAxisLineColor(Color.DKGRAY);
        leftAxis.setDrawGridLines(true);
        leftAxis.setDrawAxisLine(true);
        leftAxis.setGridLineWidth(3f);
        leftAxis.setTextSize(16f);

        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setGridColor(Color.DKGRAY);
        rightAxis.setAxisLineColor(Color.DKGRAY);
        rightAxis.setDrawLabels(false);
        rightAxis.setDrawGridLines(false);
        rightAxis.setGridLineWidth(3f);
        rightAxis.setDrawAxisLine(true);

        chart.setData(data);

        return view;
    }
}