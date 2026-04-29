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

    public Inscription save(Inscription i) {
        return repo.save(i);
    }

    public List<Inscription> findByUser(Long userId) {
        return repo.findByApprenantId(userId);
    }
}