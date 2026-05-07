package com.fst.elearning.controller;

import com.fst.elearning.entity.*;
import com.fst.elearning.service.*;
import org.springframework.security.core.annotation
    .AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@Controller
public class QuizController {

    private final QuizService quizService;
    private final UtilisateurService utilisateurService;
    private final ModuleService moduleService;

    public QuizController(
            QuizService quizService,
            UtilisateurService utilisateurService,
            ModuleService moduleService) {
        this.quizService = quizService;
        this.utilisateurService = utilisateurService;
        this.moduleService = moduleService;
    }

    // ─── Formateur : créer quiz ───────────────────────
    @GetMapping("/formateur/modules/{moduleId}/quiz/nouveau")
    public String nouveauQuizForm(
            @PathVariable Long moduleId, Model model) {
        model.addAttribute("module",
            moduleService.findById(moduleId));
        return "formateur/quiz-form";
    }

    @PostMapping("/formateur/modules/{moduleId}/quiz/nouveau")
    public String creerQuiz(
            @PathVariable Long moduleId,
            @RequestParam String titre) {
        quizService.creerQuiz(moduleId, titre);
        return "redirect:/formateur/modules/"
            + moduleId + "/quiz/questions";
    }

    // ─── Formateur : gérer questions ──────────────────
    @GetMapping("/formateur/modules/{moduleId}/quiz/questions")
    public String gererQuestions(
            @PathVariable Long moduleId, Model model) {
        Quiz quiz = quizService.findByModuleId(moduleId);
        CourseModule module =
            moduleService.findById(moduleId);

        model.addAttribute("module", module);
        model.addAttribute("quiz", quiz);
        model.addAttribute("questions",
            quiz != null ?
            quizService.getQuestions(quiz.getId()) :
            java.util.List.of());
        model.addAttribute("nouvelleQuestion",
            new Question());
        return "formateur/quiz-questions";
    }

    @PostMapping("/formateur/quiz/{quizId}/question/nouvelle")
    public String ajouterQuestion(
            @PathVariable Long quizId,
            @ModelAttribute Question question) {
        quizService.ajouterQuestion(quizId, question);
        Quiz quiz = quizService.findById(quizId);
        Long moduleId = quiz.getModule().getId();
        return "redirect:/formateur/modules/"
            + moduleId + "/quiz/questions";
    }

    @PostMapping("/formateur/questions/{id}/supprimer")
    public String supprimerQuestion(
            @PathVariable Long id) {
        quizService.supprimerQuestion(id);
        return "redirect:/formateur/dashboard";
    }

    // ─── Apprenant : passer quiz ──────────────────────
    @GetMapping("/apprenant/quiz/{quizId}")
    public String passerQuiz(
            @PathVariable Long quizId,
            Model model) {
        Quiz quiz = quizService.findById(quizId);
        model.addAttribute("quiz", quiz);
        model.addAttribute("questions",
            quizService.getQuestions(quizId));
        return "apprenant/quiz";
    }

    @PostMapping("/apprenant/quiz/{quizId}/soumettre")
    public String soumettreQuiz(
            @PathVariable Long quizId,
            @RequestParam Map<String, String> params,
            @AuthenticationPrincipal UserDetails userDetails,
            Model model) {

        Utilisateur apprenant = utilisateurService
            .findByEmail(userDetails.getUsername());

        // Extraire les réponses
        java.util.Map<Long, String> reponses =
            new java.util.HashMap<>();
        for (Map.Entry<String, String> entry :
                params.entrySet()) {
            if (entry.getKey().startsWith("question_")) {
                Long questionId = Long.parseLong(
                    entry.getKey().replace("question_", ""));
                reponses.put(questionId, entry.getValue());
            }
        }

        ReponseApprenant resultat =
            quizService.corrigerQuiz(
                apprenant, quizId, reponses);

        model.addAttribute("resultat", resultat);
        model.addAttribute("quiz",
            quizService.findById(quizId));
        return "apprenant/quiz-resultat";
    }
}
