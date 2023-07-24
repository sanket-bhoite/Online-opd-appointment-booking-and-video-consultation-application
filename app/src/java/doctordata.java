package com.Appointment.doctor;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class doctordata extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5,e6,e7,e8,e9,e10,e11;
    Button save;
    String str1,str2,str3;
    RadioButton rd;
    RadioGroup radioGroup;
    String cat;
    DatabaseReference refer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        str1=getIntent().getStringExtra("us");
        str2=getIntent().getStringExtra("da");
        str3=getIntent().getStringExtra("ps");
        setContentView(R.layout.activity_doctordata);
        getSupportActionBar().setTitle("Myprofile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        refer=FirebaseDatabase.getInstance().getReference(str2);
        e1=findViewById(R.id.infodocfm);
        e2=findViewById(R.id.infodocregisteremail);
        e3=findViewById(R.id.infodocregisterphone);
        e4=findViewById(R.id.infoqualificationid);
        e5=findViewById(R.id.infofm);
        e6=findViewById(R.id.infosp);
        e7=findViewById(R.id.infodocls);
        e8=findViewById(R.id.infosigninpass);
        e9=findViewById(R.id.infolocation);
        e11=findViewById(R.id.infoamt);
        save=findViewById(R.id.infodocrg);
        radioGroup=findViewById(R.id.radiocategory);

        e2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(doctordata.this,emailconfirm.class);
                i.putExtra("username",str1);
                i.putExtra("password",str3);
                i.putExtra("database",str2);
                startActivity(i);
            }
        });
        e9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(doctordata.this,placepicker.class);
                i.putExtra("username",str1);
                i.putExtra("password",str3);
                i.putExtra("database",str2);
                startActivity(i);
            }
        });
        e3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(doctordata.this,MainActivity2.class);
                i.putExtra("username",str1);
                i.putExtra("password",str3);
                i.putExtra("database",str2);
                startActivity(i);
            }
        });
        Query check1=refer.orderByChild("user").equalTo(str1);
        check1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String fullname = snapshot.child(str1).child("name").getValue(String.class);
                    e1.setText(fullname);
                    String emailaddresss = snapshot.child(str1).child("email").getValue(String.class);
                    e2.setText(emailaddresss);
                    String licensenumber = snapshot.child(str1).child("license").getValue(String.class);
                    e7.setText(licensenumber);
                    String mob = snapshot.child(str1).child("mobile").getValue(String.class);
                    if (mob.equals("value")) {

                    } else {
                        e3.setText(mob);
                    }
                    String qualify = snapshot.child(str1).child("qualification").getValue(String.class);
                    if (qualify.equals("value")) {

                    } else {
                        e4.setText(qualify);
                    }
                    String clinic = snapshot.child(str1).child("hospital").getValue(String.class);
                    if (clinic.equals("value")) {

                    } else {
                        e5.setText(clinic);
                    }
                    String special = snapshot.child(str1).child("speciality").getValue(String.class);
                    if (special.equals("value")) {

                    } else {
                        e6.setText(special);
                    }
                    String dept = snapshot.child(str1).child("departmentname").getValue(String.class);
                    if (dept.equals("value")) {

                    } else {
                        e8.setText(dept);
                    }
                    String l = snapshot.child(str1).child("address").getValue(String.class);
                    if (l.equals("value")) {

                    } else {
                        e9.setText(l);
                    }
                    String amt = snapshot.child(str1).child("amount").getValue(String.class);
                    if (amt.equals("value")) {

                    } else {
                        e11.setText(amt);
                    }
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectid=radioGroup.getCheckedRadioButtonId();
                rd=(RadioButton) findViewById(selectid);
                if(selectid==-1)
                {

                }
                else
                {
                    cat=rd.getText().toString();
                }
               refer.child(str1).child("name").setValue(e1.getEditableText().toString());
                refer.child(str1).child("email").setValue(e2.getEditableText().toString());
                refer.child(str1).child("mobile").setValue(e3.getEditableText().toString());
                refer.child(str1).child("qualification").setValue(e4.getEditableText().toString());
                refer.child(str1).child("hospital").setValue(e5.getEditableText().toString());
                refer.child(str1).child("speciality").setValue(e6.getEditableText().toString());
                refer.child(str1).child("license").setValue(e7.getEditableText().toString());
                refer.child(str1).child("departmentname").setValue(e8.getEditableText().toString());
                refer.child(str1).child("address").setValue(e9.getEditableText().toString());
                refer.child(str1).child("amount").setValue(e11.getEditableText().toString());
                refer.child(str1).child("category").setValue(cat);
                Toast.makeText(doctordata.this, "Profile Updated Successfully", Toast.LENGTH_SHORT).show();

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
    public Boolean validatename() {
        String val = e1.getEditableText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";

        if (val.isEmpty()) {
            e1.setError("Field cannot be empty");
            return false;
        } else {
            e1.setError(null);
            return true;
        }
    }
    public boolean isValidPhone() {
        String string=e3.getEditableText().toString();
        if (string.isEmpty()) {
            e3.setError("Field cannot be empty");
            return false;
        } else {
            e3.setError(null);
            return true;
        }
    }

}