package com.example.cargobooking.Admin;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cargobooking.Classes.DatabaseHelper;
import com.example.cargobooking.Classes.UserListAdaptor;
import com.example.cargobooking.Classes.UserModel;
import com.example.cargobooking.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class UsersActivity extends AppCompatActivity {

    private FloatingActionButton btn_add;
    private RecyclerView recyclerView;
    private UserListAdaptor adapter;
    private ArrayList<UserModel> list = new ArrayList<>();
    private DatabaseHelper helper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        recyclerView = findViewById(R.id.list_users);
        btn_add = findViewById(R.id.fab_add_user);
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("List of Supervisors");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        adapter = new UserListAdaptor(UsersActivity.this, list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        helper = new DatabaseHelper(UsersActivity.this);
        db = helper.getReadableDatabase();
        Cursor cursor = helper.getSupervisor(db);
        while (cursor.moveToNext()) {
            list.add(new UserModel(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
        }
        recyclerView.setAdapter(adapter);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add = new Intent(UsersActivity.this, AddUserActivity.class);
                startActivity(add);
            }
        });
    }

}