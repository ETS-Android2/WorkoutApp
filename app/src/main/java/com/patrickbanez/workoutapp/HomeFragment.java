package com.patrickbanez.workoutapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends AppCompatActivity {

	ImageButton Home,Workout,Statistics,Setting;
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_home);

		Home = (ImageButton)findViewById(R.id.homeButton);
		Workout = (ImageButton)findViewById(R.id.workoutButton);
		Statistics = (ImageButton)findViewById(R.id.statisticsButton);
		Setting = (ImageButton)findViewById(R.id.settingsButton);

		Home.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
				Fragment fragment1 = new Fragment();
			}
		});
	}

}