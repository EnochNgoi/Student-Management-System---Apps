package com.example.studentmanagementapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.text.method.ScrollingMovementMethod;


public class MainActivity extends AppCompatActivity {

    // Variable declarations for UI elements
    private Spinner spinnerOptions;
    private EditText etStudentId, etStudentName, etStudentAge, etStudentGender, etStudentCourse;
    private Button btnSubmit;
    private TextView tvResult;

    // Declare StudentManager
    private StudentManager studentManager;

    // Track the current selected operation
    private String currentOperation = "Add Student";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Apply system bars insets for edge-to-edge layout
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize UI components
        spinnerOptions = findViewById(R.id.spinnerOptions);
        etStudentId = findViewById(R.id.etStudentId);
        etStudentName = findViewById(R.id.etStudentName);
        etStudentAge = findViewById(R.id.etStudentAge);
        etStudentGender = findViewById(R.id.etStudentGender);
        etStudentCourse = findViewById(R.id.etStudentCourse);
        btnSubmit = findViewById(R.id.button);
        tvResult = findViewById(R.id.textView3);
        tvResult.setMovementMethod(new ScrollingMovementMethod());

        // Initialize StudentManager with a maximum capacity of 100 students
        studentManager = new StudentManager(100);

        // Spinner selection logic
        spinnerOptions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentOperation = parent.getItemAtPosition(position).toString();
                switch (currentOperation) {
                    case "Add Student":
                        tvResult.setText("Fill out the form and press Submit to add a student.");
                        break;
                    case "Delete Student":
                        tvResult.setText("Enter Student ID and press Submit to delete the student.");
                        break;
                    case "Update Student":
                        tvResult.setText("Enter ID and updated details, then press Submit.");
                        break;
                    case "Display All Students":
                        tvResult.setText(studentManager.displayAllStudents());
                        break;
                    case "Sort Students by Name":
                        studentManager.sortStudentsByName();
                        tvResult.setText("Students sorted by name.");
                        break;
                    case "Calculate Average Age":
                        double avgAge = studentManager.calculateAverageAge();
                        tvResult.setText(String.format("Average Age: %.2f", avgAge));
                        break;
                    default:
                        tvResult.setText("Please select a valid option.");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                tvResult.setText("No option selected.");
            }
        });

        // Button click logic
        btnSubmit.setOnClickListener(v -> {
            // Retrieve inputs
            String studentIdStr = etStudentId.getText().toString().trim();
            String studentName = etStudentName.getText().toString().trim();
            String studentAgeStr = etStudentAge.getText().toString().trim();
            String studentGender = etStudentGender.getText().toString().trim();
            String studentCourse = etStudentCourse.getText().toString().trim();

            if (studentIdStr.isEmpty()) {
                tvResult.setText("Student ID is required for this operation.");
                return;
            }

            try {
                int studentId = Integer.parseInt(studentIdStr);

                switch (currentOperation) {
                    case "Add Student":
                        int studentAge = Integer.parseInt(studentAgeStr);
                        Student newStudent = new Student(studentId, studentName, studentAge, studentGender, studentCourse);
                        if (studentManager.addStudent(newStudent)) {
                            tvResult.setText("Student added successfully!");
                        } else {
                            tvResult.setText("Failed to add student. Maximum capacity reached.");
                        }
                        break;

                    case "Delete Student":
                        if (studentManager.deleteStudent(studentId)) {
                            tvResult.setText("Student deleted successfully!");
                        } else {
                            tvResult.setText("Student not found.");
                        }
                        break;

                    case "Update Student":
                        int updatedAge = Integer.parseInt(studentAgeStr);
                        if (studentManager.updateStudent(studentId, studentName, updatedAge, studentGender, studentCourse)) {
                            tvResult.setText("Student updated successfully!");
                        } else {
                            tvResult.setText("Student not found.");
                        }
                        break;

                    case "Search Student by ID":
                        Student student = studentManager.searchStudentById(studentId);
                        if (student != null) {
                            tvResult.setText(student.toString());
                        } else {
                            tvResult.setText("Student not found.");
                        }
                        break;

                    default:
                        tvResult.setText("Please select a valid option.");
                        break;
                }

                // Clear input fields after operation
                etStudentId.setText("");
                etStudentName.setText("");
                etStudentAge.setText("");
                etStudentGender.setText("");
                etStudentCourse.setText("");

            } catch (NumberFormatException e) {
                tvResult.setText("Invalid input for ID or Age. Please enter valid numbers.");
            }
        });
    }
}
