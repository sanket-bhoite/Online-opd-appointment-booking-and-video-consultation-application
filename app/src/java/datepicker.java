package com.Appointment.doctor;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import android.os.Bundle;
import android.app.DatePickerDialog;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.text.DateFormat;
import java.util.Calendar;
public class datepicker extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    TextView t;
    String user,p,database,d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        p=getIntent().getStringExtra("password");
        user=getIntent().getStringExtra("username");
        database=getIntent().getStringExtra("database");
        setContentView(R.layout.activity_datepicker);
        getSupportActionBar().setTitle("Choose your Date Of Birth");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Button button = (Button) findViewById(R.id.button);
        t=findViewById(R.id.dobupdate);
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference refer = FirebaseDatabase.getInstance().getReference(database);
                refer.child(user).child("dob").setValue(d);
                Toast.makeText(datepicker.this, "DOB is updated", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        t.setText(currentDateString);
        d=t.getText().toString();
    }
}
