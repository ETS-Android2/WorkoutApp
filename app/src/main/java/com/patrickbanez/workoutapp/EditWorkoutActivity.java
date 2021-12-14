package com.patrickbanez.workoutapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.patrickbanez.workoutapp.Workout.Exercise;
import com.patrickbanez.workoutapp.Workout.Workout;
import com.patrickbanez.workoutapp.Workout.WorkoutList;

public class EditWorkoutActivity extends AppCompatActivity {


    private EditWorkoutActivity editWorkoutActivity;
    private EditExerciseFragment [] myEditExerciseFragments;
    private Button backButton;
    private WorkoutList workoutList;
    private Workout workout;


    public EditWorkoutActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_workout);
        setTitle("Edit Workout");

        CreateWorkoutList createWorkoutList = new CreateWorkoutList();

        WorkoutList workoutList = createWorkoutList.getWorkoutList();

        workout = new Workout();

        workout.setWorkout(workoutList.getWorkout(getIntent().getIntExtra("index", 0)));


        backButton = (Button) findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        //will change

        myEditExerciseFragments = new EditExerciseFragment[workout.getCount()];

        for(int i = 0; i < workout.getCount(); i++){
            myEditExerciseFragments[i] = new EditExerciseFragment(workout.getExercise(i));
        }

        for(int i = 0; i < workout.getCount(); i++){
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragmentLayout, myEditExerciseFragments[i]).addToBackStack(null).commit();
        }

    }

}
