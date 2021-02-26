package ensta;

import ensta.ship.*;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestBoard {
	@Test
	public void showBoard() {
		/*
		 * Create a board "player1"
		 */
		Board player1=new Board("player1",10);
		player1.print();
		
		/*
		 * Create two ships
		 */
		Submarine  s1=new Submarine(Directions.WEST);
		AircraftCarrier  a1=new AircraftCarrier(Directions.EAST);
		
		/*
		 * Try to put a Submarine
		 */
		System.out.println("Essayer de mettre une submarine a F7, avec une oritation WEST");
		try {
			player1.putShip(s1, 6, 5);
			
		}catch (Exception e) {
			System.out.println(e);
		}
		player1.print();
		/*
		 * Try to put an aircraft-carrier
		 */
		System.out.println("Essayer de mettre un aircraft-carrier a F7, avec une oritation EAST");
		try {
			player1.putShip(a1, 6, 5);
			
		}catch (Exception e) {
			System.out.println(e);
		}
		
		player1.print();
	}
}
