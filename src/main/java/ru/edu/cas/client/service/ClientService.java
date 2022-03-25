package ru.edu.cas.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.edu.cas.client.dao.*;
import ru.edu.cas.client.repo.*;
import ru.edu.cas.product.dao.Product;
import ru.edu.cas.user.dao.User;
import ru.edu.cas.user.repo.UserRepository;
import ru.edu.cas.product.service.ProductService;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Класс предназначен для работы с таблицами clients, clientType, clientSegment, finance, report
 */
@Service
public class ClientService {
    private ClientRepository clientsRepository;
    private ClientTypeRepository typeRepository;
    private ClientSegmentRepository segmentRepository;
    private UserRepository userRepository;
    private ClientFinanceRepository clientFinanceRepository;
    private ClientReportRepository clientReportRepository;
    private ClientProductsRepository clientProductsRepository;

    private ProductService productService;


    public ClientService(ClientRepository clientsRepository, ClientTypeRepository typeRepository
            , ClientSegmentRepository segmentRepository, UserRepository userRepository
            , ClientFinanceRepository clientFinanceRepository
            , ClientReportRepository clientReportRepository
    ,ClientProductsRepository clientProductsRepository) {
        this.clientsRepository = clientsRepository;
        this.typeRepository = typeRepository;
        this.segmentRepository = segmentRepository;
        this.userRepository = userRepository;
        this.clientFinanceRepository = clientFinanceRepository;
        this.clientReportRepository = clientReportRepository;
        this.clientProductsRepository = clientProductsRepository;
    }

    @Autowired
    public void setService (ProductService productService) {
        this.productService = productService;
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
     * Получить клиента по идентификатору
     * */
    public Client getClientById(int id){
        return clientsRepository.findById(id);
    }


    /**
     * Метод по выручке и штату сотрудников определяет и возвращает id сегмента
     * @param clientId -идентификатор клиент по которому необходимо определить сегмент
     * */
    public int calcSegmentId(int clientId){
        Client client = clientsRepository.getById(clientId);
        List<ClientFinance> financeList = clientFinanceRepository.findByClientId(client);
        if(financeList.isEmpty()){
            return 1;
        }

        ClientFinance finance = financeList.get(financeList.size()-1);

        int staf = finance.getStaf();
        int revenue =finance.getRevenue();

        if(staf < 250 || revenue <= 400_000_000){
            return 1;
        }
        return 2;
    }


    /**
     * Метод возвращает список всех продуктов клиента
     * @param clientId -идентификатор клиента по которому нужно получить информацию
     * @return  список(без дубликатов) продуктов клиента
     * */
    public Set<String> getAllProductsByClient(int clientId){
        Client client = clientsRepository.findById(clientId);
        List<ClientProducts> clientProducts  = clientProductsRepository.findAllByClientId(client);
        Set<String> products = clientProducts.stream()
                .map(ClientProducts::getProductId)
                .map(Product::getName)
                .collect(Collectors.toSet());
        return products;
    }

    /**
     * Метод добавляет продукт клиенту
     * @param clientId -идентификатор клиента
     * @param productId -идентификатор продукта
     * @return при успешном добавлении записи возвращает true иначе false
     * */
    public boolean createClientProduct(int clientId, int productId){
        Client client = getClientById(clientId);
        Product product = productService.getProductById(productId);

       List<Integer> parameters = Arrays.asList(clientId, productId);
        if (parameters.contains(null)) {
            throw new RuntimeException("Fields must not be null!");
        }

        ClientProducts clientProducts = new ClientProducts();
        clientProducts.setClientId(client);
        clientProducts.setProductId(product);
        clientProductsRepository.save(clientProducts);
        return true;
    }

    /**
     * Метод добавляет продукт клиенту
     * @param  client -клиент
     * @param product -продукт
     * @return при успешном добавлении записи возвращает true иначе false
     * */
    public ClientProducts createClientProduct(Client client, Product product){
        if (client == null || product ==null) {
            throw new RuntimeException("Fields must not be null!");
        }

        ClientProducts clientProducts = new ClientProducts();
        clientProducts.setClientId(client);
        clientProducts.setProductId(product);
        clientProductsRepository.save(clientProducts);
        return clientProducts;
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
     * @param segment - сегмент клиента
     * @return ClientSegment
     */
    public ClientSegment getSegment(String segment) {
        return segmentRepository.findBySegment(segment);
    }

    /**
     * Метод возвращает записть из таблицы ClientType по названию типа клиента
     *
     * @param type - тип клиента
     * @return ClientType
     */
    public ClientType getType(String type) {
        return typeRepository.findByType(type);
    }

    /**
     * Метод возвращает пользователя из таблицы user по id
     *
     * @param id - id пользователя
     * @return User
     */
    public User getUser(int id) {
        return userRepository.getById(id);
    }

    /**
     * Метод возвращает список из таблицы ClientSegment
     *
     * @return List<ClientSegment>
     */
    public List<ClientSegment> getListSegments() {
        return segmentRepository.findAll();
    }

    /**
     * Метод возвращает список из таблицы ClientType
     *
     * @return - List<ClientType>
     */
    public List<ClientType> getListTypes() {
        return typeRepository.findAll();
    }

    /**
     * Метод создает или редактирует запись в таблице Client
     *
     * @param name -название клиента
     * @param type -тип клиента
     * @param inn -inn клиента
     * @param ogrn -ogrn клиента
     * @param segment -segment клиента
     * @return - Client
     */
    public Client createClient(String name,
                               String type,
                               String inn,
                               String ogrn,
                               String segment) {
        List<String> parameters = Arrays.asList(name, inn);
        if (parameters.contains(null)||parameters.contains("")) {
           return null;
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
     * @param inn - inn клиента
     * @return Client
     */
    private Client getClient(String inn) {
        return clientsRepository.findByInn(inn);
    }

    /**
     * Метод возвращает записи из таблицы finance по inn
     * @param inn - ИНН клиента
     * @return List<ClientFinance> - список финансовых показателей клиента
     */
    public List<ClientFinance> getAllFinanceByClientInn(String inn){
        Client client = getClient(inn);
        return clientFinanceRepository.findByClientId(client);
    }

    /**
     * Метод возвращает записи из таблицы finance по inn и date
     * @param inn - ИНН клиента
     * @param date - дата записи в таблице
     * @return List<ClientFinance> - список финансовых показателей клиента
     */
    public List<ClientFinance> getAllFinanceByClientInnAndDate(String inn, String date){

        Client client = getClient(inn);
        return clientFinanceRepository.findByClientIdAndDate(client, date);
    }



    /**
     * Метод возвращает отчет по некоторым коэффициентам клиента из таблицы report по inn
     * @param inn - ИНН клиента
     * @return List<ClientReport> - список отчетов по клиенту
     */
    public List<ClientReport> getAllReportByClientInn(String inn){

        Client client = getClient(inn);
        List<ClientFinance> financeList = getAllFinanceByClientInn(inn);
        if (financeList.size() == 0){
            return clientReportRepository.findByClientId(client);
        }
        else {
            for (ClientFinance finance : financeList){
                ClientReport clientReport = reportCounter(finance);
                clientReportRepository.save(clientReport);
            }
        }
        return clientReportRepository.findByClientId(client);
    }

    /**
     * Метод возвращает отчет по некоторым коэффициентам клиента из таблицы report по inn и дате
     * @param inn - ИНН клиента
     * @param date - дата записи
     * @return List<ClientReport> - список отчетов по клиенту
     */
    public List<ClientReport> getAllReportByClientInnAndDate(String inn, String date){

        Client client = getClient(inn);
        List<ClientFinance> financeList = getAllFinanceByClientInnAndDate(inn, date);
        if (financeList.size() == 0){
            return clientReportRepository.findByClientIdAndDate(client, date);
        }
        else {
            for (ClientFinance finance : financeList){
                ClientReport clientReport = reportCounter(finance);
                clientReportRepository.save(clientReport);
            }
        }
        return clientReportRepository.findByClientIdAndDate(client, date);
    }

    /**
     * Метод возвращает последний отчет по некоторым коэффициентам клиента из таблицы report
     * @param inn - ИНН клиента
     * @return ClientReport - отчёт по клиенту
     */
    public ClientReport getLastReportByClientInn(String inn){

        Client client = getClient(inn);
        ClientReport lastReport;
        List<ClientReport> clientReports = getAllReportByClientInn(inn);
        if (clientReports.size() == 0){
            return clientReportRepository.findByClientIdAndFinanceId(client, null);
        }
        else {
            lastReport = clientReports.get(clientReports.size() - 1);
        }
        ClientFinance finance = lastReport.getFinanceId();
        return clientReportRepository.findByClientIdAndFinanceId(client, finance);
    }

    /**
     * Вспомогательный метод
     * Метод производит расчеты коэффициентов из таблицы report по финансовым показателям клиента
     * @param finance - финансовые показания клиента
     * @return - отчет по клиенту
     */
    private ClientReport reportCounter(ClientFinance finance){

        Client clientId = finance.getClientId();
        int profit = finance.getProfit();
        int revenue = finance.getRevenue();
        int costPrice = finance.getCostPrice();
        int reserves = finance.getReserves();
        int assets = finance.getAssets();
        int loans = finance.getLoans();
        int profitabilitySale;
        int inventoryTurnOver;
        int quickLiquidity;

        ClientReport clientReport = clientReportRepository.findByClientIdAndFinanceId(clientId, finance);
        if (clientReport == null){
            clientReport = new ClientReport();
            clientReport.setClientId(clientId);
            clientReport.setFinanceId(finance);
        }

        if (revenue == 0){
            profitabilitySale = 0;
        } else {
            profitabilitySale = profit / revenue;
        }
        clientReport.setProfitabilitySale(profitabilitySale);

        if (reserves == 0){
            inventoryTurnOver = 0;
        } else {
            inventoryTurnOver = costPrice / reserves;
        }
        clientReport.setInventoryTurnover(inventoryTurnOver);

        if (loans == 0){
            quickLiquidity = 0;
        } else {
            quickLiquidity = (assets - reserves) / loans;
        }
        clientReport.setQuickLiquidity(quickLiquidity);

        return clientReport;
    }
}
