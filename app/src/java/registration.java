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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class registration extends AppCompatActivity {

    EditText e1,u1,p1;
    Button b1;
    String email,password;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        e1= findViewById(R.id.el);
        u1=findViewById(R.id.ue);
        p1=findViewById(R.id.pd);
        b1=findViewById(R.id.rb);
        t1=findViewById(R.id.ver);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!validatePassword() | !validateEmail() | !validateUsername()) {
                    return;
                } else {
                    email = e1.getEditableText().toString();
                    password = p1.getEditableText().toString();
                    final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {

                                        Toast.makeText(registration.this, "Please verify Your email-id.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(registration.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
                String email=e1.getEditableText().toString();
                String password=p1.getEditableText().toString();

                if (!validatePassword() | !validateEmail() | !validateUsername())
                {
                    return;
                }
                else
                {
                    firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                        if(task.isSuccessful())
                        {
                            if(firebaseAuth.getCurrentUser().isEmailVerified()){
                                rootNode = FirebaseDatabase.getInstance();
                                reference = rootNode.getReference();
                                String e = e1.getEditableText().toString();
                                String u = u1.getEditableText().toString();
                                String p = p1.getEditableText().toString();
                                String fn="value";
                                String dob="value";
                                String bdgroup="value";
                                String addr="value";
                                String mob="value";
                                DatabaseReference refer= FirebaseDatabase.getInstance().getReference("Patient");
                                Query check1=refer.orderByChild("user").equalTo(u);
                                check1.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if(snapshot.exists())
                                        {
                                            u1.setError("User-Id is already exists");
                                        }
                                        else
                                        {
                                            register r = new register(fn,e,mob,u,p,dob,bdgroup,addr);
                                            refer.child(u).setValue(r);
                                            Toast.makeText(registration.this, "Registration is successfull.", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                            }
                        }
                    });
                }

            }
        });


    }
    public Boolean validateEmail() {
        String val = e1.getEditableText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            e1.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            e1.setError("Invalid email address");
            return false;
        } else {
            e1.setError(null);
            return true;
        }

    }
    public Boolean validateUsername() {
        String val = u1.getEditableText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";

        if (val.isEmpty()) {
            u1.setError("Field cannot be empty");
            return false;
        } else if (val.length() >= 15) {
            u1.setError("Username too long");
            return false;
        } else if (!val.matches(noWhiteSpace)) {
            u1.setError("White Spaces are not allowed");
            return false;
        } else {
            u1.setError(null);
            return true;
        }
    }

    public Boolean validatePassword() {
        String val = p1.getEditableText().toString();
        String passwordVal = "^" +
                "(?=.*[0-9])" +         //at least 1 digit
                "(?=.*[a-z])" +         //at least 1 lower case letter
                "(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";

        if (val.isEmpty()) {
            p1.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            p1.setError("Password is too weak");
            return false;
        } else {
            p1.setError(null);
            return true;
        }
    }
}


