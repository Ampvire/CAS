package ru.edu.cas.client.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.edu.cas.client.dao.ClientSegment;

public interface ClientSegmentRepository extends JpaRepository<ClientSegment,Integer> {
    ClientSegment findBySegment(String segment);
}
