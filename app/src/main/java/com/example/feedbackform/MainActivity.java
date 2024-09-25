package com.example.feedbackform;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText nameEditText, locationEditText;
    private RadioGroup typeRadioGroup;
    private CheckBox mathCheckBox, physicsCheckBox, chemistryCheckBox;
    private SeekBar budgetSeekBar;
    private RatingBar ratingBar;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        nameEditText = findViewById(R.id.nameEditText);
        locationEditText = findViewById(R.id.locationEditText);
        typeRadioGroup = findViewById(R.id.typeRadioGroup);
        mathCheckBox = findViewById(R.id.mathCheckBox);
        physicsCheckBox = findViewById(R.id.physicsCheckBox);
        chemistryCheckBox = findViewById(R.id.chemistryCheckBox);
        budgetSeekBar = findViewById(R.id.budgetSeekBar);
        ratingBar = findViewById(R.id.ratingBar);
        submitButton = findViewById(R.id.submitButton);

        // Set listeners
        submitButton.setOnClickListener(v -> submitForm());

        budgetSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d("BudgetSeekBar", "Selected Budget: " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });
    }

    private void submitForm() {
        // Get user input values
        String name = nameEditText.getText().toString();
        String location = locationEditText.getText().toString();

        // Check if EditText fields are empty
        if (name.isEmpty() || location.isEmpty()) {
            Toast.makeText(this, "Please fill in all the details.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if a RadioButton is selected
        int selectedTypeId = typeRadioGroup.getCheckedRadioButtonId();
        if (selectedTypeId == -1) {
            Toast.makeText(this, "Please select a coaching type.", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton selectedTypeButton = findViewById(selectedTypeId);
        if (selectedTypeButton == null) {
            Toast.makeText(this, "Please select a valid coaching type.", Toast.LENGTH_SHORT).show();
            return;
        }

        String coachingType = selectedTypeButton.getText().toString();

        // Get checkbox selections
        boolean isMath = mathCheckBox.isChecked();
        boolean isPhysics = physicsCheckBox.isChecked();
        boolean isChemistry = chemistryCheckBox.isChecked();

        // Get seekbar and rating values
        int budget = budgetSeekBar.getProgress();
        float rating = ratingBar.getRating();

        // Log the inputs
        Log.d("SubmitForm", "Name: " + name + ", Location: " + location);
        Log.d("SubmitForm", "Coaching Type: " + coachingType);
        Log.d("SubmitForm", "Subjects: Math=" + isMath + ", Physics=" + isPhysics + ", Chemistry=" + isChemistry);
        Log.d("SubmitForm", "Budget: " + budget + ", Rating: " + rating);

        Toast.makeText(this, "Form submitted successfully!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, ViewActivity.class);
        startActivity(intent);
    }
}
