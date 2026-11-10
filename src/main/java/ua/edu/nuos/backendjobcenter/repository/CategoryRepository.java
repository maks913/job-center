package ua.edu.nuos.backendjobcenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.edu.nuos.backendjobcenter.data.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}