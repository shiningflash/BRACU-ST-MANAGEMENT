package com.example.bracu_st_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateInfoActivity extends AppCompatActivity {
    protected ImageView imageView;
    protected EditText full_name;
    protected EditText contact_no;
    protected EditText student_id;
    protected EditText department;
    protected Button lebel;
    protected Button back_button;
    protected Button confirm_button;
    protected ProgressBar progressBar;

    protected Student student;
    protected DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_info);

        try {
            imageView = (ImageView) findViewById(R.id.imageView);
            full_name = (EditText) findViewById(R.id.full_name);
            contact_no = (EditText) findViewById(R.id.contact_no);
            department = (EditText) findViewById(R.id.department);
            student_id = (EditText) findViewById(R.id.student_id);

            lebel = (Button) findViewById(R.id.appLebel);
            back_button = (Button) findViewById(R.id.back_button);
            confirm_button = (Button) findViewById(R.id.confirm_button);
            progressBar = (ProgressBar) findViewById(R.id.progress_bar);

            Intent intent = getIntent();
            Bundle extras = intent.getExtras();
            final String email1 = extras.getString("Email");
            final String name1 = extras.getString("Name");
            final String phoneno1 = extras.getString("Contact");
            final String stdid1 = extras.getString("Student_Id");
            final String dept1 = extras.getString("Department");
            final String stutor = extras.getString("Student_Tutor");

            full_name.setText(name1);
            contact_no.setText(phoneno1);
            student_id.setText(stdid1);
            department.setText(dept1);

            confirm_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    progressBar.setVisibility(View.VISIBLE);
                    String fname = full_name.getText().toString().trim();
                    String phoneno = contact_no.getText().toString().trim();
                    String dname = department.getText().toString().trim();
                    String sid = student_id.getText().toString().trim();

                    if (fname.isEmpty()) {
                        full_name.setError("Please, enter your full name");
                        full_name.requestFocus();
                        progressBar.setVisibility(View.GONE);
                        return;
                    }

                    if (phoneno.isEmpty()) {
                        phoneno = "01xxxxxxxxx";
                    }

                    student = new Student();
                    databaseReference = FirebaseDatabase.getInstance().getReference().child("Student");

                    student.setEmail_id(email1);
                    student.setName(fname);
                    student.setContact_no(phoneno);
                    student.setDepartment(dname);
                    student.setStudent_id(sid);
                    student.setStudent_tutor(stutor);

                    String key = email1.replace(".", "dot");

                    databaseReference.child(key).setValue(student).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task1) {
                            progressBar.setVisibility(View.GONE);
                            if (task1.isSuccessful()) {
                                Toast.makeText(UpdateInfoActivity.this, "Updated successful", Toast.LENGTH_SHORT).show();
                                // will work later



                                Intent intentHomepage = new Intent(UpdateInfoActivity.this, HomepageDrawer.class);
                                intentHomepage.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intentHomepage.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intentHomepage);
                                finish();
                            }
                            else {
                                Log.d("don't know ", "exception");
                                Toast.makeText(UpdateInfoActivity.this, task1.getException().toString(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            });

            back_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentLogin = new Intent(UpdateInfoActivity.this, HomepageDrawer.class);
                    startActivity(intentLogin);
                    finish();
                }
            });

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
