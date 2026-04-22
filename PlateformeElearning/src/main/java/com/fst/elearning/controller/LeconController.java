package com.fst.elearning.controller;

import com.fst.elearning.entities.Lecon;
import com.fst.elearning.service.LeconService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lecons")
public class LeconController {

    private final LeconService service;

    public LeconController(LeconService service) {
        this.service = service;
    }

    @PostMapping
    public Lecon create(@RequestBody Lecon l) {
        return service.save(l);
    }

    @GetMapping
    public List<Lecon> getAll() {
        return service.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}