package ru.edu.cas.user.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.edu.cas.user.dao.Category;
import ru.edu.cas.user.dao.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByLogin(String login);
    List<User> findByCategoryId(Category category);
}
