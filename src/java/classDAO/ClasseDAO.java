/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classDAO;

import static classDAO.DAOUtilitaire.fermetureSilencieuse;
import static classDAO.DAOUtilitaire.initialisationRequetePreparee;
import classMetier.Classe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Stagiaire
 */
public class ClasseDAO extends DAO<Classe> {
  
  /**
   *
   * @param conn
   */
  public ClasseDAO(Connection conn) {
    super(conn);
  }

  @Override
  public void create(Classe obj) {
    
  }    
  
  @Override
  public void create(Classe obj, int identifiant) throws DAOException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
  
  @Override
  public boolean delete(Classe obj) {
    return false;
  }
   
  @Override
  public boolean update(Classe obj) {
    return false;
  }

  @Override
  public Classe find(int id) {
    Classe classe = new Classe();            
    try {
      ResultSet result = this.connect.createStatement(
	  ResultSet.TYPE_SCROLL_INSENSITIVE, 
	  ResultSet.CONCUR_READ_ONLY
      ).executeQuery("SELECT * FROM classe WHERE ID_Classe = " + id); 

      if(result.first()){
        classe = new Classe(
				id,
				result.getString("Nom_Classe")
		);

        result = this.connect.createStatement().executeQuery(
          "SELECT p.ID_Professeur, p.Nom_Professeur, p.Prenom_Professeur from professeur p " +
          "INNER JOIN instruire i ON p.ID_Professeur = i.ID_Professeur " +
          "INNER JOIN classe c ON c.ID_Classe = i.ID_Classe AND c.ID_Classe = " + id
        );

        ProfesseurDAO profDao = new ProfesseurDAO(this.connect);

        while(result.next())             
          classe.addProfesseur(profDao.find(result.getInt("ID_Professeur")));

        EleveDAO eleveDao = new EleveDAO(this.connect);
        result = this.connect.createStatement().executeQuery(
          "SELECT ID_Eleve, Nom_Eleve, Prenom_Eleve FROM eleve e " +
          "INNER JOIN classe c ON e.ID_Classe = c.ID_Classe AND c.ID_Classe = " + id
        );

        while(result.next())
          classe.addEleve(eleveDao.find(result.getInt("ID_Eleve")));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return classe;
  }

  @Override
  public List<Classe> lister() {
	//  Creation de la liste
	List<Classe> lesClasses = new ArrayList<Classe>();
	//  Creation de la requete
	String SQLquery = "SELECT * FROM classe";
	
	try {
	  ResultSet result = this.connect.createStatement(
	  ResultSet.TYPE_SCROLL_INSENSITIVE, 
	  ResultSet.CONCUR_READ_ONLY
      ).executeQuery(SQLquery);
	  
	  while(result.next()) {
		Classe uneClasse = new Classe(
						result.getInt("ID_Classe"),
						result.getString("Nom_Classe")
		);
		
		lesClasses.add(uneClasse);
	  }
	}catch (SQLException e) {
	  e.printStackTrace();
    }
	return lesClasses;
  }

  @Override
  public Classe exister(Object... objets) throws DAOException {
	Classe classe = null;
	PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
	//  Creation de la requete
	
	String SQL_SELECT_PAR_NOM = "SELECT i.ID_Classe, i.ID_Professeur, c.Nom_Classe FROM instruire i "
			+ "INNER JOIN classe c ON i.ID_Classe = c.ID_Classe WHERE c.Nom_Classe = ? "
			+ "AND i.ID_Professeur = ? ";
	//	Recuperation de la connexion depuis le singleton de connexion
	try{
	  preparedStatement = initialisationRequetePreparee(this.connect, SQL_SELECT_PAR_NOM,
			  false, objets);
	  resultSet = preparedStatement.executeQuery();
	  if ( resultSet.next() ) {
            classe = new Classe(
					resultSet.getInt("i.ID_Classe"),
					resultSet.getString("c.Nom_Classe"));
        }
	}catch ( SQLException ex ) {
	  throw new DAOException( ex );
	}finally {
	  fermetureSilencieuse( resultSet );
	  fermetureSilencieuse( preparedStatement );	  
	}
	return classe;
  }
}
