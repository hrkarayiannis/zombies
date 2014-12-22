package IMADEATESTCASEWITHZOMBIESINIT;

import static org.junit.Assert.*;

import org.junit.Test;

import IALSOMADEAGAMEWITHZOMBIESINIT.Player;
import IALSOMADEAGAMEWITHZOMBIESINIT.Powerups.Flamethrower;
import IALSOMADEAGAMEWITHZOMBIESINIT.Powerups.Rapidfire;
import IALSOMADEAGAMEWITHZOMBIESINIT.Powerups.SpeedBoost;
import IALSOMADEAGAMEWITHZOMBIESINIT.Powerups.Trishot;

public class PowerupTest {

	@Test
	public void test() {
		Player p = new Player(100, 100);
		
		SpeedBoost sb = new SpeedBoost(50, 50);
		Rapidfire rf = new Rapidfire(60, 60);
		Trishot t = new Trishot(70, 70);
		Flamethrower f = new Flamethrower (80, 80);
		
		sb.applyTo(p);
		assertEquals(p.getSpeedTimer(), 600);
		
		// Check that starting ammo type is correct before powerups
		assertEquals(p.getAmmoType(), Player.DEFAULT);
		
		// Check that rapidfire applies correctly
		rf.applyTo(p);
		assertEquals(p.getAmmoType(), Player.RAPIDFIRE);
		assertEquals(p.getAmmoCount(), 150);
		// Check that special ammo stacks correctly
		rf.applyTo(p);
		assertEquals(p.getAmmoType(), Player.RAPIDFIRE);
		assertEquals(p.getAmmoCount(), 300);
		
		// Check that trishot applies correctly
		t.applyTo(p);
		assertEquals(p.getAmmoType(), Player.TRISHOT);
		assertEquals(p.getAmmoCount(), 75);
		// Check that special ammo stacks correctly
		t.applyTo(p);
		assertEquals(p.getAmmoType(), Player.TRISHOT);
		assertEquals(p.getAmmoCount(), 150);
		
		// Check that special ammo stacks correctly
		f.applyTo(p);
		assertEquals(p.getAmmoType(), Player.FLAME);
		assertEquals(p.getAmmoCount(), 150);
		
	}
		

}
