package com.example.cargobooking.Superviser;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cargobooking.Classes.DatabaseHelper;
import com.example.cargobooking.LoginActivity;
import com.example.cargobooking.R;
import com.google.android.material.card.MaterialCardView;

public class SupervisorHomeActivity extends AppCompatActivity {


    TextView tv_wh_name, tv_capacity, tv_location;
    MaterialCardView btn_orders, btn_logout;
    String email;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supervisor_home);

        email = getIntent().getStringExtra("user_email");

        tv_capacity = findViewById(R.id.wh_capacity);
        tv_wh_name = findViewById(R.id.wh_name);
        tv_location = findViewById(R.id.wh_location);
        btn_orders = findViewById(R.id.btn_orders);
        btn_logout = findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SupervisorHomeActivity.this, "Logged Out Successfully", Toast.LENGTH_SHORT).show();
                Intent out = new Intent(SupervisorHomeActivity.this, LoginActivity.class);
                out.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(out);
                finish();
            }
        });

        DatabaseHelper databaseHelper = new DatabaseHelper(SupervisorHomeActivity.this);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        btn_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SupervisorHomeActivity.this, ManageOrderActivity.class);
                startActivity(i);
            }
        });

        Cursor cursor = databaseHelper.getWH(db);
        while (cursor.moveToNext()) {
            if (cursor.getString(4).equals(email)) {
                tv_location.setText("Location: " + cursor.getString(2));
                tv_capacity.setText("Capacity: " + cursor.getString(3));
                tv_wh_name.setText(cursor.getString(1));
            }
        }


    }
}