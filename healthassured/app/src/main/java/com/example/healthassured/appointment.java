package com.example.healthassured;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.Calendar;

public class appointment extends AppCompatActivity implements View.OnClickListener {

    Button but,but1,but2;
    private int mYear,mMonth,mDay,mHour,mMinute;
    EditText txtDate, txtTime;

    //private Context context;


    private void getIncomingIntent(){
        if (getIntent().hasExtra("Name") && getIntent().hasExtra("Image") && getIntent().hasExtra("Desc"));
        //Log.d(TAG, "getIncomingIntent: Incoming intent");
        String Name = getIntent().getStringExtra("Name");
        String Desc = getIntent().getStringExtra("Desc");
        String Image = getIntent().getStringExtra("Image");
        setContent(Name,Desc,Image);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);


        but1 = findViewById(R.id.btn_date);
        but2 = findViewById(R.id.btn_time);
        but1.setOnClickListener(this);
        but2.setOnClickListener(this);



        but = findViewById(R.id.appointment);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Toast.makeText(appointment.this,"Appointment done",Toast.LENGTH_SHORT).show();
               startActivity(new Intent(appointment.this,appointment.class));
            }
        });



        getIncomingIntent();
    }

    private void setContent(String Name, String Rate, String Image){

        TextView name = findViewById(R.id.tTitle2);
        TextView desc = findViewById(R.id.tDesc1);
        ImageView image = findViewById(R.id.tImage1);
        name.setText(Name);
        desc.setText(Rate);
        Picasso.get().load(Image).into(image);

        }

    @Override
    public void onClick(View v) {
        if (v == but1) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);
            txtDate = findViewById(R.id.in_date);



            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == but2) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);
            txtTime = findViewById(R.id.in_time);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            txtTime.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }
}

