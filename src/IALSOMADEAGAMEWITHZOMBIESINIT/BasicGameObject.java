package IALSOMADEAGAMEWITHZOMBIESINIT;

public abstract class BasicGameObject {

	// The object's x location
	protected int x;
	// The object's y location
	protected int y;

	// Image representation of this object
	protected Sprite sprite;

	public BasicGameObject(int x, int y, String path) {
		this.x = x;
		this.y = y;
		this.sprite = SpriteStore.get().getSprite(path);
	}

	/* Getters, setters, simple methods ... */
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	public int getSpriteWidth() {
		return sprite.getWidth();
	}
	public int getSpriteHeight() {
		return sprite.getHeight();
	}
	
	// Make sure that the object is within a certain boundary
	public void validateBounds(int lower_x, int lower_y, int x_bound, int y_bound) {
		if (x < lower_x)
			x = lower_x;
		if (y < lower_y)
			y = lower_y;
		if (x > x_bound)
			x = x_bound;
		if (y > y_bound)
			y = y_bound;
	}
	
}
