package com.ensta.librarymanager.servlet;

import java.io.IOException;
import java.time.LocalDate;
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
import com.ensta.librarymanager.service.EmpruntService;
import com.ensta.librarymanager.service.EmpruntServiceImpl;
import com.ensta.librarymanager.service.LivreService;
import com.ensta.librarymanager.service.LivreServiceImpl;

public class EmpruntReturnServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	EmpruntService eService=EmpruntServiceImpl.getInstance();
    	LivreService lService=LivreServiceImpl.getInstance();
		List<Emprunt> emprunts = new ArrayList<>();
		//String inputId = request.getParameter("id");
		try {
			emprunts = eService.getListCurrent();
			
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/emprunt_return.jsp");
		request.setAttribute("currentEmprunts", emprunts);
		dispatcher.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmpruntService eService=EmpruntServiceImpl.getInstance();
		String inputIdEmprunt = request.getParameter("id");
		try {
			eService.returnBook(Integer.parseInt(inputIdEmprunt));
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/emprunt_return.jsp");
		dispatcher.forward(request, response);	
	}

}
