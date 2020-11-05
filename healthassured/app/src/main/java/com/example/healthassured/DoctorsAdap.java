package com.example.healthassured;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class DoctorsAdap extends RecyclerView.Adapter<DoctorsAdap.MyViewHolder> {
private Context context;
private ArrayList<mDoctorslist> profiles;

public DoctorsAdap(Context c, ArrayList<mDoctorslist> p){
        context = c;
        profiles = p;
        }


@NonNull
@Override
public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        try {

        View view = LayoutInflater.from(context).inflate(R.layout.doctorslayout, parent, false);
        return new MyViewHolder(view);

        }
        catch (Exception e) {
        e.printStackTrace();

        }


        return null;
        }

@Override
public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.Name.setText(profiles.get(position).getName());
        holder.Desc.setText(profiles.get(position).getDesc());
        holder.exp.setText(profiles.get(position).getExp());
        holder.exp.setText(profiles.get(position).getSpecialization());
        Picasso.get().load(profiles.get(position).getImage()).into(holder.Image);

    holder.appointment.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClick: clicked om " );
            Intent intent = new Intent(context,appointment.class);
            intent.putExtra("Image",profiles.get(position).getImage());
            intent.putExtra("Name",profiles.get(position).getName());
            intent.putExtra("Desc",profiles.get(position).getDesc());
            context.startActivity(intent);
        }
    });
    holder.setIsRecyclable(false);




        holder.mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context,mail.class));
            }
        });

        }

@Override
public int getItemCount() {
        return profiles.size();
        }

public class MyViewHolder extends RecyclerView.ViewHolder {
    TextView Name,Desc,exp,Specialization;
    ImageView Image;
    Button mail,appointment;
    RelativeLayout relativeLayout;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        Name = itemView.findViewById(R.id.tTitle2);
        Desc = itemView.findViewById(R.id.tDesc1);
        exp = itemView.findViewById(R.id.tdocdes);
        //Specialization = itemView.findViewById(R.id.trate);
        Image = itemView.findViewById(R.id.tImage1);
        mail = itemView.findViewById(R.id.call);
        appointment = itemView.findViewById(R.id.Appointment);
        //relativeLayout = itemView.findViewById(R.id.relativelayout1);



    }
}
}

