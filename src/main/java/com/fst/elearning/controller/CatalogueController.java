package com.fst.elearning.controller;

import com.fst.elearning.entity.Cours;
import com.fst.elearning.entity.Utilisateur;
import com.fst.elearning.enums.Niveau;
import com.fst.elearning.service.CoursService;
import com.fst.elearning.service.InscriptionService;
import com.fst.elearning.service.UtilisateurService;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class CatalogueController {

    private final CoursService coursService;
    private final UtilisateurService utilisateurService;
    private final InscriptionService inscriptionService;

    public CatalogueController(
            CoursService coursService,
            UtilisateurService utilisateurService,
            InscriptionService inscriptionService) {
        this.coursService = coursService;
        this.utilisateurService = utilisateurService;
        this.inscriptionService = inscriptionService;
    }

    // ─── Catalogue public ─────────────────────────────
    @GetMapping("/catalogue")
    public String catalogue(
            @RequestParam(defaultValue = "") String titre,
            @RequestParam(defaultValue = "") String categorie,
            @RequestParam(defaultValue = "") String niveau,
            @RequestParam(defaultValue = "0") int page,
            Model model) {

        Page<Cours> coursPage = coursService
            .getCatalogue(titre, categorie, niveau, page, 9);

        model.addAttribute("coursPage", coursPage);
        model.addAttribute("titre", titre);
        model.addAttribute("categorie", categorie);
        model.addAttribute("niveau", niveau);
        model.addAttribute("niveaux", Niveau.values());
        model.addAttribute("categories", List.of(
            "Programmation", "Design", "DevOps",
            "Base de donnees", "Securite", "IA"));
        return "catalogue/liste";
    }

    // ─── Détail cours ─────────────────────────────────
    @GetMapping("/cours/detail/{id}")
    public String detail(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails,
            Model model) {

        Cours cours = coursService.findById(id);
        model.addAttribute("cours", cours);

        // Valeurs par défaut
        boolean estApprenant = false;
        boolean dejaInscrit = false;

        if (userDetails != null) {
            Utilisateur user = utilisateurService
                .findByEmail(userDetails.getUsername());
            estApprenant = user.getRole().name()
                .equals("APPRENANT");
            if (estApprenant) {
                dejaInscrit = inscriptionService
                    .estInscrit(user, cours);
            }
        }

        model.addAttribute("estApprenant", estApprenant);
        model.addAttribute("dejaInscrit", dejaInscrit);

        return "catalogue/detail";
    }
}