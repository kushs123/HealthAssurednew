package com.example.healthassured;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class signup extends AppCompatActivity {
    public EditText emailId, passwd;
    Button btnSignUp;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        emailId = findViewById(R.id.Ttext);
        passwd = findViewById(R.id.Tpassword);
        btnSignUp = findViewById(R.id.Tbtn);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailID = emailId.getText().toString();
                String paswd = passwd.getText().toString();
                if (emailID.isEmpty()){
                    emailId.setError("Enter user id");
                    emailId.requestFocus();
                }else if (paswd.isEmpty()){
                    passwd.setError("Enter password");
                    emailId.requestFocus();
                }else{
                    firebaseAuth.createUserWithEmailAndPassword(emailID, paswd).addOnCompleteListener(signup.this, new OnCompleteListener<AuthResult>() {
                        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (!task.isSuccessful()) {
                                Toast.makeText(signup.this.getApplicationContext(),
                                        "SignUp unsuccessful: " + Objects.requireNonNull(task.getException()).getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                startActivity(new Intent(signup.this, MainActivity.class));
                            }
                        }
                    });

                }
            }
        });

    }
}
