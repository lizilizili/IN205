package ensta.ship;

public class BattleShip extends AbstractShip {
	/**
	 * BattleShip Constructor
	 * @param d Directions
	 */

	public BattleShip(Directions d) {
		super("BattleShip",'B',4,d);
    }
	
	/**
	 * BattleShip Constructor with default direction=EAST
	 */
	
	public BattleShip() {
		super("BattleShip",'B',4,Directions.EAST);
    }
}
