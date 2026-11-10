package ua.edu.nuos.backendjobcenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.edu.nuos.backendjobcenter.data.LanguageProficiency;

public interface LanguageProficiencyRepository extends JpaRepository<LanguageProficiency, Long> {
}