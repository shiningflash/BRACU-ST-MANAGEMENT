package com.example.bracu_st_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.HashMap;

public class EditConsultation extends AppCompatActivity {
    TextView a8, b8, c8, d8, e8, f8;
    TextView a9, b9, c9, d9, e9, f9;
    TextView a11, b11, c11, d11, e11, f11;
    TextView a12, b12, c12, d12, e12, f12;
    TextView a2, b2, c2, d2, e2, f2;
    TextView a3, b3, c3, d3, e3, f3;

    Button confirm_button, back_button;

    Consultation_ST consultation_st;
    UserSessionManager session;

    DatabaseReference databaseReference;
    DatabaseReference databaseReference2;

    void setConsultation_st(Consultation_ST consultation_st) {
        this.consultation_st = consultation_st;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_consultation);

        try {

            session = new UserSessionManager(getApplicationContext());
            HashMap<String, String> user = session.getUserDetails();
            final String Email = user.get(UserSessionManager.KEY_EMAIL);

            consultation_st = new Consultation_ST();

            final String key = Email.replace(".", "dot");

            databaseReference = FirebaseDatabase.getInstance().getReference().child("Consultation_ST");
            try {
                databaseReference.child(key).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Consultation_ST consultation = dataSnapshot.getValue(Consultation_ST.class);
                        setConsultation_st(consultation);
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
                        Toast.makeText(EditConsultation.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

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

            confirm_button = (Button) findViewById(R.id.confirm_button);
            back_button = (Button) findViewById(R.id.back_button);

            a8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (consultation_st.getA8().equals("0")) {
                        consultation_st.setA8("1");
                        a8.setBackgroundColor(Color.parseColor("#0080FF"));
                    } else {
                        consultation_st.setA8("0");
                        a8.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    }
                }
            });

            b8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (consultation_st.getB8().equals("0")) {
                        consultation_st.setB8("1");
                        b8.setBackgroundColor(Color.parseColor("#0080FF"));
                    } else {
                        b8.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        consultation_st.setB8("0");
                    }
                }
            });

            c8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (consultation_st.getC8().equals("0")) {
                        c8.setBackgroundColor(Color.parseColor("#0080FF"));
                        consultation_st.setC8("1");
                    } else {
                        c8.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        consultation_st.setC8("0");
                    }
                }
            });

            d8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (consultation_st.getD8().equals("0")) {
                        d8.setBackgroundColor(Color.parseColor("#0080FF"));
                        consultation_st.setD8("1");
                    } else {
                        d8.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        consultation_st.setD8("0");
                    }
                }
            });

            e8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (consultation_st.getE8().equals("0")) {
                        e8.setBackgroundColor(Color.parseColor("#0080FF"));
                        consultation_st.setE8("1");
                    } else {
                        e8.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        consultation_st.setE8("0");
                    }
                }
            });

            f8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (consultation_st.getF8().equals("0")) {
                        f8.setBackgroundColor(Color.parseColor("#0080FF"));
                        consultation_st.setF8("1");
                    } else {
                        f8.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        consultation_st.setF8("0");
                    }
                }
            });

            // =========================================================================== //

            a9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (consultation_st.getA9().equals("0")) {
                        a9.setBackgroundColor(Color.parseColor("#0080FF"));
                        consultation_st.setA9("1");
                    } else {
                        a9.setBackgroundColor(Color.parseColor("#E8E9F0"));
                        consultation_st.setA9("0");
                    }
                }
            });

            b9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (consultation_st.getB9().equals("0")) {
                        b9.setBackgroundColor(Color.parseColor("#0080FF"));
                        consultation_st.setB9("1");
                    } else {
                        b9.setBackgroundColor(Color.parseColor("#E8E9F0"));
                        consultation_st.setB9("0");
                    }
                }
            });

            c9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (consultation_st.getC9().equals("0")) {
                        c9.setBackgroundColor(Color.parseColor("#0080FF"));
                        consultation_st.setC9("1");
                    } else {
                        c9.setBackgroundColor(Color.parseColor("#E8E9F0"));
                        consultation_st.setC9("0");
                    }
                }
            });

            d9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (consultation_st.getD9().equals("0")) {
                        d9.setBackgroundColor(Color.parseColor("#0080FF"));
                        consultation_st.setD9("1");
                    } else {
                        d9.setBackgroundColor(Color.parseColor("#E8E9F0"));
                        consultation_st.setD9("0");
                    }
                }
            });

            e9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (consultation_st.getE9().equals("0")) {
                        e9.setBackgroundColor(Color.parseColor("#0080FF"));
                        consultation_st.setE9("1");
                    } else {
                        e9.setBackgroundColor(Color.parseColor("#E8E9F0"));
                        consultation_st.setE9("0");
                    }
                }
            });

            f9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (consultation_st.getF9().equals("0")) {
                        f9.setBackgroundColor(Color.parseColor("#0080FF"));
                        consultation_st.setF9("1");
                    } else {
                        f9.setBackgroundColor(Color.parseColor("#E8E9F0"));
                        consultation_st.setF9("0");
                    }
                }
            });

            // ====================================================================================== //

            a11.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (consultation_st.getA11().equals("0")) {
                        consultation_st.setA11("1");
                        a11.setBackgroundColor(Color.parseColor("#0080FF"));
                    } else {
                        consultation_st.setA11("0");
                        a11.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    }
                }
            });

            b11.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (consultation_st.getB11().equals("0")) {
                        consultation_st.setB11("1");
                        b11.setBackgroundColor(Color.parseColor("#0080FF"));
                    } else {
                        b11.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        consultation_st.setB11("0");
                    }
                }
            });

            c11.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (consultation_st.getC11().equals("0")) {
                        c11.setBackgroundColor(Color.parseColor("#0080FF"));
                        consultation_st.setC11("1");
                    } else {
                        c11.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        consultation_st.setC11("0");
                    }
                }
            });

            d11.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (consultation_st.getD11().equals("0")) {
                        d11.setBackgroundColor(Color.parseColor("#0080FF"));
                        consultation_st.setD11("1");
                    } else {
                        d11.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        consultation_st.setD11("0");
                    }
                }
            });

            e11.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (consultation_st.getE11().equals("0")) {
                        e11.setBackgroundColor(Color.parseColor("#0080FF"));
                        consultation_st.setE11("1");
                    } else {
                        e11.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        consultation_st.setE11("0");
                    }
                }
            });

            f11.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (consultation_st.getF11().equals("0")) {
                        f11.setBackgroundColor(Color.parseColor("#0080FF"));
                        consultation_st.setF11("1");
                    } else {
                        f11.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        consultation_st.setF11("0");
                    }
                }
            });

            // ========================================================================== //

            a12.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (consultation_st.getA12().equals("0")) {
                        a12.setBackgroundColor(Color.parseColor("#0080FF"));
                        consultation_st.setA12("1");
                    } else {
                        a12.setBackgroundColor(Color.parseColor("#E8E9F0"));
                        consultation_st.setA12("0");
                    }
                }
            });

            b12.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (consultation_st.getB12().equals("0")) {
                        b12.setBackgroundColor(Color.parseColor("#0080FF"));
                        consultation_st.setB12("1");
                    } else {
                        b12.setBackgroundColor(Color.parseColor("#E8E9F0"));
                        consultation_st.setB12("0");
                    }
                }
            });

            c12.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (consultation_st.getC12().equals("0")) {
                        c12.setBackgroundColor(Color.parseColor("#0080FF"));
                        consultation_st.setC12("1");
                    } else {
                        c12.setBackgroundColor(Color.parseColor("#E8E9F0"));
                        consultation_st.setC12("0");
                    }
                }
            });

            d12.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (consultation_st.getD12().equals("0")) {
                        d12.setBackgroundColor(Color.parseColor("#0080FF"));
                        consultation_st.setD12("1");
                    } else {
                        d12.setBackgroundColor(Color.parseColor("#E8E9F0"));
                        consultation_st.setD12("0");
                    }
                }
            });
            e12.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (consultation_st.getE12().equals("0")) {
                        e12.setBackgroundColor(Color.parseColor("#0080FF"));
                        consultation_st.setE12("1");
                    } else {
                        e12.setBackgroundColor(Color.parseColor("#E8E9F0"));
                        consultation_st.setE12("0");
                    }
                }
            });

            f12.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (consultation_st.getF12().equals("0")) {
                        f12.setBackgroundColor(Color.parseColor("#0080FF"));
                        consultation_st.setF12("1");
                    } else {
                        f12.setBackgroundColor(Color.parseColor("#E8E9F0"));
                        consultation_st.setF12("0");
                    }
                }
            });

            // ======================================================================================= //

            a2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (consultation_st.getA2().equals("0")) {
                        consultation_st.setA2("1");
                        a2.setBackgroundColor(Color.parseColor("#0080FF"));
                    } else {
                        consultation_st.setA2("0");
                        a2.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    }
                }
            });

            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (consultation_st.getB2().equals("0")) {
                        consultation_st.setB2("1");
                        b2.setBackgroundColor(Color.parseColor("#0080FF"));
                    } else {
                        b2.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        consultation_st.setB2("0");
                    }
                }
            });

            c2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (consultation_st.getC2().equals("0")) {
                        c2.setBackgroundColor(Color.parseColor("#0080FF"));
                        consultation_st.setC2("1");
                    } else {
                        c2.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        consultation_st.setC2("0");
                    }
                }
            });

            d2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (consultation_st.getD2().equals("0")) {
                        d2.setBackgroundColor(Color.parseColor("#0080FF"));
                        consultation_st.setD2("1");
                    } else {
                        d2.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        consultation_st.setD2("0");
                    }
                }
            });

            e2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (consultation_st.getE2().equals("0")) {
                        e2.setBackgroundColor(Color.parseColor("#0080FF"));
                        consultation_st.setE2("1");
                    } else {
                        e2.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        consultation_st.setE2("0");
                    }
                }
            });

            f2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (consultation_st.getF2().equals("0")) {
                        f2.setBackgroundColor(Color.parseColor("#0080FF"));
                        consultation_st.setF2("1");
                    } else {
                        f2.setBackgroundColor(Color.parseColor("#FFFFFF"));
                        consultation_st.setF2("0");
                    }
                }
            });

            // =========================================================================== //

            a3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (consultation_st.getA3().equals("0")) {
                        a3.setBackgroundColor(Color.parseColor("#0080FF"));
                        consultation_st.setA3("1");
                    } else {
                        a3.setBackgroundColor(Color.parseColor("#E8E9F0"));
                        consultation_st.setA3("0");
                    }
                }
            });

            b3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (consultation_st.getB3().equals("0")) {
                        b3.setBackgroundColor(Color.parseColor("#0080FF"));
                        consultation_st.setB3("1");
                    } else {
                        b3.setBackgroundColor(Color.parseColor("#E8E9F0"));
                        consultation_st.setB3("0");
                    }
                }
            });

            c3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (consultation_st.getC3().equals("0")) {
                        c3.setBackgroundColor(Color.parseColor("#0080FF"));
                        consultation_st.setC3("1");
                    } else {
                        c3.setBackgroundColor(Color.parseColor("#E8E9F0"));
                        consultation_st.setC3("0");
                    }
                }
            });

            d3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (consultation_st.getD3().equals("0")) {
                        d3.setBackgroundColor(Color.parseColor("#0080FF"));
                        consultation_st.setD3("1");
                    } else {
                        d3.setBackgroundColor(Color.parseColor("#E8E9F0"));
                        consultation_st.setD3("0");
                    }
                }
            });

            e3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (consultation_st.getE3().equals("0")) {
                        e3.setBackgroundColor(Color.parseColor("#0080FF"));
                        consultation_st.setE3("1");
                    } else {
                        e3.setBackgroundColor(Color.parseColor("#E8E9F0"));
                        consultation_st.setE3("0");
                    }
                }
            });

            f3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (consultation_st.getF3().equals("0")) {
                        f3.setBackgroundColor(Color.parseColor("#0080FF"));
                        consultation_st.setF3("1");
                    } else {
                        f3.setBackgroundColor(Color.parseColor("#E8E9F0"));
                        consultation_st.setF3("0");
                    }
                }
            });

            // ====================================================================================== //

            try {
                confirm_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        databaseReference2 = FirebaseDatabase.getInstance().getReference().child("Consultation_ST");
                        databaseReference2.child(key).setValue(consultation_st).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task1) {
                                if (task1.isSuccessful()) {
                                    Toast.makeText(EditConsultation.this, "Successfully updated", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(EditConsultation.this, task1.getException().toString(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                        Intent intent = new Intent(EditConsultation.this, UserProfileActivity.class);
                        intent.putExtra("Email", Email);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                });
            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            back_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(EditConsultation.this, UserProfileActivity.class);
                    intent.putExtra("Email", Email);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
            });
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
