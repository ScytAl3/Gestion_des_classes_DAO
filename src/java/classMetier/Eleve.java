/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classMetier;

/**
 * Classe representant les eleves
 * @author Stagiaire
 */
public class Eleve {
  //ID
  private int id = 0;
  //Nom de l'élève
  private String nom = "";
  //Prénom de l'élève
  private String prenom = "";
   
  /**
   * Constructeur de l eleve
   * @param id
   * @param nom
   * @param prenom
   */
  public Eleve(int id, String nom, String prenom) {
    this.id = id;
    this.nom = nom;
    this.prenom = prenom;
  }

  /**
   * Constructeur de la classe par defaut
   */
  public Eleve(){};
     
  /**
   * Retourne l identifiant de l eleve
   * @return
   */
  public int getId() {
    return id;
  }

  /**
   * Definit l identifiant de l eleve
   * @param id
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Retourne le nom de l eleve
   * @return
   */
  public String getNom() {
    return nom;
  }

  /**
   * Definit le nom de l eleve
   * @param nom
   */
  public void setNom(String nom) {
    this.nom = nom;
  }

  /** 
   * Retourne le prenom de l eleve
   * @return
   */
  public String getPrenom() {
    return prenom;
  }

  /**
   * Definit le prenom de l eleve
   * @param prenom
   */
  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }
}
