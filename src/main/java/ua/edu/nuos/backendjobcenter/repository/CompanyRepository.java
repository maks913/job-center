package ua.edu.nuos.backendjobcenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.edu.nuos.backendjobcenter.data.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}