package IALSOMADEAGAMEWITHZOMBIESINIT.Projectiles;

import IALSOMADEAGAMEWITHZOMBIESINIT.GameObject;
import IALSOMADEAGAMEWITHZOMBIESINIT.Enemies.BasicZombie;

public class BasicProjectile extends GameObject {

	final static String projectile_path = "Images/projectile.png";
	
	// The amount of damage that this projectile will deal to zombies!
	protected int damage = 1;
	protected int uses = 1;
	
	// Creates a regular basicprojectile
	public BasicProjectile(int x, int y) {
		super(x, y, projectile_path);
		speed = 20;
	}
	
	// To be used by subclasses
	protected BasicProjectile(int x, int y, String path) {
		super(x, y, path);
	}

	public int getDamage() {
		return damage;
	}

	public void incrementOrientation(double d) {
		orientation += d;
	}
	
	public void applyTo(BasicZombie zombie) {
		zombie.hitBy(this);
		uses--;
	}
	
	public void validateBounds(int lower_x, int lower_y, int x_bound, int y_bound) {
		if (x < lower_y || y < lower_y)
			uses = 0;
		if (x > x_bound || y > y_bound)
			uses = 0;
	}
	

	public boolean isRemovable() {
		return uses <= 0;
	}
	
}