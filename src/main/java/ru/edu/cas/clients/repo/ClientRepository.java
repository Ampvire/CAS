package ru.edu.cas.clients.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.edu.cas.clients.dao.Clients;

public interface ClientRepository extends JpaRepository<Clients, String> {
    /**
     * Изменить значение в поле clients.segment_id (записать идентификатор сегмента)
     * */
    @Modifying
    @Query("update Clients c set c.segmentId = :segmentId")
    int updateSegmentId (@Param("segmentId")int segmentId);
}
