package com.ensta.librarymanager.modele;

import com.ensta.librarymanager.modele.Membre.Abonnement;

public class Membre {
	
	int id;

    String nom;
    
    String prenom;
    
    String adresse;

    String email;
    
    String telephone;
    
    Abonnement abonnement;
    
    public enum Abonnement{BASIC,PREMIUM,VIP};

    @Override
    public String toString() {
        return "Membre [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse +", email=" + email +
        		", telephone=" + telephone +", abonnement=" + abonnement.toString() +"]";
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getTel() {
        return prenom;
    }

    public void setTel(String tel) {
        this.telephone = tel;
    }
    
    
    public Abonnement getAbonnement() {
        return abonnement;
    }

    public void setAbonnement(Abonnement abonnement) {
        this.abonnement = abonnement;
    }
    

    public Membre(int id, String nom, String prenom, String adresse,String email,String telephone, Abonnement abonnement) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this.telephone = telephone;
        this.abonnement = abonnement;
        
    }

	public Membre() {
		// TODO Auto-generated constructor stub
	}
	
}
	
