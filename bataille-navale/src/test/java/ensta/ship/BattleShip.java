package ensta.ship;

public class BattleShip extends AbstractShip {
	/**
	 * BattleShip Constructor
	 * @param d char
	 */

	public BattleShip(char d) {
		super("BattleShip",'B',4,d);
    }
	
	/**
	 * BattleShip Constructor with default direction='e'
	 */
	
	public BattleShip() {
		super("BattleShip",'B',4,'e');
    }
}
