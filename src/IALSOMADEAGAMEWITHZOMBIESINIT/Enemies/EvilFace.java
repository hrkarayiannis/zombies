package IALSOMADEAGAMEWITHZOMBIESINIT.Enemies;

public class EvilFace extends BasicZombie {
	final static String evilface_path = "Images/evilface.png";
	
	public EvilFace(int x, int y) {
		super(x, y, evilface_path);
		hp = 2;
		points = 2;
		speed = 2;
	}
	
	// For use by subclasses.
	protected EvilFace(int x, int y, String path) {
		super(x, y, path);
	}
	
	@Override
	public void setOrientationToFace(int other_x, int other_y){
		// Nothing.
		return;
	};
	
	/*
	 * (non-Javadoc)
	 * @see IALSOMADEAGAMEWITHZOMBIESINIT.GameObject#moveStep()
	 */
	public void moveStep(){
		// Decide if we are going to change directions or keep going.
		double decider = Math.random();
		if (decider > 0.85)
			orientation += Math.PI / 2;
		else if (decider > 0.70)
			orientation -= Math.PI / 2;
		super.moveStep();
	}
	
	@Override
	/*
	 * (non-Javadoc)
	 * @see IALSOMADEAGAMEWITHZOMBIESINIT.BasicGameObject#validateBounds(int, int, int, int)
	 * If this is out of bounds then turn it around to move it back in.
	 */
	public void validateBounds(int lower_x, int lower_y, int x_bound, int y_bound) {
		if (x < lower_x || y < lower_y || x > x_bound || y > y_bound)
			orientation += Math.PI;
		}

}
