/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classDAO;

import static classDAO.DAOUtilitaire.fermetureSilencieuse;
import static classDAO.DAOUtilitaire.initialisationRequetePreparee;
import classMetier.Eleve;
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
public class EleveDAO extends DAO<Eleve> {
  
  /**
   *
   * @param conn
   */
  public EleveDAO(Connection conn) {
    super(conn);
  }
  
  @Override
  public void create(Eleve obj) throws DAOException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
    
  @Override
  public void create(Eleve obj, int identifiant) throws DAOException {	
	PreparedStatement preparedStatement = null;
    ResultSet valeursAutoGenerees = null;
	//  Creation de la requete
	String SQL_INSERT = "INSERT INTO eleve (Nom_Eleve, Prenom_Eleve, ID_Classe) VALUES (?, ?, ?)";
    try {
        //	Recuperation de la connexion depuis le singleton de connexion
        preparedStatement = initialisationRequetePreparee( this.connect, SQL_INSERT,
				true, obj.getNom(), obj.getPrenom(), identifiant);
        int statut = preparedStatement.executeUpdate();
        //	Analyse du statut retourné par la requête d'insertion 
        if ( statut == 0 ) {
            throw new DAOException( "Échec de la création de l'élève, aucune ligne ajoutée dans la table." );
        }
        //	Récupération de l'id auto-généré par la requête d'insertion
        valeursAutoGenerees = preparedStatement.getGeneratedKeys();
        if ( valeursAutoGenerees.next() ) {
            //	Puis initialisation de la propriété id du bean Utilisateur avec sa valeur
			obj.setId(valeursAutoGenerees.getInt(1));
        } else {
            throw new DAOException( "Échec de la création de l'élève en base, aucun ID auto-généré retourné." );
        }
    } catch ( SQLException e ) {
        throw new DAOException( e );
    } finally {
	  fermetureSilencieuse( valeursAutoGenerees );
	  fermetureSilencieuse( preparedStatement );
    } 
  }

  @Override
  public boolean delete(Eleve obj) {
    return false;
  }
   
  @Override
  public boolean update(Eleve obj) {
    return false;
  }
   
  @Override
  public Eleve find(int id) {
    Eleve eleve = new Eleve();      
      
    try {
      ResultSet result = this.connect.createStatement(
      ResultSet.TYPE_SCROLL_INSENSITIVE,
      ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM eleve WHERE ID_Eleve = " + id);
      if(result.first())
        eleve = new Eleve(
			  id,
			  result.getString("Nom_Eleve"),
			  result.getString("Prenom_Eleve")
        );         
    } catch (SQLException e) {
	  e.printStackTrace();
    }
    return eleve;
  }
  
  @Override
  public List<Eleve> lister() throws DAOException {
	//  Creation de la liste des eleves
	List<Eleve> lesEleves = new ArrayList<Eleve>();
	//  Creation de la requete
	String SQLquery = "SELECT * FROM eleve";
	
	try {
	  ResultSet result = this.connect.createStatement(
	  ResultSet.TYPE_SCROLL_INSENSITIVE, 
	  ResultSet.CONCUR_READ_ONLY
      ).executeQuery(SQLquery);
	  
	  while(result.next()) {
		Eleve unEleve = new Eleve(
						result.getInt("ID_Eleve"),
						result.getString("Nom_Eleve"),
						result.getString("Prenom_Eleve")
		);
		lesEleves.add(unEleve);		
	  }
	}catch (SQLException e) {
	  e.printStackTrace();
    }
	return lesEleves;
  }

  @Override
  public Eleve exister(Object... objets) throws DAOException {
	Eleve eleve = null;
	PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
	//  Creation de la requete
	String SQL_SELECT_PAR_NOM = "SELECT * FROM eleve WHERE Nom_Eleve = ? "
			+ "AND Prenom_Eleve = ?";
	//	Recuperation de la connexion depuis le singleton de connexion
	try{
	  preparedStatement = initialisationRequetePreparee(this.connect, SQL_SELECT_PAR_NOM,
			  false, objets);
	  resultSet = preparedStatement.executeQuery();
	  if ( resultSet.next() ) {
            eleve = new Eleve(
					resultSet.getInt("ID_Eleve"),
					resultSet.getString("Nom_Eleve"),
					resultSet.getString("Prenom_Eleve"));
        }
	}catch ( SQLException ex ) {
	  throw new DAOException( ex );
	}finally {
	  fermetureSilencieuse( resultSet );
	  fermetureSilencieuse( preparedStatement );	  
	}
	return eleve;
  }  
}
