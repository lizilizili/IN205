package ensta.ship;

public class AircraftCarrier extends AbstractShip {
	/**
	 * AircraftCarrier Constructor
	 * @param d char
	 */

	public AircraftCarrier(char d) {
		super("AircraftCarrier",'A',5,d);
    }
	
	/**
	 * AircraftCarrier Constructor with default direction='e'
	 */
	
	public AircraftCarrier() {
		super("AircraftCarrier",'A',5,'e');
    }
}
