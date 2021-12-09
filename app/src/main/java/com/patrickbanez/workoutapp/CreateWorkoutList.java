package com.patrickbanez.workoutapp;

import com.patrickbanez.workoutapp.Workout.Exercise;
import com.patrickbanez.workoutapp.Workout.Workout;
import com.patrickbanez.workoutapp.Workout.WorkoutList;

public class CreateWorkoutList {
    private WorkoutList workoutList;

    public CreateWorkoutList() {
        this.workoutList = workoutList;
        Workout pull = new Workout();

        //

        Exercise pull_down = new Exercise("Iso-Lateral Lat Pulldown");
        pull_down.setHelp("Pulldown help");
        pull_down.addSet(10, 60);
        pull_down.addSet(10, 60);
        pull_down.addSet(10, 60);

        Exercise curl = new Exercise("Narrow Grip Bicep Curl");
        pull_down.setHelp("Curl help");
        pull_down.addSet(8, 45);
        pull_down.addSet(8, 45);
        pull_down.addSet(8, 45);

        pull.setName("Pull");
        pull.setDescription("Back and Bicep");
        pull.addExercise(pull_down);
        pull.addExercise(curl);

        //

        Exercise bench_press = new Exercise("Bench Press");
        bench_press.setHelp("Bench Press help");
        bench_press.addSet(8, 135);
        bench_press.addSet(8, 135);
        bench_press.addSet(8, 135);

        Exercise shoulder_press = new Exercise("Shoulder Press");
        bench_press.setHelp("Shoulder Press help");
        bench_press.addSet(15, 40);
        bench_press.addSet(15, 40);
        bench_press.addSet(15, 40);

        Workout push = new Workout();
        push.setDescription("Chest, Shoulders, and Triceps");
        push.setName("Push");
        push.addExercise(bench_press);
        push.addExercise(shoulder_press);

        //

        Exercise leg_press = new Exercise("Leg Press");
        bench_press.setHelp("Leg Press help");
        bench_press.addSet(8, 280);
        bench_press.addSet(8, 280);
        bench_press.addSet(8, 280);

        Exercise squat = new Exercise("Squat");
        bench_press.setHelp("Squat help");
        bench_press.addSet(8, 190);
        bench_press.addSet(8, 190);
        bench_press.addSet(8, 190);

        Workout legs = new Workout();
        legs.setName("Legs");
        legs.setDescription("Lower Body");
        legs.addExercise(leg_press);
        legs.addExercise(squat);

        workoutList.addWorkouts(pull);
        workoutList.addWorkouts(push);
        workoutList.addWorkouts(legs);
    }

    public WorkoutList getWorkoutList() {
        return workoutList;
    }
}
