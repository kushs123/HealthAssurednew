package com.example.healthassured;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class homepage extends AppCompatActivity {
    public Button medShop, Doctors_con, Disease_diag;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        medShop = findViewById(R.id.Tmed);
        Doctors_con = findViewById(R.id.Tdisease);
        Disease_diag = findViewById(R.id.Tdoc);
        medShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homepage.this, med_shop.class));
            }
        });
        Doctors_con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homepage.this, Hospitals_nearby.class));
            }
        });
        Disease_diag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homepage.this, diagnose.class));
            }
        });

    }
}
