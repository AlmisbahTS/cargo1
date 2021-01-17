package com.example.cargobooking.Admin;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cargobooking.Classes.DatabaseHelper;
import com.example.cargobooking.Classes.WarehouseAdapter;
import com.example.cargobooking.Classes.WarehousesDataModel;
import com.example.cargobooking.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class WarehouseActivity extends AppCompatActivity {

    private FloatingActionButton btn_add;
    private RecyclerView recyclerView;
    private WarehouseAdapter adapter;
    private ArrayList<WarehousesDataModel> list = new ArrayList<>();
    private DatabaseHelper helper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warehouse);

        recyclerView = findViewById(R.id.list_whs);
        btn_add = findViewById(R.id.fab_add_whs);
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("List of Warehouse");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        adapter = new WarehouseAdapter(WarehouseActivity.this, list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        helper = new DatabaseHelper(WarehouseActivity.this);
        db = helper.getReadableDatabase();
        Cursor cursor = helper.getWH(db);
        while (cursor.moveToNext()) {
            list.add(new WarehousesDataModel(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4)));
            Log.d("test of list", list.toString());
        }
        recyclerView.setAdapter(adapter);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add = new Intent(WarehouseActivity.this, AddWHActivity.class);
                startActivity(add);
            }
        });
    }

}