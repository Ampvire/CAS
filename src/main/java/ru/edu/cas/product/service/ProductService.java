package ru.edu.cas.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.edu.cas.product.dao.Percent;
import ru.edu.cas.product.dao.Product;
import ru.edu.cas.product.repo.PercentRepository;
import ru.edu.cas.product.repo.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс предназначен для работы с таблицей product
 */
@Service
public class ProductService {
    private ProductRepository productRepository;
    private PercentRepository percentRepository;

    @Autowired
    public void setPercentRepository(PercentRepository percentRepository) {
        this.percentRepository = percentRepository;
    }

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Получить список всех продуктов
     */
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    ;

    /**
     * Получить продукт по идентификатору продукта
     */
    public Product getProductById(int id) {
        return productRepository.findById(id);
    }

    /**
     * Получить наименование продукта по идентификатору продукта
     */
    public String getProductNameById(int id) {
        Product product = getProductById(id);
        return product.getName();
    }

    /**
     * Получить список всех продуктов (список наименований продуктов)
     */
    public List<String> getAllProductName() {
        List<Product> products = getAllProduct();
        List<String> listProductName = products.stream()
                .map(Product::getName)
                .collect(Collectors.toList());
        return listProductName;
    }

    /**
     * Метод возвращает данные таблицы Percent
     * @return  List<Percent>
     */
    public  List<Percent>  getPercent() {
        List<Percent> percents = percentRepository.findAll();
        return percents;
    }

    /**
     * Метод возвращает данные таблицы Percent
     * @param year - кол-во лет
     * @return Percent
     */
    public  Percent  getPercentByYear(String year) {
        Percent percent = percentRepository.findPercentByYears(Integer.parseInt(year));
        return percent;
    }
}
