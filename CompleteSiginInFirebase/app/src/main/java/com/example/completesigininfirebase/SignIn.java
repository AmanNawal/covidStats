package com.example.completesigininfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class SignIn extends AppCompatActivity {


    public ProgressDialog progressDialog;
    public FirebaseDatabase database;
    public FirebaseAuth authenticate;
    public EditText email,password;
    public Button login;
    public TextView createnew;
    private String username;
    private String email2;
    private String password2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        authenticate=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        login=findViewById(R.id.Login);
        createnew=findViewById(R.id.textView2);
        email=findViewById(R.id.Email);
        password=findViewById(R.id.password);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean checkstate = false;
                Intent intent = getIntent();
                Bundle bundle = getIntent().getExtras();

                try {
                    if (bundle.getString("condition").equals("true")) {
                        if (password.getText().toString().isEmpty() || email.getText().toString().isEmpty()) {
                            Toast.makeText(getApplicationContext(), "true", Toast.LENGTH_SHORT).show();
                        }
                        normalSignin();
                        checkstate = true;
                    }
                } catch (Exception e) {

                }
                if (!bundle.getString("condition").equals("true"))
                {
                    String Email, Pass;
                Email = email.getText().toString();
                Pass = password.getText().toString();
                Uri profilepic;

                username = bundle.getString("username");
                email2 = bundle.getString("email");
                password2 = bundle.getString("pass");
                profilepic = Uri.parse(bundle.getString("useruri"));


                // Uri uri= Uri.parse(bundle.getString("uri"));
                boolean match = false;
                if (checkstate == false) {
                    match = checkParentdata(username, password2, email2);
                }
                if (match == true) {
                    authenticate.signInWithEmailAndPassword(Email, Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            FirebaseUser user = authenticate.getCurrentUser();
                            if (user.isEmailVerified()) {
                                String userid = authenticate.getUid();
                                UserData data = new UserData();
                                data.setemail(email.getText().toString());
                                data.setPassword(password.getText().toString());
                                data.setUsername(username);
                                UploadsImage upload = new UploadsImage(profilepic, 1);
                                database.getReference().child("Users").child(userid).setValue(data);
                                Toast.makeText(getApplicationContext(), "email authorized!", Toast.LENGTH_SHORT).show();
                                Intent mainScreen = new Intent(SignIn.this, ActivityScreenmain.class);

                                startActivity(mainScreen);
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(), "email does not exist", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "verification failed!", Toast.LENGTH_SHORT).show();
                        }
                    });


                }
            }
            }
        });



        createnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createUser =new Intent(SignIn.this,MainActivity.class);
                startActivity(createUser);
                finish();
            }
        });

    }

    public void  normalSignin()
    {
        authenticate.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                   Toast.makeText(getApplicationContext(), "id already created", Toast.LENGTH_SHORT).show();
                    Intent mainScreen = new Intent(SignIn.this, ActivityScreenmain.class);
                    startActivity(mainScreen);
                    finish();


                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean checkParentdata(String Parentusername,String Parentpassword,String Parentemail)
    {

        if(email.getText().toString().isEmpty())
        {
            Toast.makeText(getApplicationContext(), "email needed", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password.getText().toString().isEmpty())
        {
            Toast.makeText(getApplicationContext(), "password needed", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(!password.getText().toString().equals(Parentpassword))
        {
            Toast.makeText(getApplicationContext(), "password does not match", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!email.getText().toString().equals(Parentemail))
        {
            Toast.makeText(getApplicationContext(), "email does not match", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
        {
            return true;
        }
    }
}