-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  mer. 27 mars 2019 à 12:22
-- Version du serveur :  5.7.23
-- Version de PHP :  7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `db_ecole`
--

-- --------------------------------------------------------

--
-- Structure de la table `classe`
--

DROP TABLE IF EXISTS `classe`;
CREATE TABLE IF NOT EXISTS `classe` (
  `ID_Classe` int(11) NOT NULL AUTO_INCREMENT,
  `Nom_Classe` varchar(50) NOT NULL,
  PRIMARY KEY (`ID_Classe`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `classe`
--

INSERT INTO `classe` (`ID_Classe`, `Nom_Classe`) VALUES
(1, '6°A'),
(2, '6°B'),
(3, '6°C'),
(4, '5°A'),
(5, '5°B'),
(6, '5°C'),
(7, '4°A'),
(8, '4°B'),
(9, '4°C'),
(10, '3°A'),
(11, '3°B'),
(12, '3°C');

-- --------------------------------------------------------

--
-- Structure de la table `eleve`
--

DROP TABLE IF EXISTS `eleve`;
CREATE TABLE IF NOT EXISTS `eleve` (
  `ID_Eleve` int(11) NOT NULL AUTO_INCREMENT,
  `Nom_Eleve` varchar(50) NOT NULL,
  `Prenom_Eleve` varchar(50) NOT NULL,
  `ID_Classe` int(11) NOT NULL,
  PRIMARY KEY (`ID_Eleve`),
  KEY `ELEVE_CLASSE_FK` (`ID_Classe`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `eleve`
--

INSERT INTO `eleve` (`ID_Eleve`, `Nom_Eleve`, `Prenom_Eleve`, `ID_Classe`) VALUES
(1, 'HERBY', 'Cyrille', 1),
(2, 'COURTEL ', 'Angelo', 1),
(3, 'PITON ', 'Thomas', 1),
(4, 'COQUILLE', 'Olivier', 2),
(5, 'SALMON', 'Dylan', 2),
(6, 'MERLET', 'Benoit', 3),
(7, 'LE GALL', 'Yann', 3),
(8, 'LE GALL', 'Morgane', 4),
(9, 'LIGERON', 'Yanninck', 4),
(10, 'LIGERON ', 'Didier', 4),
(11, 'MARLEY', 'Bob', 5),
(12, 'MAHE', 'Marie', 5),
(13, 'PICARD', 'Séverine', 6),
(14, 'PICARD', 'Manuela', 6),
(15, 'BOTTE', 'Jérôme', 6),
(16, 'CARDON', 'Mathieu', 7),
(17, 'MARDET', 'Cécile', 7),
(18, 'BUBUTE', 'Armel', 7),
(19, 'MANIQUE', 'Pascal', 8),
(20, 'ALTE', 'Paul', 8),
(21, 'CORLOT', 'Amandine', 8),
(22, 'KIBOU', 'Bahija', 9),
(23, 'MADI', 'Feti', 9),
(24, 'KERGOULET', 'Erwan', 9),
(25, 'FERNAT', 'Fernand', 10),
(26, 'JOUBERT', 'Aline', 10),
(27, 'TARTUFE', 'Thérèse', 10),
(28, 'MONIN', 'Gérald', 11),
(29, 'DROUIN', 'Albert', 11),
(30, 'NAEMI', 'Toufic', 11),
(31, 'NILBE', 'Nadia', 12),
(32, 'VIVOT', 'Valérie', 12);

-- --------------------------------------------------------

--
-- Structure de la table `instruire`
--

DROP TABLE IF EXISTS `instruire`;
CREATE TABLE IF NOT EXISTS `instruire` (
  `ID_Classe` int(11) NOT NULL,
  `ID_Professeur` int(11) NOT NULL,
  PRIMARY KEY (`ID_Classe`,`ID_Professeur`),
  KEY `Instruire_PROFESSEUR0_FK` (`ID_Professeur`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `instruire`
--

INSERT INTO `instruire` (`ID_Classe`, `ID_Professeur`) VALUES
(4, 1),
(5, 1),
(2, 2),
(6, 3),
(8, 4),
(10, 4),
(12, 4),
(9, 5),
(7, 6),
(3, 7),
(11, 7),
(1, 8);

-- --------------------------------------------------------

--
-- Structure de la table `matiere`
--

DROP TABLE IF EXISTS `matiere`;
CREATE TABLE IF NOT EXISTS `matiere` (
  `ID_Matiere` int(11) NOT NULL AUTO_INCREMENT,
  `Nom_Matiere` varchar(50) NOT NULL,
  PRIMARY KEY (`ID_Matiere`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `matiere`
--

INSERT INTO `matiere` (`ID_Matiere`, `Nom_Matiere`) VALUES
(1, 'Mathématiques'),
(2, 'Français'),
(3, 'Anglais'),
(4, 'Physique'),
(5, 'Biologie'),
(6, 'Sport');

-- --------------------------------------------------------

--
-- Structure de la table `professeur`
--

DROP TABLE IF EXISTS `professeur`;
CREATE TABLE IF NOT EXISTS `professeur` (
  `ID_Professeur` int(11) NOT NULL AUTO_INCREMENT,
  `Nom_Professeur` varchar(50) NOT NULL,
  `Prenom_Professeur` varchar(50) NOT NULL,
  `ID_Matiere` int(11) NOT NULL,
  PRIMARY KEY (`ID_Professeur`),
  KEY `PROFESSEUR_MATIERE_FK` (`ID_Matiere`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `professeur`
--

INSERT INTO `professeur` (`ID_Professeur`, `Nom_Professeur`, `Prenom_Professeur`, `ID_Matiere`) VALUES
(1, 'MAMOU', 'Daniel', 1),
(2, 'SACRE', 'Sophie', 2),
(3, 'JADEN', 'Boudy', 3),
(4, 'BADEN', 'Baden', 4),
(5, 'MIOU', 'Miou', 5),
(6, 'BORA', 'Kernel', 6),
(7, 'CAISSE', 'Jean', 1),
(8, 'MOISSAT', 'Marc', 2);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `eleve`
--
ALTER TABLE `eleve`
  ADD CONSTRAINT `ELEVE_CLASSE_FK` FOREIGN KEY (`ID_Classe`) REFERENCES `classe` (`ID_Classe`);

--
-- Contraintes pour la table `instruire`
--
ALTER TABLE `instruire`
  ADD CONSTRAINT `Instruire_CLASSE_FK` FOREIGN KEY (`ID_Classe`) REFERENCES `classe` (`ID_Classe`),
  ADD CONSTRAINT `Instruire_PROFESSEUR0_FK` FOREIGN KEY (`ID_Professeur`) REFERENCES `professeur` (`ID_Professeur`);

--
-- Contraintes pour la table `professeur`
--
ALTER TABLE `professeur`
  ADD CONSTRAINT `PROFESSEUR_MATIERE_FK` FOREIGN KEY (`ID_Matiere`) REFERENCES `matiere` (`ID_Matiere`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
