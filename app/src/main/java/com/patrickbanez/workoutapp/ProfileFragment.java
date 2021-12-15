package com.patrickbanez.workoutapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
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

import androidx.fragment.app.Fragment;

import com.google.gson.Gson;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment{

    private View view;
    private EditText name, firstNameProfile, lastNameProfile, email, age, height, heightInProfile,
            heightFtProfile, weight;
    private RadioGroup goals;
    private User user;
    private ImageButton editProfileButton;
    private Button saveButton, cancelButton;
    private Drawable defaultBackground;
    private SharedPreferences sp;

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
        sp = getActivity().getSharedPreferences(String.valueOf(R.string.user_sp), Context.MODE_PRIVATE);
    }

    private void getFields() {
        name = view.findViewById(R.id.nameProfile);
        firstNameProfile = view.findViewById(R.id.firstNameProfile);
        lastNameProfile = view.findViewById(R.id.lastNameProfile);
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
        cancelButton.setVisibility(View.INVISIBLE);
        saveButton.setVisibility(View.INVISIBLE);
        height.setVisibility(View.VISIBLE);
        name.setVisibility(View.VISIBLE);
        firstNameProfile.setVisibility(View.INVISIBLE);
        lastNameProfile.setVisibility(View.INVISIBLE);
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
        } else {
            age.setText("");
        }
        if(user.getHeightFt() > 0 || user.getHeightIn() > 0) {
            height.setText(user.getHeightFt() + "'" + user.getHeightIn() + "\"");
        } else {
            height.setText("");
        }
        if(user.getWeight() > 0.0) {
            weight.setText(user.getWeight() + " lbs");
        } else {
            weight.setText("");
        }
    }

    private void setEditButton() {
        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstNameProfile.setVisibility(View.VISIBLE);
                lastNameProfile.setVisibility(View.VISIBLE);
                name.setVisibility(View.INVISIBLE);
                heightInProfile.setVisibility(View.VISIBLE);
                heightFtProfile.setVisibility(View.VISIBLE);
                height.setVisibility(View.INVISIBLE);
                firstNameProfile.setText(user.getFirstName());
                lastNameProfile.setText(user.getLastName());
                weight.setText(user.getWeight() + "");
                age.setText(user.getAge() + "");
                heightFtProfile.setText(user.getHeightFt() + "");
                heightInProfile.setText(user.getHeightIn() + "");
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
                editProfileButton.setClickable(true);
                setNullBackground();
            }
        });
    }

    private void setSaveButton() {
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newFirst = firstNameProfile.getText().toString().trim();
                String newLast = lastNameProfile.getText().toString().trim();
                if (newFirst.equals("") || newLast.equals("")) {
                    Toast.makeText(getContext(), "Please enter your first and last name",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                user.setFirstName(newFirst);
                user.setLastName(newLast);
                String newEmail = email.getText().toString().trim();
                if (!isEmailValid(newEmail)) {
                    Toast.makeText(getContext(), "Please enter your email", Toast.LENGTH_SHORT).show();
                    return;
                }
                RadioButton selectedGoal = (RadioButton) view.findViewById(goals.getCheckedRadioButtonId());
                // current user goal
                Goal goal = user.getGoal();
                if (selectedGoal.getText().toString().equals(getString(R.string.weight_loss_text))) {
                    goal = Goal.WEIGHT_LOSS;
                } else if (selectedGoal.getText().toString().equals(getString(R.string.muscle_building_text))) {
                    goal = Goal.MUSCLE_BUILDING;
                } else if (selectedGoal.toString().equals(getString(R.string.strength_building_text))) {
                    goal = Goal.STRENGTH_BUILDING;
                } else {
                    goal = Goal.MAINTENANCE;
                }
                user.setGoal(goal);
                // Add optional fields to the new user
                String temp = age.getText().toString();
                if (!temp.equals("")) {
                    int age = Integer.parseInt(temp);
                    user.setAge(age);
                } else {
                    user.setAge(0);
                }

                temp = heightFtProfile.getText().toString();
                if (!temp.equals("")) {
                    int heightFt = Integer.parseInt(temp);
                    user.setHeightFt(heightFt);
                } else {
                    user.setHeightFt(0);
                }

                temp = heightInProfile.getText().toString();
                if (!temp.equals("")) {
                    int heightIn = Integer.parseInt(temp);
                    user.setHeightIn(heightIn);
                } else {
                    user.setHeightIn(0);
                }

                temp = weight.getText().toString();
                if (!temp.equals("")) {
                    int weight = Integer.parseInt(temp);
                    user.setWeight(weight);
                } else {
                    user.setWeight(0);
                }

                SharedPreferences.Editor prefEditor = sp.edit();
                Gson gson = new Gson();
                String json = gson.toJson(user);
                prefEditor.putString(String.valueOf(R.string.user_data_key), json);
                prefEditor.commit();

                initFields();
                setFocusFalse();
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
        firstNameProfile.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        lastNameProfile.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
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