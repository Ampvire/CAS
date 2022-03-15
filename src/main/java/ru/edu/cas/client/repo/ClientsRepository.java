package ru.edu.cas.client.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.edu.cas.client.dao.Client;

import java.util.List;

public interface ClientsRepository extends JpaRepository<Client,Integer> {
    List<Client> findByUserId(int userId);
    List<Client> findByUserIdAndSegmentId(Integer userId, int segmentId);
}
