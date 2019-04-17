/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleurs;

import classDAO.DAO;
import classMetier.Classe;
import classMetier.Professeur;
import designpattern.FactoryDAO;
import designpattern.ICommand;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Stagiaire
 */
@WebServlet(name = "Servlet_pageCreaClasse", urlPatterns = {"/Servlet_pageCreaClasse"})
public class Servlet_pageCreaClasse extends HttpServlet implements ICommand {

  // Declaration des variables contenant les saisies du formulaire

  /**
   * 
   */
  public static String CHAMP_NOM = "classeName";

  /**
   *
   */
  public static String CHAMP_CHOIX_PROF = "choix-prof";
  // Declaration des variables contenant les messages de validation du formulaire

  /**
   *
   */
  public static String MESSAGES_ERREURS = "erreur";

  /**
   *
   */
  public static String MESSAGES_RESULTAT = "resultat";
  
  /**
   *  Implemente execute(HttpServletRequest, HttpServletResponse) de l interface ICommand
   * @param request
   * @param response
   * @return
   */
  public String execute (HttpServletRequest request, HttpServletResponse response) {
	// Variables pour la gestion des messages lies au formulaire
	String resultForm = null;
	Map<String, String> mErreurs = new HashMap<String, String>();
	
	try{
	  DAO<Classe> laClasseDAO = FactoryDAO.getClasseDAO();
	  List<Classe> lesClasses = laClasseDAO.lister();
	  // On récupère le nombre de classes
	  request.setAttribute("nbClasse", lesClasses.size() );
	  // On recupere laliste des classes et on l envoie
	  request.setAttribute("lesClasses", lesClasses);	  
	  // Instanciation de la classe DAO professeur
	  DAO<Professeur> leProfDAO = FactoryDAO.getProfesseurDAO();
	  // Appelle de la methode de classe pour recuperer une liste de professeurs
	  List<Professeur> lesprofesseurs = leProfDAO.lister();
	  // On recupere laliste des professeurs et on l envoie
	  request.setAttribute("lesProfs", lesprofesseurs);
	  // Recuperation des champs du formulaire
	  String nom = request.getParameter(CHAMP_NOM);
	  int professeurID = Integer.parseInt(request.getParameter(CHAMP_CHOIX_PROF));
	  // Validation du nom de la classe
	  try {
		validationNom(nom);
	  } catch (Exception ex) {
		mErreurs.put(CHAMP_NOM, ex.getMessage());
	  }
	  // Validation du choix de la matiere
	  try {
		validationProfesseur(professeurID);
	  } catch (Exception ex) {
		mErreurs.put(CHAMP_CHOIX_PROF, ex.getMessage());
	  }
	  // On verifie que cette classe n est pas deja associee a ce professeur
	  if (professeurID != 0) {
		try {
		  checkExister(laClasseDAO, nom, professeurID);
		}catch (Exception ex) {
		  mErreurs.put(CHAMP_NOM, ex.getMessage());
		}
	  }
	  // Initialisation du résultat global de la validation.
	  // Si la Map ne contient aucun message
	  if ( mErreurs.isEmpty() ) {
		// Message d information lors de la creation
		String nomProfesseur = leProfDAO.find(professeurID).getNom();
		resultForm = "Succès de la création de la classe \"" + nom + ""
				+ " a été ajouté avec le professuer \"" + nomProfesseur + "\"...";
		
	  }else {
		resultForm = "Échec de la création de la classe...";
	  }
	  // On envoie la valeur de l index selectionne dans la liste deroulante
	  request.setAttribute("profID", professeurID);		
	  // Stockage du resultat et des messages d erreur dans l objet request
	  request.setAttribute( MESSAGES_ERREURS, mErreurs );
	  request.setAttribute( MESSAGES_RESULTAT, resultForm );
	  
	}catch (Exception ex) {
	  ex.printStackTrace();	  
	}
	return ("jsp/PageCreaClasse.jsp");
  }

  /**
   * Methode pour controler la saisie du nom de la classe
   * @param inputNom
   * @throws Exception 
   */
  private void validationNom(String inputNom) throws Exception {
	//  Si le champ n est pas vide lors de la validation
	if (inputNom != null && inputNom.trim().length() != 0 ) {	  
	  //  Si la saisie ne correspondant au format demande
	  if (!inputNom.matches("^[3-6][°][A-Z]$")) {
		throw new Exception("Le nom se compose d'un numéro et d'une lette, ex: 6°A...");
	  } 
	} else {
	  throw new Exception("Vous devez saisir un nom de classe...");
	}
  }
  
  /**
   * Methode qui verifie qu un professeur a ete choisie
   * @param inputPrenom
   * @throws Exception 
   */
  private void validationProfesseur(int choix) throws Exception {
	//  Si le champ est vide lors de la validation on envoie un message
	if (choix == 0) {	  
	  throw new Exception("Vous devez sélectionner un professeur...");
	}
  }
  
  /**
   * Methode pour verifier que la classe n est pas deja associe a ce professeur
   * @param classe
   * @param objets
   * @throws Exception 
   */
  private void checkExister(DAO<Classe> classe, Object...objets) throws Exception {
		
	if (classe.exister(objets) != null){
	  // Si la matiere saisie existe deja	  
	  throw new Exception("Cette classe associée à ce professeur existe déjà...");
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
	  out.println("<title>Servlet Servlet_pageCreaClasse</title>");	  
	  out.println("</head>");
	  out.println("<body>");
	  out.println("<h1>Servlet Servlet_pageCreaClasse at " + request.getContextPath() + "</h1>");
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
