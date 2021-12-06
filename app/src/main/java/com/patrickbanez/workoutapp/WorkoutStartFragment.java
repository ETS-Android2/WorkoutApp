package com.patrickbanez.workoutapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.patrickbanez.workoutapp.Workout.*;

import org.w3c.dom.Text;

import java.time.Duration;
import java.util.Timer;
import java.util.TimerTask;

public class WorkoutStartFragment extends Fragment {

    private Workout workout;
    private Duration duration;
    private int currentExerciseIndex;
    private static final String WORKOUT_INDEX = "workoutIndex";
    private boolean paused = false;

    private TextView txtExerciseDuration;

    // used to retrieve the index for the workout in the global workout list
    private int workoutIndex;

    public WorkoutStartFragment(){

    }

    /**
     * Creates a new instance of fragment WorkoutStartFragment.
     *
     * @param workoutIndex Workout to use.
     * @return A new instance of fragment WorkoutStartFragment.
     */
    public static WorkoutStartFragment newInstance(int workoutIndex) {
        WorkoutStartFragment fragment = new WorkoutStartFragment();
        Bundle args = new Bundle();
        args.putInt(WORKOUT_INDEX, workoutIndex);
        fragment.setArguments(args);
        return fragment;
    }

    private final Handler handler = new Handler(Looper.getMainLooper());
    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            duration = duration.plus(Duration.ofSeconds(1));
            String d = "Duration: " + duration.toString().substring(2).replaceAll("(\\d[HMS])(?!$)", "$1 ").toLowerCase();
            txtExerciseDuration.setText(d);
            handler.postDelayed(runnable, 1000);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            workoutIndex = getArguments().getInt(WORKOUT_INDEX);
        }

        // needs to get the workout object from the global list
        // also need to check if the workout is valid ie not null or empty
        // for now it will just use fake data
        Workout temp = new Workout();
        temp.setName("Testing Workout");
        temp.setDescription("This is a test for the workout start class");
        Exercise tempE = new Exercise("Push Up", 10);
        Exercise tempE2 = new Exercise("Lunges", 10);
        Exercise tempE3 = new Exercise("Squats", 10);
        temp.addExercise(tempE);
        temp.addExercise(tempE2);
        temp.addExercise(tempE3);

        this.workout = temp;
        duration = Duration.ofSeconds(0);
        currentExerciseIndex = 0;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_workout_start, container, false);
        txtExerciseDuration = (TextView) view.findViewById(R.id.txtExerciseDuration);
        TextView txtExerciseName = (TextView) view.findViewById(R.id.txtExerciseName);
        String d = "Duration: " + duration.toString().substring(2).replaceAll("(\\d[HMS])(?!$)", "$1 ").toLowerCase();
        txtExerciseDuration.setText(d);
        txtExerciseName.setText(workout.getExercise(0).getName());
        ImageButton btnPrevious = (ImageButton) view.findViewById(R.id.btnPrevExercise);
        btnPrevious.setOnClickListener(v -> {
            if(currentExerciseIndex != 0) {
                //also need to change the image for the exercise

                currentExerciseIndex--;
                txtExerciseName.setText(workout.getExercise(currentExerciseIndex).getName());
            }
        });
        ImageButton btnNext = (ImageButton) view.findViewById(R.id.btnNextExercise);
        btnNext.setOnClickListener(v -> {
            if(currentExerciseIndex >= (workout.getSize() - 1)) {
                // need to save duration info before deleting

                getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
                //might want to use this depending on how the WorkoutStartFragment is instanced
                //getActivity().getSupportFragmentManager().popBackStack();
            } else {
                //also need to change the image for the exercise

                currentExerciseIndex++;
                txtExerciseName.setText(workout.getExercise(currentExerciseIndex).getName());
            }
        });
        ImageButton btnHelp = (ImageButton) view.findViewById(R.id.btnHelp);
        btnHelp.setOnClickListener(v -> {
            ExerciseInfoFragment infoFragment = ExerciseInfoFragment.newInstance(workoutIndex, currentExerciseIndex);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.activeView, infoFragment, "infoFragment").addToBackStack("infoFragment").commit();
        });
        ImageButton btnExit = (ImageButton) view.findViewById(R.id.btnExit);
        btnExit.setOnClickListener(v -> {
            // need to save duration info before deleting

            getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
            //might want to use this depending on how the WorkoutStartFragment is instanced
            //getActivity().getSupportFragmentManager().popBackStack();
        });
        ImageButton btnPause = (ImageButton) view.findViewById(R.id.btnPause);
        btnPause.setOnClickListener(v -> {
            paused = !paused;
            if(paused) {
                btnPause.setImageResource(R.drawable.play);
                handler.removeCallbacks(runnable);
            } else {
                btnPause.setImageResource(R.drawable.pause);
                handler.postDelayed(runnable, 1000);
            }
        });

        //also need to change the image for the exercise

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable, 1000);
    }
}