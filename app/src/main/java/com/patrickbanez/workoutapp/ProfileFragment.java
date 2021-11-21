package com.patrickbanez.workoutapp;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.patrickbanez.workoutapp.User.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment{

    private View view;
    private EditText name, email, age, height, heightInProfile, heightFtProfile, weight;
    private RadioGroup goals;
    private User user;
    private ImageButton editProfileButton;
    private Button saveButton, cancelButton;
    private Drawable defaultBackground;

    // User required to create profile
    private ProfileFragment() {
    }

    public ProfileFragment(User user) {
        this.user = user;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getFields();
        setEditButton();
        setCancelButton();
        setSaveButton();
        initFields();
        setNullBackground();
    }

    private void getFields() {
        name = view.findViewById(R.id.nameProfile);
        email = view.findViewById(R.id.emailProfile);
        age = view.findViewById(R.id.ageProfile);
        height = view.findViewById(R.id.heightProfile);
        heightInProfile = view.findViewById(R.id.heightInProfile);
        heightFtProfile = view.findViewById(R.id.heightFtProfile);
        weight = view.findViewById(R.id.weightProfile);
        goals = view.findViewById(R.id.goalsProfile);
        editProfileButton = view.findViewById(R.id.editProfileButton);
        saveButton = view.findViewById(R.id.saveProfileButton);
        cancelButton = view.findViewById(R.id.cancelProfileButton);
        setInputType();
        setFocusFalse();
        defaultBackground = name.getBackground();
    }

    private void initFields() {
        heightInProfile.setVisibility(View.INVISIBLE);
        heightFtProfile.setVisibility(View.INVISIBLE);
        name.setText(user.getFirstName() + " " + user.getLastName());
        email.setText(user.getEmail());
        switch(user.getGoal()) {
            case MUSCLE_BUILDING:
                ((RadioButton) goals.findViewById(R.id.muscleBuildingProfile)).setChecked(true);
                break;
            case STRENGTH_BUILDING:
                ((RadioButton) goals.findViewById(R.id.strengthBuildingProfile)).setChecked(true);
                break;
            case MAINTENANCE:
                ((RadioButton) goals.findViewById(R.id.maintenanceProfile)).setChecked(true);
                break;
            default :
                ((RadioButton) goals.findViewById(R.id.weightLossProfile)).setChecked(true);
                break;
        }
        if(user.getAge() > 0) {
            age.setText(user.getAge() + "");
        }
        if(user.getHeightFt() > 0 || user.getHeightIn() > 0) {
            height.setText(user.getHeightFt() + "'" + user.getHeightIn() + "\"");
        }
        if(user.getWeight() > 0.0) {
            weight.setText(user.getWeight() + " lbs");
        }
    }

    private void setEditButton() {
        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                heightInProfile.setVisibility(View.VISIBLE);
                heightFtProfile.setVisibility(View.VISIBLE);
                height.setVisibility(View.INVISIBLE);
                setDefaultBackground();
                setFocusTrue();
                saveButton.setVisibility(View.VISIBLE);
                cancelButton.setVisibility(View.VISIBLE);
            }
        });
    }

    private void setCancelButton() {
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initFields();
                setFocusFalse();
                cancelButton.setVisibility(View.INVISIBLE);
                saveButton.setVisibility(View.INVISIBLE);
                height.setVisibility(View.VISIBLE);
                editProfileButton.setClickable(true);
                setNullBackground();
            }
        });
    }

    private void setSaveButton() {
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = name.getText().toString().trim();
                int spaceLocation = 0;
                for (int i = 0; i < newName.length(); i++) {
                    if (newName.charAt(i) == ' ' && newName.charAt(i + 1) != ' ') {
                        spaceLocation = i;
                        break;
                    }
                }
                if (spaceLocation == 0) {
                    Toast.makeText(getContext(), "Please enter your last name", Toast.LENGTH_SHORT).show();
                    return;
                }
                user.setFirstName((String) newName.subSequence(0, spaceLocation));
                user.setLastName((String) newName.subSequence(spaceLocation + 1, newName.length()));
                if (!isEmailValid(email.getText().toString())) {
                    Toast.makeText(getContext(), "Please enter your email", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Add optional fields to the new user
                String temp = age.getText().toString();
                if (!temp.equals("")) {
                    int age = Integer.parseInt(temp);
                    user.setAge(age);
                }

                temp = heightFtProfile.getText().toString();
                if (!temp.equals("")) {
                    int heightFt = Integer.parseInt(temp);
                    user.setHeightFt(heightFt);
                }

                temp = heightInProfile.getText().toString();
                if (!temp.equals("")) {
                    int heightIn = Integer.parseInt(temp);
                    user.setHeightIn(heightIn);
                }

                temp = weight.getText().toString();
                if (!temp.equals("")) {
                    double weight = Double.parseDouble(temp);
                    user.setWeight(weight);
                }
                initFields();
                setFocusFalse();
                cancelButton.setVisibility(View.INVISIBLE);
                saveButton.setVisibility(View.INVISIBLE);
                height.setVisibility(View.VISIBLE);
                editProfileButton.setClickable(true);
                setNullBackground();
            }
        });
    }

    private void setFocusFalse() {
        name.setFocusable(false);
        email.setFocusable(false);
        age.setFocusable(false);
        height.setFocusable(false);
        weight.setFocusable(false);
        for(int i = 0; i < goals.getChildCount(); i++) {
            goals.getChildAt(i).setClickable(false);
        }
    }

    private void setFocusTrue() {
        editProfileButton.setClickable(false);
        name.setClickable(true);
        email.setClickable(true);
        age.setClickable(true);
        height.setClickable(true);
        weight.setClickable(true);
        name.setFocusableInTouchMode(true);
        email.setFocusableInTouchMode(true);
        age.setFocusableInTouchMode(true);
        height.setFocusableInTouchMode(true);
        weight.setFocusableInTouchMode(true);
        for(int i = 0; i < goals.getChildCount(); i++) {
            goals.getChildAt(i).setClickable(true);
        }
    }

    private void setDefaultBackground() {
        name.setBackground(defaultBackground);
        email.setBackground(defaultBackground);
        age.setBackground(defaultBackground);
        height.setBackground(defaultBackground);
        weight.setBackground(defaultBackground);
    }

    private void setNullBackground() {
        name.setBackground(null);
        email.setBackground(null);
        age.setBackground(null);
        height.setBackground(null);
        weight.setBackground(null);
    }

    private void setInputType() {
        name.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        email.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        age.setInputType(InputType.TYPE_CLASS_NUMBER);
        height.setInputType(InputType.TYPE_CLASS_NUMBER);
        heightInProfile.setInputType(InputType.TYPE_CLASS_NUMBER);
        heightFtProfile.setInputType(InputType.TYPE_CLASS_NUMBER);
        weight.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
    }

    // https://stackoverflow.com/questions/6119722/how-to-check-edittexts-text-is-email-address-or-not
    /**
     * method is used for checking valid email id format.
     *
     * @param email
     * @return boolean true for valid false for invalid
     */
    private static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}