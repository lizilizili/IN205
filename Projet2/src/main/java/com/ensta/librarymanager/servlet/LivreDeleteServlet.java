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

public class LivreDeleteServlet extends HttpServlet{
	
	
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LivreService lService=LivreServiceImpl.getInstance();
		String inputId = request.getParameter("id");
		
		try {
			
			lService.delete(Integer.parseInt(inputId));
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/livre_delete.jsp");
		dispatcher.forward(request, response);	
	}
}
