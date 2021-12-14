package com.patrickbanez.workoutapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.patrickbanez.workoutapp.Workout.Workout;
import com.patrickbanez.workoutapp.Workout.WorkoutList;

public class EditWorkoutActivity extends AppCompatActivity {


    private EditWorkoutActivity editWorkoutActivity;
    private EditExerciseFragment [] myEditExerciseFragments;
    private Button backButton;
    private WorkoutList workoutList;
    private Workout workout;


    public EditWorkoutActivity(Workout workout) {
        this.workout = workout;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_workout);

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
            myEditExerciseFragments[i].setExercise(workout.getExercise(i));
        }

        for(int i = 0; i < workoutList.getCount(); i++){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentLayout, myEditExerciseFragments[i], null).commit();
        }

    }

}
