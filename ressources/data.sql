CREATE TABLE IF NOT EXISTS `comments` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content` varchar(255) NOT NULL,
  `author_id` bigint DEFAULT NULL,
  `post_id` bigint DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `posts` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content` varchar(500) DEFAULT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `subject_id` bigint DEFAULT NULL,
  `author_id` bigint DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `subjects` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `subscriptions` (
  `user_id` bigint NOT NULL,
  `subject_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`subject_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

ALTER TABLE `comments` 
ADD CONSTRAINT `fk_comments_author` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
ADD CONSTRAINT `fk_comments_post` FOREIGN KEY (`post_id`) REFERENCES `posts` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `posts` 
ADD CONSTRAINT `fk_posts_author` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
ADD CONSTRAINT `fk_posts_subject` FOREIGN KEY (`subject_id`) REFERENCES `subjects` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

ALTER TABLE `subscriptions` 
ADD CONSTRAINT `fk_subscriptions_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `fk_subscriptions_subject` FOREIGN KEY (`subject_id`) REFERENCES `subjects` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;


INSERT INTO `users` (`name`, `email`, `password`, `role`, `created_at`, `updated_at`) VALUES
('user', 'user@user.com', '$2a$10$ioInQ2jPOzoSp1n9roTUE.AAYootj3PuK0DGn2gqln0VcAGYk2PFi', 'USER', '2025-01-23 14:43:55', '2025-01-23 14:43:55'),
('Marie Dupont', 'marie@example.com', '$2a$10$ioInQ2jPOzoSp1n9roTUE.AAYootj3PuK0DGn2gqln0VcAGYk2PFi', 'USER', '2025-01-24 10:23:15', '2025-01-24 10:23:15'),
('Jean Martin', 'jean@example.com', '$2a$10$ioInQ2jPOzoSp1n9roTUE.AAYootj3PuK0DGn2gqln0VcAGYk2PFi', 'USER', '2025-01-25 14:18:33', '2025-01-25 14:18:33'),
('Sophie Bernard', 'sophie@example.com', '$2a$10$ioInQ2jPOzoSp1n9roTUE.AAYootj3PuK0DGn2gqln0VcAGYk2PFi', 'ADMIN', '2025-01-26 09:45:12', '2025-01-26 09:45:12'),
('Pierre Leroy', 'pierre@example.com', '$2a$10$ioInQ2jPOzoSp1n9roTUE.AAYootj3PuK0DGn2gqln0VcAGYk2PFi', 'USER', '2025-01-27 16:37:28', '2025-01-27 16:37:28');

INSERT INTO `subjects` (`name`, `description`, `created_at`) VALUES
('Java', 'Discussions sur le langage de programmation Java', '2023-01-15 09:00:00'),
('Spring Boot', 'Discussions sur le framework Spring Boot', '2023-02-15 10:00:00'),
('Javascript', 'Discussions sur le langage de programmation JavaScript', '2023-03-15 11:00:00'),
('Angular', 'Discussions sur le framework Angular', '2023-04-15 12:00:00');

INSERT INTO `posts` (`content`, `created_at`, `subject_id`, `author_id`, `title`) VALUES
('Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam eget felis eget turpis vehicula ultrices. Donec sollicitudin molestie malesuada. Vivamus suscipit tortor eget felis porttitor volutpat. Curabitur non nulla sit amet nisl tempus convallis quis ac lectus. Donec rutrum congue leo eget malesuada. Cras ultricies ligula sed magna dictum porta. Praesent sapien massa, convallis a pellentesque nec, egestas non nisi.', '2025-02-01 08:30:00', 1, 1, 'Introduction à Java 17'),
('Vestibulum ac diam sit amet quam vehicula elementum sed sit amet dui. Pellentesque in ipsum id orci porta dapibus. Mauris blandit aliquet elit, eget tincidunt nibh pulvinar a. Curabitur aliquet quam id dui posuere blandit. Donec sollicitudin molestie malesuada. Mauris blandit aliquet elit, eget tincidunt nibh pulvinar a. Praesent sapien massa, convallis a pellentesque nec, egestas non nisi.', '2025-02-02 10:15:00', 2, 2, 'Débuter avec Spring Boot 3'),
('Nulla quis lorem ut libero malesuada feugiat. Vivamus magna justo, lacinia eget consectetur sed, convallis at tellus. Proin eget tortor risus. Donec rutrum congue leo eget malesuada. Curabitur arcu erat, accumsan id imperdiet et, porttitor at sem. Curabitur non nulla sit amet nisl tempus convallis quis ac lectus. Sed porttitor lectus nibh.', '2025-02-03 14:45:00', 3, 3, 'Les nouveautés de JavaScript ES2023'),
('Curabitur aliquet quam id dui posuere blandit. Praesent sapien massa, convallis a pellentesque nec, egestas non nisi. Vivamus suscipit tortor eget felis porttitor volutpat. Curabitur arcu erat, accumsan id imperdiet et, porttitor at sem. Nulla porttitor accumsan tincidunt. Cras ultricies ligula sed magna dictum porta. Donec rutrum congue leo eget malesuada.', '2025-02-04 16:20:00', 4, 4, 'Angular vs React: Comparaison'),
('Mauris blandit aliquet elit, eget tincidunt nibh pulvinar a. Vivamus magna justo, lacinia eget consectetur sed, convallis at tellus. Sed porttitor lectus nibh. Curabitur non nulla sit amet nisl tempus convallis quis ac lectus. Donec sollicitudin molestie malesuada. Proin eget tortor risus. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia.', '2025-02-05 09:10:00', 1, 2, 'Les design patterns en Java'),
('Cras ultricies ligula sed magna dictum porta. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia. Vivamus suscipit tortor eget felis porttitor volutpat. Nulla quis lorem ut libero malesuada feugiat. Vestibulum ac diam sit amet quam vehicula elementum sed sit amet dui. Praesent sapien massa, convallis a pellentesque nec, egestas non nisi.', '2025-02-06 11:40:00', 2, 3, 'Sécurité dans Spring Boot');

INSERT INTO `comments` (`content`, `author_id`, `post_id`, `created_at`) VALUES
('Excellente introduction, très claire et précise !', 2, 1, '2025-02-01 09:15:00.000000'),
('Je rencontre une erreur avec cette version, quelqu\'un peut m\'aider ?', 3, 1, '2025-02-01 10:30:00.000000'),
('As-tu essayé de mettre à jour tes dépendances ?', 4, 1, '2025-02-01 11:45:00.000000'),
('Très bon article, j\'ai appris beaucoup de choses.', 1, 2, '2025-02-02 12:20:00.000000'),
('Je préfère utiliser Kotlin avec Spring Boot, c\'est plus moderne.', 4, 2, '2025-02-02 14:05:00.000000'),
('Merci pour ce partage, ça va m\'aider pour mon projet !', 2, 3, '2025-02-03 16:10:00.000000'),
('J\'ai testé ces nouveautés, elles sont vraiment pratiques.', 1, 3, '2025-02-03 17:30:00.000000'),
('Je ne suis pas d\'accord avec ta comparaison, Angular a plus d\'avantages.', 3, 4, '2025-02-04 18:15:00.000000'),
('React est plus léger et plus facile à apprendre selon moi.', 2, 4, '2025-02-04 19:45:00.000000'),
('Tu as oublié de mentionner le pattern Observer qui est très utile.', 4, 5, '2025-02-05 10:30:00.000000'),
('Super article, j\'aimerais voir plus d\'exemples concrets.', 3, 5, '2025-02-05 11:50:00.000000'),
('La sécurité est souvent négligée, merci d\'avoir abordé ce sujet.', 1, 6, '2025-02-06 13:25:00.000000');

-- Ajout de subscriptions (en préservant les sujets existants)
INSERT INTO `subscriptions` (`user_id`, `subject_id`) VALUES
(1, 3), -- user existant s'abonne à JavaScript
(1, 4), -- user existant s'abonne à Angular
(2, 1), -- Marie s'abonne à Java
(2, 3), -- Marie s'abonne à JavaScript
(3, 2), -- Jean s'abonne à Spring Boot
(3, 4), -- Jean s'abonne à Angular
(4, 1), -- Sophie s'abonne à Java
(4, 2), -- Sophie s'abonne à Spring Boot
(4, 3), -- Sophie s'abonne à JavaScript
(4, 4); -- Pierre s'abonne à Ang