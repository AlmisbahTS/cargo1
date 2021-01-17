package com.example.cargobooking.Admin;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cargobooking.Classes.DatabaseHelper;
import com.example.cargobooking.Classes.OrderListAdapter;
import com.example.cargobooking.Classes.OrderModel;
import com.example.cargobooking.R;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;

public class ReportActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private OrderListAdapter adapter;
    private ArrayList<OrderModel> list = new ArrayList<>();
    private DatabaseHelper helper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Reports of Orders");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        recyclerView = findViewById(R.id.list_orders);
        adapter = new OrderListAdapter(ReportActivity.this, list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        helper = new DatabaseHelper(ReportActivity.this);
        db = helper.getReadableDatabase();
        Cursor cursor = helper.getOrders(db);
        while (cursor.moveToNext()) {
            list.add(new OrderModel(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5)));
        }
        recyclerView.setAdapter(adapter);

    }

}