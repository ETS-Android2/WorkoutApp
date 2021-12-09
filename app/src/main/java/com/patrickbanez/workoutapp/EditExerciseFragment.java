package com.patrickbanez.workoutapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.patrickbanez.workoutapp.Workout.Exercise;

public class EditExerciseFragment extends Fragment {

    private Exercise exercise;

    public EditExerciseFragment(Exercise ex) {
        exercise = new Exercise(ex.getName());
        exercise = ex;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_workout_view, container, false);


        return view;
    }
}
