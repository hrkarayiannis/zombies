package IALSOMADEAGAMEWITHZOMBIESINIT.Projectiles;


public class Rapidshot extends BasicProjectile {

	final static String rapidshot_path = "Images/rapidshot.png";
	
	public Rapidshot(int x, int y) {
		super(x, y, rapidshot_path);
		damage = 2;
		speed = 25;
	}

}
