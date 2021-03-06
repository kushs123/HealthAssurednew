package com.example.healthassured;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class med_shop extends AppCompatActivity {
    RecyclerView medlist;
    private DatabaseReference database;

    ArrayList<list> profile;
    Myadapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_shop);


            profile = new ArrayList<>();

            medlist = findViewById(R.id.myrecycler);

            adapter = new Myadapter(med_shop.this,profile);

            medlist.setAdapter(adapter);
            medlist.setHasFixedSize(true);
            final LinearLayoutManager layoutManager=new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            medlist.setLayoutManager(layoutManager);




        String current_user = FirebaseAuth.getInstance().getCurrentUser().getUid();
        System.out.println("current use"+current_user);
        database= FirebaseDatabase.getInstance().getReference().child("medicine");
        database.keepSynced(true);
        System.out.println(database);

        profile = new ArrayList<>();


        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    //Log.i("hi", (String) dataSnapshot.getValue());
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                        list l=dataSnapshot1.getValue(list.class);
               // for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                   // System.out.println(dataSnapshot1);
                   // list s = dataSnapshot1.getValue(list.class);
                    profile.add(l);
                }
                adapter = new Myadapter(med_shop.this,profile);
                medlist.setAdapter(adapter);

            }


        }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });


}}
