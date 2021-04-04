package com.ensta.librarymanager.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Emprunt;
import com.ensta.librarymanager.modele.Livre;
import com.ensta.librarymanager.modele.Membre;
import com.ensta.librarymanager.service.EmpruntService;
import com.ensta.librarymanager.service.EmpruntServiceImpl;
import com.ensta.librarymanager.service.LivreService;
import com.ensta.librarymanager.service.LivreServiceImpl;
import com.ensta.librarymanager.service.MembreService;
import com.ensta.librarymanager.service.MembreServiceImpl;

public class MembreDetailsServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	EmpruntService eService=EmpruntServiceImpl.getInstance();
    	MembreService mService=MembreServiceImpl.getInstance();
		List<Emprunt> emprunts = new ArrayList<>();
		String inputId = request.getParameter("id");
		Membre membre=new Membre();
		try {
			emprunts = eService.getListCurrentByMembre(Integer.parseInt(inputId));	
			membre=mService.getById(Integer.parseInt(inputId));
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/membre_details.jsp");
		request.setAttribute("currentEmprunts", emprunts);
		request.setAttribute("m", membre);
		dispatcher.forward(request, response);
	}

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MembreService mService=MembreServiceImpl.getInstance();
		String inputId = request.getParameter("id");
		String inputNom = request.getParameter("nom");
		String inputPrenom = request.getParameter("prenom");
		String inputAdresse = request.getParameter("adress");
		String inputEmail = request.getParameter("email");
		String inputTel = request.getParameter("telephone");
						
		try {
			Membre membre=mService.getById(Integer.parseInt(inputId));
			membre.setAdresse(inputAdresse);
			membre.setEmail(inputEmail);
			membre.setNom(inputNom);
			membre.setPrenom(inputPrenom);
			membre.setTel(inputTel);
			mService.update(membre);
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/membre_details.jsp");
		dispatcher.forward(request, response);	
	}

}
