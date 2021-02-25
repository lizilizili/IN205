package ensta;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestBoard {
	@Test
	public void showBoard() {
		Board player1=new Board("player1");
		player1.print();
	}
}
