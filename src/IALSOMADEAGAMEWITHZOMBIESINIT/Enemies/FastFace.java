package IALSOMADEAGAMEWITHZOMBIESINIT.Enemies;

public class FastFace extends BasicZombie {
		
	final static String fastface_path = "Images/fastface.png";
	
	protected int children_to_make = 8;

	protected int[] child_x_arr = new int[5];
	protected int[] child_y_arr = new int[5];
	protected int step_counter = 5;
	protected FastFace child = null;
	
	public FastFace(int x, int y) {
		super(x, y, fastface_path);
		hp = 1;
		points = 2;
		speed = 5;
		
		for (int i = 0; i < 5; i++){
			child_x_arr[i] = x;
			child_y_arr[i] = y;
		}
	}
	
	protected FastFace(int x, int y, String path) {
		super(x, y, path);
	}
	
	public void setChild(FastFace child) {
		this.child = child;
	}
	public int getChildrenToMake() {
		return children_to_make;
	}
	public FastFace getChild() {
		return child;
	}

	@Override
	public void setOrientationToFace(int other_x, int other_y){
		// Nothing.
		return;
	};
	
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
	
	public void moveStep(){
		// Decide if we are going to change directions or keep going.
		double decider = Math.random();
		// ... It shouldn't / doesn't happen often
		if (decider > 0.96)
			orientation += Math.PI / 2;
		else if (decider > 0.92)
			orientation -= Math.PI / 2;
		
		
		child_x_arr[step_counter % 5] = x;
		child_y_arr[step_counter % 5] = y;
		if (child != null) {
			child.setX(child_x_arr[(step_counter - 3) % 5]);
			child.setY(child_y_arr[(step_counter - 3) % 5]);
		}
		step_counter++;
		super.moveStep();
	}
	
	public void killChild() {
		if (child != null)
			child.kill();
	}
	
}
