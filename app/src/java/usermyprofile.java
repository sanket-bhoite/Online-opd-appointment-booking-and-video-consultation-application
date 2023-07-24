package com.Appointment.doctor;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
public class usermyprofile extends AppCompatActivity {
     EditText t1,t2,t3,t4,t5,t7;
     String str1,str2,str3;
     Button save;
    DatabaseReference refer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        str1=getIntent().getStringExtra("username");
        str2=getIntent().getStringExtra("password");
        str3=getIntent().getStringExtra("database");
        setContentView(R.layout.activity_usermyprofile);
        getSupportActionBar().setTitle("Myprofile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        refer= FirebaseDatabase.getInstance().getReference("Patient");
        t1=findViewById(R.id.infopatientfm);
        t2=findViewById(R.id.infopatientregisteremail);
        t3=findViewById(R.id.infopatientregisterphone);
        t4=findViewById(R.id.infopatientbloodid);
        t5=findViewById(R.id.infodob);
        t7=findViewById(R.id.infopatientaddress);
        save=findViewById(R.id.infopatientrg);
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(usermyprofile.this,emailconfirm.class);
                i.putExtra("username",str1);
                i.putExtra("password",str2);
                i.putExtra("database",str3);
                startActivity(i);
            }
        });
        t7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(usermyprofile.this,placepicker.class);
                i.putExtra("username",str1);
                i.putExtra("password",str2);
                i.putExtra("database",str3);
                startActivity(i);
            }
        });
        t5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(usermyprofile.this,datepicker.class);
                i.putExtra("username",str1);
                i.putExtra("password",str2);
                i.putExtra("database",str3);
                startActivity(i);
            }
        });
        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(usermyprofile.this,MainActivity2.class);
                i.putExtra("username",str1);
                i.putExtra("password",str2);
                i.putExtra("database",str3);
                startActivity(i);
            }
        });
        Query check1=refer.orderByChild("user").equalTo(str1);
        check1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String fn=snapshot.child(str1).child("fname").getValue(String.class);
                    if (fn.equals("value")) {

                    } else {
                        t1.setText(fn);
                    }

                   String em=snapshot.child(str1).child("email").getValue(String.class);
                    t2.setText(em);

                    String phone=snapshot.child(str1).child("mobile").getValue(String.class);
                    if (phone.equals("value")) {

                    } else {
                        t3.setText(phone);
                    }
                    String bl=snapshot.child(str1).child("bloodgroup").getValue(String.class);
                    if (bl.equals("value")) {

                    } else {
                        t4.setText(bl);
                    }
                   String db=snapshot.child(str1).child("dob").getValue(String.class);
                    if (db.equals("value")) {

                    } else {
                        t5.setText(db);
                    }

                    String ad=snapshot.child(str1).child("address").getValue(String.class);
                    if (ad.equals("value")) {

                    } else {
                        t7.setText(ad);
                    }


                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                refer.child(str1).child("fname").setValue(t1.getEditableText().toString());
                refer.child(str1).child("email").setValue(t2.getEditableText().toString());
                refer.child(str1).child("mobile").setValue(t3.getEditableText().toString());
                refer.child(str1).child("bloodgroup").setValue(t4.getEditableText().toString());
                refer.child(str1).child("dob").setValue(t5.getEditableText().toString());
                refer.child(str1).child("address").setValue(t7.getEditableText().toString());
                Toast.makeText(usermyprofile.this, "Profile Updated Successfully", Toast.LENGTH_SHORT).show();

            }
        });




    }
}