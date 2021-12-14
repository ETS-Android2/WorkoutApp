package com.patrickbanez.workoutapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import android.widget.Button;

import com.patrickbanez.workoutapp.Workout.WorkoutList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ManageWorkoutsFragment extends Fragment {

    private View view;
    WorkoutViewFragment[] MyWorkoutViewFragments;
    WorkoutList workoutList;

    public ManageWorkoutsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        CreateWorkoutList createWorkoutList = new CreateWorkoutList();

        workoutList = createWorkoutList.getWorkoutList();

        MyWorkoutViewFragments = new WorkoutViewFragment[workoutList.getCount()];

        for(int i = 0; i < workoutList.getCount(); i++){
            WorkoutViewFragment w = new WorkoutViewFragment(workoutList.getWorkout(i));
            MyWorkoutViewFragments[i] = w;
        }

        for(int i = 0; i < workoutList.getCount(); i++){
            getChildFragmentManager().beginTransaction().replace(R.id.fragmentLayout, MyWorkoutViewFragments[i], null).commit();
        }
        return view;
    }
}
