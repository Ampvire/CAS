package ru.edu.cas.client.service;

import org.springframework.stereotype.Service;
import ru.edu.cas.client.dao.Client;
import ru.edu.cas.client.dao.ClientSegment;
import ru.edu.cas.client.dao.ClientType;
import ru.edu.cas.client.repo.ClientSegmentRepository;
import ru.edu.cas.client.repo.ClientTypeRepository;
import ru.edu.cas.client.repo.ClientsRepository;
import ru.edu.cas.user.dao.User;
import ru.edu.cas.user.repo.UserRepository;

import java.util.Arrays;
import java.util.List;

/**
 * Класс предназначен для работы с таблицами clients, clientType, clientSegment.
 */
@Service
public class ClientService {
    private ClientsRepository clientsRepository;
    private ClientTypeRepository typeRepository;
    private ClientSegmentRepository segmentRepository;
    private UserRepository userRepository;

    public ClientService(ClientsRepository clientsRepository, ClientTypeRepository typeRepository
            , ClientSegmentRepository segmentRepository, UserRepository userRepository) {
        this.clientsRepository = clientsRepository;
        this.typeRepository = typeRepository;
        this.segmentRepository = segmentRepository;
        this.userRepository = userRepository;
    }

    /**
     * Метод возвращает список всех клиентов по id пользователя
     *
     * @param userId
     * @return
     */
    public List<Client> getAllClients(int userId) {
        User user = getUser(userId);
        return clientsRepository.findByUserId(user);
    }

    /**
     * Метод возвращает список все клиентов по id сегмента и незакрепленных за пользователем
     *
     * @param segment
     * @return
     */
    public List<Client> getAllClientsWithoutUser(String segment) {
        ClientSegment clientSegment = getSegment(segment);
        return clientsRepository.findByUserIdAndSegmentId(null, clientSegment);
    }

    /**
     * Метод возвращает записть из таблицы ClientSegment по названию сегмента
     *
     * @param segment
     * @return
     */
    public ClientSegment getSegment(String segment) {
        return segmentRepository.findBySegment(segment);
    }

    /**
     * Метод возвращает записть из таблицы ClientType по названию типа клиента
     *
     * @param type
     * @return
     */
    public ClientType getType(String type) {
        return typeRepository.findByType(type);
    }

    /**
     * Метод возвращает пользователя из таблицы user по id
     *
     * @param id
     * @return
     */
    public User getUser(int id) {
        return userRepository.getById(id);
    }

    /**
     * Метод возвращает список из таблицы ClientSegment
     *
     * @return
     */
    public List<ClientSegment> getListSegments() {
        return segmentRepository.findAll();
    }

    /**
     * Метод возвращает список из таблицы ClientType
     *
     * @return
     */
    public List<ClientType> getListTypes() {
        return typeRepository.findAll();
    }

    /**
     * Метод создает или редактирует запись в таблице Client
     *
     * @param name
     * @param type
     * @param inn
     * @param ogrn
     * @param segment
     * @return
     */
    public Client createClient(String name,
                               String type,
                               String inn,
                               String ogrn,
                               String segment) {
        List<String> parameters = Arrays.asList(name, inn);
        if (parameters.contains(null)) {
            throw new RuntimeException("Fields must not be null!");
        }
        Client client = getClient(inn);
        if (client == null) {
            client = new Client();
        }
        client.setName(name);
        client.setTypeId(getType(type));
        client.setInn(inn);
        client.setOgrn(ogrn);
        client.setSegmentId(getSegment(segment));
        return clientsRepository.save(client);
    }

    /**
     * Метод возвращает запись из таблицы Client по inn
     *
     * @param inn
     * @return
     */
    private Client getClient(String inn) {
        return clientsRepository.findByInn(inn);
    }
}
