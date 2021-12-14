package com.patrickbanez.workoutapp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.patrickbanez.workoutapp.Workout.Set;

public class EditSetFragment extends Fragment {
    private Set set;
    private int index;

    public EditSetFragment(Set s, int i) {
        set = new Set(0, 0);
        set = s;
        index = i;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_set, container, false);

        TextView setNumber = (TextView) view.findViewById(R.id.setNumber);
        setNumber.setText(index);

        EditText editReps = (EditText) view.findViewById(R.id.editReps);
        editReps.setText(set.getReps());

        EditText editWeight = (EditText) view.findViewById(R.id.editWeight);
        editWeight.setText(String.valueOf(set.getWeight()));

        return view;
    }

}
