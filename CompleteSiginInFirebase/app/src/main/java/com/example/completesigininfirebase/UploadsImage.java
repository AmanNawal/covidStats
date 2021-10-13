package com.example.completesigininfirebase;

import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.completesigininfirebase.MainActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class UploadsImage{

    public FirebaseAuth authenticate;
    public FirebaseDatabase database;
    public FirebaseStorage storage;

    UploadsImage(Uri uri,int requestcode)
    {
    authenticate=FirebaseAuth.getInstance();
    database=FirebaseDatabase.getInstance();
    storage=FirebaseStorage.getInstance();
        if(requestcode==1)
        {
           // Toast.makeText(getApplicationContext(), "upload call", Toast.LENGTH_SHORT).show();
           uploadingImage(uri,"image");
        }
    }

public void uploadingImage(Uri uri,String asDirectory)
{
    final StorageReference reference=storage.getReference().child("Users").child(authenticate.getUid()).child(asDirectory);
    reference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
        @Override
        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
            reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    database.getReference().child("Users").child(authenticate.getUid()).child(asDirectory).setValue(uri.toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                        //   Toast.makeText(getApplicationContext(), "image Uploaded successfully", Toast.LENGTH_SHORT).show();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                          //  Toast.makeText(getApplicationContext(), "failed to upload the image", Toast.LENGTH_SHORT).show();
                          //  Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
    });
}
}
