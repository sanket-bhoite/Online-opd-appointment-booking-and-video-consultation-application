package com.Appointment.doctor;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
public class MainActivity4 extends AppCompatActivity {

    RecyclerView recview;
    myadapter adapter;
    EditText sev;
    String string,patientuser;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        getSupportActionBar().setTitle("Find & Book");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        string=getIntent().getStringExtra("type");
        patientuser=getIntent().getStringExtra("u");
        recview=(RecyclerView)findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("doc").orderByChild("category").startAt(string).endAt(string+"\uf8ff"), model.class)
                        .build();

        adapter=new myadapter(options,this,patientuser);
        recview.setAdapter(adapter);

        sev=findViewById(R.id.sv);
        sev.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                String s1=sev.getText().toString();
                processsearch(s1);

            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }


    private void processsearch(String s)
    {
        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("doc").orderByChild("user").startAt(s).endAt(s+"\uf8ff"), model.class)
                        .build();
        adapter=new myadapter(options,this,patientuser);
        adapter.startListening();
        recview.setAdapter(adapter);

    }

}