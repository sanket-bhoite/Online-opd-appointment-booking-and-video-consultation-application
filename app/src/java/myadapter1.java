package com.Appointment.doctor;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
public class myadapter1 extends FirebaseRecyclerAdapter<model1,myadapter1.myviewholder>
{
    String c,e;
    private Context mcontext;
    public myadapter1(@NonNull FirebaseRecyclerOptions<model1> options, Context context)
    {
        super(options);
        mcontext=context;
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull model1 model)
    {
        holder.name.setText(model.getFn());
        holder.d.setText(model.getDate());
        holder.t.setText(model.getTime());
        holder.mail.setText(model.getE());
        c=model.getCategory();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(c.equals("video"))
                {
                    String usermail=holder.mail.getText().toString();
                    Intent i=new Intent(mcontext,consultation.class);
                    i.putExtra("mail-id",usermail);
                    mcontext.startActivity(i);
                }

            }
        });
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow1,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView name,d,t,mail;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.patientnametext);
            d=(TextView)itemView.findViewById(R.id.patientdate);
            t=(TextView)itemView.findViewById(R.id.patienttime);
            mail=(TextView)itemView.findViewById(R.id.patientemail);
        }
    }
}
