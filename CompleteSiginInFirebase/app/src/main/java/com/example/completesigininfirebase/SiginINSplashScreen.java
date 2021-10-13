package com.example.completesigininfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SiginINSplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sigin_insplash_screen);
        getSupportActionBar().hide();
        Thread thread=new Thread(){
            @Override
            public void run() {
                try {
                    sleep(1000);

                } catch (Exception e) {

                    e.printStackTrace();
                } finally {
                    Intent intent=new Intent(SiginINSplashScreen.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        };
        thread.start();
    }
}