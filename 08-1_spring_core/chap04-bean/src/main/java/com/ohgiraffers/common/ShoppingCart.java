package com.ohgiraffers.common;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private final List<Product> items;

    public ShoppingCart() {
        this.items = new ArrayList<>();
    }

    public void addItem(Product product) {
        items.add(product);
    }

    public List<Product> getItems() {
        return items;
    }
}
