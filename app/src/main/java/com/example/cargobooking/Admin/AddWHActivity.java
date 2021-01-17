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

public class AddWHActivity extends AppCompatActivity {

    private AutoCompleteTextView et_supervisor_drop;
    private TextInputEditText edt_name, edt_capacity, edt_location, edt_supervisor;
    private MaterialButton btn_save;
    private DatabaseHelper helper;
    private SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_w_h);


        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Add Warehouse");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        helper = new DatabaseHelper(AddWHActivity.this);
        db = helper.getReadableDatabase();
        et_supervisor_drop = findViewById(R.id.et_supervisor_drop);
        edt_capacity = findViewById(R.id.capacity);
        edt_location = findViewById(R.id.et_location);
        edt_name = findViewById(R.id.et_whs_name);
        btn_save = findViewById(R.id.btn_save_whs);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });


        List<String> list = new ArrayList<>();

        Cursor supervisor = helper.getSupervisor(db);
        while (supervisor.moveToNext()) {
            list.add(supervisor.getString(1));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(AddWHActivity.this, R.layout.dropdown_menu_popup_item, list);
        et_supervisor_drop.setAdapter(adapter);


    }

    private void saveData() {

        String name = Objects.requireNonNull(edt_name.getText()).toString();
        String capacity = Objects.requireNonNull(edt_capacity.getText()).toString();
        String location = Objects.requireNonNull(edt_location.getText()).toString();
        String supervisor = Objects.requireNonNull(et_supervisor_drop.getText()).toString();

        long save = helper.saveWh(db, name, capacity, location, supervisor);
        if (save >= 0) {
            Toast.makeText(this, "Data saved!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error While saving!", Toast.LENGTH_SHORT).show();
        }

    }
}