package ensta.ship;

public class AircraftCarrier extends AbstractShip {
	/**
	 * AircraftCarrier Constructor
	 * @param d Directions
	 */

	public AircraftCarrier(Directions d) {
		super("AircraftCarrier",'A',5,d);
    }
	
	/**
	 * AircraftCarrier Constructor with default direction=EAST
	 */
	
	public AircraftCarrier() {
		super("AircraftCarrier",'A',5,Directions.EAST);
    }
}
