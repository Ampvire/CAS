package ru.edu.cas.client.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.edu.cas.client.dao.Client;
import ru.edu.cas.client.dao.ClientProducts;
import ru.edu.cas.product.dao.Product;

import java.util.List;

public interface ClientProductsRepository extends JpaRepository<ClientProducts, Integer> {
    List<ClientProducts> findAllByClientId(Client clientId);
    List<ClientProducts> findAllByProductId(Product productId);
    List<ClientProducts> findByClientIdAndProductId(Client clientId, Product productId);
}
