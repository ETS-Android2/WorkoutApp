package com.patrickbanez.workoutapp.Workout;

import java.util.Iterator;
import java.util.*;

public class WorkoutList implements Iterable<Workout> {
    private final int MAX_WORKOUTS = 100;
    private ArrayList<Workout> workouts;

    public WorkoutList() {
        workouts = new ArrayList<Workout>();
    }

    public boolean addWorkouts(Workout e) {
        if(workouts.size() >= MAX_WORKOUTS) {
            return false;
        }
        return workouts.add(e);
    }

    public boolean removeWorkout(String name) {
        for(Workout e: workouts) {
            if(e.getName().equals(name)) {
                workouts.remove(e);
                return true;
            }
        }
        return false;
    }

    public Workout getWorkout(int index) {
        return workouts.get(index);
    }


    public WorkoutListIterator iterator() {
        return new WorkoutListIterator(this);
    }
}

class WorkoutListIterator implements Iterator<Workout> {
    private int currentIndex = 0;
    private WorkoutList workoutList;

    public WorkoutListIterator(WorkoutList workoutList) {
        this.workoutList = workoutList;
    }

    public boolean hasNext() {
        return (workoutList.getWorkout(currentIndex + 1) != null);
    }

    public Workout next() {
        Workout e = workoutList.getWorkout(currentIndex);
        currentIndex++;
        return e;
    }
}
