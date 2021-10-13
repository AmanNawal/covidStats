package com.example.completesigininfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.github.ybq.android.spinkit.style.Wave;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profile extends AppCompatActivity {

    private DatabaseReference referenceprofile;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth authenticate;
    private    ImageView profileimage;
    private TextView email;
    private TextView Username;
    private LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        email=findViewById(R.id.UserEmail);
        Username=findViewById(R.id.userName);
        firebaseDatabase=FirebaseDatabase.getInstance();
        profileimage=findViewById(R.id.userimg);
        linearLayout=findViewById(R.id.linearLayout);
        authenticate=FirebaseAuth.getInstance();
        referenceprofile=firebaseDatabase.getReference("Users").child(authenticate.getUid());
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.spin_kit);
        Sprite doubleBounce = new Wave();
        progressBar.setIndeterminateDrawable(doubleBounce);
        progressBar.setVisibility(View.VISIBLE);

        referenceprofile.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                        readProfile value =snapshot.getValue(readProfile.class);
                        String profileimg= value.image;
                        String email1= value.email;
                        String username1=value.username;
                        Glide.with(com.example.completesigininfirebase.profile.this).load(profileimg).into(profileimage);
                        email.setText("E-mail: "+email1);
                        Username.setText("Username: "+username1);
                        progressBar.setVisibility(View.INVISIBLE);
                       linearLayout.setVisibility(View.VISIBLE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}