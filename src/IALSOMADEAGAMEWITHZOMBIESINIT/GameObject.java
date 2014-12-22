package IALSOMADEAGAMEWITHZOMBIESINIT;

// Things that move
public class GameObject extends BasicGameObject {

	// Speed represents how many units the object can move
	// in one step of the game loop
	protected double speed = 0;
	// Orientation is a measure of where the object is facing
	// in degrees. 0 radians is straight right in +X direction
	protected double orientation = 0;

	public GameObject(int x, int y, String path) {
		super(x, y, path);
	}

	/*
	 * Moves the current object in the direction that it is facing. x and y
	 * components for movement are determined from speed.
	 */
	public void moveStep() {
		int x_amount = (int) (speed * Math.cos(orientation));
		int y_amount = (int) (speed * Math.sin(orientation));

		x += x_amount;
		y += y_amount;
	}

	/*
	 * Sets this object's orientation to face the other location marked by its x
	 * and y coordinates.
	 * 
	 * @param x The x coordinate of target location
	 * 
	 * @param y The y coordinate of the target location
	 * 
	 * Note: Decision to use x and y because rather than only passing other
	 * objects I will want to face the mouse location sometimes.
	 */
	public void setOrientationToFace(int other_x, int other_y) {
		int relative_x = other_x - x;
		int relative_y = other_y - y;

		orientation = Math.atan2(relative_y, relative_x);
	}
}
