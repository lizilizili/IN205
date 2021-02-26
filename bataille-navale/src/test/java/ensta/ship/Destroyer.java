package ensta.ship;

public class Destroyer extends AbstractShip {
	/**
	 * Destroyer Constructor
	 * @param d char
	 */

	public Destroyer(char d) {
		super("Destroyer",'D',2,d);
    }
	
	/**
	 * Destroyer Constructor with default direction='e'
	 */
	
	public Destroyer() {
		super("Destroyer",'D',2,'e');
    }
}
