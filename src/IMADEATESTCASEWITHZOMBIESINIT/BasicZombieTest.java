package IMADEATESTCASEWITHZOMBIESINIT;

import static org.junit.Assert.*;

import org.junit.Test;

import IALSOMADEAGAMEWITHZOMBIESINIT.Player;
import IALSOMADEAGAMEWITHZOMBIESINIT.Enemies.BasicZombie;

public class BasicZombieTest {

	@Test
	public void test() {
		Player p = new Player(100, 100);
		
		// Testing a -45 degree angle
		BasicZombie z = new BasicZombie(156, 156);
		
		z.setOrientationToFace(p.getX(), p.getY());
		z.moveStep();
		assertEquals(z.getX(), 154);
		assertEquals(z.getY(), 154);
		// Moving as expected so far... let's go further
		for (int i = 0; i < 7; i++)
			z.moveStep();
	
		assertTrue(z.getX() < 154);
		assertTrue(z.getY() < 154);
		
		// We got him! Walk back away.
		z.reverseOrientation();
		
		for (int i = 0; i < 8; i++)
			z.moveStep();
		
		// Back at the same place after same number of steps?
		assertEquals(z.getX(), 156);
		assertEquals(z.getY(), 156);
		
		// Testing ~60 degree angle
		BasicZombie z2 = new BasicZombie(90, 83);
		z2.setOrientationToFace(p.getX(), p.getY());
		z2.moveStep();
		assertEquals(z2.getX(), 91);
		assertEquals(z2.getY(), 85);
		
		z.kill();
		assertEquals(z.isDead(), true);
		
		p.incrementScore(z.getPoints());
		
		assertEquals(p.getScore(), 1);
		
	}

}
