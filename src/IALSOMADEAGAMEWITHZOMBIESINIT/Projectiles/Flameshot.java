package IALSOMADEAGAMEWITHZOMBIESINIT.Projectiles;

public class Flameshot extends BasicProjectile{

	final static String flameshot_path = "Images/flame.png";
	
	public Flameshot(int x, int y) {
		super(x, y, flameshot_path);
		damage = 5;
		speed = (int) (Math.random() * 13) + 7;
		// Effectively makes this a short range projectile
		uses = 20;
	}
	
	@Override
	public void moveStep() {
		super.moveStep();
		uses--;
	}
}
