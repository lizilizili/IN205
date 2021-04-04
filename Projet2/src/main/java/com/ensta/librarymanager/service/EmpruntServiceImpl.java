package com.ensta.librarymanager.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ensta.librarymanager.dao.EmpruntDao;
import com.ensta.librarymanager.dao.EmpruntDaoImpl;
import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.exception.ServiceException;
import com.ensta.librarymanager.modele.Emprunt;
import com.ensta.librarymanager.modele.Membre;
import com.ensta.librarymanager.modele.Membre.Abonnement;

public class EmpruntServiceImpl implements EmpruntService{

	// Une autre implémentation possible du design pattern Singleton
	private static EmpruntServiceImpl instance = new EmpruntServiceImpl();
	private EmpruntServiceImpl() { }	
	public static EmpruntService getInstance() {		
		return instance;
	}
	@Override
	public List<Emprunt> getList() throws ServiceException {
		EmpruntDao eDao = EmpruntDaoImpl.getInstance();
		List<Emprunt> emprunts=new ArrayList<>();
		try {
			emprunts =eDao.getList();
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return emprunts;
	}

	@Override
	public List<Emprunt> getListCurrent() throws ServiceException {
		EmpruntDao eDao = EmpruntDaoImpl.getInstance();
		List<Emprunt> emprunts=new ArrayList<>();
		try {
			emprunts =eDao.getListCurrent();
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return emprunts;
	}

	@Override
	public List<Emprunt> getListCurrentByMembre(int idMembre) throws ServiceException {
		EmpruntDao eDao = EmpruntDaoImpl.getInstance();
		List<Emprunt> emprunts=new ArrayList<>();
		try {
			emprunts =eDao.getListCurrentByMembre(idMembre);
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return emprunts;
	}

	@Override
	public List<Emprunt> getListCurrentByLivre(int idLivre) throws ServiceException {
		EmpruntDao eDao = EmpruntDaoImpl.getInstance();
		List<Emprunt> emprunts=new ArrayList<>();
		try {
			emprunts =eDao.getListCurrentByLivre(idLivre);
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return emprunts;
	}

	@Override
	public Emprunt getById(int id) throws ServiceException {
		EmpruntDao eDao = EmpruntDaoImpl.getInstance();
		Emprunt emprunt=new Emprunt();
		try {
			emprunt =eDao.getById(id);
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return emprunt;
	}

	@Override
	public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws ServiceException {
		EmpruntDao eDao = EmpruntDaoImpl.getInstance();
		try {
			eDao.create(idMembre, idLivre, dateEmprunt);
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
	}

	@Override
	public void returnBook(int id) throws ServiceException {
		EmpruntDao eDao = EmpruntDaoImpl.getInstance();
		LocalDate dateRetour=LocalDate.now();
		try {
			Emprunt emprunt=eDao.getById(id);
			emprunt.setDateRetour(dateRetour);
			eDao.update(emprunt);
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		
		
	}

	@Override
	public int count() throws ServiceException {
		EmpruntDao eDao = EmpruntDaoImpl.getInstance();
		int count=-1;
		try {
			count= eDao.count();
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return count;
	}

	@Override
	public boolean isLivreDispo(int idLivre) throws ServiceException {
		EmpruntDao eDao = EmpruntDaoImpl.getInstance();
		List<Emprunt> emprunts=new ArrayList<>();
		try {
			emprunts = eDao.getListCurrentByLivre(idLivre);
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		if (emprunts.size()==0) return true;
		return false;
	}

	@Override
	public boolean isEmpruntPossible(Membre membre) throws ServiceException {
		EmpruntDao eDao = EmpruntDaoImpl.getInstance();
		List<Emprunt> emprunts=new ArrayList<>();
		try {
			emprunts = eDao.getListCurrentByMembre(membre.getId());
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		int numLivre=emprunts.size();
		if (numLivre>=2 & membre.getAbonnement()==Abonnement.BASIC) return false;
		if (numLivre>=5 & membre.getAbonnement()==Abonnement.PREMIUM) return false;
		if (numLivre>=20 & membre.getAbonnement()==Abonnement.VIP) return false;
		return true;
	}

}
