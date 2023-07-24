package com.Appointment.doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {
    EditText user1,pass1;
    Button login;
    String ur1;
    TextView fg,dont;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user1=findViewById(R.id.signinuser);
        pass1=findViewById(R.id.signinpass);
        login=findViewById(R.id.signlogin);
        fg=findViewById(R.id.frgp);
        dont=findViewById(R.id.dnt);
        ur1=getIntent().getStringExtra("name");
        dont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(login.this,MainActivity.class);
                startActivity(i);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!validatePassword() | !validateUsername())
                {
                    return;
                }
                else {
                    DatabaseReference refer = FirebaseDatabase.getInstance().getReference(ur1);
                    String s1 = user1.getEditableText().toString();
                    String s2 = pass1.getEditableText().toString();
                    Query check1 = refer.orderByChild("user").equalTo(s1);
                    final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                    check1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                String passwordFromDB;
                                passwordFromDB = snapshot.child(s1).child("password").getValue(String.class);
                                if (passwordFromDB.equals(s2)) {
                                    if (ur1.equals("Patient")) {
                                        Intent i = new Intent(login.this, userdash.class);
                                        i.putExtra("database", ur1);
                                        i.putExtra("username", s1);
                                        i.putExtra("password", s2);
                                        user1.setText(" ");
                                        pass1.setText(" ");
                                        startActivity(i);
                                    } else {
                                        Intent i = new Intent(login.this, doctordashboard.class);
                                        i.putExtra("database", ur1);
                                        i.putExtra("username", s1);
                                        i.putExtra("password", s2);
                                        user1.setText(" ");
                                        pass1.setText(" ");
                                        startActivity(i);
                                    }
                                } else {

                                    Toast.makeText(getApplicationContext(), "Wrong Password", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Wrong username", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });

        fg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(login.this,forgetpass.class);
                i.putExtra("database",ur1);
                startActivity(i);
            }
        });


    }
    public Boolean validateUsername() {
        String val = user1.getEditableText().toString();

        if (val.isEmpty()) {
            user1.setError("Field cannot be empty");
            return false;
        } else {
            user1.setError(null);
            return true;
        }
    }
    public Boolean validatePassword() {
        String val = pass1.getEditableText().toString();
        if (val.isEmpty()) {
            pass1.setError("Field cannot be empty");
            return false;
        } else {
            pass1.setError(null);
            return true;
        }
    }

}