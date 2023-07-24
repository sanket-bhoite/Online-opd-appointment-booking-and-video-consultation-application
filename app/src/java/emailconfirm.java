package com.Appointment.doctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class emailconfirm extends AppCompatActivity {
    EditText email;
    Button v,u;
    String user,p,database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        p=getIntent().getStringExtra("password");
        user=getIntent().getStringExtra("username");
        database=getIntent().getStringExtra("database");
        setContentView(R.layout.activity_emailconfirm);
        getSupportActionBar().setTitle("Update Email");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        email=findViewById(R.id.eaddr);
        v=findViewById(R.id.confirmemailbtn);
        u=findViewById(R.id.updatebtn);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateEmail()) {
                    return;
                } else {
                    String e = email.getEditableText().toString();
                    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                    firebaseAuth.createUserWithEmailAndPassword(e, p).addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {

                                        Toast.makeText(emailconfirm.this, "Please verify Your email-id.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(emailconfirm.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        u.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
                String e=email.getEditableText().toString();
                if (!validateEmail())
                {
                    return;
                }
                else
                {
                    firebaseAuth.signInWithEmailAndPassword(e,p).addOnCompleteListener(task -> {
                        if(task.isSuccessful())
                        {
                            if(firebaseAuth.getCurrentUser().isEmailVerified()){
                                DatabaseReference refer = FirebaseDatabase.getInstance().getReference(database);
                                refer.child(user).child("email").setValue(e);
                                Toast.makeText(emailconfirm.this, "Email is updated", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }

            }
        });
    }
    public Boolean validateEmail() {
        String val = email.getEditableText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            email.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            email.setError("Invalid email address");
            return false;
        } else {
            email.setError(null);
            return true;
        }

    }
}