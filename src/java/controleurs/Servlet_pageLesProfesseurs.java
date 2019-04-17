/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleurs;

import classDAO.DAO;
import classMetier.Matiere;
import classMetier.Professeur;
import designpattern.FactoryDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import designpattern.ICommand;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Stagiaire
 */
@WebServlet(name = "Servlet_pageLesProfesseurs", urlPatterns = {"/Servlet_pageLesProfesseurs"})
public class Servlet_pageLesProfesseurs extends HttpServlet implements ICommand {

  /**
   *
   */
  public static String CHAMP_NOM = "lastName";

  /**
   *
   */
  public static String CHAMP_PRENOM = "firstName";

  /**
   *
   */
  public static String CHAMP_CHOIX_MATIERE = "choix-matiere";

  /**
   *
   */
  public static String MESSAGES_ERREURS = "erreur";

  /**
   *
   */
  public static String MESSAGES_RESULTAT = "resultat"; 

  /**
   *
   * @param request
   * @param response
   * @return
   */
  public String execute (HttpServletRequest request, HttpServletResponse response) {
	// Variables pour la gestion des messages lies au formulaire
	String resultForm = null;
	Map<String, String> mErreurs = new HashMap<String, String>();
	
	try{
	  DAO<Professeur> monProfesseurDAO = FactoryDAO.getProfesseurDAO();
	  List<Professeur> lesProfesseurs = monProfesseurDAO.lister();
	  //On récupère le nombre des élèves
	  request.setAttribute("nombre", lesProfesseurs.size() );
	  // on envoie l'objet "classe"
	  request.setAttribute("lesProfesseurs", lesProfesseurs );
	  // Instanciation de la classe DAO matiere
	  DAO<Matiere> laMatiereDAO = FactoryDAO.getMatiereDAO();
	  // Appelle de la methode de classe pour recuperer une liste de matieres
	  List<Matiere> lesMatieres = laMatiereDAO.lister();
	  // On recupere laliste des matiere et on l envoie
	  request.setAttribute("lesMatieres", lesMatieres);
	  // Recuperation des champs du formulaire
	  String nom = request.getParameter(CHAMP_NOM);
	  String prenom = request.getParameter(CHAMP_PRENOM);
	  int matiereID = Integer.parseInt(request.getParameter(CHAMP_CHOIX_MATIERE));
	  // Validation du nom
	  try {
		validationNom(nom);
	  } catch (Exception ex) {
		mErreurs.put(CHAMP_NOM, ex.getMessage());
	  }
	  // Validation du prenom
	  try {
		validationPrenom(prenom);
	  } catch (Exception ex) {
		mErreurs.put(CHAMP_PRENOM, ex.getMessage());
	  }
	  // Validation du choix de la matiere
	  try {
		validationMatiere(matiereID);
	  } catch (Exception ex) {
		mErreurs.put(CHAMP_CHOIX_MATIERE, ex.getMessage());
	  }
	  // On verifie que ce professeur n enseigne pas deja cette matiere
	  if (matiereID != 0) {
		try {
		checkExister(monProfesseurDAO, nom, prenom, matiereID);
		} catch (Exception ex) {
		  mErreurs.put(CHAMP_NOM, ex.getMessage());
		}
	  }
	  
	  // Initialisation du résultat global de la validation.
	  // Si la Map ne contient aucun message
	  if ( mErreurs.isEmpty() ) {
		// On instancie un objet Professeur avec le nom et le prenom du professeur
		Professeur addProfesseur = new Professeur();
		addProfesseur.setNom(nom);
		addProfesseur.setPrenom(prenom);
		// Appelle de la methode pour creer le nouveau professeur dans la bdd
		monProfesseurDAO.create(addProfesseur, matiereID);		
		// Mise a jour du tableau des professeurs
		List<Professeur> nouveauxProfesseurs = monProfesseurDAO.lister();
		// On recupere le nombre de professeurs		
		request.setAttribute("nombre", nouveauxProfesseurs.size() );
		// on envoie l'objet "professeur"
		request.setAttribute("lesProfesseurs", nouveauxProfesseurs);
		// Message d information lors de la creation
		String nomMatiere = laMatiereDAO.find(matiereID).getNom();
		resultForm = "Succès de la création de l'élève \"" + nom + " " + prenom + 
				  "\" a été ajouté avec la matière \"" + nomMatiere + "\"...";
		
			  
	  }else {
		resultForm = "Échec de la création du professeur...";
	  }
	  // On envoie la valeur de l index selectionne dans la liste deroulante
	  request.setAttribute("matiereID", matiereID);		
	  // Stockage du resultat et des messages d erreur dans l objet request
	  request.setAttribute( MESSAGES_ERREURS, mErreurs );
	  request.setAttribute( MESSAGES_RESULTAT, resultForm );
		
	}catch (Exception ex) {
	  
	  ex.printStackTrace();
	}
	return ("jsp/pageLesProfesseurs.jsp");
  }
  
  
  /**
   * Methode pour controler la saisie du nom de l eleve
   * @param inputNom
   * @throws Exception 
   */
  private void validationNom(String inputNom) throws Exception {
	//  Si le champ n est pas vide lors de la validation
	if (inputNom != null && inputNom.trim().length() != 0 ) {	  
	  //  Si la saisie ne correspondant au format demande
	  if (!inputNom.matches("^[A-Z -]{1,30}$")) {
		throw new Exception("Le nom doit être en majuscule...");
	  } 
	} else {
	  throw new Exception("Vous devez saisir un nom...");
	}
  }
  
  /**
   * Methode pour controler la saisie du prenom de l eleve
   * @param inputPrenom
   * @throws Exception 
   */
  private void validationPrenom( String inputPrenom) throws Exception {
	//  Si le champ n est pas vide lors de la validation
	if (inputPrenom != null && inputPrenom.trim().length() != 0 ) {	  
	  //  Si la saisie ne correspondant au format demande
	  if (!inputPrenom.matches("^[A-Z][a-zéè -]{1,30}$")) {
		throw new Exception("Le prénom doit commence par une majuscule...");
	  } 
	} else {
	  throw new Exception("Vous devez saisir un prénom...");
	}
  }
  
  /**
   * Methode qui verifie qu une matiere a ete choisie
   * @param choix
   * @throws Exception 
   */
  private void validationMatiere(int choix) throws Exception {
	//  Si le champ est vide lors de la validation on envoie un message
	if (choix == 0) {	  
	  throw new Exception("Vous devez sélectionner une matière...");
	}
  }
  
  /**
   * Methode pour verifier que le professeur n enseigne pas deja cette matiere
   * @param professeur
   * @param objets
   * @throws Exception 
   */
  private void checkExister(DAO<Professeur> professeur, Object...objets) throws Exception {
		
	if (professeur.exister(objets) != null){
	  // Si le professeur saisi existe deja	  
	  throw new Exception("Ce professeur existe déjà...");
	}
  }
  
  
  /**
   * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
   * methods.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
		  throws ServletException, IOException {
  }

  // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
  /**
   * Handles the HTTP <code>GET</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
		  throws ServletException, IOException {
	processRequest(request, response);
  }

  /**
   * Handles the HTTP <code>POST</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
		  throws ServletException, IOException {
	processRequest(request, response);
  }

  /**
   * Returns a short description of the servlet.
   *
   * @return a String containing servlet description
   */
  @Override
  public String getServletInfo() {
	return "Short description";
  }// </editor-fold>

}
