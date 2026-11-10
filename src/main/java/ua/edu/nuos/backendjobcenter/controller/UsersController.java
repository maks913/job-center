package ua.edu.nuos.backendjobcenter.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.edu.nuos.backendjobcenter.data.Resume;
import ua.edu.nuos.backendjobcenter.data.User;
import ua.edu.nuos.backendjobcenter.repository.RoleRepository;
import ua.edu.nuos.backendjobcenter.service.UserService;
import ua.edu.nuos.backendjobcenter.service.VacanciesService;

@Controller
@AllArgsConstructor
@RequestMapping("/users")
class UsersController {

    private final UserService userService;
    private final RoleRepository roleRepository;
    private final VacanciesService vacanciesService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("currentPage","users");
        return "users/users";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleRepository.findAll());
        return "users/create_user";
    }

    @GetMapping("/old/{id}")
    public String oldUser(Model model, @PathVariable Long id) {
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("roles", roleRepository.findAll());
        return "users/update_user";
    }

    @PostMapping("/save")
    public String save(User user) {
        userService.save(user);
        return "redirect:/users";
    }

    @PostMapping("/update")
    public String update(User user) {
        userService.update(user);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/users";
    }

    @GetMapping("/{id}")
    public String detailUser(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("resume", new Resume());
        model.addAttribute("vacancies", vacanciesService.findAll());
        return "users/detail_user";
    }

    @PostMapping("/save-vacancy")
    @ResponseBody
    public ResponseEntity<Object> saveVacancy(
            @RequestParam("vacancyId") Long vacancyId,
            @RequestParam("userId") Long userId
    ) {

        userService.saveVacancy(vacancyId, userId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping("/sent-application")
    @ResponseBody
    public ResponseEntity<Object> saveApplication(
            @RequestParam("vacancyId") Long vacancyId,
            @RequestParam("userId") Long userId
    ) {

        userService.saveApplication(vacancyId, userId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}
