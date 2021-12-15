package com.patrickbanez.workoutapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.widget.Button;

import com.patrickbanez.workoutapp.Workout.Workout;
import com.patrickbanez.workoutapp.Workout.WorkoutList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ManageWorkoutsFragment extends Fragment {

    private View view;
    private CreateWorkoutList createWorkoutList;
    private WorkoutViewFragment[] MyWorkoutViewFragments;
    private WorkoutList workoutList;

    public ManageWorkoutsFragment() {
    }

    public void onDestroy() {
        super.onDestroy();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_manage_workout,container,false);
        CreateWorkoutList createWorkoutList = new CreateWorkoutList();

        workoutList = createWorkoutList.getWorkoutList();

        MyWorkoutViewFragments = new WorkoutViewFragment[workoutList.getCount()];

        for(int i = 0; i < workoutList.getCount(); i++){
            MyWorkoutViewFragments[i] = new WorkoutViewFragment(workoutList.getWorkout(i));
        }

        for(int i = 0; i < workoutList.getCount(); i++){
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.add(R.id.fragmentLayout, MyWorkoutViewFragments[i]).addToBackStack(null).commit();
        }
        return view;
    }
}
