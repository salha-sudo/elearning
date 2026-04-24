

package com.fst.elearning.service;

import com.fst.elearning.dto.ProgressionDTO;
import com.fst.elearning.repository.InscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgressionService {

    private final InscriptionRepository repo;

    public ProgressionService(InscriptionRepository repo) {
        this.repo = repo;
    }

    public List<ProgressionDTO> getProgression(Long userId) {
        return repo.getProgression(userId);
    }
}