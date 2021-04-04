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
import com.ensta.librarymanager.service.EmpruntService;
import com.ensta.librarymanager.service.EmpruntServiceImpl;
import com.ensta.librarymanager.service.LivreService;
import com.ensta.librarymanager.service.LivreServiceImpl;
import com.ensta.librarymanager.service.MembreService;
import com.ensta.librarymanager.service.MembreServiceImpl;

public class DashboardServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LivreService lService=LivreServiceImpl.getInstance();
    	MembreService mService=MembreServiceImpl.getInstance();
    	EmpruntService eService=EmpruntServiceImpl.getInstance();
    	
		List<Emprunt> emprunts = new ArrayList<>();
		int lcount=1,mcount=-1,ecount=-1;
		try {
			emprunts = eService.getListCurrent();
			ecount = eService.count();
			lcount = lService.count();
			mcount = mService.count();
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/dashboard.jsp");
		request.setAttribute("currentEmprunts", emprunts);
		request.setAttribute("empruntsCount", ecount);
		request.setAttribute("livresCount", lcount);
		request.setAttribute("membresCount", mcount);
		dispatcher.forward(request, response);
	}
	



}
