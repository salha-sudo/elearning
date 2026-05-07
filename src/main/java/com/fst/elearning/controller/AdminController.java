package com.fst.elearning.controller;

import com.fst.elearning.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UtilisateurRepository utilisateurRepository;
    private final CoursRepository coursRepository;
    private final InscriptionRepository inscriptionRepository;

    public AdminController(
            UtilisateurRepository utilisateurRepository,
            CoursRepository coursRepository,
            InscriptionRepository inscriptionRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.coursRepository = coursRepository;
        this.inscriptionRepository = inscriptionRepository;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        // Statistiques globales
        long totalUtilisateurs =
            utilisateurRepository.count();
        long totalCours =
            coursRepository.countByActifTrue();
        long totalInscriptions =
            inscriptionRepository.count();

        model.addAttribute("totalUtilisateurs",
            totalUtilisateurs);
        model.addAttribute("totalCours", totalCours);
        model.addAttribute("totalInscriptions",
            totalInscriptions);
        model.addAttribute("tousLesUtilisateurs",
            utilisateurRepository.findAll());
        model.addAttribute("tousLesCours",
            coursRepository.findAll());

        return "admin/dashboard";
    }
}
