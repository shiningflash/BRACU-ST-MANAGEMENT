package com.example.bracu_st_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Random;

public class LoginActivity extends AppCompatActivity {
    protected ImageView imageView;
    protected Button lebel;
    protected EditText email_id;
    protected EditText password;
    protected Button login_button;
    protected Button forgot_password_button;
    protected Button signup_button;
    protected ProgressBar progressBar;
    protected CheckBox check_box;

    // protected DatabaseReference databaseReference;
    protected FirebaseAuth firebaseAuth;

    UserSessionManager session;

    protected String mailid_to_name(String name) {
        name = name.replace("@g.bracu.ac.bd", "");
        name = name.replace(".", " ");
        name = name.toUpperCase();
        return name;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        session = new UserSessionManager(getApplicationContext());

        try {
            imageView = (ImageView) findViewById(R.id.imageView);
            email_id = (EditText) findViewById(R.id.email_id);
            password = (EditText) findViewById(R.id.password);

            lebel = (Button) findViewById(R.id.appLebel);
            login_button = (Button) findViewById(R.id.login_button);
            forgot_password_button = (Button) findViewById(R.id.forgot_password_button);
            signup_button = (Button) findViewById(R.id.signup_button);
            check_box = (CheckBox) findViewById(R.id.check_box);

            // databaseReference = FirebaseDatabase.getInstance().getReference().child("Student");
            firebaseAuth = FirebaseAuth.getInstance();

            progressBar = (ProgressBar) findViewById(R.id.progress_bar);

            try {
                login_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        progressBar.setVisibility(View.VISIBLE);
                        final String email = email_id.getText().toString().trim();
                        final String pass = password.getText().toString().trim();

                        if (email.isEmpty() && pass.isEmpty()) {
                            Toast.makeText(LoginActivity.this, "Fields are empty", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            return;
                        }
                        if (email.isEmpty()) {
                            email_id.setError("Please, enter your email id");
                            email_id.requestFocus();
                            progressBar.setVisibility(View.GONE);
                            return;
                        }
                        if (pass.isEmpty()) {
                            password.setError("Please, enter your password");
                            password.requestFocus();
                            progressBar.setVisibility(View.GONE);
                            return;
                        }
                        firebaseAuth.signInWithEmailAndPassword(email, pass)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        progressBar.setVisibility(View.GONE);
                                        if (task.isSuccessful()) {
                                            String name = mailid_to_name(email);
                                            session.createUserLoginSession(name, email);

                                            Intent intent = new Intent(getApplicationContext(), HomepageDrawer.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(intent);
                                            finish();
                                        }
                                        else {
                                            Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                    }
                /*
                else if (! (email.isEmpty() && pass.isEmpty()) ) {
                    String key = email.replace(".", "dot");
                    if (databaseReference.child(key) != null) {
                        databaseReference.child(key).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                Student student = dataSnapshot.getValue(Student.class);
                                if (pass.equals(student.getPassword())) {
                                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                    Intent intentHomepage = new Intent(MainActivity.this, HomepageActivity.class);
                                    startActivity(intentHomepage);
                                }
                                else {
                                    Toast.makeText(MainActivity.this, "Enter correct password", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Account doesn't exist. Please try again.", Toast.LENGTH_SHORT).show();
                    }
                }
                */
                });
            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            forgot_password_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentForgetPass = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
                    startActivity(intentForgetPass);
                    finish();
                }
            });

            check_box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        // show password
                        password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    }
                    else {
                        // Hide password
                        password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    }
                }
            });

            signup_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentSignup = new Intent(LoginActivity.this, SignupActivity.class);
                    startActivity(intentSignup);
                    finish();
                }
            });
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
