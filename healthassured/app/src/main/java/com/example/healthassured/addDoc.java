package com.example.healthassured;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class addDoc extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 234 ;
    ImageView Img;
    Uri filePath;
    EditText Name;
    EditText Exp,Specialization;
            //,likes,hates,publisher;
    EditText Desc;
    Button choose;
    Button upload;
    String url;
    DatabaseReference databaseReference;
    StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doc);

        Img = findViewById(R.id.img);
        Name = findViewById(R.id.name);
        Exp = findViewById(R.id.experience);
        Specialization = findViewById(R.id.specialization);
        Desc = findViewById(R.id.description);
        //publisher = findViewById(R.id.publisher);
        //rating = findViewById(R.id.rating);
        //author = findViewById(R.id.author);
        //price = findViewById(R.id.price);
        choose = findViewById(R.id.choose);
        upload = findViewById(R.id.upload);

        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Doctordetails");

        try {
            choose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openFileChooser();
                }
            });

            upload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    uploadFile();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void openFileChooser(){
        Intent intent = new Intent();
        intent.setType("image*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select an Image"),PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
            filePath = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),filePath);
                Img.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadFile(){


        final ProgressDialog progressDialog = new ProgressDialog(this);
        StorageReference storageReference = FirebaseStorage.getInstance()
                .getReference().child(filePath.getLastPathSegment());

        storageReference.putFile(filePath)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                        while (!uriTask.isComplete());
                        Uri urlImage = uriTask.getResult();
                        url = urlImage.toString();
                        uploadBook();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(addDoc.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                        double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                        progressDialog.setMessage("Uploaded" + ((int) progress) + "%...");
                    }
                });
    }



    public void uploadBook(){

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Doctors profile uploading...");
        progressDialog.show();

        mDoctorslist bookData = new mDoctorslist(
                Name.getText().toString(),
                Desc.getText().toString(),
                Exp.getText().toString(),
                Specialization.getText().toString(),
                //publisher.getText().toString(),
                //hates.getText().toString(),

                url );


        FirebaseDatabase.getInstance().getReference()
                .child("Doctorsdetails").setValue(bookData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()){
                    Toast.makeText(addDoc.this,"Details uploaded",Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    finish();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(addDoc.this, e.getMessage(),Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });
    }
}

