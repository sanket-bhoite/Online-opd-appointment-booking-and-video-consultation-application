package com.Appointment.doctor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class myadapter extends FirebaseRecyclerAdapter<model,myadapter.myviewholder>
{
    private Context mcontext;
    String data;
    public myadapter(@NonNull FirebaseRecyclerOptions<model> options, Context context,String data) {
        super(options);
        mcontext=context;
        this.data=data;
    }
    String a;
    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull model model)
    {
        holder.name.setText(model.getName());
        holder.hs.setText(model.getHospital());
        holder.q.setText(model.getQualification());
        holder.sp.setText(model.getSpeciality());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent i=new Intent(mcontext,MainActivity7.class);
            i.putExtra("name",model.getUser());
            i.putExtra("hospital",model.getHospital());
            i.putExtra("qualification",model.getQualification());
            i.putExtra("fulname",model.getName());
            i.putExtra("special",model.getSpeciality());
            i.putExtra("patientdata",data);
            mcontext.startActivity(i);
            }
        });


    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        CircleImageView img;
        TextView name,hs,q,sp;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.nametext);
            hs=(TextView)itemView.findViewById(R.id.hos);
            q=(TextView)itemView.findViewById(R.id.ql);
            sp=(TextView)itemView.findViewById(R.id.special);
        }

    }

}


