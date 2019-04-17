/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classMetier;

import java.util.HashSet;
import java.util.Set;

/**
 * Classe representant les professeurs
 * @author Stagiaire
 */
public class Professeur {
  //ID
  private int id = 0;
  //Nom du professeur
  private String nom = "";
  //Prénom du professeur
  private String prenom = "";
  //Liste des matières dispensées
  private Set<Matiere> listMatiere = new HashSet<Matiere>();

  /**
   * Constructeur du professeur
   * @param id
   * @param nom
   * @param prenom
   */
  public Professeur(int id, String nom, String prenom) {
    this.id = id;
    this.nom = nom;
    this.prenom = prenom;
  }

  /**
   * Constructeur par defaut
   */
  public Professeur(){}

  /**
   * Retourne l identifiant du professeur
   * @return
   */
  public int getId() {
    return id;
  }

  /**
   * definit l identifiant du professeur
   * @param id
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Retourne le nom du professeur
   * @return
   */
  public String getNom() {
    return nom;
  }

  /**
   * Definit le nom du professeur
   * @param nom
   */
  public void setNom(String nom) {
    this.nom = nom;
  }

  /**
   * Retourne le prenom du professeur
   * @return
   */
  public String getPrenom() {
    return prenom;
  }

  /**
   * Definit le prenom du professeur
   * @param prenom
   */
  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }

  /**
   * Retourne la liste des matieres
   * @return
   */
  public Set<Matiere> getListMatiere() {
    return listMatiere;
  }

  /** 
   * definit la liste des matieres
   * @param listMatiere
   */
  public void setListMatiere(Set<Matiere> listMatiere) {
    this.listMatiere = listMatiere;
  }
  

  /**
   * Ajoute une matiere a un professeur
   * @param matiere
   */
  public void addMatiere(Matiere matiere){
    this.listMatiere.add(matiere);
  }

  /**
   * Retire une matiere a un professeur
   * @param matiere
   */
  public void removeMatiere(Matiere matiere){
    this.listMatiere.remove(matiere);
  }
}
