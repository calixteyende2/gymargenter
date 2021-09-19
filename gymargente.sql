-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : Dim 19 sep. 2021 à 17:23
-- Version du serveur :  5.7.31
-- Version de PHP : 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `gymargente`
--

-- --------------------------------------------------------

--
-- Structure de la table `adresse`
--

DROP TABLE IF EXISTS `adresse`;
CREATE TABLE IF NOT EXISTS `adresse` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nom_rue` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `numero_app` int(25) DEFAULT NULL,
  `numero_rue` int(25) DEFAULT NULL,
  `pays` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `province_region` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ville` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `adresse`
--

INSERT INTO `adresse` (`id`, `nom_rue`, `numero_app`, `numero_rue`, `pays`, `province_region`, `ville`) VALUES
(1, 'CALIXTE YENDE', 201, 8521, 'Canada', 'Québec', 'Québec'),
(2, 'Saint-Louis', 454, 3635, 'Canada', 'Québec', 'Québec'),
(3, 'Sainte-Anne', 106, 3635, 'Canada', 'Québec', 'Québec'),
(4, 'Chemin Sainte-Foy', 20, 105, 'Canada', 'Québec', 'Québec'),
(5, 'Mont Thabor', 210, 2440, 'Canada', 'Québec', 'Québec'),
(6, 'Grande Allée Est', 0, 464, 'Canada', 'Québec', 'Québec'),
(7, 'Laurier', 3, 88, 'Canada', 'Québec', 'Québec'),
(8, 'Ch Ste Foy', 22, 45, 'Canada', 'Québec', 'Québec'),
(9, 'gfjgjgj', 4845, 447, 'Canada', 'Québec', 'Québec'),
(10, 'ste foy', 845, 464, 'Canada', 'Québec', 'Québec'),
(11, 'Ch Ste Foy', 48, 474, 'Canada', 'Québec', 'Québec'),
(12, 'CALIXTE YENDE', 0, 0, 'Canada', 'Québec', 'Québec'),
(13, 'CALIXTE YENDE', 7, 88, 'Canada', 'Québec', 'Québec'),
(14, '8521 rue du Chevalet', 77, 88, 'Canada', 'Québec', 'Québec');

-- --------------------------------------------------------

--
-- Structure de la table `notification`
--

DROP TABLE IF EXISTS `notification`;
CREATE TABLE IF NOT EXISTS `notification` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_fin` timestamp NULL DEFAULT NULL,
  `email_client` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email_gestionnaire` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `message` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `notification`
--

INSERT INTO `notification` (`id`, `date_fin`, `email_client`, `email_gestionnaire`, `message`) VALUES
(1, '2021-10-08 05:54:00', 'tarasummers@yahoo.fr', 'calixte@yahoo.fr', 'Fin abonnement dans 30 jours'),
(2, '2021-12-10 23:39:00', 'matthiasacevedo@yahoo.fr', 'calixte@yahoo.fr', 'Fin abonnement dans 30 jours'),
(3, '2021-12-11 01:22:00', 'ravenmarshall@yahoo.fr', 'calixte@yahoo.fr', 'Fin abonnement dans 30 jours');

-- --------------------------------------------------------

--
-- Structure de la table `plan`
--

DROP TABLE IF EXISTS `plan`;
CREATE TABLE IF NOT EXISTS `plan` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `prix` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `plan`
--

INSERT INTO `plan` (`id`, `code`, `name`, `prix`) VALUES
(1, 'CM', 'Conditionnement Mensuel', 80),
(2, 'OPTCGM', 'Option Cours de Groupe Mensuel', 25),
(3, 'CT', 'Conditionnement Trimestriel', 220),
(4, 'OPTCGT', 'Option Cours de Groupe Trimestriel', 60),
(5, 'CA', 'Conditionnement Annuel ', 800),
(6, 'OPTCGA', 'Option Cours de Groupe Annuel', 200),
(7, 'CGM', 'Cours de Groupe Mensuel', 50),
(8, 'CGT', 'Cours de Groupe Trimestriel', 135),
(9, 'CGA', 'Cours de Groupe Annuel', 500),
(10, 'ENTP', 'Entraineur Privé', 75),
(11, 'PHYSIO', 'Physiothérapeute', 75),
(12, 'NUTRI', 'Nutritionniste ', 75);

-- --------------------------------------------------------

--
-- Structure de la table `rendez_vous`
--

DROP TABLE IF EXISTS `rendez_vous`;
CREATE TABLE IF NOT EXISTS `rendez_vous` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `client` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `date_debut` datetime(6) NOT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `domaine` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `quantite` int(11) NOT NULL,
  `specialiste` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `rendez_vous`
--

INSERT INTO `rendez_vous` (`id`, `client`, `date_debut`, `description`, `domaine`, `quantite`, `specialiste`) VALUES
(2, 'matthiasacevedo@yahoo.fr', '2021-09-19 17:17:00.000000', 'Séance de discussion											', 'NUTRI', 2, 'leannali@yahoo.fr');

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `role`
--

INSERT INTO `role` (`id`, `role_name`) VALUES
(1, 'CLIENT'),
(2, 'USER'),
(3, 'ADMIN');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `active` bit(1) DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `username` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `utilisateur_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmw3oj0gf2pc2ogdeqq3ixcrhb` (`utilisateur_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `active`, `password`, `username`, `utilisateur_id`) VALUES
(1, b'1', '$2a$10$Ls4OT/kJdVYDhYjuO2BqpeWOOCqNe.L6RPDZY565ha9L4v6KK93Tm', 'calixte@yahoo.fr', NULL),
(5, b'1', '$2a$10$AIpSqTC5HIO8LlLSr43heukp7JId8/or7sBCgDyL5AV.WzDyzVD0W', 'matthiasacevedo@yahoo.fr', NULL),
(6, b'1', '$2a$10$Snv4SVbRV6iFgLYyIt28auSwWDJyLLXtWqXWUH2be/0bsqrMU8AJ.', 'ravenmarshall@yahoo.fr', NULL),
(7, b'1', '$2a$10$CYtptsTEW/8KoSxchFv/aOQtO8/gBcgRhWX24rw4bYKpDo4p5bE6S', 'leannali@yahoo.fr', NULL),
(8, b'1', '$2a$10$nGO28qHbPacRinEo6CoUJuL5gxosW5F.rFA/CmV7OM7dTw2r4RtXC', 'calixte1@yahoo.fr', NULL),
(9, b'1', '$2a$10$ON.8csO6by303gdThsPBCOCaPRL/TMkUETiwVBaGblHuthtHAVgSa', 'calixte1@yahoo.fr', NULL),
(10, b'1', '$2a$10$NDVWwzXdrfSW6C9hTh4XfehygXjQeSBRRD1vzRVptDINlRnErmen.', 'calixte1@yahoo.fr', NULL),
(11, b'1', '$2a$10$ww3b2gKG5Kki1qAf1jZ.zeMzJrvI8Bjejt8Bb40CsiWcwT6QlSbKe', 'calixteee@yahoo.fr', NULL),
(12, b'1', '$2a$10$EjyLzOjqpJV0xDY/R.pO5OM2CuhXwsVHHlH.0dPcLT4fxQRZNenXK', 'calixtehh@yahoo.fr', NULL),
(13, b'1', '$2a$10$PYZZB2FpVsuInzlZ5bbL0OzAsluBoK0ayOKOeccDqtWJV8/GFZq5u', 'yendecalixtehhh@yahoo.fr', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
CREATE TABLE IF NOT EXISTS `user_role` (
  `username` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `role` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `user_role`
--

INSERT INTO `user_role` (`username`, `role`) VALUES
('calixte@yahoo.fr', 'ADMIN'),
('calixte1@yahoo.fr', 'ADMIN'),
('calixteee@yahoo.fr', 'ADMIN'),
('calixtehh@yahoo.fr', 'ADMIN'),
('leannali@yahoo.fr', 'USER'),
('matthiasacevedo@yahoo.fr', 'CLIENT'),
('ravenmarshall@yahoo.fr', 'CLIENT'),
('yendecalixtehhh@yahoo.fr', 'ADMIN');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `dtype` varchar(31) COLLATE utf8_unicode_ci NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `datenaissance` date DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `firstname` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `lastname` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ca` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cga` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cgm` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cgt` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cm` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ct` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `entp` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nutri` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `optcga` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `optcgm` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `optcgt` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `physio` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `date_debut` timestamp NULL DEFAULT NULL,
  `date_fin` timestamp NULL DEFAULT NULL,
  `adresse_id` bigint(20) NOT NULL,
  `qentp` int(11) DEFAULT NULL,
  `qnutri` int(11) DEFAULT NULL,
  `qphysio` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdvxe31y87uhakroedfn9vxgdy` (`adresse_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`dtype`, `id`, `datenaissance`, `email`, `firstname`, `lastname`, `password`, `phone`, `ca`, `cga`, `cgm`, `cgt`, `cm`, `ct`, `entp`, `nutri`, `optcga`, `optcgm`, `optcgt`, `physio`, `date_debut`, `date_fin`, `adresse_id`, `qentp`, `qnutri`, `qphysio`) VALUES
('Gestionnaire', 1, '2021-09-07', 'calixte@yahoo.fr', 'CALIXTE', 'YENDE', '$2a$10$Ls4OT/kJdVYDhYjuO2BqpeWOOCqNe.L6RPDZY565ha9L4v6KK93Tm', '5819907657', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL),
('Client', 5, '2021-09-10', 'matthiasacevedo@yahoo.fr', 'Matthias ', 'Acevedo', '$2a$10$AIpSqTC5HIO8LlLSr43heukp7JId8/or7sBCgDyL5AV.WzDyzVD0W', '5819907657', NULL, NULL, NULL, NULL, 'Conditionnement Mensuel', NULL, NULL, NULL, NULL, 'Option Cours de Groupe Mensuel', NULL, NULL, '2021-09-10 21:39:00', '2021-12-10 23:39:00', 6, 5, 4, 7),
('Client', 6, '2021-09-10', 'ravenmarshall@yahoo.fr', 'Raven ', 'Marshall', '$2a$10$Snv4SVbRV6iFgLYyIt28auSwWDJyLLXtWqXWUH2be/0bsqrMU8AJ.', '5819907657', NULL, NULL, NULL, NULL, 'Conditionnement Mensuel', NULL, NULL, NULL, NULL, 'Option Cours de Groupe Mensuel', NULL, NULL, '2021-09-10 23:22:00', '2021-12-11 01:22:00', 7, 3, 9, 12),
('Specialiste', 7, '2021-09-10', 'leannali@yahoo.fr', 'Leanna ', 'Li', '$2a$10$CYtptsTEW/8KoSxchFv/aOQtO8/gBcgRhWX24rw4bYKpDo4p5bE6S', '5819907657', NULL, NULL, NULL, NULL, NULL, NULL, 'ENTP', 'NUTRI', NULL, NULL, NULL, 'PHYSIO', NULL, NULL, 8, NULL, NULL, NULL),
('Gestionnaire', 9, '2021-09-12', 'calixtehh@yahoo.fr', 'CALIXTE', 'YENDE', '$2a$10$EjyLzOjqpJV0xDY/R.pO5OM2CuhXwsVHHlH.0dPcLT4fxQRZNenXK', '5819907657', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 13, NULL, NULL, NULL),
('Gestionnaire', 10, '2021-09-12', 'yendecalixtehhh@yahoo.fr', 'CALIXTE', 'YENDE', '$2a$10$PYZZB2FpVsuInzlZ5bbL0OzAsluBoK0ayOKOeccDqtWJV8/GFZq5u', '5819907657', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 14, NULL, NULL, NULL);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FKmw3oj0gf2pc2ogdeqq3ixcrhb` FOREIGN KEY (`utilisateur_id`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD CONSTRAINT `FKdvxe31y87uhakroedfn9vxgdy` FOREIGN KEY (`adresse_id`) REFERENCES `adresse` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
