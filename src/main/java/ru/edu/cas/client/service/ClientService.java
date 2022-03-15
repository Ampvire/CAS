package ru.edu.cas.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.edu.cas.client.dao.Client;
import ru.edu.cas.client.dao.ClientSegment;
import ru.edu.cas.client.dao.ClientType;
import ru.edu.cas.client.repo.ClientSegmentRepository;
import ru.edu.cas.client.repo.ClientTypeRepository;
import ru.edu.cas.client.repo.ClientsRepository;
import ru.edu.cas.user.dao.User;
import ru.edu.cas.user.repo.UserRepository;

import java.util.List;

@Component
public class ClientService {
    private ClientsRepository clientsRepository;
    private ClientTypeRepository typeRepository;
    private ClientSegmentRepository segmentRepository;
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setClientsRepository(ClientsRepository clientsRepository) {
        this.clientsRepository = clientsRepository;
    }

    @Autowired
    public void setTypeRepository(ClientTypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @Autowired
    public void setSegmentRepository(ClientSegmentRepository segmentRepository) {
        this.segmentRepository = segmentRepository;
    }

    public List<Client> getAllClients(int userId) {
        return clientsRepository.findByUserId(userId);
    }

    public List<Client> getAllClientsWithoutUser(int segmentId) {
        return clientsRepository.findByUserIdAndSegmentId(null, segmentId);
    }

    public ClientSegment getSegment(String segment) {
        return segmentRepository.findBySegment(segment);
    }

    public ClientType getType(String type) {
        return typeRepository.findByType(type);
    }
    public User getUser(int id){
        return userRepository.getById(id);
    }

    public List<ClientSegment> getListSegments(){
        return segmentRepository.findAll();
    }

    public List<ClientType> getListTypes(){
        return typeRepository.findAll();
    }

    public Client createClient(String name,
                               String type,
                               String inn,
                               String ogrn,
                               String segment,
                               String userId) {
        Client client = new Client();
        client.setName(name);
        client.setTypeId(getType(type));
        client.setInn(inn);
        client.setOgrn(ogrn);
        client.setSegmentId(getSegment(segment));
        client.setUserId(getUser(Integer.parseInt(userId)));
        clientsRepository.save(client);
        return client;
    }
}
