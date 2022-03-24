package ru.edu.cas.clients_account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.edu.cas.client.dao.Client;
import ru.edu.cas.client.repo.ClientRepository;
import ru.edu.cas.clients_account.dao.AccountClient;
import ru.edu.cas.clients_account.repo.AccountClientRepository;
import ru.edu.cas.user.dao.Role;
import ru.edu.cas.user.repo.RoleRepository;

import java.util.Arrays;
import java.util.List;

/**
 * Класс предназначен для работы с таблицами account_client.
 */
@Service
public class AccountClientService {
    private AccountClientRepository repository;
    private ClientRepository clientRepository;
    private RoleRepository roleRepository;

    @Autowired
    public void setRepository(AccountClientRepository repository) {
        this.repository = repository;
    }

    @Autowired
    public void setClientRepository(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * Метод добавляет запись в таблицу account_client
     *
     * @param login     - логин клиента
     * @param password- пароль клиента
     * @param client-   клиент из таблицы client
     * @param role-     роль клиента
     * @return -AccountClient
     */
    public AccountClient saveOrUpdate(String login, String password, Client client, Role role) {
        AccountClient accountClient = repository.findByLogin(login);
        List<String> parameters = Arrays.asList(login,password);
        if (parameters.contains("")||parameters.contains(null)){
            return null;
        }
        if (accountClient == null) {
            accountClient = new AccountClient();
        }
        accountClient.setLogin(login);
        accountClient.setPassword(password);
        accountClient.setClientId(client);
        accountClient.setRoleId(role);
        return save(accountClient);
    }

    /**
     * Метод выбирает запись из таблицы account_client
     *
     * @param client -клиент из таблицы client
     * @return AccountClient
     */
    public AccountClient getAccount(Client client) {
        AccountClient accountClient = repository.findByClientId(client);
        return accountClient;
    }

    /**
     * Метод добавляет запись в таблицу account_client
     * @param accountClient AccountClient
     * @return AccountClient
     */
    public AccountClient save(AccountClient accountClient){
       return repository.save(accountClient);
    }
}
