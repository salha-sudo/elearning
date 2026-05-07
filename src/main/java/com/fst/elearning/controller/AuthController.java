package com.fst.elearning.controller;

import com.fst.elearning.entity.Utilisateur;
import com.fst.elearning.enums.Role;
import com.fst.elearning.service.CoursService;
import com.fst.elearning.service.UtilisateurService;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation
    .AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    private final UtilisateurService utilisateurService;
    private final CoursService coursService;

    public AuthController(
            UtilisateurService utilisateurService,
            CoursService coursService) {
        this.utilisateurService = utilisateurService;
        this.coursService = coursService;
    }

    // ─── Page Accueil ─────────────────────────────────
    @GetMapping("/")
    public String home(
            @AuthenticationPrincipal
            UserDetails userDetails,
            Model model) {

        // Si connecté → rediriger selon rôle
        if (userDetails != null) {
            if (userDetails.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority()
                        .equals("ROLE_FORMATEUR"))) {
                return "redirect:/formateur/dashboard";
            }
            if (userDetails.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority()
                        .equals("ROLE_APPRENANT"))) {
                return "redirect:/apprenant/dashboard";
            }
            if (userDetails.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority()
                        .equals("ROLE_ADMIN"))) {
                return "redirect:/admin/dashboard";
            }
        }

        // Page accueil publique
        Page cours = coursService
            .getCatalogue(null, null, null, 0, 6);
        model.addAttribute("coursFeatured",
            cours.getContent());
        return "index";
    }

    // ─── Page Login ───────────────────────────────────
    @GetMapping("/login")
    public String loginPage(
            @AuthenticationPrincipal
            UserDetails userDetails) {

        // Si déjà connecté → dashboard
        if (userDetails != null) {
            return "redirect:/dashboard";
        }
        return "auth/login";
    }

    // ─── Page Register ────────────────────────────────
    @GetMapping("/register")
    public String registerPage(
            @AuthenticationPrincipal
            UserDetails userDetails,
            Model model) {

        // Si déjà connecté → dashboard
        if (userDetails != null) {
            return "redirect:/dashboard";
        }

        model.addAttribute("utilisateur",
            new Utilisateur());
        return "auth/register";
    }

    // ─── Traitement Register ──────────────────────────
    @PostMapping("/register")
    public String register(
            @ModelAttribute Utilisateur utilisateur,
            @RequestParam Role role,
            Model model) {
        try {
            utilisateurService.inscrire(
                utilisateur, role);
            return "redirect:/login?registered";
        } catch (RuntimeException e) {
            model.addAttribute("erreur",
                e.getMessage());
            model.addAttribute("utilisateur",
                utilisateur);
            return "auth/register";
        }
    }

    // ─── Redirection après connexion ──────────────────
    @GetMapping("/dashboard")
    public String dashboard(
            @AuthenticationPrincipal
            UserDetails userDetails) {

        // Si non connecté → login
        if (userDetails == null) {
            return "redirect:/login";
        }

        // Redirection selon le rôle
        if (userDetails.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority()
                    .equals("ROLE_FORMATEUR"))) {
            return "redirect:/formateur/dashboard";
        }

        if (userDetails.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority()
                    .equals("ROLE_APPRENANT"))) {
            return "redirect:/apprenant/dashboard";
        }

        if (userDetails.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority()
                    .equals("ROLE_ADMIN"))) {
            return "redirect:/admin/dashboard";
        }

        // Par défaut → accueil
        return "redirect:/";
    }
}