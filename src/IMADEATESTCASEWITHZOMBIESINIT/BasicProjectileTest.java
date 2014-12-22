package IMADEATESTCASEWITHZOMBIESINIT;

import static org.junit.Assert.*;

import org.junit.Test;

import IALSOMADEAGAMEWITHZOMBIESINIT.Enemies.BasicZombie;
import IALSOMADEAGAMEWITHZOMBIESINIT.Projectiles.BasicProjectile;

public class BasicProjectileTest {

	@Test
	public void test() {
		// Testing a -45 degree angle
		BasicZombie z = new BasicZombie(156, 156);
		BasicProjectile p = new BasicProjectile(100, 100);
		
		p.setOrientationToFace(z.getX(), z.getY());
		p.moveStep();
		assertEquals(p.getX(), 114);
		assertEquals(p.getY(), 114);
		// Moving as expected so far... let's go further
		for (int i = 0; i < 7; i++)
			p.moveStep();
		
		assertTrue(p.getX() > 114);
		assertTrue(p.getY() > 114);
		
	
		// Testing ~60 degree angle
		BasicZombie z2 = new BasicZombie(100, 100);
		BasicProjectile p2 = new BasicProjectile(90, 83);
		p2.setOrientationToFace(z2.getX(), z2.getY());
		p2.moveStep();
		assertEquals(p2.getX(), 100);
		assertEquals(p2.getY(), 100);
		p.applyTo(z);
		assertEquals(z.isDead(), false);
		p.applyTo(z);
		p.applyTo(z);
		p.applyTo(z);
		assertEquals(z.isDead(), true);
	}

}
