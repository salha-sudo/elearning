package com.fst.elearning.controller;

import com.fst.elearning.entities.Cours;
import com.fst.elearning.entities.Inscription;
import com.fst.elearning.service.CoursService;
import com.fst.elearning.service.InscriptionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apprenant")
public class ApprenantController {

    private final CoursService coursService;
    private final InscriptionService inscriptionService;

    public ApprenantController(CoursService coursService,
                               InscriptionService inscriptionService) {
        this.coursService = coursService;
        this.inscriptionService = inscriptionService;
    }

    // Voir catalogue
    @GetMapping("/cours")
    public List<Cours> voirCours() {
        return coursService.getCatalogue("", 0).getContent();
    }

    //  S'inscrire à un cours
    @PostMapping("/inscrire")
    public Inscription inscrire(@RequestBody Inscription i) {
        return inscriptionService.save(i);
    }

    // Mes inscriptions
    @GetMapping("/inscriptions")
    public List<Inscription> mesInscriptions() {
        return inscriptionService.findAll();
    }
}