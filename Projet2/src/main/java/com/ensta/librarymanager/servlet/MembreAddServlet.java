package com.ensta.librarymanager.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.service.LivreService;
import com.ensta.librarymanager.service.LivreServiceImpl;
import com.ensta.librarymanager.service.MembreService;
import com.ensta.librarymanager.service.MembreServiceImpl;

public class MembreAddServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MembreService mService=MembreServiceImpl.getInstance();
		String inputNom = request.getParameter("nom");
		String inputPrenom = request.getParameter("prenom");
		String inputAdresse = request.getParameter("adress");
		String inputEmail = request.getParameter("email");
		String inputTel = request.getParameter("telephone");
		try {
			
			int id = mService.create(inputNom, inputPrenom, inputAdresse, inputEmail, inputTel);
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/membre_add.jsp");
		dispatcher.forward(request, response);	
	}
}
