#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: MATIERE
#------------------------------------------------------------

CREATE TABLE MATIERE(
        ID_Matiere  Int NOT NULL AUTO_INCREMENT ,
        Nom_Matiere Varchar (50) NOT NULL
	,CONSTRAINT MATIERE_PK PRIMARY KEY (ID_Matiere)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: CLASSE
#------------------------------------------------------------

CREATE TABLE CLASSE(
        ID_Classe Int NOT NULL AUTO_INCREMENT ,
        Nom_Classe Varchar (50) NOT NULL
	,CONSTRAINT CLASSE_PK PRIMARY KEY (ID_Classe)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: ELEVE
#------------------------------------------------------------

CREATE TABLE ELEVE(
        ID_Eleve     Int NOT NULL AUTO_INCREMENT ,
        Nom_Eleve    Varchar (50) NOT NULL ,
        Prenom_Eleve Varchar (50) NOT NULL ,
        ID_Classe    Int NOT NULL
	,CONSTRAINT ELEVE_PK PRIMARY KEY (ID_Eleve)

	,CONSTRAINT ELEVE_CLASSE_FK FOREIGN KEY (ID_Classe) REFERENCES CLASSE(ID_Classe)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: PROFESSEUR
#------------------------------------------------------------

CREATE TABLE PROFESSEUR(
        ID_Professeur     Int NOT NULL AUTO_INCREMENT ,
        Nom_Professeur    Varchar (50) NOT NULL ,
        Prenom_Professeur Varchar (50) NOT NULL ,
        ID_Matiere        Int NOT NULL
	,CONSTRAINT PROFESSEUR_PK PRIMARY KEY (ID_Professeur)

	,CONSTRAINT PROFESSEUR_MATIERE_FK FOREIGN KEY (ID_Matiere) REFERENCES MATIERE(ID_Matiere)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Instruire
#------------------------------------------------------------

CREATE TABLE Instruire(
        ID_Classe     Int NOT NULL ,
        ID_Professeur Int NOT NULL
	,CONSTRAINT Instruire_PK PRIMARY KEY (ID_Classe,ID_Professeur)

	,CONSTRAINT Instruire_CLASSE_FK FOREIGN KEY (ID_Classe) REFERENCES CLASSE(ID_Classe)
	,CONSTRAINT Instruire_PROFESSEUR0_FK FOREIGN KEY (ID_Professeur) REFERENCES PROFESSEUR(ID_Professeur)
)ENGINE=InnoDB;

