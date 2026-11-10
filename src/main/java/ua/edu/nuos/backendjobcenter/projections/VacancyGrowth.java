package ua.edu.nuos.backendjobcenter.projections;

import java.time.LocalDate;

/**
 * Projection for {@link ua.edu.nuos.backendjobcenter.data.Vacancy}
 */
public interface VacancyGrowth {
    Integer getCountVacancies();
    LocalDate getDate();
}