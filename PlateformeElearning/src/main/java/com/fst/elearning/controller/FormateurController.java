package com.fst.elearning.controller;

import com.fst.elearning.entities.Cours;
import com.fst.elearning.entities.Module;
import com.fst.elearning.entities.Lecon;
import com.fst.elearning.service.CoursService;
import com.fst.elearning.service.ModuleService;
import com.fst.elearning.service.LeconService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/formateur")
public class FormateurController {

    private final CoursService coursService;
    private final ModuleService moduleService;
    private final LeconService leconService;

    public FormateurController(CoursService coursService,
                               ModuleService moduleService,
                               LeconService leconService) {
        this.coursService = coursService;
        this.moduleService = moduleService;
        this.leconService = leconService;
    }

    //  CRUD Cours (formateur)
    @PostMapping("/cours")
    public Cours createCours(@RequestBody Cours c) {
        return coursService.save(c);
    }

    @GetMapping("/cours")
    public List<Cours> mesCours() {
        return coursService.getCatalogue("", 0).getContent();
    }

    @DeleteMapping("/cours/{id}")
    public void deleteCours(@PathVariable Long id) {
        coursService.delete(id);
    }

    // Modules
    @PostMapping("/modules")
    public Module createModule(@RequestBody Module m) {
        return moduleService.save(m);
    }

    //  Leçons
    @PostMapping("/lecons")
    public Lecon createLecon(@RequestBody Lecon l) {
        return leconService.save(l);
    }
}