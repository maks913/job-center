package ua.edu.nuos.backendjobcenter.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.edu.nuos.backendjobcenter.data.Vacancy;
import ua.edu.nuos.backendjobcenter.enums.VacancyType;
import ua.edu.nuos.backendjobcenter.service.CategoryService;
import ua.edu.nuos.backendjobcenter.service.CompaniesService;
import ua.edu.nuos.backendjobcenter.service.VacanciesService;

@Controller
@AllArgsConstructor
@RequestMapping("/vacancies")
class VacanciesController {

    private final VacanciesService vacanciesService;
    private final CompaniesService companiesService;
    private final CategoryService categoryService;

    @GetMapping
    public String jobs(Model model) {
        model.addAttribute("vacancies", vacanciesService.findAll());
        model.addAttribute("currentPage","vacancies");
        return "vacancies/vacancies";
    }

    @GetMapping("/new")
    public String newVacancy(Model model) {
        model.addAttribute("vacancy", new Vacancy());
        model.addAttribute("companies", companiesService.findAll());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("types", VacancyType.values());
        return "vacancies/create_vacancy";
    }

    @GetMapping("/old/{id}")
    public String oldVacancy(Model model, @PathVariable Long id){
        model.addAttribute("vacancy", vacanciesService.findById(id));
        model.addAttribute("companies", companiesService.findAll());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("types", VacancyType.values());
        return "vacancies/update_vacancy";
    }

    @PostMapping("/save")
    public String saveVacancy(Vacancy vacancy){
        vacanciesService.save(vacancy);
        return "redirect:/vacancies";
    }

    @GetMapping("/delete/{id}")
    public String deleteVacancy(@PathVariable Long id){
        vacanciesService.delete(id);
        return "redirect:/vacancies";
    }

    @GetMapping("/{id}")
    public String getVacancy(Model model, @PathVariable Long id){
        model.addAttribute("vacancy", vacanciesService.findById(id));
        return "vacancies/detail_vacancy";
    }

}
