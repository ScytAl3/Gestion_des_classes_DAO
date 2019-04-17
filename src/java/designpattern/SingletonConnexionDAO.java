/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designpattern;

import classDAO.DAOConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stagiaire
 */
public class SingletonConnexionDAO {
  private static final String FICHIER_PROPERTIES       = "/classDAO/DAO.properties";
  private static final String PROPERTY_URL             = "url";
  private static final String PROPERTY_DRIVER          = "driver";
  private static final String PROPERTY_NOM_UTILISATEUR = "user";
  private static final String PROPERTY_MOT_DE_PASSE    = "pwd";
  
//  Objet de connection
  private static Connection	  connection;
  
  /**
   *
   * @return
   * @throws DAOConfigurationException
   */
  public static Connection getInstance() throws DAOConfigurationException {
	Properties properties = new Properties();
	String url;
	String driver;
	String nomUtilisateur;
	String motDePasse;

	ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	InputStream fichierProperties = classLoader.getResourceAsStream( FICHIER_PROPERTIES );
	//	On verifie l existence du fichier properties
	if (fichierProperties == null) {
	  throw new DAOConfigurationException( "Le fichier properties " + FICHIER_PROPERTIES + " est introuvable." );
	}

	try {
	  //  Chargement du fichier properties
	  properties.load( fichierProperties );
	  //  Assignements des parametre de la base de donnees
	  url = properties.getProperty( PROPERTY_URL );
	  driver = properties.getProperty( PROPERTY_DRIVER );
	  nomUtilisateur = properties.getProperty( PROPERTY_NOM_UTILISATEUR );
	  motDePasse = properties.getProperty( PROPERTY_MOT_DE_PASSE );
	} catch ( IOException e ) {
	  throw new DAOConfigurationException( "Impossible de charger le fichier properties " + FICHIER_PROPERTIES, e );
	}

	try {
	  //Enregistrement du driver JDBC
	  Class.forName( driver );
	  System.out.println("Driver O.K.");
	  //  Creation de la connection a la base de donnee
	  connection = DriverManager.getConnection(url, nomUtilisateur, motDePasse);
	  System.out.println("INSTANCIATION DE LA CONNEXION MySQL ! ");
	} catch ( ClassNotFoundException e ) {
	  throw new DAOConfigurationException( "Le driver est introuvable dans le classpath.", e );
	} catch (SQLException ex) {
	  Logger.getLogger(SingletonConnexionDAO.class.getName()).log(Level.SEVERE, null, ex);
	}
	return connection;
  }
}
