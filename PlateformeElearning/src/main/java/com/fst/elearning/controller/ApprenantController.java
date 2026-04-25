package com.fst.elearning.controller;

import com.fst.elearning.entities.Cours;
import com.fst.elearning.entities.Inscription;
import com.fst.elearning.entities.StatutInscription;
import com.fst.elearning.entities.Utilisateur;
import com.fst.elearning.repository.CoursRepository;
import com.fst.elearning.repository.UtilisateurRepository;
import com.fst.elearning.service.CoursService;
import com.fst.elearning.service.InscriptionService;

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

    @GetMapping("/cours")
    public String voirCours(Model model) {
        model.addAttribute("cours", coursService.getCatalogue("", 0).getContent());
        return "apprenant/cours";
    }

    @PostMapping("/inscrire")
    public String inscrire(@RequestParam Long coursId, Authentication auth) {
        Utilisateur apprenant = utilisateurRepository
            .findByUsername(auth.getName()).orElseThrow();

        Cours cours = coursRepository.findById(coursId).orElseThrow();

        Inscription inscription = new Inscription();
        inscription.setApprenant(apprenant);
        inscription.setCours(cours);
        inscription.setDateInscription(LocalDate.now());
        inscription.setStatut(StatutInscription.ACTIF);

        inscriptionService.save(inscription);
        return "redirect:/apprenant/inscriptions";
    }

    @GetMapping("/inscriptions")
    public String mesInscriptions(Model model) {
        model.addAttribute("inscriptions", inscriptionService.findAll());
        return "apprenant/inscriptions";
    }
}