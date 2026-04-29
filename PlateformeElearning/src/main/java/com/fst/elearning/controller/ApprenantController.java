package com.fst.elearning.controller;

import com.fst.elearning.entities.*;
import com.fst.elearning.repository.*;
import com.fst.elearning.service.*;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/apprenant")
public class ApprenantController {

    private final CoursService coursService;
    private final InscriptionService inscriptionService;
    private final UtilisateurRepository utilisateurRepository;
    private final CoursRepository coursRepository;

    public ApprenantController(CoursService coursService,
                               InscriptionService inscriptionService,
                               UtilisateurRepository utilisateurRepository,
                               CoursRepository coursRepository) {
        this.coursService = coursService;
        this.inscriptionService = inscriptionService;
        this.utilisateurRepository = utilisateurRepository;
        this.coursRepository = coursRepository;
    }

    // =========================
    // Catalogue des cours
    // =========================
    @GetMapping("/cours")
    public String voirCours(Model model) {
        model.addAttribute("cours",
                coursService.getCatalogue("", 0).getContent());
        return "apprenant/cours";
    }

    // =========================
    // Inscription à un cours
    // =========================
    @PostMapping("/inscrire")
    public String inscrire(@RequestParam Long coursId,
                           Authentication auth) {

        Utilisateur apprenant = utilisateurRepository
                .findByUsername(auth.getName())
                .orElseThrow();

        Cours cours = coursRepository.findById(coursId)
                .orElseThrow();

        Inscription inscription = new Inscription();
        inscription.setApprenant(apprenant);
        inscription.setCours(cours);
        inscription.setDateInscription(LocalDate.now());
        inscription.setStatut(StatutInscription.EN_COURS);

        inscriptionService.save(inscription);

        return "redirect:/apprenant/inscriptions";
    }

    // =========================
    // Mes inscriptions
    // =========================
    @GetMapping("/inscriptions")
    public String mesInscriptions(Model model,
                                  Authentication auth) {

        Utilisateur user = utilisateurRepository
                .findByUsername(auth.getName())
                .orElseThrow();

        model.addAttribute("inscriptions",
                inscriptionService.findByUser(user.getId()));

        return "apprenant/inscriptions";
    }
}