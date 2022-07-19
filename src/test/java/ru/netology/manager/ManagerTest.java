package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.childclass.Book;
import ru.netology.childclass.Smartphone;
import ru.netology.parentclass.Product;
import ru.netology.repo.Repository;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ManagerTest {
    Repository repo = Mockito.mock(Repository.class);
    Manager manager = new Manager(repo);
    Product item1 = new Book(1, "BookName1", 100, "Author1");
    Product item2 = new Book(2, "BookName2", 200, "Author2");
    Product item3 = new Smartphone(3, "SmartphoneName1", 10_000, "Maker1");
    Product item4 = new Smartphone(4, "SmartphoneName2", 20_000, "Maker2");
    Product item5 = new Smartphone(5, "SmartphoneName3", 30_000, "Maker3");

    Product[] items = {item1, item2, item3, item4, item5};

    @Test
    public void shouldAddItems() {
        doReturn(items).when(repo).getItems();
        Product[] expected = {item1, item2, item3, item4, item5};
        assertArrayEquals(expected, repo.getItems());
    }

    @Test
    public void shouldAddOneItem() {
        Product[] items = {item1};
        doReturn(items).when(repo).getItems();
        Product[] expected = {item1};
        Product[] actual = repo.getItems();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindSeveralItems() {
        String text = "Name2";
        doReturn(items).when(repo).getItems();
        Product[] expected = {item2, item4};
        assertArrayEquals(expected, manager.searchBy(text));
    }

    @Test
    public void shouldFindNone() {
        String text = "Name11";
        doReturn(items).when(repo).getItems();
        Product[] expected = {};
        assertArrayEquals(expected, manager.searchBy(text));
    }

    @Test
    public void shouldFindOneItem() {
        String text = "SmartphoneName3";
        doReturn(items).when(repo).getItems();
        Product[] expected = {item5};
        assertArrayEquals(expected, manager.searchBy(text));
    }
}

