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

    public List<Role> getAllRole(){
        return roleRepository.findAll();
    }

    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }

    public List<User> getAllUsers(){
        return repository.findAll();
    }

    public Role getRole(String role){
      return   roleRepository.findByRole(role);
    }

    public Category getCategory(String category){
        return categoryRepository.findByCategory(category);
    }

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

    public void deleteUser(String login ){
        User user = repository.findByLogin(login);
        if (repository.findByLogin(login) == null) {
            throw new RuntimeException("User with login " + login + " not found!");
        }
        repository.delete(user);
    }
}
