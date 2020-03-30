package com.example.bracu_st_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class UserProfileActivity extends AppCompatActivity {
    protected TextView full_name;
    protected TextView student_id;
    protected TextView department;

    protected TextView email_id;
    protected TextView phone_no;

    protected TableLayout consultation_table;

    protected Button edit_profile_button;
    protected Button consultation_button;
    protected Button edit_consultation_button;

    protected LinearLayout my_layout;

    TextView a8, b8, c8, d8, e8, f8;
    TextView a9, b9, c9, d9, e9, f9;
    TextView a11, b11, c11, d11, e11, f11;
    TextView a12, b12, c12, d12, e12, f12;
    TextView a2, b2, c2, d2, e2, f2;
    TextView a3, b3, c3, d3, e3, f3;

    private boolean vis;
    protected Student stdnt;

    protected DatabaseReference databaseReference;
    protected DatabaseReference databaseReference2;

    UserSessionManager session;

    private void showDetails(Context c, String timeDetails, String consultationDetails) {
        AlertDialog dialog = new AlertDialog.Builder(c)
                .setTitle(timeDetails)
                .setMessage(consultationDetails)
                .create();
        dialog.show();
    }

    protected void setStudent(Student stdnt) {
        this.stdnt = stdnt;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        session = new UserSessionManager(getApplicationContext());

        try {
            my_layout = (LinearLayout) findViewById(R.id.my_layout);

            full_name = (TextView) findViewById(R.id.full_name);
            student_id = (TextView) findViewById(R.id.student_id);
            department = (TextView) findViewById(R.id.department);

            email_id = (TextView) findViewById(R.id.email_id);
            phone_no = (TextView) findViewById(R.id.phone_no);

            consultation_table = (TableLayout) findViewById(R.id.consultation_table);

            edit_profile_button = (Button) findViewById(R.id.edit_profile_button);
            consultation_button = (Button) findViewById(R.id.consultation_button);
            edit_consultation_button = (Button) findViewById(R.id.edit_consultation_button);

            a8 = (TextView) findViewById(R.id.a8);
            b8 = (TextView) findViewById(R.id.b8);
            c8 = (TextView) findViewById(R.id.c8);
            d8 = (TextView) findViewById(R.id.d8);
            e8 = (TextView) findViewById(R.id.e8);
            f8 = (TextView) findViewById(R.id.f8);

            a9 = (TextView) findViewById(R.id.a9);
            b9 = (TextView) findViewById(R.id.b9);
            c9 = (TextView) findViewById(R.id.c9);
            d9 = (TextView) findViewById(R.id.d9);
            e9 = (TextView) findViewById(R.id.e9);
            f9 = (TextView) findViewById(R.id.f9);

            a11 = (TextView) findViewById(R.id.a11);
            b11 = (TextView) findViewById(R.id.b11);
            c11 = (TextView) findViewById(R.id.c11);
            d11 = (TextView) findViewById(R.id.d11);
            e11 = (TextView) findViewById(R.id.e11);
            f11 = (TextView) findViewById(R.id.f11);

            a12 = (TextView) findViewById(R.id.a12);
            b12 = (TextView) findViewById(R.id.b12);
            c12 = (TextView) findViewById(R.id.c12);
            d12 = (TextView) findViewById(R.id.d12);
            e12 = (TextView) findViewById(R.id.e12);
            f12 = (TextView) findViewById(R.id.f12);

            a2 = (TextView) findViewById(R.id.a2);
            b2 = (TextView) findViewById(R.id.b2);
            c2 = (TextView) findViewById(R.id.c2);
            d2 = (TextView) findViewById(R.id.d2);
            e2 = (TextView) findViewById(R.id.e2);
            f2 = (TextView) findViewById(R.id.f2);

            a3 = (TextView) findViewById(R.id.a3);
            b3 = (TextView) findViewById(R.id.b3);
            c3 = (TextView) findViewById(R.id.c3);
            d3 = (TextView) findViewById(R.id.d3);
            e3 = (TextView) findViewById(R.id.e3);
            f3 = (TextView) findViewById(R.id.f3);

            //if(session.checkLogin()) finish();
            // HashMap<String, String> user = session.getUserDetails();
            //String name = user.get(UserSessionManager.KEY_NAME);
            //String email = user.get(UserSessionManager.KEY_EMAIL);

            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            final String email = (String) bundle.get("Email");

            databaseReference = FirebaseDatabase.getInstance().getReference().child("Student");
            // databaseReference = FirebaseDatabase.getInstance().getReference().child("");

            session = new UserSessionManager(getApplicationContext());
            HashMap<String, String> user = session.getUserDetails();
            final String Name = user.get(UserSessionManager.KEY_NAME);
            final String Email = user.get(UserSessionManager.KEY_EMAIL);

            if (email.equals(Email)) {
                edit_profile_button.setVisibility(View.VISIBLE);
            }
            else {
                edit_profile_button.setVisibility(View.GONE);
                edit_consultation_button.setVisibility(View.GONE);
            }

            final String key = email.replace(".", "dot");
            try {
                databaseReference.child(key).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        my_layout.setVisibility(View.VISIBLE);
                        Student student = dataSnapshot.getValue(Student.class);
                        setStudent(student);
                        final String name = student.getName();
                        final String sid = student.getStudent_id();
                        final String dept = student.getDepartment();
                        final String st = student.getStudent_tutor();
                        final String phoneno = student.getContact_no();
                        final String eid = student.getEmail_id();

                        full_name.setText(name);
                        student_id.setText("Student Id: " + sid);
                        department.setText(dept);

                        email_id.setText(eid);
                        phone_no.setText("Contact No: " + phoneno);

                        if (st.equals("0")) {
                            consultation_table.setVisibility(View.GONE);
                            consultation_button.setVisibility(View.GONE);
                            edit_consultation_button.setVisibility(View.GONE);
                        }

                            // make INVISIBLE in xml file later
                        if (st.equals("1")) {
                            consultation_button.setVisibility(View.VISIBLE);
                            if (Email.equals(email)) edit_consultation_button.setVisibility(View.VISIBLE);

                            databaseReference2 = FirebaseDatabase.getInstance().getReference().child("Consultation_ST");
                            try {
                                databaseReference2.child(key).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        Consultation_ST consultation = dataSnapshot.getValue(Consultation_ST.class);

                                        if (consultation.getA8().equals("1")) a8.setBackgroundColor(Color.parseColor("#0080FF"));
                                        if (consultation.getB8().equals("1")) b8.setBackgroundColor(Color.parseColor("#0080FF"));
                                        if (consultation.getC8().equals("1")) c8.setBackgroundColor(Color.parseColor("#0080FF"));
                                        if (consultation.getD8().equals("1")) d8.setBackgroundColor(Color.parseColor("#0080FF"));
                                        if (consultation.getE8().equals("1")) e8.setBackgroundColor(Color.parseColor("#0080FF"));
                                        if (consultation.getF8().equals("1")) f8.setBackgroundColor(Color.parseColor("#0080FF"));

                                        if (consultation.getA9().equals("1")) a9.setBackgroundColor(Color.parseColor("#0080FF"));
                                        if (consultation.getB9().equals("1")) b9.setBackgroundColor(Color.parseColor("#0080FF"));
                                        if (consultation.getC9().equals("1")) c9.setBackgroundColor(Color.parseColor("#0080FF"));
                                        if (consultation.getD9().equals("1")) d9.setBackgroundColor(Color.parseColor("#0080FF"));
                                        if (consultation.getE9().equals("1")) e9.setBackgroundColor(Color.parseColor("#0080FF"));
                                        if (consultation.getF9().equals("1")) f9.setBackgroundColor(Color.parseColor("#0080FF"));

                                        if (consultation.getA11().equals("1")) a11.setBackgroundColor(Color.parseColor("#0080FF"));
                                        if (consultation.getB11().equals("1")) b11.setBackgroundColor(Color.parseColor("#0080FF"));
                                        if (consultation.getC11().equals("1")) c11.setBackgroundColor(Color.parseColor("#0080FF"));
                                        if (consultation.getD11().equals("1")) d11.setBackgroundColor(Color.parseColor("#0080FF"));
                                        if (consultation.getE11().equals("1")) e11.setBackgroundColor(Color.parseColor("#0080FF"));
                                        if (consultation.getF11().equals("1")) f11.setBackgroundColor(Color.parseColor("#0080FF"));

                                        if (consultation.getA12().equals("1")) a12.setBackgroundColor(Color.parseColor("#0080FF"));
                                        if (consultation.getB12().equals("1")) b12.setBackgroundColor(Color.parseColor("#0080FF"));
                                        if (consultation.getC12().equals("1")) c12.setBackgroundColor(Color.parseColor("#0080FF"));
                                        if (consultation.getD12().equals("1")) d12.setBackgroundColor(Color.parseColor("#0080FF"));
                                        if (consultation.getE12().equals("1")) e12.setBackgroundColor(Color.parseColor("#0080FF"));
                                        if (consultation.getF12().equals("1")) f12.setBackgroundColor(Color.parseColor("#0080FF"));

                                        if (consultation.getA2().equals("1")) a2.setBackgroundColor(Color.parseColor("#0080FF"));
                                        if (consultation.getB2().equals("1")) b2.setBackgroundColor(Color.parseColor("#0080FF"));
                                        if (consultation.getC2().equals("1")) c2.setBackgroundColor(Color.parseColor("#0080FF"));
                                        if (consultation.getD2().equals("1")) d2.setBackgroundColor(Color.parseColor("#0080FF"));
                                        if (consultation.getE2().equals("1")) e2.setBackgroundColor(Color.parseColor("#0080FF"));
                                        if (consultation.getF2().equals("1")) f2.setBackgroundColor(Color.parseColor("#0080FF"));

                                        if (consultation.getA3().equals("1")) a3.setBackgroundColor(Color.parseColor("#0080FF"));
                                        if (consultation.getB3().equals("1")) b3.setBackgroundColor(Color.parseColor("#0080FF"));
                                        if (consultation.getC3().equals("1")) c3.setBackgroundColor(Color.parseColor("#0080FF"));
                                        if (consultation.getD3().equals("1")) d3.setBackgroundColor(Color.parseColor("#0080FF"));
                                        if (consultation.getE3().equals("1")) e3.setBackgroundColor(Color.parseColor("#0080FF"));
                                        if (consultation.getF3().equals("1")) f3.setBackgroundColor(Color.parseColor("#0080FF"));
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {
                                        Toast.makeText(UserProfileActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } catch (Exception e) {
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(UserProfileActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            }

            vis = true;

            consultation_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!vis) {
                        consultation_table.setVisibility(View.INVISIBLE);
                        vis = true;
                    }
                    else {
                        consultation_table.setVisibility(View.VISIBLE);
                        vis = false;
                    }
                }
            });

            edit_consultation_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(UserProfileActivity.this, EditConsultation.class);
                    startActivity(intent);
                    finish();
                }
            });

            edit_profile_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(UserProfileActivity.this, UpdateInfoActivity.class);
                    Bundle extras = new Bundle();
                    extras.putString("Email", stdnt.getEmail_id());
                    extras.putString("Name", stdnt.getName());
                    extras.putString("Contact", stdnt.getContact_no());
                    extras.putString("Department", stdnt.getDepartment());
                    extras.putString("Student_Id", stdnt.getStudent_id());
                    extras.putString("Student_Tutor", stdnt.getStudent_tutor());
                    intent.putExtras(extras);
                    startActivity(intent);
                    finish();
                }
            });
        } catch (Exception ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
