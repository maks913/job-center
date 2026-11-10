package ua.edu.nuos.backendjobcenter.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.edu.nuos.backendjobcenter.projections.VacancyGrowth;
import ua.edu.nuos.backendjobcenter.projections.VacancyTypes;
import ua.edu.nuos.backendjobcenter.service.CompaniesService;
import ua.edu.nuos.backendjobcenter.service.UserService;
import ua.edu.nuos.backendjobcenter.service.VacanciesService;

import java.util.List;

@Controller
@RequestMapping("/dashboard")
@AllArgsConstructor
class DashboardController {

    private final VacanciesService vacanciesService;
    private final UserService userService;
    private final CompaniesService companiesService;

    @GetMapping
    public String thirtyDays(Model model) {

        model.addAttribute("currentPage", "dashboard");

        model.addAttribute("totalVacancies", vacanciesService.findAll().size());
        model.addAttribute("totalUsers", userService.findAll().size());
        model.addAttribute("totalCompanies", companiesService.findAll().size());
        model.addAttribute("totalApplications", userService.totalApplications());

        model.addAttribute("vacancies", vacanciesService.findAll().subList(0, 3));
        model.addAttribute("users", userService.findAll().subList(0, 3));

        return "dashboard";
    }

    @GetMapping("/growth/{days}")
    @ResponseBody
    public List<VacancyGrowth> getGrowthStats(@PathVariable Integer days) {
        return vacanciesService.getVacanciesByCreatedAtBetween(days);
    }

    @GetMapping("/types/{days}")
    @ResponseBody
    public List<VacancyTypes> getTypesStats(@PathVariable Integer days) {
        return vacanciesService.getVacanciesByCreatedAtBetweenVacancyType(days);
    }
}
