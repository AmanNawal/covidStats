package com.example.completesigininfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class validate extends AppCompatActivity {

    public ProgressDialog progressDialog;
    public TextView check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validate);
    check=findViewById(R.id.textView);
        MainActivity main=new MainActivity();
        FirebaseAuth authenticate =main.authenticate;
        progressDialog=new ProgressDialog(main.context);
        progressDialog.setTitle("validate");
        progressDialog.setMessage("checking your email");
        progressDialog.show();
        FirebaseUser fuser=authenticate.getCurrentUser();
        if (fuser.isEmailVerified()) {
            Toast.makeText(getApplicationContext(), "email authorized!", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
            finish();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "no validation", Toast.LENGTH_SHORT).show();
            check.setVisibility(View.VISIBLE);
        }
    }
}