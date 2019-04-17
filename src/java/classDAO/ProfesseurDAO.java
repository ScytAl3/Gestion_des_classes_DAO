/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classDAO;

import static classDAO.DAOUtilitaire.fermetureSilencieuse;
import static classDAO.DAOUtilitaire.initialisationRequetePreparee;
import classMetier.Professeur;
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
public class ProfesseurDAO extends DAO<Professeur> {
  
  /**
   *
   * @param conn
   */
  public ProfesseurDAO(Connection conn) {
    super(conn);
  }

  @Override
  public void create(Professeur obj) {
    
  }

  @Override
  public void create(Professeur obj, int identifiant) throws DAOException {
	PreparedStatement preparedStatement = null;
    ResultSet valeursAutoGenerees = null;
	//  Creation de la requete
	String SQL_INSERT = "INSERT INTO professeur (Nom_Professeur, Prenom_Professeur,"
			+ " ID_Matiere) VALUES (?, ?, ?)";
    try {
        //	Recuperation de la connexion depuis le singleton de connexion
        preparedStatement = initialisationRequetePreparee( this.connect, SQL_INSERT,
				true, obj.getNom(), obj.getPrenom(), identifiant);
        int statut = preparedStatement.executeUpdate();
        //	Analyse du statut retourné par la requête d'insertion 
        if ( statut == 0 ) {
            throw new DAOException( "Échec de la création du professeur, aucune ligne ajoutée dans la table." );
        }
        //	Récupération de l'id auto-généré par la requête d'insertion
        valeursAutoGenerees = preparedStatement.getGeneratedKeys();
        if ( valeursAutoGenerees.next() ) {
            //	Puis initialisation de la propriété id du bean Utilisateur avec sa valeur
			obj.setId(valeursAutoGenerees.getInt(1));
        } else {
            throw new DAOException( "Échec de la création du professeur en base, aucun ID auto-généré retourné." );
        }
    } catch ( SQLException e ) {
        throw new DAOException( e );
    } finally {
	  fermetureSilencieuse( valeursAutoGenerees );
	  fermetureSilencieuse( preparedStatement );
    }
  }
  
  @Override
  public boolean delete(Professeur obj) {
    return false;
  }

  @Override
  public boolean update(Professeur obj) {
    return false;
  }
   
  @Override
  public Professeur find(int id) {
    Professeur professeur = new Professeur();   
	
    try {
      ResultSet result = this.connect.createStatement(
	  ResultSet.TYPE_SCROLL_INSENSITIVE, 
	  ResultSet.CONCUR_READ_ONLY
      ).executeQuery(
        "SELECT * FROM professeur WHERE ID_Professeur = "+ id);
      if(result.first()){
        professeur = new Professeur(
				id,
				result.getString("Nom_Professeur"),
				result.getString("Prenom_Professeur")
		);
	  }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return professeur;
  }

  @Override
  public List<Professeur> lister() {
	//  Creation de la liste
	List<Professeur> lesProfesseur = new ArrayList<Professeur>();
	//  Creation de la requete
	String SQLquery = "SELECT * FROM professeur";
	
	try {
	  ResultSet result = this.connect.createStatement(
	  ResultSet.TYPE_SCROLL_INSENSITIVE, 
	  ResultSet.CONCUR_READ_ONLY
      ).executeQuery(SQLquery);
	  
	  while(result.next()) {
		Professeur unProfesseur = new Professeur(
						result.getInt("ID_Professeur"),
						result.getString("Nom_Professeur"),
						result.getString("Prenom_Professeur")
		);
		
		lesProfesseur.add(unProfesseur);
	  }
	}catch (SQLException e) {
	  e.printStackTrace();
    }
	return lesProfesseur;
  }

  @Override
  public Professeur exister(Object... objets) throws DAOException {
	Professeur professeur = null;
	PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
	//  Creation de la requete
	String SQL_SELECT_PAR_NOM = "SELECT * FROM professeur WHERE Nom_Professeur = ? "
			+ "AND Prenom_Professeur = ? AND ID_Matiere = ?";
	//	Recuperation de la connexion depuis le singleton de connexion
	try{
	  preparedStatement = initialisationRequetePreparee(this.connect, SQL_SELECT_PAR_NOM,
			  false, objets);
	  resultSet = preparedStatement.executeQuery();
	  if ( resultSet.next() ) {
            professeur = new Professeur(
					resultSet.getInt("ID_Professeur"),
					resultSet.getString("Nom_Professeur"),
					resultSet.getString("Prenom_Professeur"));
        }
	}catch ( SQLException ex ) {
	  throw new DAOException( ex );
	}finally {
	  fermetureSilencieuse( resultSet );
	  fermetureSilencieuse( preparedStatement );	  
	}
	return professeur;
  }  
}
