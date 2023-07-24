package com.Appointment.doctor;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class userappointmentdetails extends AppCompatActivity implements PaymentResultListener {
    String date,time,username,name,mail,phone,stat,district,taluka,pinid,category,patientdataofuser,drname,dremail,drphone,amt;
    TextView e1,e2;
    TextView fname,state,dist;
    TextView eid,phno;
    Button book;
    DatabaseReference refer,refer1;
    String fulname,useremail,mobile,birthday,addr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userappointmentdetails);
        getSupportActionBar().setTitle("Book appointment");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        date=getIntent().getStringExtra("date");
        time=getIntent().getStringExtra("time");
        username=getIntent().getStringExtra("uname");
        patientdataofuser=getIntent().getStringExtra("patientusernamedata");
        e1=findViewById(R.id.userdate);
        e2=findViewById(R.id.usertime);
        fname=findViewById(R.id.userfm);
        eid=findViewById(R.id.useremailid);
        phno=findViewById(R.id.userphone);
        state=findViewById(R.id.userstate);
        dist=findViewById(R.id.userDistrict);
        book=findViewById(R.id.userbooknow);
        e1.setText(date);
        e2.setText(time);
        refer= FirebaseDatabase.getInstance().getReference("Patient");
        refer1= FirebaseDatabase.getInstance().getReference();
        Query check1 = refer.orderByChild("user").equalTo(patientdataofuser);
        check1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    fulname=snapshot.child(patientdataofuser).child("fname").getValue(String.class);
                    useremail=snapshot.child(patientdataofuser).child("email").getValue(String.class);
                    mobile=snapshot.child(patientdataofuser).child("mobile").getValue(String.class);
                    birthday=snapshot.child(patientdataofuser).child("dob").getValue(String.class);
                    addr=snapshot.child(patientdataofuser).child("address").getValue(String.class);
                    fname.setText(fulname);
                    eid.setText(useremail);
                    phno.setText(mobile);
                    state.setText(birthday);
                    dist.setText(addr);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        refer= FirebaseDatabase.getInstance().getReference("doc");
        refer1= FirebaseDatabase.getInstance().getReference();
        Query check2 = refer.orderByChild("user").equalTo(username);
        check2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    category=snapshot.child(username).child("category").getValue(String.class);
                    drname=snapshot.child(username).child("name").getValue(String.class);
                    dremail=snapshot.child(username).child("email").getValue(String.class);
                    drphone=snapshot.child(username).child("mobile").getValue(String.class);
                    amt=snapshot.child(username).child("Amount").getValue(String.class);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name=fname.getText().toString();
                mail=eid.getText().toString();
                phone=phno.getText().toString();
                stat=state.getText().toString();
                district=dist.getText().toString();
                makepayment();
            }
        });
    }
    private void makepayment()
    {

        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_XjmOQs6T8vHMKh");
        final Activity activity = this;

        try {

            JSONObject options = new JSONObject();
            options.put("description", "Reference No. #123456");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            // options.put("order_id", "order_DBJOWzybf0sJbb");//from response of step 3.
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            options.put("amount",1000);//300 X 100
            options.put("prefill.email","sanketbhoite06@gmail.com");
            options.put("prefill.contact","9284809323");
            checkout.open(activity, options);
        } catch(Exception e) {
            Log.e("TAG", "Error in starting Razorpay Checkout", e);
        }
    }


    @Override
    public void onPaymentSuccess(String s)
    {
        appointmenthelper ah2=new appointmenthelper(name,username,date,time,category,drname,dremail,drphone);
        refer1.child(patientdataofuser).child(name).setValue(ah2);
        appointmenthelper ah=new appointmenthelper(username,name,mail,phone,stat,district,date,time,category);
        refer.child(username).child(date).child(time).setValue(ah);
        refer1.child(username).child(name).setValue(ah);
        Intent i=new Intent(userappointmentdetails.this,paymentactivity.class);
        i.putExtra("patientname",name);
        i.putExtra("patientmail",mail);
        i.putExtra("patientmobile",phone);
        i.putExtra("patientbirth",stat);
        i.putExtra("patientaddress",district);
        i.putExtra("doctorname",drname);
        i.putExtra("doctormail",dremail);
        i.putExtra("doctorphone",drphone);
        i.putExtra("amount",amt);
        i.putExtra("payid",s);
        i.putExtra("category",category);
        startActivity(i);
    }

    @Override
    public void onPaymentError(int i, String s) {

    }



}