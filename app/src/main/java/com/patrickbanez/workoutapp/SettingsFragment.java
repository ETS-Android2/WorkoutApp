package com.patrickbanez.workoutapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.patrickbanez.workoutapp.User.Goal;
import com.patrickbanez.workoutapp.User.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    View view;
    Button profileButton;
    ProfileFragment profileFragment;

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Test user
        User dummy = new User("Dummy", "Data", "DummyData@dat.com", Goal.MAINTENANCE);

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_settings, container, false);
        profileButton = view.findViewById(R.id.profileButton);
        setButtonActions();
        profileFragment = new ProfileFragment(dummy);
        return view;
    }

    public void setButtonActions() {
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.activeView, profileFragment,null).commit();
            }
        });
    }
}