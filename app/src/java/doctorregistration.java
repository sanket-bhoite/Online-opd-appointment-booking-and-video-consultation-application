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

public class doctorregistration extends AppCompatActivity {
    EditText name,emailid,lc,user,pass;
    Button sp;
    TextView t1;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctorregistration);
        name=findViewById(R.id.docfm);
        emailid=findViewById(R.id.docregisteremail);
        lc=findViewById(R.id.docls);
        user=findViewById(R.id.docsigninuser);
        pass=findViewById(R.id.docsigninpass);
        sp=findViewById(R.id.docrg);
        t1=findViewById(R.id.drver);
        sp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validatePassword() | !validateEmail() | !validateUsername() | !fullname() | !licensenumber()) {
                    return;
                } else {
                    String email = emailid.getEditableText().toString();
                    String password = pass.getEditableText().toString();
                    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {

                                        Toast.makeText(doctorregistration.this, "Please verify Your email-id.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(doctorregistration.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
                String email=emailid.getEditableText().toString();
                String password=pass.getEditableText().toString();

                if (!validatePassword() | !validateEmail() | !validateUsername() | !fullname() | !licensenumber())
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
                                String n = name.getEditableText().toString();
                                String e = emailid.getEditableText().toString();
                                String l = lc.getEditableText().toString();
                                String u = user.getEditableText().toString();
                                String p = pass.getEditableText().toString();
                                String mob = "value";
                                String qualification = "value";
                                String clinic = "value";
                                String special = "value";
                                String dept = "value";
                                String address = "value";
                                String UpiId = "value";
                                String Amt = "value";
                                String category = "value";
                                DatabaseReference refer= FirebaseDatabase.getInstance().getReference("doc");
                                Query check1=refer.orderByChild("user").equalTo(u);
                                check1.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if(snapshot.exists())
                                        {
                                            user.setError("User-Id is already exists");
                                            }
                                            else
                                            {
                                                docregister dr = new docregister(n, e, l, u, p, mob, qualification, clinic, special, dept, address, UpiId, Amt, category);
                                                reference.child("doc").child(u).setValue(dr);
                                                Toast.makeText(doctorregistration.this, "Registration Successfull", Toast.LENGTH_SHORT).show();

                                            }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                            }
                            else
                            {
                                Toast.makeText(doctorregistration.this, "Please verify your email address first", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });

    }
    public Boolean validateEmail() {
        String val = emailid.getEditableText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            emailid.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            emailid.setError("Invalid email address");
            return false;
        } else {
            emailid.setError(null);
            return true;
        }

    }
    public Boolean validateUsername() {
        String val = user.getEditableText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";

        if (val.isEmpty()) {
            user.setError("Field cannot be empty");
            return false;
        } else if (val.length() >= 15) {
            user.setError("Username too long");
            return false;
        } else if (!val.matches(noWhiteSpace)) {
            user.setError("White Spaces are not allowed");
            return false;
        } else {
            user.setError(null);
            return true;
        }
    }
    String passwordVal = "^" +
            "(?=.*[0-9])" +         //at least 1 digit
            "(?=.*[a-z])" +         //at least 1 lower case letter
            "(?=.*[A-Z])" +         //at least 1 upper case letter
            "(?=.*[a-zA-Z])" +      //any letter
            "(?=.*[@#$%^&+=])" +    //at least 1 special character
            "(?=\\S+$)" +           //no white spaces
            ".{4,}" +               //at least 4 characters
            "$";

    public Boolean validatePassword() {
        String val = pass.getEditableText().toString();
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
            pass.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            pass.setError("Password is too weak");
            return false;
        } else {
            pass.setError(null);
            return true;
        }
    }
    public Boolean fullname() {
        String val = name.getEditableText().toString();
        if (val.isEmpty()) {
            name.setError("Field cannot be empty");
            return false;
        } else {
            name.setError(null);
            return true;
        }
    }
    public Boolean licensenumber() {
        String val = lc.getEditableText().toString();
        if (val.isEmpty()) {
            lc.setError("Field cannot be empty");
            return false;
        } else {
            lc.setError(null);
            return true;
        }
    }
}