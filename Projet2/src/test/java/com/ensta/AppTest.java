package com.ensta;

import com.ensta.librarymanager.modele.*;
import com.ensta.librarymanager.modele.Membre.Abonnement;
import com.ensta.librarymanager.dao.*;
import com.ensta.librarymanager.exception.DaoException;

import org.junit.Test;
import java.time.LocalDate;


/**
 * Unit test for simple App.
 */
public class AppTest 
{
    
    @Test
    public void Ex2()
    {
    	
    	LocalDate dateEmprunt=LocalDate.now();
    	LocalDate dateRetour=LocalDate.MAX;
        Livre l1=new Livre(1,"Livre1","Auther1","Isbn1");    
        Membre m1=new Membre(1,"nom1","prenom1","adress1","email1","telephone1",Abonnement.PREMIUM);  
        Emprunt e1=new Emprunt(1,m1,l1,dateEmprunt,dateRetour);
        System.out.println("TEST EX2 :");
        System.out.println(m1.toString());
        System.out.println(l1.toString());
        System.out.println(e1.toString());
    }
    
    @Test
    public void DaoTest() throws DaoException
    {
    	LivreDao lDao=LivreDaoImpl.getInstance();
    	MembreDao mDao=MembreDaoImpl.getInstance();
    	EmpruntDao eDao=EmpruntDaoImpl.getInstance();
    	System.out.println("TEST EX3 :");
    	
    	System.out.println("\ntest getById(3) :");
    	lDao.getById(3);
    	System.out.println("\ntest create :");
    	lDao.create("titre1", "Auther1","Isbn1");
    	System.out.println("\ntest delete :");
    	lDao.delete(12);
    	System.out.println("\ntest count :");
    	System.out.println("Livres count :");
    	lDao.count();
    	System.out.println("\ntest Membre.getList() :");
    	mDao.getList();
    	System.out.println("\nMembres count :");
		mDao.count();
		System.out.println("\ntest Emprunt.getCurrentList() :");
		eDao.getListCurrent();
		System.out.println("\nEmprunts count :");
		eDao.count();
		
    }
}
