package IMADEATESTCASEWITHZOMBIESINIT;

import static org.junit.Assert.*;

import org.junit.Test;

import IALSOMADEAGAMEWITHZOMBIESINIT.Player;

public class PlayerTest {

	@Test
	public void test() {
		Player p = new Player(100, 100);
		
		p.moveUp();
		p.moveLeft();
		p.moveDown();
		p.moveRight();
		assertEquals(p.getX(), 100);
		assertEquals(p.getY(), 100);
		
		p.moveUpAndLeft();
		assertEquals(p.getX(), 96);
		assertEquals(p.getY(), 96);
		
		p.moveUpAndRight();
		p.moveDownAndLeft();
		p.moveDownAndRight();
		assertEquals(p.getX(), 100);
		assertEquals(p.getY(), 100);
		
		p.incrementScore(50);
		assertEquals(p.getScore(), 50);
		
	}

}
