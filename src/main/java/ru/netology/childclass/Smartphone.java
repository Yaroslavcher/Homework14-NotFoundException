package ru.netology.childclass;

import ru.netology.parentclass.Product;

public class Smartphone extends Product {
    protected String maker;

    public Smartphone(int id, String name, int price, String maker) {
        super(id, name, price);
        this.maker = maker;
    }
}
