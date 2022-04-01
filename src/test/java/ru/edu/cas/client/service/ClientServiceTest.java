package ru.edu.cas.client.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.edu.cas.client.dao.*;
import ru.edu.cas.product.dao.Product;
import ru.edu.cas.product.service.ProductService;
import ru.edu.cas.user.dao.User;

import java.util.List;
import java.util.Set;

/**
 * Тестовый класс. Юнит тесты к методам класса {@link ClientService}
 */
@SpringBootTest
class ClientServiceTest {
    private ClientService service;
    private ProductService productService;

    @Autowired
    public void setService(ClientService service) {
        this.service = service;
    }

    @Autowired
    public void setService(ProductService productService) {
        this.productService = productService;
    }


    /**
     * Успешное выполнение метода .getClientById()
     * Добавление нового клиента, по ID нового клиента осуществляется вызов метода .getClientById().
     * Идентификатор полученного клиента сверяется с идентификатором запрошенного клиента(проверка клиента на равенство)
     */
    @Test
    public void getClientById() {
        Client testClient = service.createClient("Копыта", "ООО", "11111111111111111", "44444444444", "");
        int clientId = testClient.getId();
        Client resultClient = service.getClientById(clientId);
        Assertions.assertEquals(testClient.getId(), resultClient.getId());
    }


    /**
     * Успешное выполнение метода .createClientProduct()
     *
     */
    @Test
    public void createClientProduct_Test() {
        int clientId = 2;
        int productId = 2;

        Client client = service.getClientById(clientId);
        Product product = productService.getProductById(productId);
        Set <String> currentProductsByClient = service.getAllProductsByClient(clientId);
        service.createClientProduct(client, product);

        Set <String> resultProductsByClient = service.getAllProductsByClient(clientId);


        Assertions.assertTrue(currentProductsByClient.size() + 1 >= resultProductsByClient.size());
    }

    /**
     * Успешное выполнение метода .createClientProduct()
     *
     */
    @Test
    public void createClientProductById_Test() {
        int clientId = 2;
        int productId = 2;

        Set <String> currentProductsByClient = service.getAllProductsByClient(clientId);
        service.createClientProduct(clientId, productId);

        Set <String> resultProductsByClient = service.getAllProductsByClient(clientId);


        Assertions.assertTrue(currentProductsByClient.size() + 1 >= resultProductsByClient.size());
    }

    /**
     * Успешное выполнение метода .getAllProductsByClient()
     * Проводится проверка будет ли полный список продуктов клиента содержать добавленный в тесте продукт
     */
    @Test
    void getAllProductsByClient_Test() {
        int clientId = 2;
        int productId = 2;
        Client client = service.getClientById(clientId);
        Product product = productService.getProductById(productId);
        service.createClientProduct(client, product);
        String productName = product.getName();
        Set<String> products = service.getAllProductsByClient(clientId);
        Assertions.assertTrue(products.contains(productName));
    }


    /**
     * Успешное выполнение метода .calcSegmentId
     * Проводятся две проверки: соответствуют ли параметры клиента сегменту 1 и проверяется результат метода .calcSegmentId
     */
    @Test
    public void calcSegmentId_Test() {
        int clientId = 2;
        Client testClient = service.getClientById(clientId);
        List<ClientFinance> financeList = service.getAllFinanceByClientInn(testClient.getInn());
        ClientFinance actualFinanceByClient = financeList.get(financeList.size() - 1);

        int staff = actualFinanceByClient.getStaf();
        int revenue = actualFinanceByClient.getRevenue();

        boolean test_1 = staff < 250 && revenue < 400_000_000;
        boolean test_2 = service.calcSegmentId(clientId) == 1;
        Assertions.assertEquals(test_1, test_2);

    }


    /**
     * Успешное выполение метода getSegment()
     */
    @Test
    void getSegment() {
        ClientSegment segment = service.getSegment("Микро и малый бизнес");
        Assertions.assertNotNull(segment);
    }

    /**
     * Успешное выполение метода getType()
     */
    @Test
    void getType() {
        ClientType type = service.getType("ИП");
        Assertions.assertNotNull(type);
        Assertions.assertEquals("Индивидуальный предприниматель", type.getNote());
    }

    /**
     * Успешное выполение метода getUser()
     */
    @Test
    void getUser() {
        User user = service.getUser(1);
        Assertions.assertNotNull(user);
    }

    /**
     * Успешное выполение метода getListSegments()
     */
    @Test
    void getListSegments() {
        List<ClientSegment> segments = service.getListSegments();
        Assertions.assertEquals(2, segments.size());
    }

    /**
     * Успешное выполение метода getListTypes()
     */
    @Test
    void getListTypes() {
        List<ClientType> types = service.getListTypes();
        Assertions.assertEquals(4, types.size());
    }

    /**
     * Успешное выполение метода createClient()
     */
    @Test
    void createClient() {
        Client client = service.createClient("test", "ООО", "123"
                , "123", "Микро и малый бизнес");
        Assertions.assertNotNull(client);
    }


    /**
     * Успешное выполнение метода getAllFinanceByClientInn
     */
    @Test
    void getAllFinanceByClientInn(){

        String inn = "33333898989";
        List<ClientFinance> finances = service.getAllFinanceByClientInn(inn);
        Assertions.assertEquals(2, finances.size());
    }

    /**
     * Успешное выполнение метода getAllFinanceByClientInnAndDate
     */
    @Test
    void getAllFinanceByClientInnAndDate(){

        String inn = "33333898989";
        String date = "1998-01-07";
        ClientFinance finances = service.getAllFinanceByClientInnAndDate(inn, date);
        Assertions.assertNotNull(finances);
    }

    /**
     * Успешное выполнение метода getAllReportByClientInn
     */
    @Test
    void getAllReportByClientInn(){

        String inn = "33333898989";
        List<ClientFinance> finances = service.getAllFinanceByClientInn(inn);
        List<ClientReport> reports = service.getAllReportByClientInn(inn);
        Assertions.assertEquals(finances.size(), reports.size());
    }

    /**
     * Успешное выполнение метода getAllReportByClientInnAndDate
     */
    @Test
    void getAllReportByClientInnAndDate(){

        String inn = "33333898989";
        String date = "2022-03-24";
        List<ClientReport> reports = service.getAllReportByClientInnAndDate(inn, date);
        Assertions.assertEquals(2, reports.size());
    }

    /**
     * Успешное выполнение метода getLastReportByClientInn
     */
    @Test
    void getLastReportByClientInn(){

        String inn = "33333898989";
        ClientReport lastReport = service.getLastReportByClientInn(inn);
        System.out.println(lastReport);
    }

    /**
     * Успешное выполнение метода saveFinanceInfo
     */
    @Test
    void saveFinanceInfoTest(){

        String inn = "33333898989";
        Client client = service.getClient(inn);
        ClientSegment clientSegment = client.getSegmentId();
        int segmentId = clientSegment.getId();
        Assertions.assertEquals(2, segmentId);

        service.saveFinanceInfo(inn, "1000000", "240", "21000", "78000", "3150", "83000","31-12-2004");

        client = service.getClient(inn);
        clientSegment = client.getSegmentId();
        segmentId = clientSegment.getId();
        Assertions.assertEquals(1, segmentId);

        service.saveFinanceInfo(inn, "1500000000", "3950", "20000", "78000", "3150", "83000","31-12-2004");

    }
}