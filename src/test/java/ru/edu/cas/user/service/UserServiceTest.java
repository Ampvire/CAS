package ru.edu.cas.user.service;

import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.edu.cas.user.dao.Category;
import ru.edu.cas.user.dao.Role;
import ru.edu.cas.user.dao.User;

import java.util.List;

/**
 * Тестовый класс. Юнит тесты к методам класса UserService
 */
@SpringBootTest
class UserServiceTest {
    private UserService service;

    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    /**
     * Успешное выполение метода getAllUsers() и createOrUpdateUser(...)
     */
    @Test
    void getAllUsers() {
        User user = service.getUser("test");
        if (user != null) {
            service.deleteUser("test");
        }
        List<User> list = service.getAllUsers();
        service.createOrUpdateUser("test", "Test", "Test"
                , "test123", "1", "Admin");
        List<User> listAfterCreate = service.getAllUsers();
        Assertions.assertEquals(list.size() + 1, listAfterCreate.size());
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
     * Успешное выполение метода getAllRole()
     */
    @Test
    void getAllRole() {
        List<Role> roles = service.getAllRole();
        Assertions.assertEquals(3, roles.size());
    }

    /**
     * Успешное выполение метода getAllCategory()
     */
    @Test
    void getAllCategory() {
        List<Category> category = service.getAllCategory();
        Assertions.assertEquals(2, category.size());
    }

    /**
     * Успешное выполение метода getRole()
     */
    @Test
    void getRole() {
        Role role = service.getRole("Admin");
        Assertions.assertEquals("full rights", role.getDescription());
    }

    /**
     * Успешное выполение метода getCategory()
     */
    @Test
    void getCategory() {
        Category category = service.getCategory("1");
        Assertions.assertEquals("First level", category.getDescription());
    }

    /**
     * Успешное выполение метода deleteUser()
     */
    @Test
    void deleteUser() {
        service.createOrUpdateUser("test", "Test", "Test"
                , "test123", "1", "Admin");
        service.deleteUser("test");
        User user = service.getUser("test");
        Assertions.assertNull(user);
    }


    /**
     * Неуспешное выполение метода createOrUpdateUser(...)
     */
    @Test
    void createOrUpdateUser() {
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage("Fields must not be null!");
        service.createOrUpdateUser(null, null, "Test"
                , "test123", "1", "Admin");
    }
}