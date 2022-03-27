package ru.edu.cas.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.edu.cas.product.dao.Product;
import ru.edu.cas.product.repo.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс предназначен для работы с таблицей product
 */
@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    /**
     * Получить список всех продуктов
     * */
    public List<Product> getAllProduct(){
        return productRepository.findAll();
    };

    /**
     * Получить продукт по идентификатору продукта
     * */
    public Product getProductById(int id){
        return productRepository.findById(id);
    }

    /**
     * Получить наименование продукта по идентификатору продукта
     * */
    public String getProductNameById(int id){
        Product product = getProductById(id);
        return product.getName();
    }

    /**
     * Получить список всех продуктов (список наименований продуктов)
     * */
    public List<String> getAllProductName(){
        List<Product> products = getAllProduct();
        List<String> listProductName = products.stream()
                .map(Product::getName)
                .collect(Collectors.toList());
        return listProductName;
    }
}
