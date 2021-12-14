/*
// Set class to hold set info
*/

package com.patrickbanez.workoutapp.Workout;

public class Set {
    private double weight;
    private int reps;
    private int index;

    public Set(int reps, double weight) {
        this.reps = reps;
        this.weight = weight;
    }

    public void setSet(Set s){
        setReps(s.getReps());
        setWeight(s.getWeight());
        setIndex(s.getIndex());
    }

    public void setIndex(int i){
        index = i;
    }

    public int getIndex(){ return index; }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps){
        this.reps = reps;
    }

    public double getWeight(){
        return weight;
    }

    public void setWeight(double weight){
        this.weight = weight;
    }
}