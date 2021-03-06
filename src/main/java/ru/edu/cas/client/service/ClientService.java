package ru.edu.cas.client.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.edu.cas.client.dao.*;
import ru.edu.cas.client.repo.*;
import ru.edu.cas.clients_account.dao.AccountClient;
import ru.edu.cas.clients_account.service.AccountClientService;
import ru.edu.cas.product.dao.Product;
import ru.edu.cas.product.service.ProductService;
import ru.edu.cas.user.dao.Category;
import ru.edu.cas.user.dao.Role;
import ru.edu.cas.user.dao.User;
import ru.edu.cas.user.repo.UserRepository;
import ru.edu.cas.user.service.UserService;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс предназначен для работы с таблицами clients, clientType, clientSegment, finance, report
 */
@Service
public class ClientService {
    private ClientRepository clientsRepository;
    private ClientTypeRepository clientTypeRepository;
    private ClientSegmentRepository clientSegmentRepository;
    private UserRepository userRepository;
    private ClientFinanceRepository clientFinanceRepository;
    private ClientReportRepository clientReportRepository;
    private ClientProductsRepository clientProductsRepository;

    private ProductService productService;
    private AccountClientService accountClientService;
    private UserService userService;


    public ClientService(ClientRepository clientsRepository, ClientTypeRepository clientTypeRepository
            , ClientSegmentRepository clientSegmentRepository, UserRepository userRepository
            , ClientFinanceRepository clientFinanceRepository
            , ClientReportRepository clientReportRepository
            , ClientProductsRepository clientProductsRepository
            , AccountClientService accountClientService, UserService userService) {
        this.clientsRepository = clientsRepository;
        this.clientTypeRepository = clientTypeRepository;
        this.clientSegmentRepository = clientSegmentRepository;
        this.userRepository = userRepository;
        this.clientFinanceRepository = clientFinanceRepository;
        this.clientReportRepository = clientReportRepository;
        this.accountClientService = accountClientService;
        this.userService = userService;
        this.clientProductsRepository = clientProductsRepository;
    }

    @Autowired
    public void setService(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Метод возвращает список всех клиентов по id пользователя
     *
     * @param userId
     * @return
     */
    public List<Client> getAllClients(int userId) {
        User user = userService.getUser(userId);
        return clientsRepository.findByUserId(user);
    }


    /**
     * Получить клиента по идентификатору
     */
    public Client getClientById(int id) {
        return clientsRepository.findById(id);
    }


    /**
     * Получить идентификатор сегмента
     *
     * @param segment -объект класса Segment
     */
    public int getSegmentId(ClientSegment segment) {
        return segment.getId();
    }


    /**
     * Метод для выбора клиентов возможных для закрепления
     */
    public boolean chekUserCategory(ClientSegment segment, User user) {
        int segmentId = getSegmentId(segment);
        Category category = userService.getCategory(String.valueOf(segmentId));
        List<User> users = userService.getUsersByCategory(category);
        return users.contains(user);
    }


    /**
     * Метод по выручке и штату сотрудников определяет и возвращает id сегмента
     *
     * @param clientId -идентификатор клиент по которому необходимо определить сегмент
     */
    public int calcSegmentId(int clientId) {
        Client client = clientsRepository.getById(clientId);
        List<ClientFinance> financeList = clientFinanceRepository.findByClientId(client);
        if (financeList.isEmpty()) {
            return 1;
        }

        ClientFinance finance = financeList.get(financeList.size() - 1);

        int staf = finance.getStaf();
        int revenue = finance.getRevenue();

        if (staf < 250 || revenue <= 400_000_000) {
            return 1;
        }
        return 2;
    }

    /**
     * Метод возвращает список всех продуктов клиента
     *
     * @param inn -inn клиента по которому нужно получить информацию
     * @return список(без дубликатов) продуктов клиента
     */
    public List<String> getAllProductsByClientInn(String inn) {
        List<String> clientProductsName = new ArrayList<>();
        Client client = clientsRepository.findByInn(inn);
        List<ClientProducts> clientProducts = clientProductsRepository.findAllByClientId(client);
        Set<String> products = clientProducts.stream()
                .map(ClientProducts::getProductId)
                .map(Product::getName)
                .collect(Collectors.toSet());
        clientProductsName.addAll(products);
        return clientProductsName;
    }

    /**
     * Метод возвращает список всех продуктов клиента
     *
     * @param clientId -идентификатор клиента по которому нужно получить информацию
     * @return список(без дубликатов) продуктов клиента
     */
    public Set<String> getAllProductsByClient(int clientId) {
        Client client = clientsRepository.findById(clientId);
        List<ClientProducts> clientProducts = clientProductsRepository.findAllByClientId(client);
        Set<String> products = clientProducts.stream()
                .map(ClientProducts::getProductId)
                .map(Product::getName)
                .collect(Collectors.toSet());
        return products;
    }


    /**
     * Изменяет менеджера у клиента
     */
    public void addManager(Client client, User user) {

        if (user == null || client == null) {
            throw new RuntimeException("Fields must not be null!");
        }
        client.setUserId(user);
        clientsRepository.save(client);
    }

    /**
     * Метод добавляет продукт клиенту
     *
     * @param clientId  -идентификатор клиента
     * @param productId -идентификатор продукта
     * @return при успешном добавлении записи возвращает true иначе false
     */

    public boolean createClientProduct(int clientId, int productId) {
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
     *
     * @param client  -клиент
     * @param product -продукт
     * @return при успешном добавлении записи возвращает true иначе false
     */

    public ClientProducts createClientProduct(Client client, Product product) {
        if (client == null || product == null) {
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
        return clientSegmentRepository.findBySegment(segment);
    }

    /**
     * Метод возвращает записть из таблицы ClientType по названию типа клиента
     *
     * @param type - тип клиента
     * @return ClientType
     */
    public ClientType getType(String type) {
        return clientTypeRepository.findByType(type);
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
        return clientSegmentRepository.findAll();
    }

    /**
     * Метод возвращает список из таблицы ClientType
     *
     * @return - List<ClientType>
     */
    public List<ClientType> getListTypes() {
        return clientTypeRepository.findAll();
    }

    /**
     * Метод создает или редактирует запись в таблице Client
     *
     * @param name    -название клиента
     * @param type    -тип клиента
     * @param inn     -inn клиента
     * @param ogrn    -ogrn клиента
     * @param segment -segment клиента
     * @return - Client
     */
    public Client createClient(String name,
                               String type,
                               String inn,
                               String ogrn,
                               String segment) {
        List<String> parameters = Arrays.asList(name, inn);
        boolean isSaveAccount = false;
        if (parameters.contains(null) || parameters.contains("")) {
            return null;
        }
        Client client = getClient(inn);
        if (client == null) {
            client = new Client();
            isSaveAccount = true;
        }
        client.setName(name);
        client.setTypeId(getType(type));
        client.setInn(inn);
        client.setOgrn(ogrn);
        client.setSegmentId(getSegment(segment));
        client = clientsRepository.save(client);
        if (isSaveAccount) {
            Random random = new Random();
            Role role = userService.getRole("Client");
            String login = name + random.nextInt(101);
            String password = RandomStringUtils.randomAlphabetic(5);
            String encodedPassword = Base64.getEncoder().encodeToString(password.getBytes());
            accountClientService.saveOrUpdate(login, encodedPassword, client, role);
        }
        return client;
    }

    /**
     * Метод возвращает запись из таблицы Client по inn
     *
     * @param inn - inn клиента
     * @return Client
     */
    public Client getClient(String inn) {
        return clientsRepository.findByInn(inn);
    }

    /**
     * Метод возвращает записи из таблицы finance по inn
     *
     * @param inn - ИНН клиента
     * @return List<ClientFinance> - список финансовых показателей клиента
     */
    public List<ClientFinance> getAllFinanceByClientInn(String inn) {
        Client client = getClient(inn);
        return clientFinanceRepository.findByClientId(client);
    }

    /**
     * Метод возвращает записи из таблицы finance по inn и date
     *
     * @param inn  - ИНН клиента
     * @param date - дата записи в таблице
     * @return ClientFinance - список финансовых показателей клиента
     */
    public ClientFinance getAllFinanceByClientInnAndDate(String inn, String date) {

        Client client = getClient(inn);
        return clientFinanceRepository.findByClientIdAndDate(client, date);
    }

    /**
     * Метод возвращает отчет по некоторым коэффициентам клиента из таблицы report по inn
     *
     * @param inn - ИНН клиента
     * @return List<ClientReport> - список отчетов по клиенту
     */
    public List<ClientReport> getAllReportByClientInn(String inn) {

        Client client = getClient(inn);
        List<ClientFinance> financeList = getAllFinanceByClientInn(inn);
        if (financeList.size() == 0) {
            return clientReportRepository.findByClientId(client);
        } else {
            for (ClientFinance finance : financeList) {
                ClientReport clientReport = reportCounter(finance);
                clientReportRepository.save(clientReport);
            }
        }
        return clientReportRepository.findByClientId(client);
    }

    /**
     * Метод возвращает отчет по некоторым коэффициентам клиента из таблицы report по inn и дате
     *
     * @param inn  - ИНН клиента
     * @param date - дата записи
     * @return List<ClientReport> - список отчетов по клиенту
     */
    public List<ClientReport> getAllReportByClientInnAndDate(String inn, String date) {

        Client client = getClient(inn);
        List<ClientFinance> financeList = getAllFinanceByClientInn(inn);
        if (financeList.size() == 0) {
            return clientReportRepository.findByClientIdAndDate(client, date);
        } else {
            for (ClientFinance finance : financeList) {
                ClientReport clientReport = reportCounter(finance);
                clientReportRepository.save(clientReport);
            }
        }
        return clientReportRepository.findByClientIdAndDate(client, date);
    }

    /**
     * Метод возвращает последний отчет по некоторым коэффициентам клиента из таблицы report
     *
     * @param inn - ИНН клиента
     * @return ClientReport - отчёт по клиенту
     */
    public ClientReport getLastReportByClientInn(String inn) {

        Client client = getClient(inn);
        ClientReport lastReport;
        List<ClientReport> clientReports = getAllReportByClientInn(inn);
        if (clientReports.size() == 0) {
            return clientReportRepository.findByClientIdAndFinanceId(client, null);
        } else {
            lastReport = clientReports.get(clientReports.size() - 1);
        }
        ClientFinance finance = lastReport.getFinanceId();
        return clientReportRepository.findByClientIdAndFinanceId(client, finance);
    }

    /**
     * Вспомогательный метод
     * Метод производит расчеты коэффициентов из таблицы report по финансовым показателям клиента
     *
     * @param finance - финансовые показания клиента
     * @return - отчет по клиенту
     */
    private ClientReport reportCounter(ClientFinance finance) {

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
        if (clientReport == null) {
            clientReport = new ClientReport();
            clientReport.setClientId(clientId);
            clientReport.setFinanceId(finance);
        }

        if (revenue == 0) {
            profitabilitySale = 0;
        } else {
            profitabilitySale = profit / revenue;
        }
        clientReport.setProfitabilitySale(profitabilitySale);

        if (reserves == 0) {
            inventoryTurnOver = 0;
        } else {
            inventoryTurnOver = costPrice / reserves;
        }
        clientReport.setInventoryTurnover(inventoryTurnOver);

        if (loans == 0) {
            quickLiquidity = 0;
        } else {
            quickLiquidity = (assets - reserves) / loans;
        }
        clientReport.setQuickLiquidity(quickLiquidity);

        return clientReport;
    }

    /**
     * Метод возвращает список из значений стоимости займа и ежемесячного платежа
     *
     * @param sum     - сумма
     * @param years   - число лет
     * @param percent - ставка займа
     * @return
     */

    public List<Integer> calculationLoans(String sum, String years, String percent) {
        int sumInt = Integer.parseInt(sum);
        int yearsInt = Integer.parseInt(years);
        int percentInt = Integer.parseInt(percent);
        double percentOneMonth = percentInt / 12;
        int sumLoans = sumInt + ((sumInt * percentInt / 100) * yearsInt);
        int monthPayment = sumLoans / yearsInt / 12;

        List<Integer> info = new ArrayList<>();
        info.add(monthPayment);
        info.add(sumLoans);
        return info;
    }

    /**
     * Метод сохраняет финансовую отчетность клиента и
     * на ее результатах изменяет или не изменяет сегмент,
     * к которому клиент принадлежит
     *
     * @param inn       - ИНН
     * @param revenue   - доход
     * @param staf      - персонал
     * @param costPrice - себестоимость
     * @param assets    - активы
     * @param reserves  - резервы
     * @param profit    - прибыль
     * @param date      - дата
     * @return - ClientFinance - финансовая отчётность
     */
    public ClientFinance saveFinanceInfo(String inn, String revenue, String staf, String costPrice, String assets,
                                         String reserves, String profit, String date) {
        Client client = getClient(inn);
        ClientFinance finance = getAllFinanceByClientInnAndDate(inn, date);

        if (finance != null) {
            return null;
        } else {
            finance = new ClientFinance();
        }
        finance.setClientId(client);
        finance.setAssets(Integer.parseInt(assets));
        finance.setRevenue(Integer.parseInt(revenue));
        finance.setCostPrice(Integer.parseInt(costPrice));
        finance.setStaf(Integer.parseInt(staf));
        finance.setReserves(Integer.parseInt(reserves));
        finance.setProfit(Integer.parseInt(profit));
        clientFinanceRepository.save(finance);
        finance.setDate(date);
        finance = clientFinanceRepository.save(finance);
        int countedSegment = calcSegmentId(client.getId());
        ClientSegment newClientSegment = clientSegmentRepository.findById(countedSegment);
        client.setSegmentId(newClientSegment);
        clientsRepository.save(client);
        return finance;
    }

    public List<Integer> getListOfYears() {
        List<Integer> years = new ArrayList<>();
        int year = 2010;
        while (year < LocalDate.now().getYear()) {
            years.add(year);
            year++;
        }
        return years;
    }

    /**
     * Метод возвращает последнюю отчетность клиента
     *
     * @param inn - ИНН
     * @return - ClientFinance - финансовая отчётность
     */
    public ClientFinance getLastFinance(String inn) {
        Client client = getClient(inn);
        return clientFinanceRepository.findFirstByClientIdOrderByIdDesc(client);
    }

    /**
     * Метод возвращает клиента по его логину
     *
     * @param login - логин
     * @return - Client - клиент
     */
    public Client getClientByLogin(String login) {
        AccountClient accountClient = accountClientService.getAccountByLogin(login);
        Client client = getClientById(accountClient.getClientId().getId());
        return client;
    }

    /**
     * Метод меняет данные клиента в таблице client
     * @param client  Client
     * @return Client
     */
    public Client updateClient(Client client) {
        client = clientsRepository.save(client);
        return client;
    }
}
