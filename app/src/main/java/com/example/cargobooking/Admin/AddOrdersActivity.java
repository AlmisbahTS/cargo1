package com.example.cargobooking.Admin;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cargobooking.Classes.DatabaseHelper;
import com.example.cargobooking.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddOrdersActivity extends AppCompatActivity {

    private TextInputEditText edt_title, edt_quantity, edt_date;
    private AutoCompleteTextView drop_to, drop_from;
    private MaterialButton btn_save;
    private DatabaseHelper helper;
    private SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_orders);
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Add Orders");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        helper = new DatabaseHelper(AddOrdersActivity.this);
        db = helper.getReadableDatabase();
        edt_date = findViewById(R.id.et_date);
        edt_quantity = findViewById(R.id.et_quantity);
        edt_title = findViewById(R.id.et_order_titel);
        drop_from = findViewById(R.id.drop_from);
        drop_to = findViewById(R.id.drop_to);
        btn_save = findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Save();
            }
        });

        setDate();
    }

    private void Save() {
        String title = Objects.requireNonNull(edt_title.getText()).toString();
        String quantity = Objects.requireNonNull(edt_quantity.getText()).toString();
        String from = Objects.requireNonNull(drop_from.getText()).toString();
        String to = Objects.requireNonNull(drop_to.getText()).toString();
        String date = Objects.requireNonNull(edt_date.getText()).toString();

        long save = helper.saveOrder(db,title,quantity,from,to,date);
        if (save >= 0) {
            Toast.makeText(this, "Data saved!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error While saving!", Toast.LENGTH_SHORT).show();
        }

    }

    private void setDate() {

        List<String> list = new ArrayList<>();

        Cursor wh = helper.getWH(db);
        while (wh.moveToNext()) {
            list.add(wh.getString(1));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(AddOrdersActivity.this, R.layout.dropdown_menu_popup_item, list);
        drop_to.setAdapter(adapter);
        drop_from.setAdapter(adapter);


    }
}