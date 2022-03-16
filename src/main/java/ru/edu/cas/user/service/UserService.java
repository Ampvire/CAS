package ru.edu.cas.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.edu.cas.user.dao.Category;
import ru.edu.cas.user.dao.Role;
import ru.edu.cas.user.dao.User;
import ru.edu.cas.user.repo.CategoryRepository;
import ru.edu.cas.user.repo.RoleRepository;
import ru.edu.cas.user.repo.UserRepository;

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
     * @return
     */
    public List<Role> getAllRole(){
        return roleRepository.findAll();
    }

    /**
     * Данный метод возвращает список категорий пользователя из таблиы category.
     * @return
     */
    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }

    /**
     * Метод возвращает список пользователей из таблицы user
     * @return
     */
    public List<User> getAllUsers(){
        return repository.findAll();
    }

    /**
     * Метод возвращает запись из таблицы role по названию роли.
     * @param role
     * @return
     */
    public Role getRole(String role){
      return   roleRepository.findByRole(role);
    }

    /**
     * Метод возвращает запись из таблицы category по названию категории.
     * @param category
     * @return
     */
    public Category getCategory(String category){
        return categoryRepository.findByCategory(category);
    }

    /**
     * Метод создает запись в таблице user или редактирует ее.
     * @param login
     * @param firstName
     * @param secondName
     * @param password
     * @param categoryName
     * @param roleName
     * @return
     */
    public User createOrUpdateUser( String login,
                                    String firstName,
                                    String secondName,
                                    String password,
                                    String categoryName,
                                    String roleName){
        String encodedPassword = Base64.getEncoder().encodeToString(password.getBytes());
        User user = new User();
        user.setCategoryId(getCategory(categoryName));
        user.setFirstName(firstName);
        user.setLastName(secondName);
        user.setPassword(encodedPassword);
        user.setRoleId(getRole(roleName));
        user.setLogin(login);
        repository.save(user);
         return user;
    }

    /**
     * Метод удаляет запись из таблицы user по логину
     * @param login
     */
    public void deleteUser(String login ){
        User user = repository.findByLogin(login);
        if (repository.findByLogin(login) == null) {
            throw new RuntimeException("User with login " + login + " not found!");
        }
        repository.delete(user);
    }
}
