package ua.edu.nuos.backendjobcenter.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.edu.nuos.backendjobcenter.data.Company;
import ua.edu.nuos.backendjobcenter.service.CompaniesService;
import ua.edu.nuos.backendjobcenter.service.UserService;

@Controller
@AllArgsConstructor
@RequestMapping("/companies")
class CompaniesController {

    private final CompaniesService companyService;
    private final UserService userService;

    @GetMapping
    public String companies(Model model) {
        model.addAttribute("companies", companyService.findAll());
        model.addAttribute("currentPage","companies");
        return "companies/companies";
    }

    @GetMapping("/new")
    public String newCompany(Model model) {
        model.addAttribute("company", new Company());
        model.addAttribute("users", userService.findAllEmployers());
        return "companies/create_company";
    }

    @PostMapping("/save")
    public String saveCompany(Company company) {
        companyService.saveCompany(company);
        return "redirect:/companies";
    }

    @GetMapping("/old/{id}")
    public String oldCompany(@PathVariable Long id, Model model) {
        model.addAttribute("company", companyService.getCompanyById(id));
        model.addAttribute("users", userService.findAllEmployers());
        return "companies/update_company";
    }

    @GetMapping("/delete/{id}")
    public String deleteCompany(@PathVariable Long id, Model model) {
        companyService.deleteCompany(id);
        return "redirect:/companies";
    }

    @GetMapping("/{id}")
    public String editCompany(@PathVariable Long id, Model model) {
        model.addAttribute("company", companyService.getCompanyById(id));
        return "companies/company_detail";
    }

}
