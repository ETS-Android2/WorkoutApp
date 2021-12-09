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
        CreateWorkoutList createWorkoutList = new CreateWorkoutList();

        workoutList = createWorkoutList.getWorkoutList();
        myEditExerciseFragments = new EditExerciseFragment[workoutList.getCount()];

        for(int i = 0; i < workoutList.getCount(); i++){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentLayout, myEditExerciseFragments[i], null).commit();
        }

    }

}
