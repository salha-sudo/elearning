package com.fst.elearning.service;

import com.fst.elearning.entities.Inscription;
import com.fst.elearning.repository.InscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscriptionService {

    private final InscriptionRepository repo;

    public InscriptionService(InscriptionRepository repo) {
        this.repo = repo;
    }

    public Inscription save(Inscription i) {  // Enregistrer une inscription (apprenant -> cours)
        return repo.save(i);
    }

    public List<Inscription> findAll() {      // Récupérer toutes les inscriptions
        return repo.findAll();
    }
}