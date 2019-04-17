/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainControleur;

import controleurs.*;
import designpattern.ICommand;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet principal qui contrôle le routage vers les autres servlets
 * @author Stagiaire
 */
@WebServlet(name = "MainServletDAO", urlPatterns = {"/MainServletDAO"})
public class MainServletDAO extends HttpServlet {
  
  private final Map commands = new HashMap();
     
  /**
   * Methode d initialisation
   */
  public void init() {
	commands.put("page1", new Servlet_pageLesClasses());
	commands.put("page2", new Servlet_pageLesEleves());
	commands.put("page3", new Servlet_pageLesProfesseurs());
	commands.put("page4", new Servlet_pageLesMatieres());
	commands.put("page5", new Servlet_pageCreaClasse());
	commands.put("page6", new Servlet_pageIndex());
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
		  throws ServletException, IOException, Exception {
	
	response.setContentType("text/html;charset=UTF-8");
	//	On récupère le paramètre passé par la page précédente ex : "ServletMvc?cmd=page1"
	String cmdQuery = request.getParameter("cmd");
	//	On récupère la classe "designpattern" qui se trouve en valeur de la clé passée 
	//	en paramètre et on instencie un objet command de cette classe
	//	ex : Servlet_pageLesClasses() pour paramètre égal à 1
	ICommand myCommand = (ICommand)commands.get(cmdQuery);
	//	On appelle la méthode "execute" de cette classe et on récupère  dans "urlsuite" 
	//	l'url de la page à afficher plus toutes les info nécessaire à la page
	//	à afficher dans "resquest"
	String urlSuite = myCommand.execute(request, response);

	//	On dispatche avec les nouvelles valeurs éventuelles passées par la classe 
	request.getRequestDispatcher(urlSuite).forward(request, response);
	
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
	try {
	  processRequest(request, response);
	} catch (Exception ex) {
	  Logger.getLogger(MainServletDAO.class.getName()).log(Level.SEVERE, null, ex);
	}
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
	try {
	  processRequest(request, response);
	} catch (Exception ex) {
	  Logger.getLogger(MainServletDAO.class.getName()).log(Level.SEVERE, null, ex);
	}
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
