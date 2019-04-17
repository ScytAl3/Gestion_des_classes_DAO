/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleurs;

import classDAO.DAO;
import classMetier.Matiere;
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

/**
 *	Servlet de controle de la page de creation d une matiere
 * @author Stagiaire
 */
@WebServlet(name = "Servlet_pageLesMatieres", urlPatterns = {"/Servlet_pageLesMatieres"})
public class Servlet_pageLesMatieres extends HttpServlet implements ICommand {
     
  /**
   *  Implemente execute(HttpServletRequest, HttpServletResponse) de l interface ICommand
   * @param request
   * @param response
   * @return
   */
  public String execute (HttpServletRequest request, HttpServletResponse response) {
	try{
	  String newMatiere = "";
	  DAO<Matiere> laMatiereDAO = FactoryDAO.getMatiereDAO();
	  List<Matiere> lesMatieres = laMatiereDAO.lister();
	  //On récupère le nombre des élèves
	  request.setAttribute("nombre", lesMatieres.size() );
	  // on envoie l'objet "classe"
	  request.setAttribute("lesMatieres", lesMatieres );
	  
	  // Recuperation des donnees du formulaire : ici, nom de la matiere
	  newMatiere = request.getParameter("matiere");
	  //  On verifie qu il n'y a pas de problème avec la saisie
	  if (validationMatiere(request, newMatiere, laMatiereDAO)) {
		// On instancie un objet Matiere avec setter pour le nom de la matiere
		Matiere addMatiere = new Matiere();
		addMatiere.setNom(newMatiere);
		// Appelle de la methode pour creer la nouvelle matiere dans la bdd
		laMatiereDAO.create(addMatiere);
		// Message information lors de la creation
		request.setAttribute("message", "La matière \"" + newMatiere + "\" a été ajouté...");
		// Mise a jour du tableau des matiere
		List<Matiere> nouvellesMatieres = laMatiereDAO.lister();
		//On récupère le nombre des élèves
		request.setAttribute("nombre", nouvellesMatieres.size() );
		// on envoie l'objet "classe"
		request.setAttribute("lesMatieres", nouvellesMatieres );
	  }
	}catch (Exception ex) {
	  ex.printStackTrace();
	}
	return ("jsp/pageLesMatieres.jsp");
  }
  
  /**
   * Methode de controle du champ de nom et de l existence de la matiere que l on veut ajouter et 
   * @param request
   * @param inputMatiere
   * @return 
   */
  private boolean validationMatiere (HttpServletRequest request, String inputMatiere, DAO<Matiere> mat) {
	boolean bMatiere = true;
	//  Si le champ est vide lors de la validation on envoie un message
	if (!"".equals(inputMatiere)) {	  
	  //  Si la saisie ne correspondant au format demandé
	  if (!inputMatiere.matches("^[A-Z][\\p{L}-]{1,29}$")) {
		request.setAttribute("message", "La matière commence par une majuscule...");
		bMatiere = false;
	  } else if (mat.exister(inputMatiere) != null) {
		//  Si la matiere saisie existe deja	  
		request.setAttribute("message", "La matière existe déjà...");
		bMatiere = false;
	  } 
	} else {
	  request.setAttribute("message", "Vous devez saisir une matière...");
	  bMatiere = false;
	}
	return bMatiere;
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
