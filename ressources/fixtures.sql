-- Insertion de données de test
INSERT INTO users (name, email, password, role, created_At, updated_At) VALUES
('user', 'user@user.com', '$2a$10$ioInQ2jPOzoSp1n9roTUE.AAYootj3PuK0DGn2gqln0VcAGYk2PFi', 'USER', '2025-01-23 14:43:55', '2025-01-23 14:43:55');

-- Insertion des sujets
INSERT INTO subjects (name, description, created_At) VALUES
('Java', 'Discussions sur le langage de programmation Java', '2023-01-15 09:00:00'),
('Spring Boot', 'Discussions sur le framework Spring Boot', '2023-02-15 10:00:00'),
('Javascript', 'Discussions sur le langage de programmation JavaScript', '2023-03-15 11:00:00'),
('Angular', 'Discussions sur le framework Angular', '2023-04-15 12:00:00');

-- Insertion des posts
INSERT INTO posts (content, created_at, subject_id, author_id) VALUES
('Lorem ipsum dolor sit amet, consectetur adipiscing elit. Java est un langage de programmation puissant.', '2023-01-20 13:00:00', 1, 1),
('Lorem ipsum dolor sit amet, consectetur adipiscing elit. Spring Boot facilite le développement d\'applications.', '2023-02-20 14:00:00', 2, 2),
('Lorem ipsum dolor sit amet, consectetur adipiscing elit. JavaScript est essentiel pour le développement web.', '2023-03-20 15:00:00', 3, 3),
('Lorem ipsum dolor sit amet, consectetur adipiscing elit. Angular est un framework front-end populaire.', '2023-04-20 16:00:00', 4, 1);

-- Insertion des commentaires
INSERT INTO comments (content, createdAt, author_id, post_id) VALUES
('Je suis d\'accord, Java est très puissant.', '2023-01-21 17:00:00', 1, 1),
('Spring Boot a vraiment simplifié mon travail.', '2023-02-21 18:00:00', 1, 2),
('JavaScript est indispensable pour le développement web moderne.', '2023-03-21 19:00:00', 1, 3),
('Angular est mon framework préféré pour les applications web.', '2023-04-21 20:00:00', 1, 4);

-- Insertion des abonnements
INSERT INTO subscriptions (user_id, subject_id) VALUES
(1, 1),
(1, 2),
(1, 3),
(1. 4);