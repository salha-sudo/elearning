INSERT IGNORE INTO utilisateur (id, username, password, role) VALUES
(1, 'admin', '$2a$10$rNLH2OyGMDSb1qtIE3LPWO/tGGxtMhThiaQBTdUrBpPCSel/8UBMG', 'ADMIN'),
(2, 'formateur1', '$2a$10$rNLH2OyGMDSb1qtIE3LPWO/tGGxtMhThiaQBTdUrBpPCSel/8UBMG', 'FORMATEUR'),
(3, 'apprenant1', '$2a$10$rNLH2OyGMDSb1qtIE3LPWO/tGGxtMhThiaQBTdUrBpPCSel/8UBMG', 'APPRENANT');

INSERT IGNORE INTO cours (id, titre, description, categorie, niveau, image_url, actif, date_creation, formateur_id) VALUES
(1, 'Java Basics', 'Cours Java débutant', 'Programmation', 'DEBUTANT', '/uploads/java.jpg', true, NOW(), 2),
(2, 'Spring Boot', 'Framework Spring', 'Backend', 'INTERMEDIAIRE', '/uploads/spring.jpg', true, NOW(), 2),
(3, 'Python', 'Cours Python', 'Programmation', 'DEBUTANT', '/uploads/python.jpg', true, NOW(), 2);

INSERT IGNORE INTO module (id, titre, description, ordre, cours_id) VALUES
(1, 'Intro Java', 'Bases', 1, 1),
(2, 'POO Java', 'Objets', 2, 1),
(3, 'Intro Spring', 'Basics', 1, 2),
(4, 'REST API', 'API', 2, 2);

INSERT IGNORE INTO lecon (id, titre, contenu, ordre, duree_min, module_id) VALUES
(1, 'Variables', 'contenu', 1, 10, 1),
(2, 'Conditions', 'contenu', 2, 15, 1),
(3, 'Boucles', 'contenu', 3, 20, 1),
(4, 'Classes', 'contenu', 1, 15, 2),
(5, 'Objets', 'contenu', 2, 20, 2),
(6, 'Héritage', 'contenu', 3, 25, 2);

-- ← inscription et progression supprimées
-- Hibernate génère les IDs automatiquement via le bouton S'inscrire

UPDATE cours_seq SET next_val = 50;
UPDATE module_seq SET next_val = 50;
UPDATE lecon_seq SET next_val = 50;
UPDATE inscription_seq SET next_val = 50;
UPDATE progression_lecon_seq SET next_val = 50;