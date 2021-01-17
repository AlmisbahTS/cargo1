package com.example.cargobooking;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cargobooking.Admin.AdminHomeActivity;
import com.example.cargobooking.Classes.DatabaseHelper;
import com.example.cargobooking.Superviser.SupervisorHomeActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {


    private MaterialButton btn_login;
    private TextInputEditText et_email, et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        btn_login = findViewById(R.id.btn_login);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });


    }

    private void login() {
        String email = Objects.requireNonNull(et_email.getText()).toString();
        String password = Objects.requireNonNull(et_password.getText()).toString();

        DatabaseHelper databaseHelper = new DatabaseHelper(LoginActivity.this);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        Cursor user = databaseHelper.login(email, password, db);
        if (user.getCount() == 1) {
            while (user.moveToNext()) {
                String user_type = user.getString(3);
                if (user_type.equals("admin")) {
                    Toast.makeText(this, "Logged in as admin Successfully", Toast.LENGTH_SHORT).show();
                    Intent gotoadmin = new Intent(LoginActivity.this, AdminHomeActivity.class);
                    gotoadmin.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(gotoadmin);
                    finish();
                } else if (user_type.equals("supervisor")) {
                    Toast.makeText(this, "Logged in as Supervisor Successfully", Toast.LENGTH_SHORT).show();
                    Intent gotoadmin = new Intent(LoginActivity.this, SupervisorHomeActivity.class);
                    gotoadmin.putExtra("user_email", user.getString(1));
                    gotoadmin.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(gotoadmin);
                    finish();
                } else {
                    Toast.makeText(this, "Invalid User, Please Try again!", Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            Toast.makeText(this, "Password or Email incorrect! Please try again!", Toast.LENGTH_SHORT).show();
        }


    }
}