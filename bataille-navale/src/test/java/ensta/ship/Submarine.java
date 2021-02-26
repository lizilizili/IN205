package ensta.ship;

public class Submarine extends AbstractShip {
	/**
	 * Submarine Constructor
	 * @param d char
	 */

	public Submarine(char d) {
		super("Submarine",'S',3,d);
    }
	
	/**
	 * Submarine Constructor with default direction='e'
	 */
	
	public Submarine() {
		super("Submarine",'S',3,'e');
    }
}
