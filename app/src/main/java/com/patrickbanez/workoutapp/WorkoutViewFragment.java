package com.patrickbanez.workoutapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.patrickbanez.workoutapp.Workout.Workout;

public class WorkoutViewFragment extends Fragment {
    private Workout workout;
    private Intent editWorkout;
    private Button editWorkoutButton;
    private Button deleteWorkoutButton;

    public WorkoutViewFragment() {
        Workout workout = new Workout();
    }
    public WorkoutViewFragment(Workout w) {
        Workout workout = new Workout();
        workout = w;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_workout_view, container, false);

        TextView WorkoutName = (TextView) view.findViewById(R.id.exerciseName);
        WorkoutName.setText(workout.getName());

        TextView ExerciseNames = (TextView) view.findViewById(R.id.viewExercises);
        ExerciseNames.setText(workout.getExerciseNames());

        TextView Duration = (TextView) view.findViewById(R.id.viewDuration);
        Duration.setText(workout.getDuration());

        editWorkoutButton = (Button) view.findViewById(R.id.editWorkoutButton);

        editWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), EditWorkoutActivity.class));
            }
        });

        deleteWorkoutButton = (Button) view.findViewById(R.id.deleteWorkoutButton);

        deleteWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){

            }
        });

        return view;
    }
}
