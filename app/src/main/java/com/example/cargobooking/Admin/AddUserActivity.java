package com.example.cargobooking.Admin;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cargobooking.Classes.DatabaseHelper;
import com.example.cargobooking.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class AddUserActivity extends AppCompatActivity {

    private TextInputEditText edt_email, edt_password;
    private MaterialButton btn_save;
    private DatabaseHelper helper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Add Warehouse");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        helper = new DatabaseHelper(AddUserActivity.this);
        db = helper.getReadableDatabase();
        edt_email = findViewById(R.id.et_email);
        edt_password = findViewById(R.id.et_password);
        btn_save = findViewById(R.id.btn_save_whs);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });


    }

    private void saveData() {

        String email = Objects.requireNonNull(edt_email.getText()).toString();
        String password = Objects.requireNonNull(edt_password.getText()).toString();

        long save = helper.CreateUser(email, password, "supervisor", db);
        if (save >= 0) {
            Toast.makeText(this, "Data saved!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error While saving!", Toast.LENGTH_SHORT).show();
        }

    }
}
