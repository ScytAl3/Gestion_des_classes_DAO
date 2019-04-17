/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classMetier;

import java.util.HashSet;
import java.util.Set;

/**
 * Classe representant une classe d eleve
 * @author Stagiaire
 */
public class Classe {
  //ID
  private int id = 0;
  //Nom du professeur
  private String nom = "";
  //Liste des professeurs
  private Set<Professeur> listProfesseur = new HashSet<Professeur>();
  //Liste des élèves
  private Set<Eleve> listEleve = new HashSet<Eleve>();

  /**
   * Constructeur de la classe
   * @param id
   * @param nom
   */
  public Classe(int id, String nom) {
    this.id = id;
    this.nom = nom;
  }

  /**
   * Constructeur par defaut
   */
  public Classe(){}

  /**
   * Retourne l identifiant de la classe
   * @return
   */
  public int getId() {
    return id;
  }

  /**
   * Definit l identidiant de la classe
   * @param id
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * retourne le nom
   * @return
   */
  public String getNom() {
    return nom;
  }

  /**
   * Definit le nom
   * @param nom
   */
  public void setNom(String nom) {
    this.nom = nom;
  }

  /**
   * Retourne la liste des professeurs
   * @return
   */
  public Set<Professeur> getListProfesseur() {
    return listProfesseur;
  }

  /**
   * Definit la liste des professeurs
   * @param listProfesseur
   */
  public void setListProfesseur(Set<Professeur> listProfesseur) {
    this.listProfesseur = listProfesseur;
  }

  /**
   * Ajoute un professeur
   * @param prof
   */
  public void addProfesseur(Professeur prof) {
    if(!listProfesseur.contains(prof))
      listProfesseur.add(prof);
  }

  /**
   * Supprime un professeur
   * @param prof
   */
  public void removeProfesseur(Professeur prof ) {
    this.listProfesseur.remove(prof);
  }

  /**
   * Retourne la liste d eleve
   * @return
   */
  public Set<Eleve> getListEleve() {
    return listEleve;
  }

  /**
   * definit la liste d eleves
   * @param listEleve
   */
  public void setListEleve(Set<Eleve> listEleve) {
    this.listEleve = listEleve;
  }
  

  /**
   * Ajoute un eleve a la classe
   * @param eleve
   */
  public void addEleve(Eleve eleve){
    if(!this.listEleve.contains(eleve))
      this.listEleve.add(eleve);
  }

  /**
   * Retire un eleve a la classe
   * @param eleve
   */
  public void removeEleve(Eleve eleve){
    this.listEleve.remove(eleve);
  }

  /**
   * Methode pour verifier si la classe existe deja
   * @param cls
   * @return
   */
  public boolean equals(Classe cls){
    return this.getId() == cls.getId();
  }   
}
