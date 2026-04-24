package com.fst.elearning.controller;

import com.fst.elearning.entities.Cours;
import com.fst.elearning.service.CoursService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cours")
public class CoursController {

    @Autowired
    private CoursService coursService;

    @GetMapping
    public String listCours(Model model) {
        model.addAttribute("cours", coursService.getAllCours());
        return "cours/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("cours", new Cours());
        return "cours/form";
    }

    @PostMapping("/save")
    public String saveCours(@Validated @ModelAttribute Cours cours,
                            BindingResult result) {

        if (result.hasErrors()) {
            return "cours/form";
        }

        coursService.save(cours);
        return "redirect:/cours";
    }

    @GetMapping("/delete/{id}")
    public String deleteCours(@PathVariable Long id) {
        coursService.delete(id);
        return "redirect:/cours";
    }
}