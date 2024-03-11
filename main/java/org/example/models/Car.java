package org.example.models;

public class Car {

    int id;
    String brand;
    int price;
    boolean available;

    public Car(int id, String brand, int price, boolean available) {
        this.id = id;
        this.brand = brand;
        this.price = price;
        this.available = available;
    }

    public Car() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
