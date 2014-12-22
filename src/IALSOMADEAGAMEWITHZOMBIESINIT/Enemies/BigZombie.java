package IALSOMADEAGAMEWITHZOMBIESINIT.Enemies;

import IALSOMADEAGAMEWITHZOMBIESINIT.Projectiles.BasicProjectile;

public class BigZombie extends BasicZombie {
	final static String bigzombie_path = "Images/bigZombie.png";
	
	public BigZombie(int x, int y) {
		super(x, y, bigzombie_path);
		hp = 5;
		points = 2;
		speed = 2;
	}
	
	/*
	 * (non-Javadoc)
	 * @see IALSOMADEAGAMEWITHZOMBIESINIT.Enemies.BasicZombie#hitBy(IALSOMADEAGAMEWITHZOMBIESINIT.Projectiles.BasicProjectile)
	 */
	@Override
	public void hitBy(BasicProjectile p) {
		super.hitBy(p);
		setOrientationToFace(p.getX(), p.getY());
		orientation += Math.PI;
		for (int i = 0; i < 8; i++)
			moveStep();
	}
	
	
}
