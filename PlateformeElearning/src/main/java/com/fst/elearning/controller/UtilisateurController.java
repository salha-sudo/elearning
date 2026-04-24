package com.fst.elearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.fst.elearning.entities.Utilisateur;
import com.fst.elearning.service.UtilisateurService;

@Controller
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new Utilisateur());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Validated @ModelAttribute("user") Utilisateur user,
                           BindingResult result) {

        if (result.hasErrors()) {
            return "register";
        }

        utilisateurService.register(user);
        return "redirect:/login";
    }
}