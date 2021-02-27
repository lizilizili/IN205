package ensta.ship;

import ensta.*;
public class ShipState {
	
	/* **
     * Attributes
     */
	public AbstractShip ship;
	public boolean struck=false;
	
	
	/* **
     * Methods
     */
	
	/**
	 * Add strike to this state  
	 */
	public void addStrike() {
		if (this.isStruck()) 
		{
			throw new IllegalArgumentException("Cette position est deja touche!");
		}
		this.struck=true;
	}
	
	/**
	 *  Get if this position is struck
	 *  @return true if this position is struck
	 */
	public boolean isStruck() {return this.struck;}
	
	/**
	 *  Return the label of this ship
	 *  @return true if this position is struck
	 */
	public String toString() {
		String s=""+this.ship.getLabel();
		if (this.isStruck()) {
			return (ColorUtil.colorize(s,ColorUtil.Color.RED)); 
		}
		else return(s);	
	}
	
	/**
	 *  Get if the ship is sunk
	 *  @return true if the ship is sunk
	 */
	public boolean isSunk() {return (this.ship.isSunk());}
	
	/**
	 *  Return the ship
	 *  @return ship
	 */
	 public AbstractShip getShip() {return(this.ship);}
}
