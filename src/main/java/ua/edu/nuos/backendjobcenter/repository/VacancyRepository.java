package ua.edu.nuos.backendjobcenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.edu.nuos.backendjobcenter.data.Vacancy;
import ua.edu.nuos.backendjobcenter.projections.VacancyGrowth;
import ua.edu.nuos.backendjobcenter.projections.VacancyTypes;

import java.time.LocalDate;
import java.util.List;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {

    @Query("select count(v) as countVacancies, " +
            " v.createdAt as date " +
            " from Vacancy v " +
            " where v.createdAt between ?1 and ?2 " +
            " group by v.createdAt")
    List<VacancyGrowth> countVacanciesByCreatedAtBetween(LocalDate dateStart, LocalDate dateEnd);

    @Query("select count(v) as countVacancies, " +
            "v.vacancyType as vacancyType " +
            "from Vacancy v " +
            "where v.createdAt between ?1 and ?2 " +
            "group by v.vacancyType")
    List<VacancyTypes> countVacanciesByCreatedAtBetweenVacancyType(LocalDate createdAtAfter, LocalDate createdAtBefore);
}