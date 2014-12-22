package IALSOMADEAGAMEWITHZOMBIESINIT.Enemies;

import java.util.Random;

public class BigSlime extends BasicZombie {
	final static String bigslime_path = "Images/bigslime.png";
	
	// The amount of child slimes that should be spawned when this dies
	private int children_to_make = 4;
	
	public BigSlime(int x, int y) {
		super(x, y, bigslime_path);
		hp = 4;
		points = 0;
		speed = 2;
		
	}

	// Should be used for subclasses
	protected BigSlime(int x, int y, String path) {
		super(x, y, path);
	}
	
	public int getChildrenToMake() {
		return children_to_make;
	}
	
	/*
	 * Generate a nearby location for a child to be made at. The dimensions
	 * of this sprite are used so that the child spawns in a location that 
	 * makes sense.
	 * 
	 *  @return A coordinate location for child to be set to
	 */
	public int generateChildX() {
		Random rand = new Random();
		return rand.nextInt(getSpriteWidth()) - getSpriteWidth() / 2 + x;
	}
	/* See above */
	public int generateChildY() {
		Random rand = new Random();
		return rand.nextInt(getSpriteHeight()) - getSpriteHeight() / 2 + y;
	}
}
