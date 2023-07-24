package com.Appointment.doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class changepassword extends AppCompatActivity {
    Button b1;
    EditText e;
    String datab,usern;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        datab=getIntent().getStringExtra("database");
        usern=getIntent().getStringExtra("username");
        setContentView(R.layout.activity_changepassword);
        getSupportActionBar().setTitle("Reset Your Password");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        e=findViewById(R.id.chpassword);
        b1=findViewById(R.id.btnchpassword);
        DatabaseReference refer= FirebaseDatabase.getInstance().getReference(datab);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String p=e.getEditableText().toString();
                if (!validatePassword()) {
                    return;
                }
                else
                {
                    refer.child(usern).child("password").setValue(p);
                    Toast.makeText(getApplicationContext(),"Password is succesfully reset",Toast.LENGTH_SHORT).show();
                    e.setText(" ");
                }

            }
        });

    }
    public Boolean validatePassword() {
        String val = e.getEditableText().toString();
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
            e.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            e.setError("Password is too weak");
            return false;
        } else {
            e.setError(null);
            return true;
        }
    }
}