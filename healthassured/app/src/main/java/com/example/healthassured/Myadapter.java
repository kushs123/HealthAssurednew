package com.example.healthassured;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class Myadapter extends RecyclerView.Adapter<Myadapter.MyViewHolder> {
    private Context context;
    private ArrayList<list> profiles;

    public Myadapter(Context c, ArrayList<list> p){
        context = c;
        profiles = p;
        //this.mOnclicklistner = mOnclicklistner;

    }
    //private final View.OnClickListener mOnclicklistner;



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {



        try {

            View view = LayoutInflater.from(context).inflate(R.layout.layout, parent, false);
            //view.setOnClickListener(mOnclicklistner);
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
        holder.Title.setText(profiles.get(position).getTitle());
        holder.Rate.setText(profiles.get(position).getRate());
        Picasso.get().load(profiles.get(position).getImage()).into(holder.Image);


        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked om " );
                Intent intent = new Intent(context,kart.class);
                intent.putExtra("Image",profiles.get(position).getImage());
                intent.putExtra("Name",profiles.get(position).getName());
                intent.putExtra("Rate",profiles.get(position).getRate());
                context.startActivity(intent);
            }
        });
        holder.setIsRecyclable(false);

    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Name,Title,Rate;
        ImageView Image;
        RelativeLayout relativeLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.tTitle);
            Title = itemView.findViewById(R.id.tDesc);
            Image = itemView.findViewById(R.id.tImage);
            Rate = itemView.findViewById(R.id.trate);
            relativeLayout = itemView.findViewById(R.id.relative_layout);


        }
    }
}
