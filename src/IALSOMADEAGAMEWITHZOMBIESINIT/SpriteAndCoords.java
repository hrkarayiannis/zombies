package IALSOMADEAGAMEWITHZOMBIESINIT;

public class SpriteAndCoords {
	
	private int x;
	private int y;
	private Sprite sprite;

	public SpriteAndCoords(Sprite sprite, int x, int y) {
		this.sprite = sprite;
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Sprite getSprite() {
		return sprite;
	}

}
