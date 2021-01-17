package com.example.cargobooking.Classes;

public class OrderModel {

    int id;
    String title, date, quantity, form, to;

    public OrderModel(int id, String title, String date, String quantity, String form, String to) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.quantity = quantity;
        this.form = form;
        this.to = to;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
