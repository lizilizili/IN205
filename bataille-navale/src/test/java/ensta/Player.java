package ensta;

import ensta.ship.*;

import java.io.Serializable;
import java.util.List;


public class Player {
	
	    /* **
	     * Attributes
	     */
	    protected Board board;
	    protected Board opponentBoard;
	    protected int destroyedCount;
	    protected AbstractShip[] ships;
	    protected boolean lose;

	    /* **
	     * Constructor
	     */
	    public Player(Board board, Board opponentBoard, List<AbstractShip> ships) {
	        this.board = board;
	        this.ships = ships.toArray(new AbstractShip[0]);
	        this.opponentBoard = opponentBoard;
	    }

	    /* **
	     * Methods
	     */

	    /**
	     * Read keyboard input to get ships coordinates. Place ships on given coodrinates.
	     */
	    public void putShips() {
	        boolean done = false;
	        int i = 0;

	        do {
	            AbstractShip s = ships[i];
	            String msg = String.format("placer %d : %s(%d)", i + 1, s.getName(), s.getLength());
	            System.out.println(msg);
	            InputHelper.ShipInput res = InputHelper.readShipInput();
	            switch (res.orientation) {
	            case "n":
	            	s.setDirection(Directions.NORTH);
	            	break;
	            case "s":
	            	s.setDirection(Directions.SOUTH);
	            	break;
	            case "w":
	            	s.setDirection(Directions.WEST);
	            	break;
	            case "e":
	            	s.setDirection(Directions.EAST);
	            	break;
	            }
	            
	            
	            try {
	        		board.putShip(s, res.y, res.x);
	        		++i;	
	        	}catch (Exception e) {
	        		System.out.println(e);
	        	}
	            
	            		
	            
	            	
	            // TODO set ship orientation
	            // TODO put ship at given position
	            // TODO when ship placement successful
	            
	            done = i == 5;

	            board.print();
	        } while (!done);
	    }

	    public Hit sendHit(int[] coords) {
	        boolean done=false;
	        Hit hit = null;

	        do {
	            System.out.println("ou frapper?");
	            InputHelper.CoordInput hitInput = InputHelper.readCoordInput();
	            // TODO call sendHit on this.opponentBoard

	            // TODO : Game expects sendHit to return BOTH hit result & hit coords.
	            // return hit is obvious. But how to return coords at the same time ?
	        } while (!done);

	        return hit;
	    }

	    public AbstractShip[] getShips() {
	        return ships;
	    }

	    public void setShips(AbstractShip[] ships) {
	        this.ships = ships;
	    }
	
}
