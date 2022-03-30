package ru.edu.cas.client.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.edu.cas.client.dao.Client;
import ru.edu.cas.client.dao.ClientSegment;
import ru.edu.cas.user.dao.User;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client,Integer> {
    List<Client> findByUserId(User userId);
    List<Client> findByUserIdAndSegmentId(User userId, ClientSegment segmentId);
    Client findByInn(String inn);
    Client findById(int id);
}
