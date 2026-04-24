package com.fst.elearning.service;

import com.fst.elearning.entities.Cours;
import com.fst.elearning.repository.CoursRepository;

import org.springframework.lang.Nullable;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service // @Service = classe métier
public class CoursService {

private final CoursRepository repo;

public CoursService(CoursRepository repo) { // Injection du repository via constructeur
this.repo = repo;
}

public Page<Cours> getCatalogue(String keyword, int page) {  // Récupérer le catalogue des cours avec recherche + pagination
return repo.findByTitreContaining(keyword, PageRequest.of(page, 6));  // PageRequest.of(page, 6) = page demandée + 6 éléments par page
}

public Cours save(Cours c) {  // Sauvegarder ou mettre à jour un cours
return repo.save(c);
}

public void delete(Long id) {   // Supprimer un cours par son ID
repo.deleteById(id);
}

public Cours getById(Long id) {   // Récupérer un cours par ID
return repo.findById(id).orElseThrow();
}



public @Nullable Object getAllCours() {
	// TODO Auto-generated method stub
	return null;
}
}