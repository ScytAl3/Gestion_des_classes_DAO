/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleurs;

import classDAO.DAO;
import classMetier.Classe;
import designpattern.FactoryDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import designpattern.ICommand;

/**
 *
 * @author Stagiaire
 */
@WebServlet(name = "Servlet_pageLesClasses", urlPatterns = {"/Servlet_pageLesClasses"})
public class Servlet_pageLesClasses extends HttpServlet implements ICommand {

  /**
   *
   * @param request
   * @param response
   * @return
   */
  public String execute (HttpServletRequest request, HttpServletResponse response) {
	try{
	  DAO<Classe> laClasseDAO = FactoryDAO.getClasseDAO();
	  List<Classe> lesClasses = laClasseDAO.lister();
	  // On récupère le nombre de classes
	  request.setAttribute("nbClasse", lesClasses.size() );
	  // On recupere laliste des classes et on l envoie
	  request.setAttribute("lesClasses", lesClasses);	  
	  // Recuperation de l index lors du choix dans la liste deroulante
	  int classeID = Integer.parseInt(request.getParameter("choix-classe"));
	  // Appelle de la methode find(id) de la class Classe pour obtenir les informations
	  Classe laClasse = laClasseDAO.find(classeID);
	  // On envoie la valeur de l index selectionne dans la liste deroulante
	  request.setAttribute("classeID", classeID);
	  // On envoie le nombre de classe
	  request.setAttribute("nbClasse", lesClasses.size() );	  
	  // On envoie le nombre des élèves
	  request.setAttribute("nombre", laClasse.getListEleve().size() );
	  // On envoie l'objet "classe"
	  request.setAttribute("laclasse", laClasse );
	}catch (Exception ex) {
	  ex.printStackTrace();	  
	}
	return ("jsp/pageLesClasses.jsp");
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