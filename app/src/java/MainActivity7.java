package com.Appointment.doctor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import androidx.annotation.NonNull;

public class MainActivity7 extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    TextView t1,t2,t3,t4;
    TextView textView;
    Button button2;
    String patientuser,spec;
    String u,databaseop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        getSupportActionBar().setTitle("Book Appointment");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        t1=findViewById(R.id.nameid);
        t2=findViewById(R.id.hospitalid);
        t3=findViewById(R.id.qualifyid);
        t4=findViewById(R.id.special1);
        t1.setText(getIntent().getStringExtra("fulname"));
        u=getIntent().getStringExtra("name");
        t2.setText(getIntent().getStringExtra("hospital"));
        t3.setText(getIntent().getStringExtra("qualification"));
        patientuser=getIntent().getStringExtra("patientdata");
        spec=getIntent().getStringExtra("special");
        t4.setText(spec);
        databaseop="Patient";
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");

            }
        });
        display1();




    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.DEFAULT).format(c.getTime());
        textView=findViewById(R.id.textView);
        textView.setText(currentDateString);


    }
    public void display()
    {
        Intent intent=new Intent(this,TimeSlots.class);
        intent.putExtra("date",textView.getText());
        intent.putExtra("name",u);
        intent.putExtra("db",databaseop);
        intent.putExtra("pauser",patientuser);
        startActivity(intent);
    }
    public void display1()
    {
        button2=findViewById(R.id.btproceed);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a=textView.getText().toString();
                DatabaseReference refer = FirebaseDatabase.getInstance().getReference("doc");
                Query check1 = refer.orderByChild("user").equalTo(u);
                check1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            Query check2=refer.child(u).orderByChild("date").equalTo(a);
                            check2.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if(snapshot.exists())
                                    {
                                        display();
                                    }
                                    else
                                    {
                                        textView.setText("sorry");
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }


                        else {

                            textView.setText("database error");

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {


                    }


                });
            }
        });

    }


}