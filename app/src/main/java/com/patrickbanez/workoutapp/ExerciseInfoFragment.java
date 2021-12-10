package com.patrickbanez.workoutapp;

import android.media.Image;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.patrickbanez.workoutapp.Workout.*;

public class ExerciseInfoFragment extends Fragment{
    private static final String WORKOUT_INDEX = "workoutIndex";
    private static final String EXERCISE_INDEX = "exerciseIndex";

    private int workoutIndex, exerciseIndex;

    public ExerciseInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Creates a new instance of the ExerciseInfoFragment
     *
     * @param workoutIndex Index of workout to use
     * @param exerciseIndex Index of exercise to use
     * @return A new instance of fragment ExerciseInfoFragment.
     */
    public static ExerciseInfoFragment newInstance(int workoutIndex, int exerciseIndex) {
        ExerciseInfoFragment fragment = new ExerciseInfoFragment();
        Bundle args = new Bundle();
        args.putInt(WORKOUT_INDEX, workoutIndex);
        args.putInt(EXERCISE_INDEX, exerciseIndex);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            workoutIndex = getArguments().getInt(WORKOUT_INDEX);
            exerciseIndex = getArguments().getInt(EXERCISE_INDEX);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercise_info, container, false);

        // this is where you would get the exercise from the workout list
        // but for now it will just use fake data
        Exercise exercise = new Exercise("Push Up");
        exercise.setHelp("1. Get down on all fours, placing your hands slightly wider than your shoulders.\n\n2. Straighten your arms and legs.\n\n3. Lower your body until your chest nearly touches the floor.\n\n4. Pause, then push yourself back up.\n\n5. Repeat.");

        ImageButton btnExit = (ImageButton) view.findViewById(R.id.btnExit);
        btnExit.setOnClickListener(v -> getActivity().getSupportFragmentManager().popBackStack());
        TextView txtExerciseName = (TextView) view.findViewById(R.id.txtExerciseName);
        TextView txtExerciseInfo = (TextView) view.findViewById(R.id.txtExerciseInfo);
        txtExerciseName.setText(exercise.getName());
        txtExerciseInfo.setText(exercise.getHelp());

        // this is where you would set the image for the exercise
        // for now it will use a default image
        ImageView imgExerciseImage = (ImageView) view.findViewById(R.id.imgExerciseImage);
        //imgExerciseImage.setImageDrawable();

        return view;
    }
}