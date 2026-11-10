package ua.edu.nuos.backendjobcenter.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.edu.nuos.backendjobcenter.data.Vacancy;
import ua.edu.nuos.backendjobcenter.projections.VacancyGrowth;
import ua.edu.nuos.backendjobcenter.projections.VacancyTypes;
import ua.edu.nuos.backendjobcenter.repository.VacancyRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class VacanciesService {

    private final VacancyRepository vacancyRepository;

    public List<Vacancy> findAll() {
        return vacancyRepository.findAll();
    }

    public Vacancy findById(Long id) {
        return vacancyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No Vacancy found with id: " + id));
    }

    @Transactional
    public void save(Vacancy vacancy) {
        vacancyRepository.save(vacancy);
    }

    @Transactional
    public void delete(Long id) {
        vacancyRepository.deleteById(id);
    }

    public List<VacancyGrowth> getVacanciesByCreatedAtBetween(Integer days) {
        return vacancyRepository.countVacanciesByCreatedAtBetween(LocalDate.now().minusDays(days), LocalDate.now());
    }

    public List<VacancyTypes> getVacanciesByCreatedAtBetweenVacancyType(Integer days) {
        return vacancyRepository.countVacanciesByCreatedAtBetweenVacancyType(LocalDate.now().minusDays(days), LocalDate.now());
    }

}
