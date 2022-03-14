package ru.edu.cas.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.edu.cas.user.dao.Category;
import ru.edu.cas.user.dao.Role;
import ru.edu.cas.user.dao.User;
import ru.edu.cas.user.repo.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    private UserRepository repository;

    @Autowired
    public void setRepository(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/newUser")
    public ModelAndView info(HttpServletRequest req
            , HttpServletResponse res) {
        return new ModelAndView("/create_user.jsp");
    }

    @PostMapping("/update")
    public ModelAndView newUser(@RequestParam("login") String login,
                                @RequestParam("firstName") String firstName,
                                @RequestParam("secondName") String secondName,
                                @RequestParam("password") String password,
                                @RequestParam("categoryId") String categoryId,
                                @RequestParam("roleId") String roleId,
                                HttpServletRequest req
            , HttpServletResponse res) {
        Role role = new Role();
        role.setRole(roleId);
        role.setDescription("Meneger");
        Category category = new Category();
        category.setCategory(categoryId);
        User userEntity = new User();
        userEntity.setLogin(login);
        userEntity.setFirstName(firstName);
        userEntity.setLastName(secondName);
        userEntity.setPassword(password);
        userEntity.setCategoryId(category);
        userEntity.setRoleId(role);
        if (repository.findByLogin(login) != null) {
            throw new RuntimeException("User with login " + login + "was found!");
        }
        repository.save(userEntity);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message","User");
        modelAndView.setViewName("/success_create.jsp");
        return modelAndView;
    }
}
