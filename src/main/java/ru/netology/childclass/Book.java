package ru.netology.childclass;

import ru.netology.parentclass.Product;

public class Book extends Product {
    protected String author;

    public Book(int id, String name, int price, String author) {
        super(id, name, price);
        this.author = author;
    }
}
