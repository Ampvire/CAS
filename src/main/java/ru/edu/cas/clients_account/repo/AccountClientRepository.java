package ru.edu.cas.clients_account.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.edu.cas.client.dao.Client;
import ru.edu.cas.clients_account.dao.AccountClient;

public interface AccountClientRepository extends JpaRepository<AccountClient, Integer> {
    AccountClient findByLogin(String login);
    AccountClient findByClientId(Client client);
}
