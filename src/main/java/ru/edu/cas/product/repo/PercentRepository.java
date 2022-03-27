package ru.edu.cas.product.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.edu.cas.product.dao.Percent;

public interface PercentRepository extends JpaRepository<Percent,Integer> {
}
