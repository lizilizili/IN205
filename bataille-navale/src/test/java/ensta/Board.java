package ensta;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class Board {
	
	public String nom;
	public char[][] navires;
	public boolean[][] frappes;
	
	public Board(String nom, int taille) {
		
    	this.nom=nom;
    	this.frappes=new boolean[taille][taille];
    	this.navires=new char[taille][taille];
        for (int i=0;i<taille;i++)
        	for (int j=0;j<taille;j++)
        	{
        		this.frappes[i][j]=false;
            	this.navires[i][j]='.';
        	}
    }
	
	public Board(String nom) {	
    	this(nom,10);
    }
	
	void print() {
		int taille=this.frappes.length;
		String s="";
		for (int i=0;i<taille;i++) s+="  ";
		System.out.println("Navires :"+s+"Frappes :");
		s="  ";
		
		for (int i=0;i<taille;i++) {
			int ascii=65+i;
			s+=" "+ (char)ascii ;
		}
		s+="       "+s;
		System.out.println(s);
		for (int i=0;i<taille;i++) {
			s=""+(i+1);
			if (i<9) s+=" ";
			for (int j=0;j<taille;j++) s+=" "+this.navires[i][j];
			s+="       "+(i+1);
			if (i<9) s+=" ";
			for (int j=0;j<taille;j++) {
				if (this.frappes[i][j]) s +=" "+"x";
				else  s +=" "+".";
			}
			System.out.println(s);
		}	
	}
}
