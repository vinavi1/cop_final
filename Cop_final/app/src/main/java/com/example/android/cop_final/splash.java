package com.example.android.cop_final;

/**
 * Created by HARISH on 23-01-2016.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


public class splash extends  Activity{
    private int time=2500;


    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent atEnd=new Intent(splash.this,MainActivity.class);
                startActivity(atEnd);
                finish();
            }
        },time);
    }
}
