package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class CreateNewNoteActivity extends AppCompatActivity {

    private EditText editTextTitle;
    private EditText editTextDescription;
    private Spinner spinnerDays;
    private RadioGroup radioGroupPriorities;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_note);

        editTextTitle = findViewById(R.id.editTextTitle);
        editTextDescription = findViewById(R.id.editTextDescription);
        spinnerDays = findViewById(R.id.spinnerDays);
        radioGroupPriorities = findViewById(R.id.radioGroupPriorities);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
    }

    public void Create(View view) {
        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();
        String day = spinnerDays.getSelectedItem().toString();

        int checkedRadioButtonId = radioGroupPriorities.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(checkedRadioButtonId);
        int priority = Integer.parseInt(radioButton.getText().toString());

        if (title.isEmpty() && description.isEmpty()) {
            Toast.makeText(getApplicationContext(), R.string.toast_text, Toast.LENGTH_SHORT).show();
        } else {
            Note note = new Note(title, description, day, priority);
            viewModel.insertNote(note);

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
    }
}