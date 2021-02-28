package ensta.ship;

public class Destroyer extends AbstractShip {
	/**
	 * Destroyer Constructor
	 * @param d Directions
	 */

	public Destroyer(Directions d) {
		super("Destroyer",'D',2,d);
    }
	
	/**
	 * Destroyer Constructor with default direction=EAST
	 */
	
	public Destroyer() {
		super("Destroyer",'D',2,Directions.EAST);
    }
}
