package ensta.ship;

public class Submarine extends AbstractShip {
	/**
	 * Submarine Constructor
	 * @param d Directions
	 */

	public Submarine(Directions d) {
		super("Submarine",'S',3,d);
    }
	
	/**
	 * Submarine Constructor with default direction=EAST
	 */
	
	public Submarine() {
		super("Submarine",'S',3,Directions.EAST);
    }
}
