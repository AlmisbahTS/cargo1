package com.example.cargobooking.Classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Cargo.db";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String users_tbl = "CREATE TABLE tbl_users(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "email TEXT NOT NULL," +
                "password TEXT NOT NULL," +
                "user_type TEXT NOT NULL)";

        String tbl_warehouse = "CREATE TABLE tbl_warehouse(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL," +
                "location TEXT NOT NULL," +
                "capacity TEXT NOT NULL," +
                "supervisor TEXT NOT NULL)";

        String tbl_orders = "CREATE TABLE tbl_orders(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "title TEXT NOT NULL," +
                "quantity TEXT NOT NULL," +
                "form TEXT NOT NULL," +
                "send_to TEXT NOT NULL," +
                "date TEXT NOT NULL)";


        db.execSQL(users_tbl);
        db.execSQL(tbl_orders);
        db.execSQL(tbl_warehouse);
        CreateUser("admin@gmail.com", "password", "admin", db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long CreateUser(String email, String password, String user_type, SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put("email", email);
        values.put("password", password);
        values.put("user_type", user_type);
        return db.insert("tbl_users", null, values);
    }

    public Cursor login(String email, String password, SQLiteDatabase db) {
        return db.query("tbl_users", null, "email='" + email + "' AND password='" + password + "'", null, null, null, null);
    }

    public Cursor getSupervisor(SQLiteDatabase db) {
        return db.query("tbl_users", null, "user_type='supervisor'", null, null, null, null);
    }

    public Cursor getOrders(SQLiteDatabase db) {
        return db.query("tbl_orders", null, null, null, null, null, null);
    }

    public Cursor getWH(SQLiteDatabase db) {
        return db.query("tbl_warehouse", null, null, null, null, null, null);
    }

    public long saveWh(SQLiteDatabase db, String name, String capacity, String location, String supervisor) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("location", location);
        values.put("capacity", capacity);
        values.put("supervisor", supervisor);
        return db.insert("tbl_warehouse", null, values);
    }

    public long saveOrder(SQLiteDatabase db, String title, String quantity, String from, String to, String date) {
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("quantity", quantity);
        values.put("send_to", to);
        values.put("form", from);
        values.put("date", date);
        return db.insert("tbl_orders", null, values);
    }

    public long updateOrder(SQLiteDatabase db, int id, String title, String quantity, String from, String to, String date) {
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("quantity", quantity);
        values.put("send_to", to);
        values.put("form", from);
        values.put("date", date);
        return db.update("tbl_orders", values, "id='" + id + "'", null);
    }

    public void cancelOrder(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("tbl_orders", "id='" + id + "'", null);
    }
}
