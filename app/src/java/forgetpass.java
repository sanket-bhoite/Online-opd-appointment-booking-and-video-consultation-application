package com.Appointment.doctor;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class forgetpass extends AppCompatActivity {
    String db;
    EditText e1,e2;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db=getIntent().getStringExtra("database");
        setContentView(R.layout.activity_forgetpass);
        getSupportActionBar().setTitle("Forget Password");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        e1=findViewById(R.id.forgetuser);
        e2=findViewById(R.id.forgetemail);
        b=findViewById(R.id.forgetproceed);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1=e1.getEditableText().toString();
                String s2=e2.getEditableText().toString();
                if (!validateEmail() | !validateUsername())
                {
                    return;
                }
                else
                {
                    DatabaseReference refer= FirebaseDatabase.getInstance().getReference(db);
                    Query check1=refer.orderByChild("user").equalTo(s1);
                    check1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists())
                            {
                                String passwordFromDB;
                                passwordFromDB = snapshot.child(s1).child("email").getValue(String.class);
                                if(passwordFromDB.equals(s2))
                                {
                                            Intent i=new Intent(forgetpass.this,forget2.class);
                                            i.putExtra("username",s1);
                                            i.putExtra("database",db);
                                            startActivity(i);
                                }
                                else
                                {

                                    Toast.makeText(getApplicationContext(),"Email-id is not exists",Toast.LENGTH_SHORT).show();
                                }
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"username is not exists",Toast.LENGTH_SHORT).show();
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
    public Boolean validateEmail() {
        String val = e2.getEditableText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            e2.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            e2.setError("Invalid email address");
            return false;
        } else {
            e2.setError(null);
            return true;
        }

    }
    public Boolean validateUsername() {
        String val = e1.getEditableText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";

        if (val.isEmpty()) {
            e1.setError("Field cannot be empty");
            return false;
        } else if (val.length() >= 15) {
            e1.setError("Username too long");
            return false;
        } else if (!val.matches(noWhiteSpace)) {
            e1.setError("White Spaces are not allowed");
            return false;
        } else {
            e1.setError(null);
            return true;
        }
    }

}