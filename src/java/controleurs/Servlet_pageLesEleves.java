/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleurs;

import classDAO.DAO;
import classMetier.Classe;
import classMetier.Eleve;
import designpattern.FactoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "Servlet_pageLesEleves", urlPatterns = {"/Servlet_pageLesEleves"})
public class Servlet_pageLesEleves extends HttpServlet implements ICommand {

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
  public static String CHAMP_CHOIX_CLASSE = "choix-classe";

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
	  // Instanciation d une classe DAO eleve 
	  DAO<Eleve> monEleveDAO = FactoryDAO.getEleveDAO();
	  // Appelle de la methode de classe pour recuperer une liste d eleves
	  List<Eleve> lesEleves = monEleveDAO.lister();	
	  // On récupère le nombre des élèves
	  request.setAttribute("nombre", lesEleves.size() );
	  // On envoie l objet "Eleve"
	  request.setAttribute("lesEleves", lesEleves );
	  // Instanciation de la classe DAO classe
	  DAO<Classe> laClasseDAO = FactoryDAO.getClasseDAO();
	  // Appelle de la methode de classe pour recuperer une liste de classes
	  List<Classe> lesClasses = laClasseDAO.lister();
	  // On recupere laliste des classes et on l envoie
	  request.setAttribute("lesClasses", lesClasses);
	  // Recuperation des champs du formulaire
	  String nom = request.getParameter(CHAMP_NOM);
	  String prenom = request.getParameter(CHAMP_PRENOM);
	  int classeID = Integer.parseInt(request.getParameter(CHAMP_CHOIX_CLASSE));
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
	  // Validation du choix de la classe
	  try {
		validationClasse(classeID);
	  } catch (Exception ex) {
		mErreurs.put(CHAMP_CHOIX_CLASSE, ex.getMessage());
	  }
	  // On verifie l existence de l eleve (un n° INSEE serait mieux)
	  try {
		checkExister(monEleveDAO, nom, prenom);
	  } catch (Exception ex) {
		mErreurs.put(CHAMP_NOM, ex.getMessage());
	  }
	  // Initialisation du résultat global de la validation.
	  // Si la Map ne contient aucun message
	  if ( mErreurs.isEmpty() ) {		
		// On instancie un objet Eleve avec le nom et le prenom de l eleve
		Eleve addEleve = new Eleve();
		addEleve.setNom(nom);
		addEleve.setPrenom(prenom);		  
		// Appelle de la methode pour creer le nouvel eleve dans la bdd
		monEleveDAO.create(addEleve, classeID);
		// Message d information lors de la creation
		String nomClasse = laClasseDAO.find(classeID).getNom();
		// Mise a jour du tableau des eleves
		List<Eleve> nouveauxEleves = monEleveDAO.lister();
		// On recupere le nombre d eleves
		request.setAttribute("nombre", nouveauxEleves.size() );
		// on envoie l'objet "classe"
		request.setAttribute("lesEleves", nouveauxEleves );
		resultForm = "Succès de la création de l'élève \"" + nom + " " + prenom + 
				  "\" a été ajouté dans la classe \"" + nomClasse + "\"...";
		
	  } else {
		  resultForm = "Échec de la création de l'élève...";
	  }	
	  // On envoie la valeur de l index selectionne dans la liste deroulante
	  request.setAttribute("classeID", classeID);		
	  // Stockage du resultat et des messages d erreur dans l objet request
	  request.setAttribute( MESSAGES_ERREURS, mErreurs );
	  request.setAttribute( MESSAGES_RESULTAT, resultForm );
	  
	}catch (Exception ex) {
	  ex.printStackTrace();
	}
	return ("jsp/pageLesEleves.jsp");
  }
  
  /**
   * Methode pour controler la saisie du nom de l eleve
   * @param inputNom
   * @throws Exception 
   */
  private void validationNom( String inputNom) throws Exception {
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
   * Methode qui verifie qu une classe a ete choisie
   * @param inputPrenom
   * @throws Exception 
   */
  private void validationClasse( int choix) throws Exception {
	//  Si le champ est vide lors de la validation on envoie un message
	if (choix == 0) {	  
	  throw new Exception("Vous devez sélectionner une classe...");
	}
  }
  
  /**
   * Methode pour verifier que l eleve n existe pas deja
   * @param request
   * @param inputPrenom
   * @param eleve
   * @return 
   */
  private void checkExister(DAO<Eleve> eleve, Object...objets) throws Exception {
		
	if (eleve.exister(objets) != null){
	  // Si l eleve saisi existe deja	  
	  throw new Exception("Cet élève existe déjà...");
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
	response.setContentType("text/html;charset=UTF-8");
	try (PrintWriter out = response.getWriter()) {
	  /* TODO output your page here. You may use following sample code. */
	  out.println("<!DOCTYPE html>");
	  out.println("<html>");
	  out.println("<head>");
	  out.println("<title>Servlet temp</title>");	  
	  out.println("</head>");
	  out.println("<body>");
	  out.println("<h1>Servlet temp at " + request.getContextPath() + "</h1>");
	  out.println("</body>");
	  out.println("</html>");
	}
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
