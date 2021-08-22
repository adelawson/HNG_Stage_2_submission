package com.example.hng;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class splashscreen extends AppCompatActivity {
    Handler splashHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        splashHandler.postDelayed(() -> {
            Intent intent=new Intent(splashscreen.this,MainActivity.class);
            startActivity(intent);
            finish();
        },3000);

            }

}