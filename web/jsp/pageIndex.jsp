<%-- 
    Document   : pageIndex
    Created on : 11 mars 2019, 15:35:59
    Author     : Stagiaire
--%>

<%@page import="java.util.*"%> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Page index JSP</title>
	<meta name="author" content="Franck Jakubowski">
	<meta name="description" content="Gestion des salles de classes: Elèves, classes
		  professeurs et matières.">
	<!--Mobile Specific Metas-->
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!--Force IE9 to render in normal mode-->
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<!--Bootstrap stylesheets-->
	<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
	<!--Default stylesheets-->
	<link rel="stylesheet" type="text/css" href="css/mainPage.css">
  </head>
  <body>
	<header>
	  <!--Barre de navigation-->
	  <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="#">Navbar</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
		  <span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNavDropdown">
		  <ul class="navbar-nav mr-auto">
			<li class="nav-item active">
			  <a class="nav-link" href="MainServletDAO?cmd=page6">Home <span class="sr-only">(current)</span></a>
			</li>
			<li class="nav-item">
			  <a class="nav-link" href="#">Features</a>
			</li>
			<li class="nav-item">
			  <a class="nav-link" href="#">Pricing</a>
			</li>
			<li class="nav-item dropdown">
			  <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				Dropdown link
			  </a>
			  <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
				<a class="dropdown-item" href="MainServletDAO?cmd=page1">Les Classes</a>
				<a class="dropdown-item" href="MainServletDAO?cmd=page2">Les Élèves</a>
				<a class="dropdown-item" href="MainServletDAO?cmd=page3">Les Professeurs</a>
				<a class="dropdown-item" href="MainServletDAO?cmd=page4">Les Matières</a>
				<a class="dropdown-item" href="MainServletDAO?cmd=page5">Créer Classe</a>
			  </div>
			</li>
		  </ul>
		  <form class="form-inline">
			<input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
			<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
		</form>
		</div>
	  </nav>
	</header>
	
	<main class="text-center">
	  
	  <section style="background-color:#ccffcc">
		<h1>Hello World!</h1>
		<hr class="mb-4">
		<p>Vous êtes sur la page index.jsp</p>
	  </section>
	  
	  <footer class="my-5 pt-5 text-muted text-center text-small">
        <p class="mb-1">© 2018-2019 AFPA - CDI</p>
        <ul class="list-inline">
          <li class="list-inline-item"><a href="#">Privacy</a></li>
          <li class="list-inline-item"><a href="#">Terms</a></li>
          <li class="list-inline-item"><a href="#">Support</a></li>
        </ul>
      </footer>
	  
	</main>	
	
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" crossorigin="anonymous"></script>
	
  </body>
</html>
