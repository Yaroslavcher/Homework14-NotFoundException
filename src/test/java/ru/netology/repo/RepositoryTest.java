package ru.netology.repo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.childclass.Book;
import ru.netology.childclass.Smartphone;
import ru.netology.parentclass.Product;

import static org.junit.jupiter.api.Assertions.*;

public class RepositoryTest {
    Product item1 = new Book(1, "BookName1", 100, "Author1");
    Product item2 = new Book(2, "BookName2", 200, "Author2");
    Product item3 = new Smartphone(3, "SmartphoneName1", 10_000, "Maker1");
    Product item4 = new Smartphone(4, "SmartphoneName2", 20_000, "Maker2");
    Product item5 = new Smartphone(5, "SmartphoneName3", 30_000, "Maker3");
    Repository repo = new Repository();

    @BeforeEach
    public void b4EachTest() {
        repo.save(item1);
        repo.save(item2);
        repo.save(item3);
        repo.save(item4);
        repo.save(item5);
    }
    @Test
    public void shouldSaveItem() {
        Product item6 = new Book(6, "BookName6", 200, "Author2");
        repo.save(item6);
        Product[] expected = {item1, item2, item3, item4, item5, item6};
        assertArrayEquals(expected, repo.findAll());
    }
    @Test
    public void shouldFindById() {
        repo.findById(3);
        Product expected = item3;
        assertEquals(expected, repo.findById(3));
    }
    @Test
    public void shouldThrowExceptionIfIdExists() {
        Product item6 = new Book(5, "BookName6", 200, "Author2");
        assertThrows(AlreadyExistsException.class, () -> {repo.save(item6);});
    }

    @Test
    public void shouldRemoveById() {
        repo.removeById(3);
        Product[] expected = {item1, item2, item4, item5};
        assertArrayEquals(expected, repo.getItems());
    }

    @Test
    public void shouldThrowExceptionIfNoSuchId() {
        assertThrows(NotFoundException.class, () -> {
            repo.removeById(33);
        });
    }
}
