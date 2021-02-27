package ensta;

import ensta.ship.*;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestBoard {
	@Test
	public void tests() {
		/*
		 * Create a board "player1"
		 */
		Board board=new Board("player1",10);
		System.out.println("\n Exercice 1: Print board \n");
		board.print();
		
		/*
		 * Create two ships
		 */
		Submarine  s1=new Submarine(Directions.WEST);
		Submarine  s2=new Submarine(Directions.NORTH);
		AircraftCarrier  a1=new AircraftCarrier(Directions.SOUTH);
		
		/*
		 * Try to put a Submarine
		 */
		System.out.println("\n Exercice 3: Placement des navires \n");
		System.out.println("Essayer de mettre une submarine a F7, avec une oritation WEST");
		try {
			board.putShip(s1, 6, 5);
			board.print();
			//board.putShip(s2, 8, 8);
		}catch (Exception e) {
			System.out.println(e);
		}
		
		/*
		 * Try to put an aircraft-carrier
		 */
		System.out.println("\nEssayer de mettre un aircraft-carrier a F7, avec une oritation EAST");
		try {
			board.putShip(a1, 5, 4);
			board.print();
		}catch (Exception e) {
			System.out.println(e);
		}
		
		
		/*
		 * Try to set a hit 
		 */
		System.out.println("\n Exercice 5: Etat des frappes \n");
		System.out.println("Essayer de Mettre un hit reussi en A6");
		try {
			board.setHit(true, 5, 0);
			board.print();
		}catch (Exception e) {
			System.out.println(e);
		}
		
		System.out.println("\n Exercice 6: Envoyer des frappes \n");
		System.out.println("Essayer de Frapper le submarine");
		try {
			Hit hit=board.sendHit(6,4);
			System.out.println("Frappe E7 :"+hit.toString());
			hit=board.sendHit(6,5);
			System.out.println("Frappe F7 :"+hit.toString());
			hit=board.sendHit(6,3);
			System.out.println("Frappe D7 :"+hit.toString());
			hit=board.sendHit(5,3);
			System.out.println("Frappe D6 :"+hit.toString());
			
		}catch (Exception e) {
			System.out.println(e);
		}
	}
	
}
