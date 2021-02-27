package ensta;

import ensta.ship.*;

public class Board implements IBoard{
	
	/* **
     * Attributes
     */
	public String name;
	public ShipState[][] ships;
	public Boolean[][] hits;
	
	
	
	/**
	 * Board Constructor
	 * @param name String
	 * @param size int
	 */
	public Board(String name, int size) {
		
    	this.name=name;
    	this.hits=new Boolean[size][size];
    	this.ships=new ShipState[size][size];
        for (int i=0;i<size;i++)
        	for (int j=0;j<size;j++)
        	{
        		this.hits[i][j]=null;
            	this.ships[i][j]=new ShipState();
            	this.ships[i][j].ship=null;
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
    public int getSize() {return this.hits.length;}

    /**
    * Put the given ship at the given position
    * @param ship The ship to place on the board
    * @param x 
    * @param y
    */
    public void putShip(AbstractShip ship, int x, int y) {
    	
    	int shipSize=ship.getLength();
    	int gridSize=this.getSize();
    	//char label=ship.getLabel();
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
    		this.ships[x_pre][y_pre].ship=ship;
    	}
    }

    /**
     * Get if a ship is placed at the given position
     * @param x
     * @param y
     * @return true if a ship is located at the given position
     */
    public boolean hasShip(int x, int y) {
    	return (this.ships[x][y].ship!=null); 	
    }

    /**
     * Set the state of the hit at a given position
     * @param hit true if the hit must be set to successful
     * @param x
     * @param y
     */
    public void setHit(boolean hit, int x, int y) {
    	if (this.getHit(x, y)!=null) 
		{
			throw new IllegalArgumentException("Cette position est deja touche!");
		}
		this.hits[x][y]=hit;
    }

    /**
     * Get the state of a hit at the given position
     * @param x
     * @param y
     * @return true if the hit is successful
     */
    public Boolean getHit(int x, int y) {
    	return (this.hits[x][y]);
    }
    
    /**
     * Sends a hit at the given position
     * @param x
     * @param y
     * @return status for the hit (eg : strike or miss)
     */
    public Hit sendHit(int x, int y) {
    	if (this.hasShip(x, y)) {
    		this.ships[x][y].ship.addSrike();
    		
    		if (!this.ships[x][y].ship.isSunk()) return Hit.STIKE;
    		//case if a ship is Sunk
    		switch (this.ships[x][y].ship.getLabel()) {
    		case 'D':
    			return Hit.DESTROYER;
    		case 'S':
    			return Hit.SUBMARINE;
    		case 'B':
    			return Hit.BATTLESHIP;
    		case 'C':
    			return Hit.CARRIER;
    		}
    	}
    	return Hit.MISS;
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
			for (int j=0;j<taille;j++) {
				if (this.ships[i][j].ship==null) s+=" .";
				else s+=" "+this.ships[i][j].toString();
			}
				
			s+="       "+(i+1);
			if (i<9) s+=" ";
			for (int j=0;j<taille;j++) {
				if(this.hits[i][j]==null) s +=" "+".";
				else {
					if(this.hits[i][j]==true) s +=" "+ColorUtil.colorize("X",ColorUtil.Color.RED);
					if(this.hits[i][j]==false) s +=" "+ColorUtil.colorize("X",ColorUtil.Color.WHITE);
			
				}
			}
			System.out.println(s);
		}	
	}
}
