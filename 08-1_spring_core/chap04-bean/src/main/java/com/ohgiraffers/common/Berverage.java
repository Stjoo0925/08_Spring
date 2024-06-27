package com.ohgiraffers.common;

public class Berverage extends Product {

    private int capacity;

    public Berverage() {
//        super(); -> 생략되어 있음
    }

    public Berverage(String name, int price, int capacity) {
        super(name, price);
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return super.toString() + " " + this.capacity;
    }
}
