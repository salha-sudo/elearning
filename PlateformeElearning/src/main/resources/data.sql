-- UTILISATEURS
INSERT INTO utilisateur (id, username, password, role) VALUES
(1, 'admin', '$2a$10$Dow1sF9G7H0Kj8k', 'ROLE_ADMIN'), -- mot de passe non encodé (Spring Security)
(2, 'formateur1', '$2a$10$Dow1sF9G7H0Kj8kJ1l', 'ROLE_FORMATEUR'),
(3, 'apprenant1', '$2a$10$Dow1sF9G7H0Kj8kJ1lY', 'ROLE_APPRENANT');

-- COURS
INSERT INTO cours (id, titre, description, categorie, niveau, image_url, actif, date_creation, formateur_id)
VALUES
(1, 'Java Basics', 'Cours Java débutant', 'Programmation', 'DEBUTANT', '/uploads/java.jpg', true, NOW(), 2),
(2, 'Spring Boot', 'Framework Spring', 'Backend', 'INTERMEDIAIRE', '/uploads/spring.jpg', true, NOW(), 2),
(3, 'Python', 'Cours Python', 'Programmation', 'DEBUTANT', '/uploads/python.jpg', true, NOW(), 2);

-- MODULES
INSERT INTO module (id, titre, description, ordre, cours_id) VALUES
(1, 'Intro Java', 'Bases', 1, 1),
(2, 'POO Java', 'Objets', 2, 1),
(3, 'Intro Spring', 'Basics', 1, 2),
(4, 'REST API', 'API', 2, 2);

-- LECONS
INSERT INTO lecon (id, titre, contenu, ordre, duree_min, module_id) VALUES
(1, 'Variables', 'contenu', 1, 10, 1),
(2, 'Conditions', 'contenu', 2, 15, 1),
(3, 'Boucles', 'contenu', 3, 20, 1),
(4, 'Classes', 'contenu', 1, 15, 2),
(5, 'Objets', 'contenu', 2, 20, 2),
(6, 'Héritage', 'contenu', 3, 25, 2);

-- INSCRIPTION
INSERT INTO inscription (id, apprenant_id, cours_id, date_inscription, statut)
VALUES (1, 3, 1, CURRENT_DATE, 'EN_COURS');

-- PROGRESSION
INSERT INTO progression_lecon (id, apprenant_id, lecon_id, completee, date_completion)
VALUES
(1, 3, 1, true, NOW()),
(2, 3, 2, true, NOW());