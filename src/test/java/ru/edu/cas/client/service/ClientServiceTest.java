package ru.edu.cas.client.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.edu.cas.client.dao.*;
import ru.edu.cas.client.repo.ClientReportRepository;
import ru.edu.cas.user.dao.User;

import java.util.List;

/**
 * Тестовый класс. Юнит тесты к методам класса ClientService
 */
@SpringBootTest
class ClientServiceTest {
    private ClientService service;

    @Autowired
    public void setService(ClientService service) {
        this.service = service;
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
        List<ClientFinance> finances = service.getAllFinanceByClientInnAndDate(inn, date);
        Assertions.assertEquals(1, finances.size());
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
}