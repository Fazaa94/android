package com.yourdomain.yourapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    private EditText participantNameInput, dogNameInput, dogBreedInput;
    private Button registerButton;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        participantNameInput = findViewById(R.id.participantNameInput);
        dogNameInput = findViewById(R.id.dogNameInput);
        dogBreedInput = findViewById(R.id.dogBreedInput);
        registerButton = findViewById(R.id.registerButton);

        sharedPreferences = getSharedPreferences("DogRaceAppPrefs", Context.MODE_PRIVATE);

        registerButton.setOnClickListener(v -> {
            String participantName = participantNameInput.getText().toString().trim();
            String dogName = dogNameInput.getText().toString().trim();
            String dogBreed = dogBreedInput.getText().toString().trim();

            if (participantName.isEmpty() || dogName.isEmpty() || dogBreed.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("participantName", participantName);
                editor.putString("dogName", dogName);
                editor.putString("dogBreed", dogBreed);
                editor.apply();
                Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}