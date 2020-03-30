package com.example.bracu_st_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class Selecting_ST extends AppCompatActivity {
    protected TextView course_code, course_title, section;
    protected Button confirm_button, back_button;
    protected ProgressBar progress_bar;

    DatabaseReference databaseReference;
    DatabaseReference databaseReference1;
    Consultation_ST consultation_st;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecting__st);

        course_code = (EditText) findViewById(R.id.course_code);
        course_title = (EditText) findViewById(R.id.course_title);
        section = (EditText) findViewById(R.id.section);

        confirm_button = (Button) findViewById(R.id.confirm_button);
        back_button = (Button) findViewById(R.id.back_button);

        progress_bar = (ProgressBar) findViewById(R.id.progress_bar);

        Intent intent = getIntent();
        String email_id = intent.getStringExtra("Email");
        final String key = email_id.replace(".", "dot");

        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress_bar.setVisibility(View.VISIBLE);

                String Course_code = course_code.getText().toString().trim();
                String Course_title = course_title.getText().toString();
                String Section = section.getText().toString();

                if (Course_code.isEmpty()) {
                    course_code.setError("Please, enter course code");
                    course_code.requestFocus();
                    progress_bar.setVisibility(View.GONE);
                    return;
                }

                if (Course_title.isEmpty()) {
                    course_title.setError("Please, enter course title");
                    course_title.requestFocus();
                    progress_bar.setVisibility(View.GONE);
                    return;
                }

                if (Section.isEmpty()) {
                    section.setError("Please, enter section");
                    section.requestFocus();
                    progress_bar.setVisibility(View.GONE);
                    return;
                }

                consultation_st = new Consultation_ST();
                consultation_st.setCourse_code(Course_code);
                consultation_st.setCourse_title(Course_title);
                consultation_st.setSection(Section);
                // the rest variables are already zero

                try {
                    databaseReference1 = FirebaseDatabase.getInstance().getReference().child("Student").child(key);
                    databaseReference1.child("student_tutor").setValue("1").addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task1) {
                            progress_bar.setVisibility(View.GONE);
                            if (task1.isSuccessful()) {
                            }
                            else {
                                Toast.makeText(Selecting_ST.this, task1.getException().toString(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                } catch (Exception e) {
                    Toast.makeText(Selecting_ST.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                try {
                    databaseReference = FirebaseDatabase.getInstance().getReference().child("Consultation_ST");
                    databaseReference.child(key).setValue(consultation_st).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task1) {
                            progress_bar.setVisibility(View.GONE);
                            if (task1.isSuccessful()) {
                                Toast.makeText(Selecting_ST.this, "Student Tutor assigned successful", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(Selecting_ST.this, ShowStApplicantInfoActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(Selecting_ST.this, task1.getException().toString(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                } catch (Exception e) {
                    Toast.makeText(Selecting_ST.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {Intent intentSignupActivity = new Intent(Selecting_ST.this, ShowStApplicantInfoActivity.class);
                startActivity(intentSignupActivity);
            }
        });
    }
}
