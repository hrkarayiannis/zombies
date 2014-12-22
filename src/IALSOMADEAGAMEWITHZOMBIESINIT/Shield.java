package IALSOMADEAGAMEWITHZOMBIESINIT;

public class Shield extends BasicGameObject {
	final static String shield_path = "Images/shield.png";
	
	public Shield(int x, int y) {
		super(x, y, shield_path);
		// TODO Auto-generated constructor stub
	}
	
	public void lockMoveTo(int x, int y){
		this.x = x;
		this.y = y;
	}

	public void setCoordsTo(int x, int y) {
		this.x = x;
		this.y = y;
	}

}
