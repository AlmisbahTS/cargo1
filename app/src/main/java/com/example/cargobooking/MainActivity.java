package com.example.cargobooking;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                Intent next = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(next);
                finish();
            }
        }, 3000);   //3 seconds

    }
}