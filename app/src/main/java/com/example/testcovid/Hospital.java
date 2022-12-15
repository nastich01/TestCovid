package com.example.testcovid;

public class Hospital {
    private long id;
    private String name;
    private String address;
    private String price;
    private String phone;
    private double latitude;
    private double longitude;

    public Hospital(long id, String name, String address, String price, String phone, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.price = price;
        this.phone = phone;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return this.name +
                "\n "+this.address+
                "\n "+this.price+
                "\n "+this.phone;
    }
}
