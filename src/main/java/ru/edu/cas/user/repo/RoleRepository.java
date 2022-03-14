package ru.edu.cas.user.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.edu.cas.user.dao.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
