package ua.edu.nuos.backendjobcenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.edu.nuos.backendjobcenter.data.Resume;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
}