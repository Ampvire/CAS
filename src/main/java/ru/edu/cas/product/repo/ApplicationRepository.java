package ru.edu.cas.product.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.edu.cas.client.dao.Client;
import ru.edu.cas.product.dao.Application;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application,Integer> {
    List<Application> findByClientId(Client client);

    List<Application> findByClientIdIn(List<Client> clients);
}
