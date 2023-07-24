package com.Appointment.doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static com.google.android.gms.location.places.ui.PlacePicker.*;

public class placepicker extends AppCompatActivity {
    Button btn_PickLocation,uplocation;
    TextView tv_MyLocation;
    WifiManager wifiManager;
    String p,user,database;
    String string;
    private final static int PLACE_PICKER_REQUEST = 999;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        p=getIntent().getStringExtra("password");
        user=getIntent().getStringExtra("username");
        database=getIntent().getStringExtra("database");
        setContentView(R.layout.activity_placepicker);
        getSupportActionBar().setTitle("Location");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tv_MyLocation=(TextView) findViewById(R.id.MyLocation);
        uplocation=findViewById(R.id.updatelocation);
        wifiManager= (WifiManager) this.getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        tv_MyLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Disable Wifi
                wifiManager.setWifiEnabled(false);
                openPlacePicker();
            }
        });


    }
    private void openPlacePicker() {
        IntentBuilder builder = new IntentBuilder();
        try {
            startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);

            //Enable Wifi
            wifiManager.setWifiEnabled(true);

        } catch (GooglePlayServicesRepairableException e) {
            Log.d("Exception",e.getMessage());

            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            Log.d("Exception",e.getMessage());

            e.printStackTrace();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode){
                case PLACE_PICKER_REQUEST:
                    Place place = getPlace(placepicker.this, data);
                    Geocoder gcd=new Geocoder(this, Locale.getDefault());
                    List<Address> addresses=null;
                    try {
                        addresses=gcd.getFromLocation(place.getLatLng().latitude,place.getLatLng().longitude,1);
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                    if(addresses.size()>0){
                        string=String.format("Place: %s", addresses.get(0).getLocality() + "-"+addresses.get(0).getCountryName()+"-"+addresses.get(0).getCountryCode());
                        tv_MyLocation.setText(string);
                    }

                    uplocation.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            DatabaseReference refer = FirebaseDatabase.getInstance().getReference(database);
                            refer.child(user).child("address").setValue(string);
                            Toast.makeText(placepicker.this, "Address is updated", Toast.LENGTH_SHORT).show();

                        }
                    });
            }
        }
    }


}