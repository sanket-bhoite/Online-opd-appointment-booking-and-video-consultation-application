package com.Appointment.doctor;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class MainActivity2 extends AppCompatActivity {

    private Button btnLogin;
    private EditText etPhoneNumber;

    private static final String TAG = "MainActivity2";
    String user,database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        user=getIntent().getStringExtra("username");
        database=getIntent().getStringExtra("database");
        btnLogin = findViewById(R.id.btnLogin);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = etPhoneNumber.getText().toString();
                if (phoneNumber.isEmpty())
                    Toast.makeText(MainActivity2.this, "Enter your phone number", Toast.LENGTH_SHORT).show();
                else {
                    //verify phone number
                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            "+91"+phoneNumber, 60, TimeUnit.SECONDS, MainActivity2.this,
                            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                @Override
                                public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                                    signInUser(phoneAuthCredential);
                                }

                                @Override
                                public void onVerificationFailed(FirebaseException e) {
                                    Log.d(TAG, "onVerificationFailed:"+e.getLocalizedMessage());
                                }

                                @Override
                                public void onCodeSent(final String verificationId, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                    super.onCodeSent(verificationId, forceResendingToken);
                                    //
                                    Dialog dialog = new Dialog(MainActivity2.this);
                                    dialog.setContentView(R.layout.verify_popup);

                                    final EditText etVerifyCode = dialog.findViewById(R.id.etVerifyCode);
                                    Button btnVerifyCode = dialog.findViewById(R.id.btnVerifyOTP);
                                    btnVerifyCode.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            String verificationCode = etVerifyCode.getText().toString();
                                            if(verificationId.isEmpty()) return;
                                            //create a credential
                                            PhoneAuthCredential credential= PhoneAuthProvider.getCredential(verificationId,verificationCode);
                                            signInUser(credential);
                                        }
                                    });

                                    dialog.show();
                                }
                            });
                }
            }
        });

    }

    private void signInUser(PhoneAuthCredential credential) {
        FirebaseAuth.getInstance().signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            String phone=etPhoneNumber.getEditableText().toString();
                            DatabaseReference refer = FirebaseDatabase.getInstance().getReference(database);
                            refer.child(user).child("mobile").setValue(phone);
                            Toast.makeText(MainActivity2.this, "Verified", Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Log.d(TAG, "onComplete:"+task.getException().getLocalizedMessage());
                        }
                    }
                });
    }
}