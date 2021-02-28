package ensta;

import ensta.ship.*;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class TestGame {
	@Test
	public void test() {
		/*
		 * Create a board "player1"
		 */
		Board board1=new Board("player1");
		//Board board2=new Board("player2");
		/*
		 * Create list of ships
		 */
		Destroyer d1=new Destroyer();
		Submarine s1=new Submarine();
		Submarine s2=new Submarine();
		BattleShip b1=new BattleShip();
		AircraftCarrier c1=new AircraftCarrier();
		List<AbstractShip> ships = new LinkedList<>();
		ships.add(d1);
		ships.add(s1);
		ships.add(s2);
		ships.add(b1);
		ships.add(c1);
		
		/*
		 * Initialize AI
		 */
		BattleShipsAI ai = new BattleShipsAI (board1,board1);
		ai.putShips(ships);
		System.out.println("Board initiale");
		board1.print();
		int countShip=0,count=0;
		int[] coord= {0,0};
		/*
		 * Game end if all ships sunk
		 */
		while (countShip < 5) {
			count++;
			Hit hit=ai.sendHit(coord);
			char coord1 =(char) (coord[1] + 65);
			int coord0=coord[0]+1;
			System.out.println( "\nFrappe NO."+ count +" en " +coord1 + coord0+" : "+ hit.toString());
			if (hit!=Hit.MISS && hit!=Hit.STIKE) countShip++;
			board1.print();
			sleep(2000);
			
		}
		System.out.println("GAME OVER!");
		
	
	}
	/** *
	 * Sleep of thread
	 * @param ms duration(ms)
	 */
	private static void sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
