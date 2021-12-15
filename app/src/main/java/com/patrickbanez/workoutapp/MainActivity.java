package com.patrickbanez.workoutapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
//    private UserDao mUserDao;
    String[] tabNames = {"Home", "Manage Workouts", "Statistics", "Settings"};
    TextView dayText;
    ImageButton search_button;
    private static HomeFragment home;
    private static ManageWorkoutsFragment manageWorkoutsFragment;
    private static StatisticsFragment statistics;
    private static SettingsFragment settings;

    static {
        home = new HomeFragment();
        manageWorkoutsFragment = new ManageWorkoutsFragment();
        statistics = new StatisticsFragment();
        settings = new SettingsFragment();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Date currentTime = Calendar.getInstance().getTime();
        String formattedDate = DateFormat.getDateInstance(DateFormat.FULL).format(currentTime);


        dayText = findViewById(R.id.dayText);
        dayText.setText(formattedDate);
        search_button = findViewById(R.id.search_button);
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateUserActivity.class);
                startActivity(intent); // move to exercise activity
            }
        });
        setTitle(tabNames[0]);
    }

    public void swapFragment(View v) {
        if (v.getId() == R.id.homeButton) {
            getSupportFragmentManager().beginTransaction().replace(R.id.activeView, home, null).commit();
            setTitle(tabNames[0]);
        }
        if (v.getId() == R.id.workoutButton) {
            getSupportFragmentManager().beginTransaction().replace(R.id.activeView, manageWorkoutsFragment, null).commit();
            setTitle(tabNames[1]);
        }
        if (v.getId() == R.id.statisticsButton) {
            getSupportFragmentManager().beginTransaction().replace(R.id.activeView, statistics, null).commit();
            setTitle(tabNames[2]);
        }
        if (v.getId() == R.id.settingsButton) {
            getSupportFragmentManager().beginTransaction().replace(R.id.activeView, settings, null).commit();
            setTitle(tabNames[3]);
        }
    }

    public void startWorkoutSwap(WorkoutStartFragment f){
        getSupportFragmentManager().beginTransaction().replace(R.id.activeView, f, null).commit();
    }
}