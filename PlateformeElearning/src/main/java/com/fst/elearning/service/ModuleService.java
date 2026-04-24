package com.fst.elearning.service;

import com.fst.elearning.entities.Module;
import com.fst.elearning.repository.ModuleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleService {

    private final ModuleRepository repo;

    public ModuleService(ModuleRepository repo) {
        this.repo = repo;
    }

    public Module save(Module m) {  // Sauvegarder ou modifier un module
        return repo.save(m);
    }

    public List<Module> findAll() {  // Récupérer tous les modules
        return repo.findAll();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}