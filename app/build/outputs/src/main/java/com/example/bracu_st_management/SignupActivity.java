package com.example.bracu_st_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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

import java.util.Random;

public class SignupActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {

    protected ImageView imageView;
    protected EditText full_name;
    protected EditText email_id;
    protected EditText password;
    protected EditText repeat_password;
    protected EditText contact_no;
    protected Spinner department;
    protected Button lebel;
    protected Button login_button;
    protected Button register_button;
    protected ProgressBar progressBar;

    // protected DatabaseReference databaseReference;
    // protected Student student;

    private void sendMessage(final String receiver, final String key) {
        final ProgressDialog dialog = new ProgressDialog(SignupActivity.this);
        final String subject = "Account Confirmation Code for BRACU STUDENT TUTOR MANAGEMENT";
        final String mail = "Use " + key + " (this secret code) to sign up for your account";
        dialog.setTitle("Sending Email");
        dialog.setMessage("Please wait");
        dialog.show();
        Thread sender = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    GmailSender sender = new GmailSender("bracustmanagement@gmail.com", "15201524");
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
        setContentView(R.layout.activity_signup);

        imageView = (ImageView) findViewById(R.id.imageView);
        full_name = (EditText) findViewById(R.id.full_name);
        email_id = (EditText) findViewById(R.id.email_id);
        password = (EditText) findViewById(R.id.password);
        repeat_password = (EditText) findViewById(R.id.repeat_password);
        contact_no = (EditText) findViewById(R.id.contact_no);
        department = (Spinner) findViewById(R.id.department);

        lebel = (Button) findViewById(R.id.appLebel);
        login_button = (Button) findViewById(R.id.login_button);
        register_button = (Button) findViewById(R.id.register_button);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        // student = new Student();
        // databaseReference = FirebaseDatabase.getInstance().getReference().child("Student");

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String fname = full_name.getText().toString().trim();
                String phoneno = contact_no.getText().toString().trim();
                String eid = email_id.getText().toString().trim();
                String pass = password.getText().toString().trim();
                String repass = repeat_password.getText().toString().trim();
                String dname = String.valueOf(department.getSelectedItem());

                if (eid.isEmpty() && pass.isEmpty() && repass.isEmpty() && fname.isEmpty()) {
                    Toast.makeText(SignupActivity.this, "Please, fill up the required field", Toast.LENGTH_SHORT).show();
                    email_id.requestFocus();
                    password.requestFocus();
                    repeat_password.requestFocus();
                    full_name.requestFocus();
                    progressBar.setVisibility(View.GONE);
                    return;
                }

                if (eid.isEmpty()) {
                    email_id.setError("Please, enter your email id");
                    email_id.requestFocus();
                    progressBar.setVisibility(View.GONE);
                    return;
                }

                if (!(eid.endsWith("@g.bracu.ac.bd"))) {
                    email_id.setError("Please, enter g-suite mail id");
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

                if (repass.isEmpty()) {
                    repeat_password.setError("Please, enter your password again");
                    repeat_password.requestFocus();
                    progressBar.setVisibility(View.GONE);
                    return;
                }

                if (!(pass.equals(repass))) {
                    Toast.makeText(SignupActivity.this, "Password doesn't matched", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    return;
                }

                if (fname.isEmpty()) {
                    full_name.setError("Please, enter your full name");
                    full_name.requestFocus();
                    progressBar.setVisibility(View.GONE);
                    return;
                }

                if (phoneno.isEmpty()) {
                    phoneno = "01xxxxxxxxx";
                }

                // String key = eid.replace(".", "dot");

                Intent intentConfirmSignupActivity = new Intent(SignupActivity.this, ConfirmSignupActivity.class);
                Bundle extras = new Bundle();
                extras.putString("Email", eid);
                extras.putString("Password", pass);
                extras.putString("Name", fname);
                extras.putString("Contact", phoneno);
                extras.putString("Department", dname);
                intentConfirmSignupActivity.putExtras(extras);
                startActivity(intentConfirmSignupActivity);
                finish();
            }
        });

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogin = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intentLogin);
                finish();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String department = parent.getItemAtPosition(position).toString();
        Toast.makeText(this, department + " selected", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}
}

