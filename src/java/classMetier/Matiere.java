/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classMetier;

/**
 * Classe representant les matieres enseignees
 * @author Stagiaire
 */
public class Matiere {
  //ID
  private int id = 0;
  //Nom du professeur
  private String nom = "";

  /**
   * Constructeur de la matiere
   * @param id
   * @param nom
   */
  public Matiere(int id, String nom) {
    this.id = id;
    this.nom = nom;
  }

  /**
   * Constructeur par defaut
   */
  public Matiere(){}

  /**
   * Retourne l identifiant de la matiere
   * @return
   */
  public int getId() {
    return id;
  }

  /**
   * Definit l identidiant de la matiere
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
   * definit le nom
   * @param nom
   */
  public void setNom(String nom) {
    this.nom = nom;
  }
}
