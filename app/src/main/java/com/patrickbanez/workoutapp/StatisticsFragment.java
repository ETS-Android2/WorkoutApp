package com.patrickbanez.workoutapp;

import android.graphics.Color;
import android.os.Bundle;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class StatisticsFragment extends Fragment implements View.OnClickListener {

    private View view;
    private Button[] btnArr;
    private int currFocus;
    private int primaryColor;

    public StatisticsFragment() {
        // Required empty public constructor
        currFocus = 0;
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

        btnArr = new Button[]{view.findViewById(R.id.btnOneWeek), view.findViewById(R.id.btnOneMonth), view.findViewById(R.id.btnSixMonths),
                              view.findViewById(R.id.btnOneYear), view.findViewById(R.id.btnAllTime)};
        for(Button b : btnArr) {
            b.setBackgroundColor(Color.rgb(207, 207, 207));
            b.setOnClickListener(this);
        }
        primaryColor = ResourcesCompat.getColor(getResources(), R.color.design_default_color_primary, null);
        btnArr[currFocus].setTextColor(Color.rgb(255, 255, 255));
        btnArr[currFocus].setBackgroundColor(primaryColor);
        btnArr[currFocus].setClickable(false);

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
        chart.setExtraRightOffset(25f);
        chart.setDragEnabled(false);
        chart.setScaleEnabled(false);

        java.time.LocalDate.now().getDayOfWeek().toString();
        java.time.LocalDate.now().getDayOfMonth();

        ArrayList<Entry> yValues = new ArrayList<>();

        //Setting up an array for the week
        String[] xValueLabels = new String[7];
        long j = 0;
        for(int i = 6; i >= 0; i--) {
            int dayInt = java.time.LocalDate.now().minusDays(j).getDayOfMonth();
            xValueLabels[i] = java.time.LocalDate.now().minusDays(j).getDayOfWeek().toString().substring(0, 2) + " " +
                              dayInt + getDayOfMonthSuffix(dayInt);
            xValueLabels[i] = xValueLabels[i].substring(0, 1) + xValueLabels[i].substring(1, 2).toLowerCase() +
                              xValueLabels[i].substring(2);
            j++;
        }

        //This is where data would be set for the charts from the global stat holder
        //for now it will just use fake data
        yValues.add(new Entry(0,30f));
        yValues.add(new Entry(1,35f));
        yValues.add(new Entry(2,61f));
        yValues.add(new Entry(3,48f));
        yValues.add(new Entry(4,52f));
        yValues.add(new Entry(5,25f));
        yValues.add(new Entry(6,90f));

        //value formatter for the xValue labels
        ValueFormatter formatter = new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                return xValueLabels[(int) value];
            }
        };

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
        xAxis.setGranularityEnabled(true);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(formatter);
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
        chart.invalidate();

        return view;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.btnOneWeek:
                changeFocus(0);
                //This is where the graph would be changed
            break;
            case R.id.btnOneMonth:
                changeFocus(1);
                //This is where the graph would be changed
            break;
            case R.id.btnSixMonths:
                changeFocus(2);
                //This is where the graph would be changed
            break;
            case R.id.btnOneYear:
                changeFocus(3);
                //This is where the graph would be changed
            break;
            case R.id.btnAllTime:
                changeFocus(4);
                //This is where the graph would be changed
            break;
        }
    }

    private void changeFocus(int index) {
        btnArr[currFocus].setTextColor(Color.rgb(49, 50, 51));
        btnArr[currFocus].setBackgroundColor(Color.rgb(207, 207, 207));
        btnArr[currFocus].setClickable(true);
        btnArr[index].setTextColor(Color.rgb(255, 255, 255));
        btnArr[index].setBackgroundColor(primaryColor);
        btnArr[index].setClickable(false);
        currFocus = index;
    }

    String getDayOfMonthSuffix(final int n) {
        if (n >= 11 && n <= 13) {
            return "th";
        }
        switch (n % 10) {
            case 1:  return "st";
            case 2:  return "nd";
            case 3:  return "rd";
            default: return "th";
        }
    }
}