package com.Appointment.doctor;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
public class paymentactivity extends AppCompatActivity {
    Button r;
    String patientname,patientmail,patientmob,patientaddr,birth,docname,docmail,docphone,cat,amt,id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymentactivity);
        patientname=getIntent().getStringExtra("patientname");
        patientmail=getIntent().getStringExtra("patientmail");
        patientmob=getIntent().getStringExtra("patientmobile");
        patientaddr=getIntent().getStringExtra("patientaddress");
        birth=getIntent().getStringExtra("patientbirth");
        docname=getIntent().getStringExtra("doctorname");
        docmail=getIntent().getStringExtra("doctormail");
        docphone=getIntent().getStringExtra("doctorphone");
        cat=getIntent().getStringExtra("category");
        amt=getIntent().getStringExtra("amount");
        id=getIntent().getStringExtra("payid");
        r=findViewById(R.id.rtrnhome);
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        createPDF();
    }
    private void createPDF() {
        r.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                PdfDocument myPdfDocument = new PdfDocument();
                Paint myPaint = new Paint();
                PdfDocument.PageInfo myPageinfo1 = new PdfDocument.PageInfo.Builder(400, 600, 1).create();
                PdfDocument.Page myPage1 = myPdfDocument.startPage(myPageinfo1);
                Canvas canvas = myPage1.getCanvas();
                canvas.drawText("Appointment Slip", 50, 50, myPaint);
                canvas.drawText("-------------------------------------------------------------------------------------", 50, 65, myPaint);
                canvas.drawText("Patient Name   :"+patientname, 50, 80, myPaint);
                canvas.drawText("Patient Email   :"+patientmail, 50, 95, myPaint);
                canvas.drawText("Patient Mobile :"+patientmob, 50, 110, myPaint);
                canvas.drawText("Address            :"+patientaddr, 50, 125, myPaint);
                canvas.drawText("DOB                :"+birth, 50, 140, myPaint);
                canvas.drawText("-------------------------------------------------------------------------------------", 50, 155, myPaint);
                canvas.drawText("Doctor Name    :"+docname, 50, 170, myPaint);
                canvas.drawText("Doctor Email    :"+docmail, 50, 185, myPaint);
                canvas.drawText("Doctor Mobile  :"+docphone, 50, 200, myPaint);
                canvas.drawText("Category        :"+cat, 50, 215, myPaint);
                canvas.drawText("Paid amount    :"+amt+"Rupees", 50, 230, myPaint);
                canvas.drawText("Pyment Id      :"+id, 50, 245, myPaint);
                myPdfDocument.finishPage(myPage1);
                File file = new File(Environment.getExternalStorageDirectory(),"/docpe.pdf");

                try {
                    myPdfDocument.writeTo(new FileOutputStream(file));
                    Toast.makeText(paymentactivity.this, "Appointment slip is downloaded", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                myPdfDocument.close();

            }
        });
    }
}