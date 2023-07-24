package com.Appointment.doctor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DateFormat;
import java.util.Calendar;
import androidx.annotation.NonNull;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class appointmentmanager extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
     EditText selectdate;
     Button add;
     String username,database,date,currentDateString;
     TextView tslot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointmentmanager);
        getSupportActionBar().setTitle("Manage Appointment");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        selectdate=findViewById(R.id.dateselector);
        add=findViewById(R.id.adddate);
        tslot=findViewById(R.id.seetime);
        username=getIntent().getStringExtra("us");
        database=getIntent().getStringExtra("da");
        selectdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });
        DatabaseReference refer = FirebaseDatabase.getInstance().getReference("doc");
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date=selectdate.getEditableText().toString();
                Query check1 = refer.child(username).orderByChild("date").equalTo(date);
                check1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            Toast.makeText(appointmentmanager.this, "Date is already exists", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            datafordoctor da=new datafordoctor(date);
                            refer.child(username).child(date).setValue(da);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {


                    }


                });
            }
        });
        tslot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date=selectdate.getEditableText().toString();
                Query check1 = refer.child(username).orderByChild("date").equalTo(date);
                check1.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            Intent intent=new Intent(appointmentmanager.this,TimeSlots.class);
                            intent.putExtra("name",username);
                            intent.putExtra("date",date);
                            intent.putExtra("db",database);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(appointmentmanager.this, "Date is not exists", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {


                    }


                });
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        currentDateString = DateFormat.getDateInstance(DateFormat.DEFAULT).format(c.getTime());
        selectdate.setText(currentDateString);

    }
}