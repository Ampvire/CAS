package ru.edu.cas.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.edu.cas.user.dao.Category;
import ru.edu.cas.user.dao.Role;
import ru.edu.cas.user.dao.User;
import ru.edu.cas.user.repo.CategoryRepository;
import ru.edu.cas.user.repo.RoleRepository;
import ru.edu.cas.user.repo.UserRepository;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

/**
 * Класс предназначен для работы с таблицами user, category, role.
 */

@Service
public class UserService {
    private UserRepository repository;
    private RoleRepository roleRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setRepository(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * Данный метод возвращает список ролей пользователя из таблицы role.
     *
     * @return
     */
    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }

    /**
     * Данный метод возвращает список категорий пользователя из таблиы category.
     *
     * @return
     */
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    /**
     * Метод возвращает список пользователей из таблицы user
     *
     * @return
     */
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    /**
     * Метод возвращает пользователя из таблицы user по id
     *
     * @param id - id пользователя
     * @return User
     */
    public User getUser(int id) {
        return repository.getById(id);
    }


    /**
     * Метод возвращает список пользователей из таблицы user categoryId
     *
     * @param categoryId -логин пользователя
     */
    public List<User> getUsersByCategory(Category categoryId) {
        return repository.findByCategoryId(categoryId);
    }

    /**
     * Метод возвращает запись из таблицы role по названию роли.
     *
     * @param role
     * @return
     */
    public Role getRole(String role) {
        return roleRepository.findByRole(role);
    }

    /**
     * Метод возвращает запись из таблицы category по названию категории.
     *
     * @param category
     * @return
     */
    public Category getCategory(String category) {
        return categoryRepository.findByCategory(category);
    }


    /**
     * Метод возвращает запись из таблицы category по идентификатору категории.
     *
     * @param categoryId -идентификатор категории
     * @return
     */
    public Category getCategory(int categoryId) {
        return categoryRepository.findById(categoryId);
    }

    /**
     * Метод создает запись в таблице user или редактирует ее.
     *
     * @param login
     * @param firstName
     * @param secondName
     * @param password
     * @param categoryName
     * @param roleName
     * @return
     */
    public User createOrUpdateUser(String login,
                                   String firstName,
                                   String secondName,
                                   String password,
                                   String categoryName,
                                   String roleName) {
        List<String> parameters = Arrays.asList(login, firstName, secondName, password);
        if (parameters.contains(null) || parameters.contains("")) {
            return null;
        }

        User user = getUser(login);
        if (user == null) {
            String encodedPassword = Base64.getEncoder().encodeToString(password.getBytes());
            user = new User();
            user.setPassword(encodedPassword);
        }
        user.setCategoryId(getCategory(categoryName));
        user.setFirstName(firstName);
        user.setLastName(secondName);
        user.setRoleId(getRole(roleName));
        user.setLogin(login);
        repository.save(user);
        return user;
    }

    public User getUser(String login) {
        return repository.findByLogin(login);
    }

    /**
     * Метод удаляет запись из таблицы user по логину
     *
     * @param login
     */
    public User deleteUser(String login) {
        if (getUser(login) == null) {
            return null;
        }
        User user = getUser(login);
        repository.delete(user);
        return user;
    }
}
