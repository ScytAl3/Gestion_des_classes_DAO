/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classDAO;

import static classDAO.DAOUtilitaire.fermetureSilencieuse;
import static classDAO.DAOUtilitaire.initialisationRequetePreparee;
import classMetier.Matiere;
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
public class MatiereDAO extends DAO<Matiere>{
  
  /**
   *
   * @param conn
   */
  public MatiereDAO(Connection conn) {
    super(conn);
  }

  @Override
  public void create(Matiere obj) throws DAOException {
    PreparedStatement preparedStatement = null;
    ResultSet valeursAutoGenerees = null;
	//  Creation de la requete
	String SQL_INSERT = "INSERT INTO matiere (nom_Matiere) VALUES (?)";
    try {
        //	Recuperation de la connexion depuis le singleton de connexion
        preparedStatement = initialisationRequetePreparee( this.connect, SQL_INSERT,
				true, obj.getNom() );
        int statut = preparedStatement.executeUpdate();
        //	Analyse du statut retourné par la requête d'insertion 
        if ( statut == 0 ) {
            throw new DAOException( "Échec de la création de la matière, aucune ligne ajoutée dans la table." );
        }
        //	Récupération de l'id auto-généré par la requête d'insertion
        valeursAutoGenerees = preparedStatement.getGeneratedKeys();
        if ( valeursAutoGenerees.next() ) {
            //	Puis initialisation de la propriété id du bean Utilisateur avec sa valeur
			obj.setId(valeursAutoGenerees.getInt(1));
        } else {
            throw new DAOException( "Échec de la création de la matière en base, aucun ID auto-généré retourné." );
        }
    } catch ( SQLException e ) {
        throw new DAOException( e );
    } finally {
	  fermetureSilencieuse( valeursAutoGenerees );
	  fermetureSilencieuse( preparedStatement );
    }
  }
  
  @Override
  public void create(Matiere obj, int identifiant) throws DAOException {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }
  
  @Override
  public boolean delete(Matiere obj) {
    return false;
  }

  @Override
  public boolean update(Matiere obj) {
    return false;
  }

  @Override
  public Matiere find(int id) throws DAOException {
	ResultSet resultSet = null;
    Matiere matiere = new Matiere();  
	//	Recuperation de la connexion depuis le singleton de connexion
    try {
      ResultSet result = this.connect.createStatement(
	  ResultSet.TYPE_SCROLL_INSENSITIVE, 
	  ResultSet.CONCUR_READ_ONLY
      ).executeQuery("SELECT * FROM matiere WHERE ID_Matiere = " + id);
        if(result.first())
          matiere = new Matiere(				  
				  id,
				  result.getString("nom_Matiere")
		  );         
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
	  fermetureSilencieuse( resultSet );
	}
    return matiere;
  }

  @Override
  public List<Matiere> lister() throws DAOException {
	ResultSet resultSet = null;
	//  Creation de la liste
	List<Matiere> lesMatiere = new ArrayList<Matiere>();
	//  Creation de la requete
	String SQLquery = "SELECT * FROM matiere";
	//	Recuperation de la connexion depuis le singleton de connexion
	try {
	  ResultSet result = this.connect.createStatement(
	  ResultSet.TYPE_SCROLL_INSENSITIVE, 
	  ResultSet.CONCUR_READ_ONLY
      ).executeQuery(SQLquery);
	  
	  while(result.next()) {
		Matiere uneMatiere = new Matiere(
						result.getInt("ID_Matiere"),
						result.getString("Nom_Matiere")
		);
		
		lesMatiere.add(uneMatiere);
	  }
	}catch (SQLException e) {
	  e.printStackTrace();
    }finally {
	  fermetureSilencieuse( resultSet );  
	}
	return lesMatiere;
  }

  /**
   * 
   * @param objets
   * @return
   * @throws DAOException 
   */
  public Matiere exister(Object... objets) throws DAOException {
	Matiere matiere = null;
	PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
	//  Creation de la requete
	String SQL_SELECT_PAR_NOM = "SELECT * FROM matiere WHERE nom_Matiere = ?";
	//	Recuperation de la connexion depuis le singleton de connexion
	try{
	  preparedStatement = initialisationRequetePreparee(this.connect, SQL_SELECT_PAR_NOM,
			  false, objets);
	  resultSet = preparedStatement.executeQuery();
	  if ( resultSet.next() ) {
            matiere = new Matiere(
					resultSet.getInt("ID_Matiere"),
					resultSet.getString("Nom_Matiere"));
        }
	}catch ( SQLException ex ) {
	  throw new DAOException( ex );
	}finally {
	  fermetureSilencieuse( resultSet );
	  fermetureSilencieuse( preparedStatement );	  
	}
	return matiere;
  }  
}
