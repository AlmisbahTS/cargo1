package com.example.cargobooking.Classes;

public class WarehousesDataModel {

    String id,name,location,capacity,supervisor;

    public WarehousesDataModel(String id, String name, String location, String capacity, String supervisor) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.supervisor = supervisor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }
}
