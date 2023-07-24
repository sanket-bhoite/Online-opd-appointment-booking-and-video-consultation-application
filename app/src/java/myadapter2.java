package com.Appointment.doctor;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class myadapter2 extends FirebaseRecyclerAdapter<model2,myadapter2.myviewholder>
{
    String c,e,user;
    private Context mcontext;
    public myadapter2(@NonNull FirebaseRecyclerOptions<model2> options, String u,Context context) {

        super(options);
        mcontext=context;
        user=u;
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull model2 model)
    {
        holder.name.setText(model.getName());
        holder.email.setText(model.getEmail());
        holder.phone.setText(model.getMobile());
        holder.d.setText(model.getDate());
        holder.t.setText(model.getTime());
        holder.c.setText(model.getCategory());
        holder.t2.setText(model.getUsername());

        holder.b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference().child(user).child(getRef(position).getKey()).removeValue();
                FirebaseDatabase.getInstance().getReference().child(holder.t2.getText().toString()).child(getRef(position).getKey()).removeValue();
                FirebaseDatabase.getInstance().getReference("doc").child(holder.t2.getText().toString()).child(holder.d.getText().toString()).child(holder.t.getText().toString()).removeValue();
            }
        });
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow2,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView name,email,phone,d,t,c,t2;
        Button b;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.drname);
            email=(TextView)itemView.findViewById(R.id.drmail);
            phone=(TextView)itemView.findViewById(R.id.drphone);
            d=(TextView)itemView.findViewById(R.id.apdate);
            t=(TextView)itemView.findViewById(R.id.drtime);
            b=itemView.findViewById(R.id.del);
            c=itemView.findViewById(R.id.drcategory);
            t2=itemView.findViewById(R.id.userdrname);
        }
    }
}
