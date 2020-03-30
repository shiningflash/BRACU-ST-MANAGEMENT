package com.example.bracu_st_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;

public class HomepageActivity extends AppCompatActivity {
    protected Button student_list_button;
    protected Button st_list_button;
    protected Button faculty_list_button;
    protected Button logout_button;
    protected Button my_profile_button;
    protected Button applyST_button;
    protected Button show_applicantList_button;

    UserSessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        student_list_button = (Button) findViewById(R.id.student_list_button);
        st_list_button = (Button) findViewById(R.id.st_list_button);
        faculty_list_button = (Button) findViewById(R.id.faculty_list_button);
        logout_button = (Button) findViewById(R.id.logout_button);
        my_profile_button = (Button) findViewById(R.id.my_profile_button);
        applyST_button = (Button) findViewById(R.id.applyST_button);
        show_applicantList_button = (Button) findViewById(R.id.show_applicantList_button);

        session = new UserSessionManager(getApplicationContext());

        if(session.checkLogin()) finish();

        HashMap<String, String> user = session.getUserDetails();
        String name = user.get(UserSessionManager.KEY_NAME);
        final String email = user.get(UserSessionManager.KEY_EMAIL);

        student_list_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomepageActivity.this, Student_ListActivity.class);
                startActivity(intent);
            }
        });

        my_profile_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomepageActivity.this, UserProfileActivity.class);
                intent.putExtra("Email", email);
                startActivity(intent);
            }
        });

        applyST_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomepageActivity.this, ApplyForST.class);
                startActivity(intent);
            }
        });

        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                session.logoutUser();
                finish();
            }
        });

        show_applicantList_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomepageActivity.this, ShowStApplicantInfoActivity.class);
                startActivity(intent);
            }
        });
    }
}
