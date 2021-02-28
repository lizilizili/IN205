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
	    protected List<AbstractShip> ships;
	    protected boolean lose;

	    /* **
	     * Constructor
	     */
	    public Player(Board board, Board opponentBoard, List<AbstractShip> ships) {
	        this.board = board;
	        this.ships = ships;
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
	            AbstractShip s = ships.get(i);
	            String msg = String.format("placer %d : %s(%d)", i + 1, s.getName(), s.getLength());
	            System.out.println(msg);
	            InputHelper.ShipInput res = InputHelper.readShipInput();
	            
	         // set ship orientation
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
	            
	            // put ship at given position	            
	            try {
	        		board.putShip(s, res.y, res.x);
	        		// when ship placement successful
	        		++i;	
	        	}catch (Exception e) {
	        		System.out.println(e);
	        	}
	            	            
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
	            // call sendHit on this.opponentBoard
	            try {
	            	hit=this.opponentBoard.sendHit(hitInput.y, hitInput.x);
	            	coords[0]=hitInput.y;
	            	coords[1]=hitInput.x;
	            	done=true;
	            }catch (Exception e) {
	    			System.out.println(e);
	    		}
	            // TODO : Game expects sendHit to return BOTH hit result & hit coords.
	            // return hit is obvious. But how to return coords at the same time ?
	        } while (!done);
	        
	        
            board.setHit(hit != Hit.MISS, coords[0], coords[1]);
	        return hit;
	    }

	    public List<AbstractShip> getShips() {
	        return ships;
	    }

	    public void setShips(List<AbstractShip> ships) {
	        this.ships = ships;
	    }
	
}
