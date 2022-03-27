package ru.edu.cas.product.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.edu.cas.product.dao.Application;

public interface ApplicationRepository extends JpaRepository<Application,Integer> {
}
