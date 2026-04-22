package com.fst.elearning.controller;

import com.fst.elearning.entities.Module;
import com.fst.elearning.service.ModuleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/modules")
public class ModuleController {

    private final ModuleService service;

    public ModuleController(ModuleService service) {
        this.service = service;
    }

    @PostMapping
    public Module create(@RequestBody Module m) {
        return service.save(m);
    }

    @GetMapping
    public List<Module> getAll() {
        return service.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}