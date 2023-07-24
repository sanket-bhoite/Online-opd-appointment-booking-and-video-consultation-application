package com.Appointment.doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

public class doctordashboard extends AppCompatActivity {
    Button b1,b2,b3,b4;
    String s,s1,s2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctordashboard);
        getSupportActionBar().setTitle("Doc+pe");
        s=getIntent().getStringExtra("username");
        s1=getIntent().getStringExtra("database");
        s2=getIntent().getStringExtra("password");
        b1=findViewById(R.id.docbtnprofile);
        b2=findViewById(R.id.btnrout);
        b3=findViewById(R.id.btnbook);
        b4=findViewById(R.id.clicktochange);
        ImageSlider imageSlider = findViewById(R.id.slider);

        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.slide1));
        slideModels.add(new SlideModel(R.drawable.slide4));
        slideModels.add(new SlideModel(R.drawable.salute));
        imageSlider.setImageList(slideModels,true);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(doctordashboard.this,doctordata.class);
                i.putExtra("us",s);
                i.putExtra("da",s1);
                i.putExtra("ps",s2);
                startActivity(i);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(doctordashboard.this,appointmentmanager.class);
                i.putExtra("us",s);
                i.putExtra("da",s1);
                i.putExtra("ps",s2);
                startActivity(i);
                
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(doctordashboard.this,myinclinicappointments.class);
                i.putExtra("us",s);
                startActivity(i);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(doctordashboard.this,changepassword.class);
                i.putExtra("username",s);
                i.putExtra("database",s1);
                startActivity(i);
            }
        });
    }
}