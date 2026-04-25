package com.fst.elearning.controller;

import com.fst.elearning.entities.Cours;
import com.fst.elearning.entities.Module;
import com.fst.elearning.entities.Lecon;
import com.fst.elearning.service.CoursService;
import com.fst.elearning.service.ModuleService;
import com.fst.elearning.service.LeconService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/formateur")
public class FormateurController {

    private final CoursService coursService;
    private final ModuleService moduleService;
    private final LeconService leconService;

    public FormateurController(CoursService coursService,
                               ModuleService moduleService,
                               LeconService leconService) {
        this.coursService = coursService;
        this.moduleService = moduleService;
        this.leconService = leconService;
    }

    @GetMapping("/cours")
    public String mesCours(Model model) {
        model.addAttribute("cours", coursService.getCatalogue("", 0).getContent());
        return "formateur/cours";  
    }

    @PostMapping("/cours")
    public String createCours(@ModelAttribute Cours c) {
        coursService.save(c);
        return "redirect:/formateur/cours";
    }

    @GetMapping("/cours/delete/{id}")  
    public String deleteCours(@PathVariable Long id) {
        coursService.delete(id);
        return "redirect:/formateur/cours";
    }

    @PostMapping("/modules")
    public String createModule(@ModelAttribute Module m) {
        moduleService.save(m);
        return "redirect:/formateur/cours";
    }

    @PostMapping("/lecons")
    public String createLecon(@ModelAttribute Lecon l) {
        leconService.save(l);
        return "redirect:/formateur/cours";
    }
}