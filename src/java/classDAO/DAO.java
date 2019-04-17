/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classDAO;

import java.sql.Connection;
import java.util.List;

/**
 *
 * @author Stagiaire
 * @param <T>
 */
public abstract class DAO<T> {

  /**
   *
   */
  protected Connection connect = null;
   
  /**
   *
   * @param conn
   */
  public DAO(Connection conn){
    this.connect = conn;
  }
   
  /**
  * Méthode de création d un objet
  * @param obj 
  */
  public abstract void create(T obj) throws DAOException;

  /**
   * Methode de creation d un objet avec une cle etrangere
   * @param obj
   * @param identifiant
   * @throws DAOException
   */
  public abstract void create(T obj, int identifiant) throws DAOException;
  
  /**
  * Méthode pour effacer
  * @param obj
  * @return boolean 
  */
  public abstract boolean delete(T obj) throws DAOException;

  /**
  * Méthode de mise à jour
  * @param obj
  * @return boolean
  */
  public abstract boolean update(T obj) throws DAOException;

  /**
  * Méthode de recherche des informations
  * @param id
  * @return T
  */
  public abstract T find(int id) throws DAOException;
  
  /**
   * Methode pour recuperer une liste d objet
   * @return
   * @throws DAOException 
   */
  public abstract List<T> lister() throws DAOException;
    
 /**
  * Methode pour verifier l existence d un objet
  * @param objets
  * @return
  * @throws DAOException 
  */
  public abstract T exister(Object... objets) throws DAOException;  
}
