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






