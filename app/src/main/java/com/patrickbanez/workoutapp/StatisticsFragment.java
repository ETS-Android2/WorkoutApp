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
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class StatisticsFragment extends Fragment implements View.OnClickListener {
    private View view;
    private Button[] btnArr;
    private TextView xAxisTitle, yAxisTitle;
    private int currFocus;
    private int primaryColor;
    private IndexAxisValueFormatter weeklyFormatter, sixMonthFormatter, monthlyFormatter, allTimeFormatter;
    private LineDataSet weeklySet, monthSet, sixMonthSet, monthlySet, allTimeSet;
    private LineData weeklyData, monthData, sixMonthData, monthlyData, allTimeData;
    private LineChart chart;

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

        //X and Y axis titles
        xAxisTitle = (TextView) view.findViewById(R.id.xAxisTitle);
        yAxisTitle = (TextView) view.findViewById(R.id.yAxisTitle);

        //Buttons for changing graph
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

        //General stats text views
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
        chart = (LineChart) view.findViewById(R.id.LineChart);
        chart.setNoDataText("Error: No Data Is Found");
        chart.setGridBackgroundColor(Color.DKGRAY);
        chart.setDrawBorders(true);
        chart.setBorderWidth(3f);
        chart.setBorderColor(Color.DKGRAY);
        chart.setExtraBottomOffset(6f);
        chart.setExtraRightOffset(25f);
        chart.setDragEnabled(false);
        chart.setScaleEnabled(false);

        createData();
        createFormatters();
        ArrayList<ILineDataSet> weeklyDataSet = new ArrayList<>();
        weeklyDataSet.add(weeklySet);
        ArrayList<ILineDataSet> monthDataSet = new ArrayList<>();
        monthDataSet.add(monthSet);
        ArrayList<ILineDataSet> sixMonthDataSet = new ArrayList<>();
        sixMonthDataSet.add(sixMonthSet);
        ArrayList<ILineDataSet> monthlyDataSet = new ArrayList<>();
        monthlyDataSet.add(monthlySet);
        ArrayList<ILineDataSet> allTimeDataSet = new ArrayList<>();
        allTimeDataSet.add(allTimeSet);

        weeklyData = new LineData(weeklyDataSet);
        monthData = new LineData(monthDataSet);
        sixMonthData = new LineData(sixMonthDataSet);
        monthlyData = new LineData(monthlyDataSet);
        allTimeData = new LineData(allTimeDataSet);

        Description description = chart.getDescription();
        description.setEnabled(false);

        Legend legend = chart.getLegend();
        legend.setEnabled(false);

        XAxis xAxis = chart.getXAxis();
        xAxis.setGranularityEnabled(true);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(weeklyFormatter);
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
        rightAxis.setAxisLineColor(Color.DKGRAY);
        rightAxis.setDrawLabels(false);
        rightAxis.setDrawGridLines(false);
        rightAxis.setDrawAxisLine(true);

        chart.setData(weeklyData);
        chart.invalidate();

        return view;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.btnOneWeek:
                changeFocus(0);
                //This is where the graph would be changed
                xAxisTitle.setText("Duration In Minutes");
                yAxisTitle.setText("Workout Date (Past 7 Days)");
                chart.getXAxis().setValueFormatter(weeklyFormatter);
                chart.setData(weeklyData);
                chart.getXAxis().setLabelCount(7);
                chart.invalidate();
            break;
            case R.id.btnOneMonth:
                changeFocus(1);
                //This is where the graph would be changed
                xAxisTitle.setText("Duration In Minutes");
                yAxisTitle.setText("Workout Date (Past 30 Days)");
                chart.getXAxis().setValueFormatter(null);
                chart.setData(monthData);
                chart.getXAxis().setLabelCount(15);
                chart.invalidate();
            break;
            case R.id.btnSixMonths:
                changeFocus(2);
                //This is where the graph would be changed
                xAxisTitle.setText("Total Duration In Hours");
                yAxisTitle.setText("Workout Month (Past 6 Months)");
                chart.getXAxis().setValueFormatter(sixMonthFormatter);
                chart.setData(sixMonthData);
                chart.getXAxis().setLabelCount(6);
                chart.invalidate();
            break;
            case R.id.btnOneYear:
                changeFocus(3);
                //This is where the graph would be changed
                xAxisTitle.setText("Total Duration In Hours");
                yAxisTitle.setText("Workout Month (Past 12 Months)");
                chart.getXAxis().setValueFormatter(monthlyFormatter);
                chart.setData(monthlyData);
                chart.getXAxis().setLabelCount(12);
                chart.invalidate();
            break;
            case R.id.btnAllTime:
                changeFocus(4);
                //This is where the graph would be changed
                xAxisTitle.setText("Total Duration In Hours");
                yAxisTitle.setText("Workout Month (All Time)");
                chart.getXAxis().setValueFormatter(allTimeFormatter);
                chart.setData(allTimeData);
                chart.getXAxis().setLabelCount(15); //change this
                chart.invalidate();
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

    void createFormatters() {
        //Setting up an array for the labels

        //Might need to be an arraylist

        String[] weeklyLabels = new String[7];
        String[] sixMonthLabels = new String[6];
        String[] monthlyLabels = new String[12];
        String[] allTimeLabels = new String[15]; //should be set to the length of app usage for user

        //Setting the label values
        long j = 0;
        for(int i = 6; i >= 0; i--) {
            int dayInt = java.time.LocalDate.now().minusDays(j).getDayOfMonth();
            weeklyLabels[i] = java.time.LocalDate.now().minusDays(j).getDayOfWeek().toString().substring(0, 2) + " " +
                    dayInt + getDayOfMonthSuffix(dayInt);
            weeklyLabels[i] = weeklyLabels[i].charAt(0) + weeklyLabels[i].substring(1, 2).toLowerCase() +
                    weeklyLabels[i].substring(2);
            j++;
        }
        j = 0;
        for(int i = allTimeLabels.length-1; i >= 0; i--) {
            String label = java.time.LocalDate.now().minusMonths(j).getMonth().toString().charAt(0) + java.time.LocalDate.now().minusMonths(j).getMonth().toString().substring(1, 2).toLowerCase();
            if(j < sixMonthLabels.length) {
                sixMonthLabels[(int) (sixMonthLabels.length - j - 1)] = label;
            }
            if(j < monthlyLabels.length) {
                monthlyLabels[(int) (monthlyLabels.length - j - 1)] = label;
            }
            allTimeLabels[i] = label;
            j++;
        }

        //value formatters for the xValue labels
        weeklyFormatter = new IndexAxisValueFormatter(weeklyLabels);
        sixMonthFormatter = new IndexAxisValueFormatter(sixMonthLabels);
        monthlyFormatter = new IndexAxisValueFormatter(monthlyLabels);
        allTimeFormatter = new IndexAxisValueFormatter(allTimeLabels);
    }

    void createData() {
        Random r = new Random();

        //creating the durations data lists
        ArrayList<Entry> weeklyDuration = new ArrayList<>();
        ArrayList<Entry> monthDuration = new ArrayList<>();
        ArrayList<Entry> sixMonthDuration = new ArrayList<>();
        ArrayList<Entry> MonthlyDuration = new ArrayList<>();
        ArrayList<Entry> allTimeDurations = new ArrayList<>();

        //This is where data would be set for the charts from the global stat holder
        //for now it will just use fake data (30 is for the full month values but if all time is high it could go further)
        for(int i = 0; i < 30; i++) {
            int rMinuteValue = r.nextInt(100-20) + 20;
            if(i < 7) {
                weeklyDuration.add(new Entry(i,rMinuteValue));
            }
            monthDuration.add(new Entry(i,rMinuteValue));
            int rHourValue = r.nextInt(120-40) + 40;
            if(i < 6) {
                sixMonthDuration.add(new Entry(i,rHourValue));
            }
            if(i < 12) {
                MonthlyDuration.add(new Entry(i,rHourValue));
            }
            if(i < 15) {
                allTimeDurations.add(new Entry(i,rHourValue));
            }
        }

        weeklySet = new LineDataSet(weeklyDuration, "Weekly Workout Duration");
        monthSet = new LineDataSet(monthDuration, "Months Workout Duration");
        sixMonthSet = new LineDataSet(sixMonthDuration, "Six Months Workout Duration");
        monthlySet = new LineDataSet(MonthlyDuration, "Monthly Workout Duration");
        allTimeSet = new LineDataSet(allTimeDurations, "All Time Workout Duration");

        setLineValues(weeklySet);
        setLineValues(monthSet);
        setLineValues(sixMonthSet);
        setLineValues(monthlySet);
        setLineValues(allTimeSet);
    }

    void setLineValues(LineDataSet lineSet) {
        lineSet.setFillAlpha(110);
        lineSet.setColor(Color.DKGRAY);
        lineSet.setLineWidth(5f);
        lineSet.setValueTextSize(0f);
        lineSet.setCircleRadius(5f);
        lineSet.setCircleColor(Color.BLACK);
    }
}