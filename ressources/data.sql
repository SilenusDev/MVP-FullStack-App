-- Étape 1 : Insertion des utilisateurs
INSERT INTO `users` (`name`, `email`, `password`, `role`, `created_At`, `updated_At`) VALUES
('user', 'user@user.com', '$2a$10$ioInQ2jPOzoSp1n9roTUE.AAYootj3PuK0DGn2gqln0VcAGYk2PFi', 'USER', '2025-01-23 14:43:55', '2025-01-23 14:43:55');

-- Étape 2 : Insertion des sujets
INSERT INTO `subjects` (`name`, `description`, `created_At`) VALUES
('Java', 'Discussions sur le langage de programmation Java', '2023-01-15 09:00:00'),
('Spring Boot', 'Discussions sur le framework Spring Boot', '2023-02-15 10:00:00'),
('Javascript', 'Discussions sur le langage de programmation JavaScript', '2023-03-15 11:00:00'),
('Angular', 'Discussions sur le framework Angular', '2023-04-15 12:00:00');

-- Étape 3 : Insertion des posts
INSERT INTO `posts` (`content`, `created_at`, `subject_id`, `author_id`, `title`) VALUES
('Lorem ipsum dolor sit amet, consectetur adipiscing elit. Java est un langage de programmation puissant.', '2023-01-20 13:00:00', 1, 1, NULL),
('Lorem ipsum dolor sit amet, consectetur adipiscing elit. Spring Boot facilite le développement d\'applications.', '2023-02-20 14:00:00', 2, 1, NULL),
('Lorem ipsum dolor sit amet, consectetur adipiscing elit. JavaScript est essentiel pour le développement web.', '2023-03-20 15:00:00', 3, 1, NULL),
('Lorem ipsum dolor sit amet, consectetur adipiscing elit. Angular est un framework front-end populaire.', '2023-04-20 16:00:00', 4, 1, NULL);

-- Étape 4 : Insertion des commentaires
INSERT INTO `comments` (`content`, `author_id`, `post_id`, `created_at`) VALUES
('Je suis d\'accord, Java est très puissant.', 1, 1, '2023-01-21 17:33:39.000000'),
('Spring Boot a vraiment simplifié mon travail.', 1, 2, '2023-01-21 17:33:46.000000'),
('JavaScript est indispensable pour le développement web moderne.', 1, 3, '2023-01-21 17:33:54.000000'),
('Angular est mon framework préféré pour les applications web.', 1, 4, '2023-01-21 17:33:58.000000');

-- Étape 5 : Insertion des abonnements (avec ID)
INSERT INTO `subscriptions` (`user_id`, `subject_id`) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4);


