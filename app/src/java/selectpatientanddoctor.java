package com.Appointment.doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class selectpatientanddoctor extends AppCompatActivity {
     TextView t1,t2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectpatientanddoctor);
        t1=findViewById(R.id.textView2);
        t2=findViewById(R.id.textView3);
        String s=getIntent().getStringExtra("name");
        String p="Patient";
        String d="doc";
        if(s.equals("Sign Up")) {
            t1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(selectpatientanddoctor.this, doctorregistration.class);
                    startActivity(i);
                }
            });
            t2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(selectpatientanddoctor.this, registration.class);
                    startActivity(i);
                }
            });
        }
        if(s.equals("Sign In")) {
            t1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(selectpatientanddoctor.this, login.class);
                    i.putExtra("name",d);
                    startActivity(i);
                }
            });
            t2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(selectpatientanddoctor.this, login.class);
                    i.putExtra("name",p);
                    startActivity(i);
                }
            });
        }
    }
}