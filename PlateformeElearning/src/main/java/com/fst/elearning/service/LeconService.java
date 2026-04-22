package com.fst.elearning.service;

import com.fst.elearning.entities.Lecon;
import com.fst.elearning.repository.LeconRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeconService {

    private final LeconRepository repo;

    public LeconService(LeconRepository repo) {
        this.repo = repo;
    }

    public Lecon save(Lecon l) {
        return repo.save(l);
    }

    public List<Lecon> findAll() {
        return repo.findAll();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}