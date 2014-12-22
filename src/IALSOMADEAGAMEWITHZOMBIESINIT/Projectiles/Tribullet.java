package IALSOMADEAGAMEWITHZOMBIESINIT.Projectiles;


public class Tribullet extends BasicProjectile {

	final static String tribullet_path = "Images/greenshot.png";
	
	public Tribullet(int x, int y) {
		super(x, y, tribullet_path);
		damage = 3;
		speed = 20;
	}
}
