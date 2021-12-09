package com.patrickbanez.workoutapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;


public class LoadingActivity extends AppCompatActivity {
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        setTitle("Loading");

        Intent createActivity = new Intent(this, CreateUserActivity.class);
        Intent mainActivity = new Intent(this, MainActivity.class);

        sp = getSharedPreferences(String.valueOf(R.string.user_sp), MODE_PRIVATE);

        Gson gson = new Gson();
        String json = sp.getString("userData", "");
        User existence = (User) gson.fromJson(json, User.class);


        // Since we won't be implementing a LoginActivity, this is a test for going from a loading
        // screen that checks if a user has created their profile:
        //      true = go to MainActivity HomeFragment
        //      false = go to CreateUserActivity
        // I also wanted to have a loading screen to have an image or animation of working out and a
        // place to have our names on the application
        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (existence == null) {
                    startActivity(createActivity);
                    finish();
                } else {
                    startActivity(mainActivity);
                    finish();
                }
            }
        }, 3000); // for now it waits 3 seconds then goes to MainActivity
    }
}