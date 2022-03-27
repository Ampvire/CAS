package ru.edu.cas.client.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.edu.cas.client.dao.Client;
import ru.edu.cas.client.dao.ClientFinance;

import java.util.List;

public interface ClientFinanceRepository extends JpaRepository<ClientFinance, Integer> {
    List<ClientFinance> findByClientId(Client clientId);
    List<ClientFinance> findByClientIdAndDate(Client clientId, String date);
}
