-- Création de la table User
CREATE TABLE `USER` (
  `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
  `username` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Ajout d'un index unique sur l'email
CREATE UNIQUE INDEX `USER_email_index` ON `USER` (`email`);

-- Ajout d'un index unique sur le username
CREATE UNIQUE INDEX `USER_username_index` ON `USER` (`username`);

-- Création de la table Subject
CREATE TABLE `SUBJECT` (
  `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `description` TEXT
);

-- Création de la table Post
CREATE TABLE `POST` (
  `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
  `title` VARCHAR(255) NOT NULL,
  `content` TEXT NOT NULL,
  `author_id` INTEGER,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `subject_id` INTEGER
);

-- Création de la table Comment
CREATE TABLE `COMMENT` (
  `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
  `content` TEXT NOT NULL,
  `author_id` INTEGER,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `post_id` INTEGER
);

-- Création de la table Subscription
CREATE TABLE `SUBSCRIPTION` (
  `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
  `user_id` INTEGER,
  `subject_id` INTEGER
);

-- Ajout des clés étrangères
ALTER TABLE `POST` ADD FOREIGN KEY (`author_id`) REFERENCES `USER` (`id`);
ALTER TABLE `POST` ADD FOREIGN KEY (`subject_id`) REFERENCES `SUBJECT` (`id`);

ALTER TABLE `COMMENT` ADD FOREIGN KEY (`author_id`) REFERENCES `USER` (`id`);
ALTER TABLE `COMMENT` ADD FOREIGN KEY (`post_id`) REFERENCES `POST` (`id`);

ALTER TABLE `SUBSCRIPTION` ADD FOREIGN KEY (`user_id`) REFERENCES `USER` (`id`);
ALTER TABLE `SUBSCRIPTION` ADD FOREIGN KEY (`subject_id`) REFERENCES `SUBJECT` (`id`);
