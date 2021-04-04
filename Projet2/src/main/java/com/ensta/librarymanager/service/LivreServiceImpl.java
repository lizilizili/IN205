package com.ensta.librarymanager.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.ensta.librarymanager.dao.LivreDao;
import com.ensta.librarymanager.dao.LivreDaoImpl;
import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Livre;

public class LivreServiceImpl implements LivreService{

	// Une autre implémentation possible du design pattern Singleton
	private static LivreServiceImpl instance = new LivreServiceImpl();
	private LivreServiceImpl() { }	
	public static LivreService getInstance() {		
		return instance;
	}
	
	@Override
	public List<Livre> getList() throws ServiceException {
		LivreDao lDao = LivreDaoImpl.getInstance();
		List<Livre> livres=new ArrayList<>();
		try {
			livres =lDao.getList();
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return livres;
	}

	@Override
	public List<Livre> getListDispo() throws ServiceException {
		LivreDao lDao = LivreDaoImpl.getInstance();
		EmpruntService eServ = EmpruntServiceImpl.getInstance();
		List<Livre> livres=new ArrayList<>();
		//Livre livreNonDispo
		try {
			livres =lDao.getList();
			for (Iterator<Livre> iterator = livres.iterator(); iterator.hasNext(); ) {
	            while (!eServ.isLivreDispo(iterator.next().getId())){
	            	iterator.remove();
	            }   
	        }
	
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return livres;
	}

	@Override
	public Livre getById(int id) throws ServiceException {
		LivreDao lDao = LivreDaoImpl.getInstance();
		Livre livre=new Livre();
		try {
			livre =lDao.getById(id);
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return livre;
	}

	@Override
	public int create(String titre, String auteur, String isbn) throws ServiceException {
		LivreDao lDao = LivreDaoImpl.getInstance();
		int i = -1;
		try {
			i = lDao.create(titre,auteur,isbn);
		}  catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		} 
		return i;
	}

	@Override
	public void update(Livre livre) throws ServiceException {
		LivreDao lDao = LivreDaoImpl.getInstance();
		try {
			lDao.update(livre);
		}  catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		} 
		
	}

	@Override
	public void delete(int id) throws ServiceException {
		LivreDao lDao = LivreDaoImpl.getInstance();
		try {
			lDao.delete(id);;
		}  catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		} 
		
	}

	@Override
	public int count() throws ServiceException {
		LivreDao lDao = LivreDaoImpl.getInstance();
		int count=-1;
		try {
			count = lDao.count();
		}  catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		} 
		return count;
	}
	

}
