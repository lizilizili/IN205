package ensta;

import com.ensta.librarymanager.model.*;
import com.ensta.librarymanager.model.Membre.Abonnement;

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
}
