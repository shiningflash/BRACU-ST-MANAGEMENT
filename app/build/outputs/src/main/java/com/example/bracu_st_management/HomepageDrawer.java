package com.example.bracu_st_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;

public class HomepageDrawer extends AppCompatActivity {
    protected DrawerLayout drawerLayout;
    protected ActionBarDrawerToggle toggle;
    protected Toolbar toolbar;
    protected TextView name;
    protected TextView email;

    UserSessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage_drawer);

        try {
            session = new UserSessionManager(getApplicationContext());
            if(session.checkLogin()) finish();
            HashMap<String, String> user = session.getUserDetails();
            final String Name = user.get(UserSessionManager.KEY_NAME);
            final String Email = user.get(UserSessionManager.KEY_EMAIL);

            name = (TextView) findViewById(R.id.name);
            email = (TextView) findViewById(R.id.email);

            name.setText(Name);
            email.setText(Email);

            /*
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            drawerLayout = (DrawerLayout) findViewById(R.id.drawyerLayout);
            toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.Open, R.string.Close);
            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();
             */

            NavigationView nav_view = (NavigationView) findViewById(R.id.nav_view);
            nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    int id = menuItem.getItemId();
                    if (id == R.id.my_profile) {
                        Intent intent = new Intent(HomepageDrawer.this, UserProfileActivity.class);
                        intent.putExtra("Email", Email);
                        startActivity(intent);
                    }
                    if (id == R.id.change_pass) {
                        Intent intent = new Intent(HomepageDrawer.this, ChangePasswordActivity.class);
                        startActivity(intent);
                    }
                    if (id == R.id.student_list) {
                        Intent intent = new Intent(HomepageDrawer.this, Student_ListActivity.class);
                        startActivity(intent);
                    }
                    if (id == R.id.apply_st) {
                        Intent intent = new Intent(HomepageDrawer.this, ApplyForST.class);
                        startActivity(intent);
                    }
                    if (id == R.id.applicant_list) {
                        Intent intent = new Intent(HomepageDrawer.this, ShowStApplicantInfoActivity.class);
                        startActivity(intent);
                    }
                    if (id == R.id.logout) {
                        FirebaseAuth.getInstance().signOut();
                        Intent intentLogin = new Intent(HomepageDrawer.this, LoginActivity.class);
                        intentLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intentLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intentLogin);
                        session.logoutUser();
                        finish();
                    }
                    // drawerLayout.closeDrawers();
                    return true;
                }
            });
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return toggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }
}
