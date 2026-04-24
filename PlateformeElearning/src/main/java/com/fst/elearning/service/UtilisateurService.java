package com.fst.elearning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fst.elearning.entities.Utilisateur;
import com.fst.elearning.repository.UtilisateurRepository;

@Service
public class UtilisateurService { 

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void register(Utilisateur user) {
        //  encoder le mot de passe
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // rôle par défaut
        user.setRole("USER");

        //  sauvegarde
        utilisateurRepository.save(user);
    }
}