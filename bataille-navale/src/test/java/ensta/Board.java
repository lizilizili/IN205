package ensta;

import ensta.ship.AbstractShip;

public class Board implements IBoard{
	
	/* **
     * Attributes
     */
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
	
	/* **
     * Methods
     */
	
	
	/**
     * Get the size of the grids contained in the Board
     * @return the size of the grids contained in the Board
     */
    public int getSize() {return this.frappes.length;}

    /**
    * Put the given ship at the given position
    * @param ship The ship to place on the board
    * @param x 
    * @param y
    */
    public void putShip(AbstractShip ship, int x, int y) {
    	
    	int shipSize=ship.getSize();
    	int gridSize=this.getSize();
    	char label=ship.getLabel();
    	int [] direction=ship.dir2vertor();
    	for (int i=0;i<shipSize;i++)
    	{
    		int x_pre=x+direction[0]*i;
    		int y_pre=y+direction[1]*i;
    		//System.out.println(x_pre,y_pre);
    		if (x_pre<0 || x_pre >= gridSize || y_pre<0 || y_pre >= gridSize) 
    		{
    			throw new IllegalArgumentException("Le " + ship.getName()+ " sort de la grille! Essayez une nouvelle position!");
    		}
    		if (hasShip(x_pre,y_pre)) 
    		{
    			throw new IllegalArgumentException("Le " + ship.getName()+ " chevauche un autre navire! Essayez une nouvelle position!");
    		}
    		
    	}
    	for (int i=0;i<shipSize;i++)
    	{
    		int x_pre=x+direction[0]*i;
    		int y_pre=y+direction[1]*i;
    		this.navires[x_pre][y_pre]=label;
    	}
    }

    /**
     * Get if a ship is placed at the given position
     * @param x
     * @param y
     * @return true if a ship is located at the given position
     */
    public boolean hasShip(int x, int y) {
    	return (this.navires[x][y]!='.'); 	
    }

    /**
     * Set the state of the hit at a given position
     * @param hit true if the hit must be set to successful
     * @param x
     * @param y
     */
    public void setHit(boolean hit, int x, int y) {
    	if (this.getHit(x, y)) this.navires[x][y]='x';
    }

    /**
     * Get the state of a hit at the given position
     * @param x
     * @param y
     * @return true if the hit is successful
     */
    public Boolean getHit(int x, int y) {
    	return (this.navires[x][y]!= '.' && this.navires[x][y]!= 'x');
    }
	/**
	 * Print Board
	 */
	void print() {
		int taille=this.getSize();
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
