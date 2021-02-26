package ensta;

public class Board {
	
	public String name;
	public char[][] navires;
	public boolean[][] frappes;
	
	
	/**
	 * Board Constructor
	 * @param name String
	 * @param size int
	 */
	public Board(String name, int size) {
		
    	this.name=name;
    	this.frappes=new boolean[size][size];
    	this.navires=new char[size][size];
        for (int i=0;i<size;i++)
        	for (int j=0;j<size;j++)
        	{
        		this.frappes[i][j]=false;
            	this.navires[i][j]='.';
        	}
    }
	
	/**
	 * Board Constructor with default size=10
	 * @param name String
	 */
	public Board(String nom) {	
    	this(nom,10);
    }
	
	/**
	 * Print Board
	 */
	void print() {
		int taille=this.frappes.length;
		String s="";
		for (int i=0;i<taille;i++) s+="  ";
		System.out.println("Navires :"+s+"Frappes :");
		s="  ";
		
		for (int i=0;i<taille;i++) {
			int ascii=65+i; //  ASCII of 'A' is 65
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
