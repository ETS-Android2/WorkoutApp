package com.patrickbanez.workoutapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.patrickbanez.workoutapp.Workout.Exercise;
import com.patrickbanez.workoutapp.Workout.Set;

public class EditExerciseFragment extends Fragment {

    private Exercise exercise;
    private EditSetFragment[] myEditSetFragments;

    public EditExerciseFragment(Exercise ex) {
        exercise = new Exercise();
        exercise.setExercise(ex);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_exercise, container, false);

        TextView ExerciseName = (TextView) view.findViewById(R.id.exerciseName);
        ExerciseName.setText(exercise.getName());

        myEditSetFragments = new EditSetFragment[exercise.getCount()];

        for(int i = 0; i < exercise.getCount(); i++){
            myEditSetFragments[i] = new EditSetFragment(exercise.getSet(i), i);
        }

        for(int i = 0; i < exercise.getCount(); i++){
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.add(R.id.fragmentLayout, myEditSetFragments[i]).addToBackStack(null).commit();
        }

        return view;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }
}
