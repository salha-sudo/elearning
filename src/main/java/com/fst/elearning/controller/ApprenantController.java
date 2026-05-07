package com.fst.elearning.controller;

import com.fst.elearning.entity.*;
import com.fst.elearning.service.*;
import org.springframework.security.core.annotation
    .AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/apprenant")
public class ApprenantController {

    private final UtilisateurService utilisateurService;
    private final InscriptionService inscriptionService;
    private final ProgressionService progressionService;
    private final CoursService coursService;

    public ApprenantController(
            UtilisateurService utilisateurService,
            InscriptionService inscriptionService,
            ProgressionService progressionService,
            CoursService coursService) {
        this.utilisateurService = utilisateurService;
        this.inscriptionService = inscriptionService;
        this.progressionService = progressionService;
        this.coursService = coursService;
    }

    // ─── Dashboard apprenant ──────────────────────────
    @GetMapping("/dashboard")
    public String dashboard(
            @AuthenticationPrincipal UserDetails userDetails,
            Model model) {

        Utilisateur apprenant = utilisateurService
            .findByEmail(userDetails.getUsername());

        List<Inscription> inscriptions = inscriptionService
            .getMesInscriptions(apprenant);

        // Calculer progression pour chaque cours
        java.util.Map<Long, Integer> progressions =
            new java.util.HashMap<>();
        for (Inscription ins : inscriptions) {
            int prog = progressionService
                .calculerProgression(
                    apprenant, ins.getCours());
            progressions.put(ins.getCours().getId(), prog);
        }

        model.addAttribute("apprenant", apprenant);
        model.addAttribute("inscriptions", inscriptions);
        model.addAttribute("progressions", progressions);
        return "apprenant/dashboard";
    }

    // ─── S'inscrire à un cours ────────────────────────
    @PostMapping("/inscrire/{coursId}")
    public String inscrire(
            @PathVariable Long coursId,
            @AuthenticationPrincipal UserDetails userDetails) {
        Utilisateur apprenant = utilisateurService
            .findByEmail(userDetails.getUsername());
        try {
            inscriptionService.inscrire(apprenant, coursId);
        } catch (RuntimeException e) {
            return "redirect:/cours/detail/"
                + coursId + "?erreur=" + e.getMessage();
        }
        return "redirect:/apprenant/dashboard";
    }

    // ─── Voir un cours (leçons) ───────────────────────
    @GetMapping("/cours/{coursId}")
    public String voirCours(
            @PathVariable Long coursId,
            @AuthenticationPrincipal UserDetails userDetails,
            Model model) {

        Utilisateur apprenant = utilisateurService
            .findByEmail(userDetails.getUsername());
        Cours cours = coursService.findById(coursId);

        // Vérifier inscription
        if (!inscriptionService.estInscrit(
                apprenant, cours)) {
            return "redirect:/catalogue";
        }

        int progression = progressionService
            .calculerProgression(apprenant, cours);

        model.addAttribute("cours", cours);
        model.addAttribute("apprenant", apprenant);
        model.addAttribute("progression", progression);
        return "apprenant/cours-detail";
    }

    // ─── Compléter une leçon ──────────────────────────
    @PostMapping("/lecon/{leconId}/completer")
    public String completerLecon(
            @PathVariable Long leconId,
            @RequestParam Long coursId,
            @AuthenticationPrincipal UserDetails userDetails) {

        Utilisateur apprenant = utilisateurService
            .findByEmail(userDetails.getUsername());
        progressionService.completerLecon(
            apprenant, leconId);
        return "redirect:/apprenant/cours/" + coursId;
    }
}