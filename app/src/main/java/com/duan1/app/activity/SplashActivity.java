package com.duan1.app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import com.duan1.app.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new CountDownTimer(3000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                
            }

            @Override
            public void onFinish() {
                Intent intent;
                intent=new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
            }
        }.start();
    }
}