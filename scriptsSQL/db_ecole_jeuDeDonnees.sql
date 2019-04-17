-- --------------------------------------------------------

--
-- Jeu de donnees pour la table matiere
--

INSERT INTO matiere (ID_Matiere, Nom_Matiere) VALUES (1, 'Mathématiques');
INSERT INTO matiere (ID_Matiere, Nom_Matiere) VALUES (2, 'Français');
INSERT INTO matiere (ID_Matiere, Nom_Matiere) VALUES (3, 'Anglais');
INSERT INTO matiere (ID_Matiere, Nom_Matiere) VALUES (4, 'Physique');
INSERT INTO matiere (ID_Matiere, Nom_Matiere) VALUES (5, 'Biologie');
INSERT INTO matiere (ID_Matiere, Nom_Matiere) VALUES (6, 'Sport');

-- --------------------------------------------------------

--
-- Jeu de donnees pour la table professeur
--

INSERT INTO professeur (ID_Professeur, Nom_Professeur, Prenom_Professeur, ID_Matiere) VALUES (1, 'MAMOU', 'Daniel', 1);
INSERT INTO professeur (ID_Professeur, Nom_Professeur, Prenom_Professeur, ID_Matiere) VALUES (2, 'SACRE', 'Sophie', 2);
INSERT INTO professeur (ID_Professeur, Nom_Professeur, Prenom_Professeur, ID_Matiere) VALUES (3, 'JADEN', 'Boudy', 3);
INSERT INTO professeur (ID_Professeur, Nom_Professeur, Prenom_Professeur, ID_Matiere) VALUES (4, 'BADEN', 'Baden', 4);
INSERT INTO professeur (ID_Professeur, Nom_Professeur, Prenom_Professeur, ID_Matiere) VALUES (5, 'MIOU', 'Miou', 5);
INSERT INTO professeur (ID_Professeur, Nom_Professeur, Prenom_Professeur, ID_Matiere) VALUES (6, 'BORA', 'Kernel', 6);
INSERT INTO professeur (ID_Professeur, Nom_Professeur, Prenom_Professeur, ID_Matiere) VALUES (7, 'CAISSE', 'Jean', 1);
INSERT INTO professeur (ID_Professeur, Nom_Professeur, Prenom_Professeur, ID_Matiere) VALUES (8, 'MOISSAT', 'Marc', 2);

-- --------------------------------------------------------

--
-- Jeu de donnees pour la table classe
--

INSERT INTO classe (ID_Classe, Nom_Classe) VALUES (1, '6°A');
INSERT INTO classe (ID_Classe, Nom_Classe) VALUES (2, '6°B');
INSERT INTO classe (ID_Classe, Nom_Classe) VALUES (3, '6°C');
INSERT INTO classe (ID_Classe, Nom_Classe) VALUES (4, '5°A');
INSERT INTO classe (ID_Classe, Nom_Classe) VALUES (5, '5°B');
INSERT INTO classe (ID_Classe, Nom_Classe) VALUES (6, '5°C');
INSERT INTO classe (ID_Classe, Nom_Classe) VALUES (7, '4°A');
INSERT INTO classe (ID_Classe, Nom_Classe) VALUES (8, '4°B');
INSERT INTO classe (ID_Classe, Nom_Classe) VALUES (9, '4°C');
INSERT INTO classe (ID_Classe, Nom_Classe) VALUES (10, '3°A');
INSERT INTO classe (ID_Classe, Nom_Classe) VALUES (11, '3°B');
INSERT INTO classe (ID_Classe, Nom_Classe) VALUES (12, '3°C');


-- --------------------------------------------------------

--
-- Jeu de donnees pour la table instruire
--

INSERT INTO instruire (ID_Classe, ID_Professeur) VALUES (1, 8);
INSERT INTO instruire (ID_Classe, ID_Professeur) VALUES (2, 2);
INSERT INTO instruire (ID_Classe, ID_Professeur) VALUES (3, 7);
INSERT INTO instruire (ID_Classe, ID_Professeur) VALUES (4, 1);
INSERT INTO instruire (ID_Classe, ID_Professeur) VALUES (5, 1);
INSERT INTO instruire (ID_Classe, ID_Professeur) VALUES (6, 3);
INSERT INTO instruire (ID_Classe, ID_Professeur) VALUES (7, 6);
INSERT INTO instruire (ID_Classe, ID_Professeur) VALUES (8, 4);
INSERT INTO instruire (ID_Classe, ID_Professeur) VALUES (9, 5);
INSERT INTO instruire (ID_Classe, ID_Professeur) VALUES (10, 4);
INSERT INTO instruire (ID_Classe, ID_Professeur) VALUES (11, 7);
INSERT INTO instruire (ID_Classe, ID_Professeur) VALUES (12, 4);

-- --------------------------------------------------------

--
-- Jeu de donnees pour la table eleve
--

INSERT INTO eleve (ID_Eleve, Nom_Eleve, Prenom_Eleve, ID_Classe) VALUES (1, 'HERBY', 'Cyrille', 1);
INSERT INTO eleve (ID_Eleve, Nom_Eleve, Prenom_Eleve, ID_Classe) VALUES (2, 'COURTEL ', 'Angelo', 1);
INSERT INTO eleve (ID_Eleve, Nom_Eleve, Prenom_Eleve, ID_Classe) VALUES (3, 'PITON ', 'Thomas', 1);
INSERT INTO eleve (ID_Eleve, Nom_Eleve, Prenom_Eleve, ID_Classe) VALUES (4, 'COQUILLE', 'Olivier', 2);
INSERT INTO eleve (ID_Eleve, Nom_Eleve, Prenom_Eleve, ID_Classe) VALUES (5, 'SALMON', 'Dylan', 2);
INSERT INTO eleve (ID_Eleve, Nom_Eleve, Prenom_Eleve, ID_Classe) VALUES (6, 'MERLET', 'Benoit', 3);
INSERT INTO eleve (ID_Eleve, Nom_Eleve, Prenom_Eleve, ID_Classe) VALUES (7, 'LE GALL', 'Yann', 3);
INSERT INTO eleve (ID_Eleve, Nom_Eleve, Prenom_Eleve, ID_Classe) VALUES (8, 'LE GALL', 'Morgane', 4);
INSERT INTO eleve (ID_Eleve, Nom_Eleve, Prenom_Eleve, ID_Classe) VALUES (9, 'LIGERON', 'Yanninck', 4);
INSERT INTO eleve (ID_Eleve, Nom_Eleve, Prenom_Eleve, ID_Classe) VALUES (10, 'LIGERON ', 'Didier', 4);
INSERT INTO eleve (ID_Eleve, Nom_Eleve, Prenom_Eleve, ID_Classe) VALUES (11, 'MARLEY', 'Bob', 5);
INSERT INTO eleve (ID_Eleve, Nom_Eleve, Prenom_Eleve, ID_Classe) VALUES (12, 'MAHE', 'Marie', 5);
INSERT INTO eleve (ID_Eleve, Nom_Eleve, Prenom_Eleve, ID_Classe) VALUES (13, 'PICARD', 'Séverine', 6);
INSERT INTO eleve (ID_Eleve, Nom_Eleve, Prenom_Eleve, ID_Classe) VALUES (14, 'PICARD', 'Manuela', 6);
INSERT INTO eleve (ID_Eleve, Nom_Eleve, Prenom_Eleve, ID_Classe) VALUES (15, 'BOTTE', 'Jérôme', 6);
INSERT INTO eleve (ID_Eleve, Nom_Eleve, Prenom_Eleve, ID_Classe) VALUES (16, 'CARDON', 'Mathieu', 7);
INSERT INTO eleve (ID_Eleve, Nom_Eleve, Prenom_Eleve, ID_Classe) VALUES (17, 'MARDET', 'Cécile', 7);
INSERT INTO eleve (ID_Eleve, Nom_Eleve, Prenom_Eleve, ID_Classe) VALUES (18, 'BUBUTE', 'Armel', 7);
INSERT INTO eleve (ID_Eleve, Nom_Eleve, Prenom_Eleve, ID_Classe) VALUES (19, 'MANIQUE', 'Pascal', 8);
INSERT INTO eleve (ID_Eleve, Nom_Eleve, Prenom_Eleve, ID_Classe) VALUES (20, 'ALTE', 'Paul', 8);
INSERT INTO eleve (ID_Eleve, Nom_Eleve, Prenom_Eleve, ID_Classe) VALUES (21, 'CORLOT', 'Amandine', 8);
INSERT INTO eleve (ID_Eleve, Nom_Eleve, Prenom_Eleve, ID_Classe) VALUES (22, 'KIBOU', 'Bahija', 9);
INSERT INTO eleve (ID_Eleve, Nom_Eleve, Prenom_Eleve, ID_Classe) VALUES (23, 'MADI', 'Feti', 9);
INSERT INTO eleve (ID_Eleve, Nom_Eleve, Prenom_Eleve, ID_Classe) VALUES (24, 'KERGOULET', 'Erwan', 9);
INSERT INTO eleve (ID_Eleve, Nom_Eleve, Prenom_Eleve, ID_Classe) VALUES (25, 'FERNAT', 'Fernand', 10);
INSERT INTO eleve (ID_Eleve, Nom_Eleve, Prenom_Eleve, ID_Classe) VALUES (26, 'JOUBERT', 'Aline', 10);
INSERT INTO eleve (ID_Eleve, Nom_Eleve, Prenom_Eleve, ID_Classe) VALUES (27, 'TARTUFE', 'Thérèse', 10);
INSERT INTO eleve (ID_Eleve, Nom_Eleve, Prenom_Eleve, ID_Classe) VALUES (28, 'MONIN', 'Gérald', 11);
INSERT INTO eleve (ID_Eleve, Nom_Eleve, Prenom_Eleve, ID_Classe) VALUES (29, 'DROUIN', 'Albert', 11);
INSERT INTO eleve (ID_Eleve, Nom_Eleve, Prenom_Eleve, ID_Classe) VALUES (30, 'NAEMI', 'Toufic', 11);
INSERT INTO eleve (ID_Eleve, Nom_Eleve, Prenom_Eleve, ID_Classe) VALUES (31, 'NILBE', 'Nadia', 12);
INSERT INTO eleve (ID_Eleve, Nom_Eleve, Prenom_Eleve, ID_Classe) VALUES (32, 'VIVOT', 'Valérie', 12);

