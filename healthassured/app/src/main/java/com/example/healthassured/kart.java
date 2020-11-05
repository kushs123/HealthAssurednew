package com.example.healthassured;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class kart extends AppCompatActivity {
    private Context context;
    private DatabaseReference database;
    EditText carddnum,expdatee,Cvv,No_of_medicines;
    TextView total,rate;
    ImageView image;
    Button pay;
    ArrayList<DataSnapshot> profile;

    private void getIncomingIntent(){
        if (getIntent().hasExtra("Name") && getIntent().hasExtra("Image") && getIntent().hasExtra("Rate"));
        //Log.d(TAG, "getIncomingIntent: Incoming intent");
        String Name = getIntent().getStringExtra("Name");
        String Rate = getIntent().getStringExtra("Rate");
        String Image = getIntent().getStringExtra("Image");
        setContent(Name,Rate,Image);
    }

    private void setContent(String Name, String Rate, String Image){

        TextView name = findViewById(R.id.fmed);
        TextView rate = findViewById(R.id.trate);
        ImageView image = findViewById(R.id.tTemp);
        name.setText(Name);
        rate.setText(Rate);
        Picasso.get().load(Image).into(image);


    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kart);
        carddnum = findViewById(R.id.editText);
        expdatee = findViewById(R.id.TexpDate);
        Cvv = findViewById(R.id.CVV);
        pay = findViewById(R.id.btn);
        rate = findViewById(R.id.trate);
        No_of_medicines = findViewById(R.id.no_of_medicines);
        total = findViewById(R.id.ttotal);

        getIncomingIntent();





        pay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(kart.this,"Payment Successful" , Toast.LENGTH_LONG).show();
                startActivity(new Intent(kart.this,Delivery.class));

            }
        });

}}
//                //List profile = new ArrayList();
//                final String cardname = carddnum.getText().toString();
//                final String expdate = expdatee.getText().toString();
//                final String cvv = Cvv.getText().toString();
//                database = FirebaseDatabase.getInstance().getReference().child("details");
//                database.child("").addListenerForSingleValueEvent(new  ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        if(dataSnapshot.exists()){
//                        for (DataSnapshot data: dataSnapshot.getChildren()){
//                            System.out.println("Details="+data.getValue());
//                            profile.add(data);
////                            for (int i=0;i<=profile.size();i++){
////                                if (cardname.contains(cardname) ){
////                                    Toast.makeText(kart.this,"Payment Successful" , Toast.LENGTH_LONG).show();
////                                    startActivity(new Intent(kart.this,delivery.class));
////
////                                }
////                            }
//                            //|| data.child(expdate).exists() || data.child(cvv).exists()
//                            System.out.println(data.child(cardname));
//                            System.out.println(data);
//                            if(data.child(cardname).exists()){
//                                System.out.println("Hello kushagra");
//                                Toast.makeText(kart.this,"Payment Successful" , Toast.LENGTH_LONG).show();
//                                startActivity(new Intent(kart.this, Delivery.class));
//                            }
//                            else
//                                {
//                                Toast.makeText(kart.this,"Data not found",Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    }}

//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//                        Toast.makeText(kart.this,"Error database",Toast.LENGTH_SHORT).show();
//
//                    }
//                });
//
//            }
//        });
//
//
   //}

//    public void calculate(View view) {
//
//        String Rate = rate.getText().toString();
//        String no_of_medicines = No_of_medicines.getText().toString();
//
//            int num1 = Integer.parseInt(Rate    );
//            int num2 = Integer.parseInt(no_of_medicines);
//
//            int total = num1 * num2;
//
//
//
//        TextView Total = findViewById(R.id.ttotal);
//        Total.setText(Integer.toString(total));
//
//    }
//}
  //  }}