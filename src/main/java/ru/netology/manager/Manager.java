package ru.netology.manager;

import ru.netology.parentclass.Product;
import ru.netology.childclass.Book;
import ru.netology.repo.Repository;

public class Manager {
    protected Repository repo;      //поле типа Repository

    public Manager(Repository repo) {     //конструктор с параметром репозиторий
        this.repo = repo;
    }

    public void add(Product items) {       //метод добавления товаров в репо
        repo.save(items);
    }

    public Product[] searchBy(String text) {    //метод поиска по товарам в репозитории
        Product[] result = new Product[0];      //в result хранится соответствующие поиску товары
        for (Product item : repo.getItems()) {
            if (matches(item, text)) {
                Product[] tmp = new Product[result.length + 1];
                for (int i = 0; i < result.length; i++) {
                    tmp[i] = result[i];
                }
                tmp[tmp.length - 1] = item;
                result = tmp;
            }
        }
        return result;
    }

    public boolean matches(Product item, String search) {
        if (item.getName().contains(search)) {
            return true;
        } else {
            return false;
        }
    }
}
