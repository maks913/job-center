package ua.edu.nuos.backendjobcenter.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.edu.nuos.backendjobcenter.data.Education;
import ua.edu.nuos.backendjobcenter.data.LanguageProficiency;
import ua.edu.nuos.backendjobcenter.data.Resume;
import ua.edu.nuos.backendjobcenter.enums.EducationType;
import ua.edu.nuos.backendjobcenter.enums.Language;
import ua.edu.nuos.backendjobcenter.enums.Proficiency;
import ua.edu.nuos.backendjobcenter.service.ResumeService;

@Controller
@AllArgsConstructor
@RequestMapping("/resume")
class ResumeController {

    private final ResumeService resumeService;

    @GetMapping("/{id}")
    public String resume(@PathVariable Long id, Model model) {
        var resume = resumeService.findById(id);

        model.addAttribute("resume", resume);
        model.addAttribute("educationTypes", EducationType.values());
        model.addAttribute("education", new Education());

        model.addAttribute("languages", Language.values());
        model.addAttribute("proficiencies", Proficiency.values());
        model.addAttribute("languageProficiency", new LanguageProficiency());
        return "users/resume_user";
    }

    @PostMapping
    public String saveResume(Resume resume, @RequestParam("user_id") Long userId) {
        resumeService.save(resume, userId);
        return "redirect:/resume/" + resume.getId();
    }

    @PostMapping("/education/{id}")
    public String saveEducation(@ModelAttribute Education education, @PathVariable Long id, Model model) {
        resumeService.saveEducation(education, id);
        return "redirect:/resume/" + id;
    }

    @PostMapping("/language/{id}")
    public String saveLanguage(@ModelAttribute LanguageProficiency language, @PathVariable Long id, Model model) {
        resumeService.saveLanguage(language, id);
        return "redirect:/resume/" + id;
    }

    @GetMapping("/language/delete/{id}")
    public String deleteLanguage(@PathVariable Long id, Model model) {
        var resumeId = resumeService.deleteLanguage(id);
        return "redirect:/resume/" + resumeId;
    }

    @GetMapping("/education/delete/{id}")
    public String deleteEducation(@PathVariable Long id, Model model) {
        var resumeId = resumeService.deleteEducation(id);
        return "redirect:/resume/" + resumeId;
    }

    @GetMapping("/delete/{id}")
    public String deleteResume(@PathVariable Long id) {
        var userId = resumeService.deleteById(id);
        return "redirect:/users/" + userId;
    }

}
