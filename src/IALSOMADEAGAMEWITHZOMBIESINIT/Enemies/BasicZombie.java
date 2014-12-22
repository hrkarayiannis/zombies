package IALSOMADEAGAMEWITHZOMBIESINIT.Enemies;

import IALSOMADEAGAMEWITHZOMBIESINIT.GameObject;
import IALSOMADEAGAMEWITHZOMBIESINIT.Projectiles.BasicProjectile;

public class BasicZombie extends GameObject {
	final static String basiczombie_path = "Images/basicZombie.png";
	
	// The amount of damage that this zombie can withstand before perishing!
	protected int hp;
	// The score that is earned when this zombie is defeated!
	protected int points;

	// Constructor for default basiczombie
	public BasicZombie(int x, int y) {
		super(x, y, basiczombie_path);
		this.hp = 3;
		this.points = 1;
		speed = 3;
	}
	// Should be used for subclasses
	protected BasicZombie(int x, int y, String path) {
		super(x, y, path);
	}

	public int getPoints() {
		return points;
	}
	public void kill() {
		hp = -5;
	}

	public boolean isDead(){
		return hp <= 0;
	}
	/*
	 * Applies damage and other appropriate effects when 
	 * hit by a projectile
	 */	
	public void hitBy(BasicProjectile p){
		int damage = p.getDamage();
		hp -= damage;
	}

	/*
	 * If the player dies we can walk away from the body like a boss.
	 */
	public void reverseOrientation() {
		orientation = orientation + Math.PI;
	}

}
