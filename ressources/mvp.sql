
CREATE TABLE IF NOT EXISTS `comments` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content` varchar(255) NOT NULL,
  `author_id` bigint DEFAULT NULL,
  `post_id` bigint DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKn2na60ukhs76ibtpt9burkm27` (`author_id`),
  KEY `FKh4c7lvsc298whoyd4w9ta25cr` (`post_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `comments`
--

INSERT INTO `comments` (`id`, `content`, `createdAt`, `author_id`, `post_id`, `created_at`) VALUES
(1, 'Je suis d\'accord, Java est très puissant.', '2023-01-21 17:34:02', 1, 1, '2023-01-21 17:33:39.000000'),
(2, 'Spring Boot a vraiment simplifié mon travail.', '2023-02-21 18:00:00', 1, 2, '2023-01-21 17:33:46.000000'),
(3, 'JavaScript est indispensable pour le développement web moderne.', '2023-03-21 19:00:00', 1, 3, '2023-01-21 17:33:54.000000'),
(4, 'Angular est mon framework préféré pour les applications web.', '2023-04-21 20:00:00', 1, 4, '2023-01-21 17:33:58.000000');

-- --------------------------------------------------------


CREATE TABLE IF NOT EXISTS `posts` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `subject_id` bigint DEFAULT NULL,
  `author_id` bigint DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6xvn0811tkyo3nfjk2xvqx6ns` (`author_id`),
  KEY `FKk31c8ca9t9q3hp8w73ymubhap` (`subject_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `posts`
--

INSERT INTO `posts` (`id`, `content`, `created_at`, `subject_id`, `author_id`, `title`) VALUES
(1, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Java est un langage de programmation puissant.', '2023-01-20 13:00:00', 1, 1, NULL),
(2, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Spring Boot facilite le développement d\'applications.', '2023-02-20 14:00:00', 2, 2, NULL),
(3, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. JavaScript est essentiel pour le développement web.', '2023-03-20 15:00:00', 3, 3, NULL),
(4, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Angular est un framework front-end populaire.', '2023-04-20 16:00:00', 4, 1, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `subjects`
--

CREATE TABLE IF NOT EXISTS `subjects` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `created_At` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `subjects`
--

INSERT INTO `subjects` (`id`, `name`, `description`, `created_At`) VALUES
(1, 'Java', 'Discussions sur le langage de programmation Java', '2023-01-15 09:00:00'),
(2, 'Spring Boot', 'Discussions sur le framework Spring Boot', '2023-02-15 10:00:00'),
(3, 'Javascript', 'Discussions sur le langage de programmation JavaScript', '2023-03-15 11:00:00'),
(4, 'Angular', 'Discussions sur le framework Angular', '2023-04-15 12:00:00');

-- --------------------------------------------------------

--
-- Structure de la table `subscriptions`
--

DROP TABLE IF EXISTS `subscriptions`;
CREATE TABLE IF NOT EXISTS `subscriptions` (
  `user_id` bigint NOT NULL,
  `subject_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`subject_id`),
  KEY `FK8ejb8e2fndtnpalu38hbn7cvv` (`subject_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `subscriptions`
--

INSERT INTO `subscriptions` (`user_id`, `subject_id`) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4);

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `created_At` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_At` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`id`, `name`, `email`, `password`, `role`, `created_At`, `updated_At`) VALUES
(1, 'user', 'user@user.com', '$2a$10$ioInQ2jPOzoSp1n9roTUE.AAYootj3PuK0DGn2gqln0VcAGYk2PFi', 'USER', '2025-01-23 14:43:55', '2025-01-23 14:43:55'),
(2, 'sam', 'sam@user.com', '$2a$10$JD.W5meU4oq113.zotWAJOYyXLfJHU.eUrpNIVEbxC/rLI./LHk4e', 'USER', '2025-02-25 13:43:46', '2025-02-25 13:43:46'),
(3, 'sam2', 'sam2@user.com', '$2a$10$dbXn7/LjDvFdatZXaesg9u6t12lfzzh7tQMTD1spLukX951dy.N7q', 'USER', '2025-02-27 13:32:40', '2025-02-27 13:32:40');


/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
