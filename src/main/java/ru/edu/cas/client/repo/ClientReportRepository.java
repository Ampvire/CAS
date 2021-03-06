package ru.edu.cas.client.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.edu.cas.client.dao.Client;
import ru.edu.cas.client.dao.ClientFinance;
import ru.edu.cas.client.dao.ClientReport;

import java.util.List;

public interface ClientReportRepository extends JpaRepository<ClientReport, Integer> {

    List<ClientReport> findByClientId(Client clientId);
    List<ClientReport> findByClientIdAndDate(Client clientId, String date);
    ClientReport findByClientIdAndFinanceId(Client clientId, ClientFinance financeId);
}
