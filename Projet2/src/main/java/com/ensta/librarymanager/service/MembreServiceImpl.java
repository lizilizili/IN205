package com.ensta.librarymanager.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.ensta.librarymanager.dao.MembreDao;
import com.ensta.librarymanager.dao.MembreDaoImpl;
import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Membre;

public class MembreServiceImpl implements MembreService{

	// Une autre implémentation possible du design pattern Singleton
	private static MembreServiceImpl instance = new MembreServiceImpl();
	private MembreServiceImpl() { }	
	public static MembreService getInstance() {		
		return instance;
	}
	
	@Override
	public List<Membre> getList() throws ServiceException {
		MembreDao mDao = MembreDaoImpl.getInstance();
		List<Membre> membres=new ArrayList<>();
		try {
			membres =mDao.getList();
			
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return membres;
	}

	@Override
	public List<Membre> getListMembreEmpruntPossible() throws ServiceException {
		MembreDao mDao = MembreDaoImpl.getInstance();
		EmpruntService eServ = EmpruntServiceImpl.getInstance();
		List<Membre> membres=new ArrayList<>();
		try {
			membres =mDao.getList();
			for (Iterator<Membre> iterator = membres.iterator(); iterator.hasNext(); ) {
	            while (!eServ.isEmpruntPossible(iterator.next())){
	            	iterator.remove();
	            }   
	        }
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return membres;
	}

	@Override
	public Membre getById(int id) throws ServiceException {
		MembreDao mDao = MembreDaoImpl.getInstance();
		Membre membre=new Membre();
		try {
			membre =mDao.getById(id);
			
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return membre;
	}

	@Override
	public int create(String nom, String prenom, String adresse, String email, String telephone)
			throws ServiceException {
		MembreDao mDao = MembreDaoImpl.getInstance();
		int id=-1;
		try {
			id =mDao.create(nom, prenom, adresse, email, telephone);
			
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return id;
	}

	@Override
	public void update(Membre membre) throws ServiceException {
		MembreDao mDao = MembreDaoImpl.getInstance();
		try {
			mDao.update(membre);			
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		
	}

	@Override
	public void delete(int id) throws ServiceException {
		MembreDao mDao = MembreDaoImpl.getInstance();
		try {
			mDao.delete(id);			
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		
	}

	@Override
	public int count() throws ServiceException {
		MembreDao mDao = MembreDaoImpl.getInstance();
		int count=-1;
		try {
			count = mDao.count();		
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return count;
	}

}
