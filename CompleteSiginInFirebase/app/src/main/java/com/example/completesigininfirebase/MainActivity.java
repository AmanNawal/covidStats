package com.example.completesigininfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.github.dhaval2404.imagepicker.ImagePicker;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import de.hdodenhof.circleimageview.CircleImageView;


public class MainActivity extends AppCompatActivity {
public CircleImageView imageView;
public FloatingActionButton fab;
public FirebaseDatabase database;
public FirebaseAuth authenticate;
public EditText email,username,password;
public Button signUp,google,facebook;
public Context context=MainActivity.this;
public ProgressDialog progressDialog;
public Uri uri;
public TextView checkEmail,alreadyaccount;
public GoogleSignInClient signInClient;
public CallbackManager callbackManager;
public  Boolean fblogin=false;
public FirebaseStorage storage;
public boolean reload=false;
public FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        database=FirebaseDatabase.getInstance();
        authenticate=FirebaseAuth.getInstance();
        storage=FirebaseStorage.getInstance();


        imageView=findViewById(R.id.userimg);
        fab=findViewById(R.id.floatingActionButton2);
        signUp=findViewById(R.id.Login);
        email=findViewById(R.id.Email);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        checkEmail=findViewById(R.id.textView5);
        alreadyaccount=findViewById(R.id.alreadyhaveacc);
        google=findViewById(R.id.Google);
        facebook=findViewById(R.id.facebook);
        AccessToken accessToken=AccessToken.getCurrentAccessToken();
        currentUser = authenticate.getCurrentUser();
        try {


            if (currentUser.isEmailVerified()) {
                reload = true;
            }
        }
        catch (Exception e)
        {

        }



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(MainActivity.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }

        });


      accessToken = AccessToken.getCurrentAccessToken();
      if(accessToken != null && !accessToken.isExpired())
      {
          fblogin=true;
      }

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String em=email.getText().toString(),pass=password.getText().toString();

                chekData(em,pass);


            }
        });

        alreadyaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signin=new Intent(MainActivity.this,SignIn.class);
                Bundle bundle=new Bundle();
                bundle.putString("condition","true");
                signin.putExtras(bundle);
                startActivity(signin);
                finish();
                System.exit(0);
            }
        });


        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                googleLogin1();
            }
        });


        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,fbactivity.class);
                startActivity(intent);
                finish();
                fblogin=false;
            }
        });

    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.



        if(reload && currentUser!=null )
        {
            progressDialog =new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("");
            progressDialog.setMessage("logging in");

            progressDialog.show();
            Toast.makeText(getApplicationContext(), "on start verified email", Toast.LENGTH_SHORT).show();
            Intent mainScreen = new Intent(MainActivity.this, ActivityScreenmain.class);
            startActivity(mainScreen);
            progressDialog.dismiss();
            finish();
        }


        else if(currentUser!=null && authenticate.getCurrentUser().getIdToken(false).getResult().getSignInProvider().equals("facebook.com") )
        {
            progressDialog =new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("");
            progressDialog.setMessage("logging in");

            progressDialog.show();
            Toast.makeText(getApplicationContext(), "fblogin", Toast.LENGTH_SHORT).show();

            Intent intent=new Intent(MainActivity.this,ActivityScreenmain.class);
            startActivity(intent);
            progressDialog.dismiss();
            finish();

        }
        else if(currentUser!=null && authenticate.getCurrentUser().getProviderId().equals("google.com"))
        {
            progressDialog =new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("");
            progressDialog.setMessage("logging in");

            progressDialog.show();
            Toast.makeText(getApplicationContext(), "google", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(MainActivity.this,ActivityScreenmain.class);
            startActivity(intent);
            progressDialog.dismiss();
            finish();
        }
    }

    public void chekData(String em, String pass)
    {
        if(uri==null)
        {
            Toast.makeText(getApplicationContext(), "user Image necessary", Toast.LENGTH_SHORT).show();
        }
        if(em.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "email required", Toast.LENGTH_SHORT).show();
        }
        if(pass.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "password required", Toast.LENGTH_SHORT).show();
        }
        else
        {
            progressDialog =new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Creating account");
            progressDialog.setMessage("Creating a new account");

            progressDialog.show();
            authenticate.createUserWithEmailAndPassword(em,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()) {

                        FirebaseUser fuser=authenticate.getCurrentUser();
                        fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(getApplicationContext(), "Email Sent", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                                        Intent signin=new Intent(MainActivity.this,SignIn.class);
                                        Bundle bundle=new Bundle();
                                        bundle.putString("email",em);
                                        bundle.putString("pass",pass);
                                        bundle.putString("condition","false");
                                        bundle.putString("useruri",uri.toString());
                                        bundle.putString("username",username.getText().toString());
                                        Toast.makeText(getApplicationContext(), imageView.getDrawable().toString(), Toast.LENGTH_SHORT).show();

                                        //signin.putExtra("uri",uri.toString());
                                        signin.putExtras(bundle);
                                        reload=false;
                                        startActivity(signin);






                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "error in Email sending", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    else
                    {
                        Log.d("ERRRRRORRRRRRRRRRRR",task.getException().toString());
                        Toast.makeText(getApplicationContext(), task.getException().toString(), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }
            });
        }
    }


    public void googleLogin1()
    {
        progressDialog =new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Google signup");
        progressDialog.setMessage("Logging in");

        progressDialog.show();
        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
                 signInClient = GoogleSignIn.getClient(this, gso);
                 signIn();
    }


    public int RC_SIGN_IN=65;    // can be 1- 99
    private void signIn() {
        Intent signInIntent = signInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d("TAG", "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());

            } catch (ApiException e) {

                // Google Sign In failed, update UI appropriately
                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
            }
        }
        else if(resultCode==Activity.RESULT_OK)
        {
            uri=data.getData();
            imageView.setImageURI(uri);
        }
    }
    private void firebaseAuthWithGoogle(String idToken) {

        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        authenticate.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                           progressDialog.dismiss();
                            FirebaseUser user = authenticate.getCurrentUser();
                            data userdata=new data();
                            userdata.setEmail(user.getEmail());
                            userdata.setUsername(user.getDisplayName());
                            userdata.setImage(user.getPhotoUrl().toString());
                            try {



                                database.getReference().child("Users").child(user.getUid()).setValue(userdata);

                                Intent mainScreen = new Intent(MainActivity.this, ActivityScreenmain.class);
                                startActivity(mainScreen);
                                finish();

                            }
                            catch (Exception e)
                            {
                                Toast.makeText(getApplicationContext(), "storage error", Toast.LENGTH_SHORT).show();
                                Log.d("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE",userdata.getImage().toString()+userdata.getUsername()+userdata.getEmail());
                                Toast.makeText(getApplicationContext(),userdata.getImage().toString()+" "+userdata.getUsername()+userdata.getEmail() , Toast.LENGTH_LONG).show();
                                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                            }

                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithCredential:failure", task.getException());
                            Toast.makeText(getApplicationContext(), task.getException().toString(), Toast.LENGTH_SHORT).show();
                            // updateUI(null);
                        }
                    }
                });
    }





}