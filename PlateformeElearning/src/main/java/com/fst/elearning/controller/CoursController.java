package com.fst.elearning.controller;

import com.fst.elearning.entities.Cours;
import com.fst.elearning.service.CoursService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cours")
public class CoursController {

    private final CoursService service;

    public CoursController(CoursService service) {
        this.service = service;
    }

    // Catalogue public (pagination + recherche)
    @GetMapping("/catalogue")
    public Page<Cours> catalogue(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "0") int page
    ) {
        return service.getCatalogue(keyword, page);
    }

    //  CRUD
    @PostMapping
    public Cours create(@RequestBody Cours c) {
        return service.save(c);
    }

    @GetMapping("/{id}")
    public Cours get(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public Cours update(@PathVariable Long id, @RequestBody Cours c) {
        c.setId(id);
        return service.save(c);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
    @GetMapping("/test")
    public String test() {
        return "login";
    }
}