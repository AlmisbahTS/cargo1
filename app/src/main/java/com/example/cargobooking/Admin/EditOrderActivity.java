package com.example.cargobooking.Admin;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cargobooking.R;

public class EditOrderActivity extends AppCompatActivity {

    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_order);

        id = getIntent().getIntExtra("id", 0);


    }
}