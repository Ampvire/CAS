package ru.edu.cas.user.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.edu.cas.user.dao.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByCategory(String category);
    Category findById(int id);
}
