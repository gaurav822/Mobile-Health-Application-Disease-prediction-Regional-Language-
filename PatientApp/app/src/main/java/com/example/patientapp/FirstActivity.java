package com.example.patientapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class FirstActivity extends AppCompatActivity {

    Handler handler;
    Runnable runnable;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        img=(ImageView) findViewById(R.id.img);
        img.animate().alpha(4000).setDuration(0);

        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent dsp=new Intent(FirstActivity.this,Chooselanguage.class);
                startActivity(dsp);
                finish();
            }
        },4000);
    }
}