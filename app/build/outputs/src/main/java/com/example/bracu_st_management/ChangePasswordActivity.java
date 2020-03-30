package com.example.bracu_st_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.HashMap;

public class ChangePasswordActivity extends AppCompatActivity {
    protected EditText prev_pass, new_pass, confirm_pass;
    protected Button confirm_button, back_button;

    UserSessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        prev_pass = (EditText) findViewById(R.id.prev_pass);
        new_pass = (EditText) findViewById(R.id.new_pass);
        confirm_pass = (EditText) findViewById(R.id.confirm_pass);

        confirm_button = (Button) findViewById(R.id.confirm_button);
        back_button = (Button) findViewById(R.id.back_button);

        session = new UserSessionManager(getApplicationContext());
        HashMap<String, String> user = session.getUserDetails();
        final String Email = user.get(UserSessionManager.KEY_EMAIL);

        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String prepass = prev_pass.getText().toString().trim();
                final String newpass = new_pass.getText().toString().trim();
                final String conpass = confirm_pass.getText().toString().trim();

                if (prepass.isEmpty()) {
                    prev_pass.setError("Please, enter previous password");
                    prev_pass.requestFocus();
                    return;
                }

                if (newpass.isEmpty()) {
                    new_pass.setError("Please, enter new password");
                    new_pass.requestFocus();
                    return;
                }

                if (!(newpass.equals(conpass))) {
                    Toast.makeText(ChangePasswordActivity.this, "Repeat Password doesn't matched", Toast.LENGTH_SHORT).show();
                    return;
                }

                final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                AuthCredential credential = EmailAuthProvider.getCredential(Email, prepass);

                user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            user.updatePassword(newpass).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(ChangePasswordActivity.this, "Password Updated", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(ChangePasswordActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(ChangePasswordActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                        }
                        Intent intent = new Intent(ChangePasswordActivity.this, HomepageDrawer.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChangePasswordActivity.this, HomepageDrawer.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
