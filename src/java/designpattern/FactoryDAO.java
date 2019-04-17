/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designpattern;

import classDAO.*;
import java.sql.Connection;

/**
 *
 * @author Stagiaire
 */
public class FactoryDAO {

  /**
   *
   */
  protected static final Connection connect = SingletonConnexionDAO.getInstance();
  
  /**
   * Retourne un objet Classe interagissant avec la BDD
   * @return
   */
  public static DAO getClasseDAO(){
	return new ClasseDAO(connect);
  }
  
  /**
  * Retourne un objet Professeur interagissant avec la BDD
  * @return DAO
  */
  public static DAO getProfesseurDAO(){
    return new ProfesseurDAO(connect);
  }

  /**
  * Retourne un objet Eleve interagissant avec la BDD
  * @return DAO
  */
  public static DAO getEleveDAO(){
    return new EleveDAO(connect);
  }

  /**
  * Retourne un objet Matiere interagissant avec la BDD
  * @return DAO
  */
  public static DAO getMatiereDAO(){
    return new MatiereDAO(connect);
  } 
}
