package com.example.bracu_st_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class ApplyForST extends AppCompatActivity {
    protected ProgressBar progress_bar;
    protected EditText credit_completed;
    protected EditText cgpa;
    protected EditText number_of_courses;
    protected EditText total_class_hour;
    protected EditText cse110_grade;
    protected EditText cse111_grade;
    protected EditText cse220_grade;
    protected EditText cse221_grade;
    protected EditText cse230_grade;
    protected EditText cse260_grade;
    protected EditText cse250_grade;
    protected EditText cse251_grade;
    protected EditText st_experience;
    protected EditText other_experience;
    protected EditText scholarship;
    protected Button apply_button;
    protected ImageView back_button;

    protected DatabaseReference databaseReference;

    UserSessionManager session;
    ST_Applicant applicant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_for_st);

        session = new UserSessionManager(getApplicationContext());

        back_button = (ImageView) findViewById(R.id.back_button);
        progress_bar = (ProgressBar) findViewById(R.id.progress_bar);

        credit_completed = (EditText) findViewById(R.id.credit_completed);
        cgpa = (EditText) findViewById(R.id.cgpa);
        number_of_courses = (EditText) findViewById(R.id.number_of_course);
        total_class_hour = (EditText) findViewById(R.id.total_class_hour);

        cse110_grade = (EditText) findViewById(R.id.cse110_grade);
        cse111_grade = (EditText) findViewById(R.id.cse111_grade);
        cse220_grade = (EditText) findViewById(R.id.cse220_grade);
        cse221_grade = (EditText) findViewById(R.id.cse221_grade);
        cse230_grade = (EditText) findViewById(R.id.cse230_grade);
        cse260_grade = (EditText) findViewById(R.id.cse260_grade);
        cse250_grade = (EditText) findViewById(R.id.cse250_grade);
        cse251_grade = (EditText) findViewById(R.id.cse251_grade);

        st_experience = (EditText) findViewById(R.id.st_experience);
        other_experience = (EditText) findViewById(R.id.other_experience);
        scholarship = (EditText) findViewById(R.id.scholarship);

        apply_button = (Button) findViewById(R.id.apply_button);

        // check user session
        session = new UserSessionManager(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        final String name = user.get(UserSessionManager.KEY_NAME);
        final String email = user.get(UserSessionManager.KEY_EMAIL);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHomepage = new Intent(ApplyForST.this, HomepageDrawer.class);
                intentHomepage.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intentHomepage.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intentHomepage);
                finish();
            }
        });

        apply_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress_bar.setVisibility(View.VISIBLE);

                final String completed_credit = credit_completed.getText().toString();
                final String current_cgpa = cgpa.getText().toString();
                final String current_course = number_of_courses.getText().toString();
                final String current_classhour = total_class_hour.getText().toString();

                final String cse110 = cse110_grade.getText().toString().toUpperCase();
                final String cse111 = cse111_grade.getText().toString().toUpperCase();
                final String cse220 = cse220_grade.getText().toString().toUpperCase();
                final String cse221 = cse221_grade.getText().toString().toUpperCase();
                final String cse230 = cse230_grade.getText().toString().toUpperCase();
                final String cse260 = cse260_grade.getText().toString().toUpperCase();
                final String cse250 = cse250_grade.getText().toString().toUpperCase();
                final String cse251 = cse251_grade.getText().toString().toUpperCase();

                final String experience_st = st_experience.getText().toString();
                final String experience_other = other_experience.getText().toString();
                final String scholarship_type = scholarship.getText().toString();

                if (completed_credit.isEmpty() && current_cgpa.isEmpty() && current_course.isEmpty() &&
                    current_classhour.isEmpty() && cse110.isEmpty() && cse111.isEmpty() && cse220.isEmpty() &&
                    cse221.isEmpty() && cse230.isEmpty() && cse260.isEmpty()) {
                    Toast.makeText(ApplyForST.this, "Fields are empty", Toast.LENGTH_SHORT).show();
                    progress_bar.setVisibility(View.GONE);
                    return;
                }
                if (completed_credit.isEmpty()) {
                    credit_completed.setError("Please, fill up the field");
                    credit_completed.requestFocus();
                    progress_bar.setVisibility(View.GONE);
                    return;
                }
                if (current_cgpa.isEmpty()) {
                    cgpa.setError("Please, fill up the field");
                    cgpa.requestFocus();
                    progress_bar.setVisibility(View.GONE);
                    return;
                }
                if (current_course.isEmpty()) {
                    number_of_courses.setError("Please, fill up the field");
                    number_of_courses.requestFocus();
                    progress_bar.setVisibility(View.GONE);
                    return;
                }
                if (current_classhour.isEmpty()) {
                    total_class_hour.setError("Please, fill up the field");
                    total_class_hour.requestFocus();
                    progress_bar.setVisibility(View.GONE);
                    return;
                }
                if (cse110.isEmpty()) {
                    cse110_grade.setError("Please, fill up the field");
                    cse110_grade.requestFocus();
                    progress_bar.setVisibility(View.GONE);
                    return;
                }
                if (cse111.isEmpty()) {
                    cse111_grade.setError("Please, fill up the field");
                    cse111_grade.requestFocus();
                    progress_bar.setVisibility(View.GONE);
                    return;
                }
                if (cse220.isEmpty()) {
                    cse220_grade.setError("Please, fill up the field");
                    cse220_grade.requestFocus();
                    progress_bar.setVisibility(View.GONE);
                    return;
                }
                if (cse221.isEmpty()) {
                    cse221_grade.setError("Please, fill up the field");
                    cse221_grade.requestFocus();
                    progress_bar.setVisibility(View.GONE);
                    return;
                }
                if (cse230.isEmpty()) {
                    cse230_grade.setError("Please, fill up the field");
                    cse230_grade.requestFocus();
                    progress_bar.setVisibility(View.GONE);
                    return;
                }
                if (cse260.isEmpty()) {
                    cse260_grade.setError("Please, fill up the field");
                    cse260_grade.requestFocus();
                    progress_bar.setVisibility(View.GONE);
                    return;
                }
                try {
                    if (Double.parseDouble(completed_credit) < 60.0) {
                        Toast.makeText(ApplyForST.this, "Credit lower than 60 can't apply. Please, try later.", Toast.LENGTH_SHORT).show();
                        progress_bar.setVisibility(View.GONE);
                        return;
                    }
                } catch (Exception e) {
                    Toast.makeText(ApplyForST.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    progress_bar.setVisibility(View.GONE);
                    return;
                }
                try {
                    if (Double.parseDouble(current_cgpa) < 3.50) {
                        Toast.makeText(ApplyForST.this, "CGPA less than 3.5 can't apply. Please, try later.", Toast.LENGTH_SHORT).show();
                        progress_bar.setVisibility(View.GONE);
                        return;
                    }
                } catch (Exception e) {
                    Toast.makeText(ApplyForST.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    progress_bar.setVisibility(View.GONE);
                    return;
                }

                try {
                    applicant = new ST_Applicant();
                    databaseReference = FirebaseDatabase.getInstance().getReference().child("STApplicant");

                    applicant.setEmail_id(email);
                    applicant.setApplicantName(name);
                    applicant.setTotalCredit(completed_credit);
                    applicant.setCGPA(current_cgpa);
                    applicant.setCurrentCourse(current_course);
                    applicant.setCurrentClassHour(current_classhour);
                    applicant.setCSE110(cse110);
                    applicant.setCSE111(cse111);
                    applicant.setCSE220(cse220);
                    applicant.setCSE221(cse221);
                    applicant.setCSE230(cse230);
                    applicant.setCSE260(cse260);
                    applicant.setCSE250(cse250);
                    applicant.setCSE251(cse251);
                    applicant.setSTExperience(experience_st);
                    applicant.setOtherExperience(experience_other);
                    applicant.setScholarship(scholarship_type);

                    String key = email.replace(".", "dot");

                    databaseReference.child(key).setValue(applicant).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task1) {
                            progress_bar.setVisibility(View.GONE);
                            if (task1.isSuccessful()) {
                                Toast.makeText(ApplyForST.this, "Application submitted successfully", Toast.LENGTH_SHORT).show();
                                // will work later



                                Intent intentHomepage = new Intent(ApplyForST.this, HomepageDrawer.class);
                                intentHomepage.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intentHomepage.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intentHomepage);
                                finish();
                            }
                            else {
                                Toast.makeText(ApplyForST.this, task1.getException().toString(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                } catch (Exception e) {
                    Toast.makeText(ApplyForST.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
