package ua.edu.nuos.backendjobcenter.projections;

import ua.edu.nuos.backendjobcenter.enums.VacancyType;

import java.time.LocalDate;

/**
 * Projection for {@link ua.edu.nuos.backendjobcenter.data.Vacancy}
 */
public interface VacancyTypes {
    Integer getCountVacancies();
    VacancyType getVacancyType();
}