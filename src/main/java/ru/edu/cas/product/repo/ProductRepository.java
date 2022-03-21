package ru.edu.cas.product.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.edu.cas.product.dao.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    Product findById(int id);
    List<Product> findAll();
}
