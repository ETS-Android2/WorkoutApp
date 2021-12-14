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
import androidx.fragment.app.FragmentTransaction;

import com.patrickbanez.workoutapp.Workout.Workout;

public class WorkoutViewFragment extends Fragment {

    private View view;
    private Workout workout;
    private Intent editWorkout;
    private Button editWorkoutButton;
    private Button startWorkoutButton;
    private Button deleteWorkoutButton;

    public WorkoutViewFragment() {
        Workout workout = new Workout();
    }
    public WorkoutViewFragment(Workout w) {
        workout = new Workout();
        workout.setWorkout(w);
    }

    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_workout_view, container, false);

        TextView WorkoutName = (TextView) view.findViewById(R.id.workoutName);
        WorkoutName.setText(workout.getName());

        TextView ExerciseNames = (TextView) view.findViewById(R.id.exerciseNames);
        ExerciseNames.setText(workout.getExerciseNames());

        TextView Duration = (TextView) view.findViewById(R.id.viewDuration);
        //placeholder. change when you fix duration to Time object
        Duration.setText("15:50");


        editWorkoutButton = (Button) view.findViewById(R.id.editWorkoutButton);

        editWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), EditWorkoutActivity.class);
                intent.putExtra("index", workout.getIndex());
                startActivity(intent);
            }
        });

        startWorkoutButton = (Button) view.findViewById(R.id.startButton);

        startWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WorkoutStartFragment startWorkout = new WorkoutStartFragment();
                ((MainActivity)getActivity()).startWorkoutSwap(startWorkout);
            }
        });

        /*deleteWorkoutButton = (Button) view.findViewById(R.id.deleteWorkoutButton);

        deleteWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
            }
        });
*/
        return view;
    }
}
