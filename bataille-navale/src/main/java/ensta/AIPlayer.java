package ensta;
import ensta.ship.*;

import java.io.Serializable;
import java.util.List;

public class AIPlayer extends Player {
    /* **
     * Attributes
     */
    private BattleShipsAI ai;

    /* **
     * Constructor
     */
    public AIPlayer(Board ownBoard, Board opponentBoard, List<AbstractShip> ships) {
        super(ownBoard, opponentBoard, ships);
        ai = new BattleShipsAI(ownBoard, opponentBoard);
    }
    public void putShips() {
    	ai.putShips(this.ships);
    }
    
    public Hit sendHit(int[] coords) {
    	Hit hit=ai.sendHit(coords);
    	return hit;
    }
    // TODO AIPlayer must not inherit "keyboard behavior" from player. Call ai instead.
}
