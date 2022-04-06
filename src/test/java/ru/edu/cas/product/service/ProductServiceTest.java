package ru.edu.cas.product.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.edu.cas.product.dao.Product;

import java.util.List;

/**
 * Класс с тестами для {@link ProductService}
 */

@SpringBootTest
public class ProductServiceTest {
    private ProductService productService;

    @Autowired
    public void setService (ProductService productService) {
        this.productService = productService;
    }


    /**
     * Проверка успешного выполнения метода .getAllProduct()
     * */
    @Test
    public void getAllProduct_Test(){
        List<Product> products = productService.getAllProduct();
        Assertions.assertFalse(products.isEmpty());
    }

    /**
     * Проверка успешного выполнения метода .getProductById()
     * */
    @Test
    public void getProductById_Test(){
        Product product = productService.getProductById(1);
        String productName = "РКО";
        Assertions.assertEquals(productName, product.getName());
    }

    /**
     * Проверка успешного выполнения метода .getProductNameById()
     * */
    @Test
    public void getProductNameById_Test(){
        String productNameTest = productService.getProductNameById(1);
        String productName = "РКО";
        Assertions.assertEquals(productName, productNameTest);
    }

    /**
     * Проверка успешного выполнения метода .getAllProductName()
     */
    @Test
    public void getAllProductName_Test(){
        List<String> products = productService.getAllProductName();
        Assertions.assertEquals(3, products.size());
    }
}
