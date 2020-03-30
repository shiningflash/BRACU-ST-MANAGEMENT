package com.example.bracu_st_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import io.opencensus.tags.Tag;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class ConfirmSignupActivity extends AppCompatActivity {
    protected EditText confirmation_code;
    protected EditText student_id;
    protected Button confirm_button;
    protected Button back_button;
    protected ProgressBar progressBar;
    protected Button send_code;

    protected FirebaseAuth firebaseAuth;

    protected DatabaseReference databaseReference;
    protected Student student;

    private void sendMessage(final String receiver, final String key) {
        final ProgressDialog dialog = new ProgressDialog(ConfirmSignupActivity.this);
        final String subject = "Account Confirmation Code for BRACU STUDENT TUTOR MANAGEMENT";
        final String mail = "Use " + key + " (this secret code) to sign up for your account";
        dialog.setTitle("Sending Email");
        dialog.setMessage("Please wait");
        dialog.show();
        Thread sender = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    GmailSender sender = new GmailSender("bracustmanagement@gmail.com", "[Your password will be here]");
                    sender.sendMail(subject,
                            mail,
                            "bracustmanagement@gmail.com",
                            receiver);
                    dialog.dismiss();
                } catch (Exception e) {
                    Log.e("mylog", "Error: " + e.getMessage());
                }
            }
        });
        sender.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_signup);

        confirmation_code = (EditText) findViewById(R.id.confirmation_code);
        student_id = (EditText) findViewById(R.id.student_id);
        confirm_button = (Button) findViewById(R.id.confirm_button);
        back_button = (Button) findViewById(R.id.back_button);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        send_code = (Button) findViewById(R.id.send_code);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final String email = extras.getString("Email");
        final String password = extras.getString("Password");
        final String name = extras.getString("Name");
        final String phoneno = extras.getString("Contact");
        final String department = extras.getString("Department");

        firebaseAuth = FirebaseAuth.getInstance();

        int generator = (int) (new Random().nextDouble() * 1000000);
        final String confirm_number = generator + "";

        send_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(email, confirm_number);
            }
        });

        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);

                String confirm_code = confirmation_code.getText().toString().trim();
                final String std_id = student_id.getText().toString().trim();

                if (confirm_code.isEmpty()) {
                    confirmation_code.setError("Please, enter confirmation code");
                    confirmation_code.requestFocus();
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                if(!(confirm_code.equals(confirm_number))) {
                    Toast.makeText(ConfirmSignupActivity.this, "Wrong confirmation code", Toast.LENGTH_SHORT).show();
                    confirmation_code.setError("Please, enter correct confirmation code");
                    confirmation_code.requestFocus();
                    progressBar.setVisibility(View.GONE);
                    return;
                }
                if (std_id.isEmpty()) {
                    student_id.setError("Please, enter your student id");
                    student_id.requestFocus();
                    progressBar.setVisibility(View.GONE);
                    return;
                }

                try {
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        student = new Student();
                                        databaseReference = FirebaseDatabase.getInstance().getReference().child("Student");

                                        student.setEmail_id(email);
                                        student.setName(name);
                                        student.setContact_no(phoneno);
                                        student.setDepartment(department);
                                        student.setStudent_id(std_id);
                                        student.setStudent_tutor("0");

                                        String key = email.replace(".", "dot");

                                        databaseReference.child(key).setValue(student).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task1) {
                                                progressBar.setVisibility(View.GONE);
                                                if (task1.isSuccessful()) {
                                                    Toast.makeText(ConfirmSignupActivity.this, "Sign up successful", Toast.LENGTH_SHORT).show();
                                                    // will work later



                                                    Intent intentHomepage = new Intent(ConfirmSignupActivity.this, HomepageDrawer.class);
                                                    intentHomepage.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                    intentHomepage.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                    startActivity(intentHomepage);
                                                    finish();
                                                }
                                                else {
                                                    Log.d("don't know ", "exception");
                                                    Toast.makeText(ConfirmSignupActivity.this, task1.getException().toString(), Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        });
                                    } else {
                                        Toast.makeText(ConfirmSignupActivity.this, task.getException().toString(), Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                } catch (Exception e) {
                    Toast.makeText(ConfirmSignupActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {Intent intentSignupActivity = new Intent(ConfirmSignupActivity.this, SignupActivity.class);
                startActivity(intentSignupActivity);
            }
        });
    }
}
