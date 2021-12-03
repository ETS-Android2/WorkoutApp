package com.patrickbanez.workoutapp;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    private int heightFt;
    private int heightIn;
    private int age;
    private int weight;
    private String firstName;
    private String lastName;
    private String email;


//    private Goal goal;

    // User cannot be created without a first name, last name, email, goal, and uid.
    public User(){};


    // First name, last name, email, and uid are required when creating a user.
    /**
     * Initializes required fields to given parameters and initializes optional fields to 0.
     *
     * @param firstName
     * @param lastName
     * @param email
     */
    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
//        this.goal = goal;
        age = 0;
        heightFt = 0;
        heightIn = 0;
        weight = 0;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getHeightFt() {
        return heightFt;
    }

    public void setHeightFt(int heightFt) {
        this.heightFt = heightFt;
    }

    public int getHeightIn() {
        return heightIn;
    }

    public void setHeightIn(int heightIn) {
        this.heightIn = heightIn;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

//    public void setGoal(Goal goal) {
//        this.goal = goal;
//    }
//
//    public Goal getGoal() {
//        return goal;
//    }

}
