package ru.netology.esaukova.hw13.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.esaukova.hw13.domain.AlreadyExistsException;
import ru.netology.esaukova.hw13.domain.NotFoundException;
import ru.netology.esaukova.hw13.domain.Product;

public class ShopRepositoryTest {
    ShopRepository repo = new ShopRepository();

    Product product1 = new Product(1, "Юбка", 50);
    Product product2 = new Product(20, "Блузка", 600);
    Product product3 = new Product(300, "Платье", 1_000);

    @Test
    public void addProductTest() {
        repo.add(product1);
        repo.add(product2);

        Product[] expected = {product1, product2};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void alreadyExistExceptionTest() {
        repo.add(product1);
        repo.add(product2);
        repo.add(product3);

        Assertions.assertThrows(AlreadyExistsException.class, () -> repo.add(product3));
    }

    @Test
    public void removeByIdTest() {
        repo.add(product1);
        repo.add(product2);
        repo.add(product3);

        repo.removeById(20);
        Product[] expected = {product1, product3};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void notFoundExceptionTest() {
        repo.add(product1);
        repo.add(product2);
        repo.add(product3);

        Assertions.assertThrows(NotFoundException.class, () -> repo.removeById(10));
    }
}
