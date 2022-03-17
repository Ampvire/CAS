package ru.edu.cas.client.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.edu.cas.client.dao.Client;
import ru.edu.cas.client.dao.ClientSegment;
import ru.edu.cas.user.dao.User;

import java.util.List;

public interface ClientsRepository extends JpaRepository<Client,Integer> {
    List<Client> findByUserId(User userId);
    List<Client> findByUserIdAndSegmentId(Integer userId, ClientSegment segmentId);
    Client findByInn(String inn);
}
