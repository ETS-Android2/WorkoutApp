package com.patrickbanez.workoutapp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.patrickbanez.workoutapp.Workout.Workout;

public class EditWorkoutActivity extends AppCompatActivity {

    String[] tabNames = {"Home", "Workout", "Statistics", "Settings"};

    private static WorkoutViewFragment workoutView;

    static {
        workoutView = new WorkoutViewFragment();
    }

    // For now the app opens to the Home page because I didn't set a starting point.
    // In the future we can set it to the login activity.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_workout);


    }

    private
}
