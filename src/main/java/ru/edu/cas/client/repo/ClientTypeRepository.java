package ru.edu.cas.client.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.edu.cas.client.dao.ClientType;

public interface ClientTypeRepository extends JpaRepository<ClientType,Integer> {
    ClientType findByType(String type);
}
