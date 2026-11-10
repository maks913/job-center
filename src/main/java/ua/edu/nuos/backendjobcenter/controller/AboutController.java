package ua.edu.nuos.backendjobcenter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/about")
class AboutController {

    @GetMapping
    public String about(Model model) {
        model.addAttribute("currentPage","about");
        return "about";
    }
}
