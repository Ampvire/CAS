package ru.edu.cas.user.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.edu.cas.user.dao.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByLogin(String login);
}
