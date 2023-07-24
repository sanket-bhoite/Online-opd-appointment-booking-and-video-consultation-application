package com.Appointment.doctor;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

public class userdash extends AppCompatActivity {
    Button inclinic,video,myappoint,startmeet;
    String str1,pass;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdash);
        getSupportActionBar().setTitle("Doc+pe");
        inclinic=findViewById(R.id.btnprofile);
        video=findViewById(R.id.btnrout);
        myappoint=findViewById(R.id.btnfeedbak);
        startmeet=findViewById(R.id.btnhistory);
        String string=getIntent().getStringExtra("username");
        str1=getIntent().getStringExtra("database");
        pass=getIntent().getStringExtra("password");
        String s1="In-Clinic";
        String s2="video";
        ImageSlider imageSlider = findViewById(R.id.slider);

        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.slide1));
        slideModels.add(new SlideModel(R.drawable.slide3));
        slideModels.add(new SlideModel(R.drawable.slide4));
        slideModels.add(new SlideModel(R.drawable.salute));
        imageSlider.setImageList(slideModels,true);


        inclinic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent intent=new Intent(userdash.this,MainActivity4.class);
                intent.putExtra("dba",str1);
                intent.putExtra("type",s1);
                intent.putExtra("u",string);
                 startActivity(intent);
            }
        });
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(userdash.this,MainActivity4.class);
                intent.putExtra("dba",str1);
                intent.putExtra("type",s2);
                intent.putExtra("u",string);
                startActivity(intent);
            }
        });
        myappoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(userdash.this,usermyappointments.class);
                intent.putExtra("us",string);
                startActivity(intent);
            }
        });
        startmeet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(userdash.this,videomeet.class);
                startActivity(i);
            }
        });

        dl = (DrawerLayout)findViewById(R.id.activity_main);
        t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);
        t.setDrawerIndicatorEnabled(true);

        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView)findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id ==R.id.booking){
                    Intent intent=new Intent(userdash.this,usermyprofile.class);
                    intent.putExtra("username",string);
                    intent.putExtra("password",pass);
                    intent.putExtra("database",str1);
                    startActivity(intent);
                }
                if (id ==R.id.appointment){
                    Intent intent=new Intent(userdash.this,usermyappointments.class);
                    intent.putExtra("us",string);
                    startActivity(intent);
                }
                if (id ==R.id.medical_record){
                    Intent intent=new Intent(userdash.this,changepassword.class);
                    intent.putExtra("username",string);
                    intent.putExtra("password",pass);
                    intent.putExtra("database",str1);
                    startActivity(intent);
                }
                if (id ==R.id.healthplan){
                    Intent intent=new Intent(userdash.this,MainActivity.class);
                    startActivity(intent);
                }

                return true;
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

}