package com.fst.elearning.controller;

import com.fst.elearning.entity.Cours;
import com.fst.elearning.entity.CourseModule;
import com.fst.elearning.entity.Lecon;
import com.fst.elearning.entity.Utilisateur;
import com.fst.elearning.enums.Niveau;
import com.fst.elearning.repository.InscriptionRepository;
import com.fst.elearning.service.CoursService;
import com.fst.elearning.service.LeconService;
import com.fst.elearning.service.ModuleService;
import com.fst.elearning.service.UtilisateurService;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation
    .AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/formateur")
public class FormateurController {

    private final CoursService coursService;
    private final ModuleService moduleService;
    private final LeconService leconService;
    private final UtilisateurService utilisateurService;
    private final InscriptionRepository
        inscriptionRepository;

    public FormateurController(
            CoursService coursService,
            ModuleService moduleService,
            LeconService leconService,
            UtilisateurService utilisateurService,
            InscriptionRepository inscriptionRepository) {
        this.coursService = coursService;
        this.moduleService = moduleService;
        this.leconService = leconService;
        this.utilisateurService = utilisateurService;
        this.inscriptionRepository = inscriptionRepository;
    }

    // ─── Dashboard ────────────────────────────────────
    @GetMapping("/dashboard")
    public String dashboard(
            @AuthenticationPrincipal
            UserDetails userDetails,
            @RequestParam(defaultValue = "0") int page,
            Model model) {

        Utilisateur formateur = utilisateurService
            .findByEmail(userDetails.getUsername());

        Page<Cours> mesCoursPage = coursService
            .getCoursFormateur(formateur, page, 8);

        // Statistiques
        long totalCours =
            mesCoursPage.getTotalElements();

        long totalInscrits = 0;
        for (Cours cours : mesCoursPage.getContent()) {
            totalInscrits += inscriptionRepository
                .countByCours(cours);
        }

        model.addAttribute("mesCoursPage", mesCoursPage);
        model.addAttribute("formateur", formateur);
        model.addAttribute("totalCours", totalCours);
        model.addAttribute("totalInscrits", totalInscrits);

        return "formateur/dashboard";
    }

    // ─── Nouveau cours ────────────────────────────────
    @GetMapping("/cours/nouveau")
    public String nouveauCoursForm(Model model) {
        model.addAttribute("cours", new Cours());
        model.addAttribute("niveaux", Niveau.values());
        model.addAttribute("categories", List.of(
            "Programmation", "Design", "DevOps",
            "Base de donnees", "Securite", "IA"));
        return "formateur/cours-form";
    }

    @PostMapping("/cours/nouveau")
    public String creerCours(
            @ModelAttribute Cours cours,
            @RequestParam("image") MultipartFile image,
            @AuthenticationPrincipal
            UserDetails userDetails)
            throws IOException {
        Utilisateur formateur = utilisateurService
            .findByEmail(userDetails.getUsername());
        coursService.creer(cours, image, formateur);
        return "redirect:/formateur/dashboard";
    }

    // ─── Modifier cours ───────────────────────────────
    @GetMapping("/cours/{id}/modifier")
    public String modifierCoursForm(
            @PathVariable Long id,
            Model model) {
        model.addAttribute("cours",
            coursService.findById(id));
        model.addAttribute("niveaux", Niveau.values());
        model.addAttribute("categories", List.of(
            "Programmation", "Design", "DevOps",
            "Base de donnees", "Securite", "IA"));
        return "formateur/cours-form";
    }

    @PostMapping("/cours/{id}/modifier")
    public String modifierCours(
            @PathVariable Long id,
            @ModelAttribute Cours cours,
            @RequestParam("image") MultipartFile image)
            throws IOException {
        coursService.modifier(id, cours, image);
        return "redirect:/formateur/dashboard";
    }

    // ─── Supprimer cours ──────────────────────────────
    @PostMapping("/cours/{id}/supprimer")
    public String supprimerCours(
            @PathVariable Long id)
            throws IOException {
        coursService.supprimer(id);
        return "redirect:/formateur/dashboard";
    }

    // ─── Voir inscrits d'un cours ─────────────────────
    @GetMapping("/cours/{coursId}/inscrits")
    public String voirInscrits(
            @PathVariable Long coursId,
            Model model) {
        Cours cours = coursService.findById(coursId);
        model.addAttribute("cours", cours);
        model.addAttribute("inscrits",
            inscriptionRepository.findByCours(cours));
        return "formateur/inscrits";
    }

    // ─── Liste modules ────────────────────────────────
    @GetMapping("/cours/{coursId}/modules")
    public String listeModules(
            @PathVariable Long coursId,
            Model model) {
        Cours cours = coursService.findById(coursId);
        model.addAttribute("cours", cours);
        model.addAttribute("modules",
            moduleService.findByCours(cours));
        model.addAttribute("nouveauModule",
            new CourseModule());
        return "formateur/modules";
    }

    // ─── Créer module ─────────────────────────────────
    @PostMapping("/cours/{coursId}/modules/nouveau")
    public String creerModule(
            @PathVariable Long coursId,
            @ModelAttribute("nouveauModule")
            CourseModule module) {
        moduleService.creer(module, coursId);
        return "redirect:/formateur/cours/"
            + coursId + "/modules";
    }

    // ─── Supprimer module ─────────────────────────────
    @PostMapping("/modules/{id}/supprimer")
    public String supprimerModule(
            @PathVariable Long id) {
        Long coursId = moduleService
            .findById(id).getCours().getId();
        moduleService.supprimer(id);
        return "redirect:/formateur/cours/"
            + coursId + "/modules";
    }

    // ─── Liste leçons ─────────────────────────────────
    @GetMapping("/modules/{moduleId}/lecons")
    public String listeLecons(
            @PathVariable Long moduleId,
            Model model) {
        CourseModule module =
            moduleService.findById(moduleId);
        model.addAttribute("module", module);
        model.addAttribute("lecons",
            leconService.findByModule(module));
        model.addAttribute("nouvelleLecon", new Lecon());
        return "formateur/lecons";
    }

    // ─── Créer leçon ──────────────────────────────────
    @PostMapping("/modules/{moduleId}/lecons/nouvelle")
    public String creerLecon(
            @PathVariable Long moduleId,
            @ModelAttribute("nouvelleLecon")
            Lecon lecon) {
        leconService.creer(lecon, moduleId);
        return "redirect:/formateur/modules/"
            + moduleId + "/lecons";
    }

    // ─── Supprimer leçon ──────────────────────────────
    @PostMapping("/lecons/{id}/supprimer")
    public String supprimerLecon(
            @PathVariable Long id) {
        Long moduleId = leconService
            .findById(id).getModule().getId();
        leconService.supprimer(id);
        return "redirect:/formateur/modules/"
            + moduleId + "/lecons";
    }
}