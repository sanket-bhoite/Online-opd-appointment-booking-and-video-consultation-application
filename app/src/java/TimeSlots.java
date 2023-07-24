package com.Appointment.doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import androidx.annotation.NonNull;

public class TimeSlots extends AppCompatActivity {
    TextView block;
    Button button1,button2,button3,button4,button5,button6,button7,button8,button9,button10,button11,button12,button13,button14;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_slots);
        getSupportActionBar().setTitle("Book Appointment");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        button1=findViewById(R.id.bt1);
        block=findViewById(R.id.txt2);
        String d=getIntent().getStringExtra("date");
        String n=getIntent().getStringExtra("name");
        String strdb=getIntent().getStringExtra("db");
        String patientusername=getIntent().getStringExtra("pauser");
        String str2=button1.getText().toString();
        DatabaseReference refer = FirebaseDatabase.getInstance().getReference("doc");
        Query check1 = refer.child(n).child(d).orderByChild("time").equalTo(str2);
        check1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    button1.setBackgroundColor(Color.RED);
                    button1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(TimeSlots.this, "Not available", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                else {

                    button1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            timezone tm=new timezone(str2);
                            if(strdb.equals("Patient"))
                            {
                                Intent i=new Intent(TimeSlots.this,userappointmentdetails.class);
                                i.putExtra("date",d);
                                i.putExtra("time",str2);
                                i.putExtra("uname",n);
                                i.putExtra("patientusernamedata",patientusername);
                                startActivity(i);

                            }
                             else {
                                refer.child(n).child(d).child(str2).setValue(tm);
                                button1.setBackgroundColor(Color.GREEN);
                                Toast.makeText(TimeSlots.this, "Appointment is booked", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        button2=findViewById(R.id.bt2);
        String str3=button2.getText().toString();
        Query check3 = refer.child(n).child(d).orderByChild("time").equalTo(str3);
        check3.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    button2.setBackgroundColor(Color.RED);
                    button2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Toast.makeText(TimeSlots.this, "Not available", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                else {

                    button2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            timezone tm=new timezone(str3);
                            if(strdb.equals("Patient"))
                            {
                                Intent i=new Intent(TimeSlots.this,userappointmentdetails.class);
                                i.putExtra("date",d);
                                i.putExtra("time",str3);
                                i.putExtra("uname",n);
                                i.putExtra("patientusernamedata",patientusername);
                                startActivity(i);

                            }
                            else {
                                refer.child(n).child(d).child(str3).setValue(tm);
                                button2.setBackgroundColor(Color.GREEN);
                                Toast.makeText(TimeSlots.this, "Appointment is booked", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        button3=findViewById(R.id.bt3);
        String str4=button3.getText().toString();
        Query check4 = refer.child(n).child(d).orderByChild("time").equalTo(str4);
        check4.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    button3.setBackgroundColor(Color.RED);
                    button3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Toast.makeText(TimeSlots.this, "Not available", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                else {

                    button3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            timezone tm=new timezone(str4);
                            if(strdb.equals("Patient"))
                            {
                                Intent i=new Intent(TimeSlots.this,userappointmentdetails.class);
                                i.putExtra("date",d);
                                i.putExtra("time",str4);
                                i.putExtra("uname",n);
                                i.putExtra("patientusernamedata",patientusername);
                                startActivity(i);

                            }
                            else {
                                refer.child(n).child(d).child(str4).setValue(tm);
                                button3.setBackgroundColor(Color.GREEN);
                                Toast.makeText(TimeSlots.this, "Appointment is booked", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        button4=findViewById(R.id.bt4);
        String str5=button4.getText().toString();
        Query check5 = refer.child(n).child(d).orderByChild("time").equalTo(str5);
        check5.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    button4.setBackgroundColor(Color.RED);
                    button4.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {

                            Toast.makeText(TimeSlots.this, "Not available", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                else {

                    button4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            timezone tm=new timezone(str5);
                            if(strdb.equals("Patient"))
                            {
                                Intent i=new Intent(TimeSlots.this,userappointmentdetails.class);
                                i.putExtra("date",d);
                                i.putExtra("time",str5);
                                i.putExtra("uname",n);
                                i.putExtra("patientusernamedata",patientusername);
                                startActivity(i);

                            }
                            else {
                                refer.child(n).child(d).child(str5).setValue(tm);
                                button4.setBackgroundColor(Color.GREEN);
                                Toast.makeText(TimeSlots.this, "Appointment is booked", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        button5=findViewById(R.id.bt5);
        String str6=button5.getText().toString();
        Query check6 = refer.child(n).child(d).orderByChild("time").equalTo(str6);
        check6.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    button5.setBackgroundColor(Color.RED);
                    button5.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Toast.makeText(TimeSlots.this, "Not available", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                else {

                    button5.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            timezone tm=new timezone(str6);
                            if(strdb.equals("Patient"))
                            {
                                Intent i=new Intent(TimeSlots.this,userappointmentdetails.class);
                                i.putExtra("date",d);
                                i.putExtra("time",str6);
                                i.putExtra("uname",n);
                                i.putExtra("patientusernamedata",patientusername);
                                startActivity(i);

                            }
                            else {
                                refer.child(n).child(d).child(str6).setValue(tm);
                                button5.setBackgroundColor(Color.GREEN);
                                Toast.makeText(TimeSlots.this, "Appointment is booked", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        button6=findViewById(R.id.bt6);
        String str7=button6.getText().toString();
        Query check7 = refer.child(n).child(d).orderByChild("time").equalTo(str7);
        check7.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    button6.setBackgroundColor(Color.RED);
                    button6.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Toast.makeText(TimeSlots.this, "Not available", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                else {

                    button6.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            timezone tm=new timezone(str7);
                            if(strdb.equals("Patient"))
                            {
                                Intent i=new Intent(TimeSlots.this,userappointmentdetails.class);
                                i.putExtra("date",d);
                                i.putExtra("time",str7);
                                i.putExtra("uname",n);
                                i.putExtra("patientusernamedata",patientusername);
                                startActivity(i);

                            }
                            else {
                                refer.child(n).child(d).child(str7).setValue(tm);
                                button6.setBackgroundColor(Color.GREEN);
                                Toast.makeText(TimeSlots.this, "Appointment is booked", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        button7=findViewById(R.id.bt7);
        String str8=button7.getText().toString();
        Query check8 = refer.child(n).child(d).orderByChild("time").equalTo(str8);
        check8.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    button7.setBackgroundColor(Color.RED);
                    button7.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Toast.makeText(TimeSlots.this, "Not available", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                else {

                    button7.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            timezone tm=new timezone(str8);
                            if(strdb.equals("Patient"))
                            {
                                Intent i=new Intent(TimeSlots.this,userappointmentdetails.class);
                                i.putExtra("date",d);
                                i.putExtra("time",str8);
                                i.putExtra("uname",n);
                                i.putExtra("patientusernamedata",patientusername);
                                startActivity(i);

                            }
                            else {
                                refer.child(n).child(d).child(str8).setValue(tm);
                                button7.setBackgroundColor(Color.GREEN);
                                Toast.makeText(TimeSlots.this, "Appointment is booked", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        button8=findViewById(R.id.bt8);
        String str9=button8.getText().toString();
        Query check9 = refer.child(n).child(d).orderByChild("time").equalTo(str9);
        check9.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    button8.setBackgroundColor(Color.RED);
                    button8.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Toast.makeText(TimeSlots.this, "Not available", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                else {

                    button8.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            timezone tm=new timezone(str9);
                            if(strdb.equals("Patient"))
                            {
                                Intent i=new Intent(TimeSlots.this,userappointmentdetails.class);
                                i.putExtra("date",d);
                                i.putExtra("time",str9);
                                i.putExtra("uname",n);
                                i.putExtra("patientusernamedata",patientusername);
                                startActivity(i);

                            }
                            else {
                                refer.child(n).child(d).child(str9).setValue(tm);
                                button8.setBackgroundColor(Color.GREEN);
                                Toast.makeText(TimeSlots.this, "Appointment is booked", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        button9=findViewById(R.id.bt9);
        String str10=button9.getText().toString();
        Query check10 = refer.child(n).child(d).orderByChild("time").equalTo(str10);
        check10.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    button9.setBackgroundColor(Color.RED);
                    button9.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Toast.makeText(TimeSlots.this, "Not available", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                else {

                    button9.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            timezone tm=new timezone(str10);
                            if(strdb.equals("Patient"))
                            {
                                Intent i=new Intent(TimeSlots.this,userappointmentdetails.class);
                                i.putExtra("date",d);
                                i.putExtra("time",str10);
                                i.putExtra("uname",n);
                                i.putExtra("patientusernamedata",patientusername);
                                startActivity(i);

                            }
                            else {
                                refer.child(n).child(d).child(str10).setValue(tm);
                                button9.setBackgroundColor(Color.GREEN);
                                Toast.makeText(TimeSlots.this, "Appointment is booked", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        button10=findViewById(R.id.bt10);
        String str11=button10.getText().toString();
        Query check11 = refer.child(n).child(d).orderByChild("time").equalTo(str11);
        check11.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    button10.setBackgroundColor(Color.RED);
                    button10.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Toast.makeText(TimeSlots.this, "Not available", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                else {

                    button10.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            timezone tm=new timezone(str11);
                            if(strdb.equals("Patient"))
                            {
                                Intent i=new Intent(TimeSlots.this,userappointmentdetails.class);
                                i.putExtra("date",d);
                                i.putExtra("time",str11);
                                i.putExtra("uname",n);
                                i.putExtra("patientusernamedata",patientusername);
                                startActivity(i);

                            }
                            else {
                                refer.child(n).child(d).child(str11).setValue(tm);
                                button10.setBackgroundColor(Color.GREEN);
                                Toast.makeText(TimeSlots.this, "Appointment is booked", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        button11=findViewById(R.id.bt11);
        String str12=button11.getText().toString();
        Query check12 = refer.child(n).child(d).orderByChild("time").equalTo(str12);
        check12.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    button11.setBackgroundColor(Color.RED);
                    button11.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Toast.makeText(TimeSlots.this, "Not available", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                else {

                    button11.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            timezone tm=new timezone(str12);
                            if(strdb.equals("Patient"))
                            {
                                Intent i=new Intent(TimeSlots.this,userappointmentdetails.class);
                                i.putExtra("date",d);
                                i.putExtra("time",str12);
                                i.putExtra("uname",n);
                                i.putExtra("patientusernamedata",patientusername);
                                startActivity(i);

                            }
                            else {
                                refer.child(n).child(d).child(str12).setValue(tm);
                                button11.setBackgroundColor(Color.GREEN);
                                Toast.makeText(TimeSlots.this, "Appointment is booked", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        button12=findViewById(R.id.bt12);
        String str13=button12.getText().toString();
        Query check13 = refer.child(n).child(d).orderByChild("time").equalTo(str13);
        check13.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    button12.setBackgroundColor(Color.RED);
                    button12.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Toast.makeText(TimeSlots.this, "Not available", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                else {

                    button12.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            timezone tm=new timezone(str13);
                            if(strdb.equals("Patient"))
                            {
                                Intent i=new Intent(TimeSlots.this,userappointmentdetails.class);
                                i.putExtra("date",d);
                                i.putExtra("time",str13);
                                i.putExtra("uname",n);
                                i.putExtra("patientusernamedata",patientusername);
                                startActivity(i);

                            }
                            else
                            {
                            refer.child(n).child(d).child(str13).setValue(tm);
                            button12.setBackgroundColor(Color.GREEN);
                            Toast.makeText(TimeSlots.this, "Appointment is booked", Toast.LENGTH_SHORT).show();
}                       }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        button13=findViewById(R.id.bt13);
        String str14=button13.getText().toString();
        Query check14 = refer.child(n).child(d).orderByChild("time").equalTo(str14);
        check14.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    button13.setBackgroundColor(Color.RED);
                    button13.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Toast.makeText(TimeSlots.this, "Not available", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                else {

                    button13.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            timezone tm=new timezone(str14);
                            if(strdb.equals("Patient"))
                            {
                                Intent i=new Intent(TimeSlots.this,userappointmentdetails.class);
                                i.putExtra("date",d);
                                i.putExtra("time",str14);
                                i.putExtra("uname",n);
                                i.putExtra("patientusernamedata",patientusername);
                                startActivity(i);

                            }
                            else {
                                refer.child(n).child(d).child(str14).setValue(tm);
                                button13.setBackgroundColor(Color.GREEN);
                                Toast.makeText(TimeSlots.this, "Appointment is booked", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        button14=findViewById(R.id.bt14);
        String str15=button14.getText().toString();
        Query check15 = refer.child(n).child(d).orderByChild("time").equalTo(str15);
        check15.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    button14.setBackgroundColor(Color.RED);
                    button14.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Toast.makeText(TimeSlots.this, "Not available", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
                else {

                    button14.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            timezone tm=new timezone(str15);
                            if(strdb.equals("Patient"))
                            {
                                Intent i=new Intent(TimeSlots.this,userappointmentdetails.class);
                                i.putExtra("date",d);
                                i.putExtra("time",str15);
                                i.putExtra("uname",n);
                                i.putExtra("patientusernamedata",patientusername);
                                startActivity(i);

                            }
                            else {
                                refer.child(n).child(d).child(str15).setValue(tm);
                                button14.setBackgroundColor(Color.GREEN);
                                Toast.makeText(TimeSlots.this, "Appointment is booked", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}
