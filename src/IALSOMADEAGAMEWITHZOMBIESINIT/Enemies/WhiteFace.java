package IALSOMADEAGAMEWITHZOMBIESINIT.Enemies;

public class WhiteFace extends FastFace {

	final static String whiteface_path = "Images/whiteface.png";
	
	public WhiteFace(int x, int y, int children_to_make) {
		super(x, y, whiteface_path);
		this.children_to_make = children_to_make;
		
		hp = 1;
		speed = 5;
		step_counter = 5;
		for (int i = 0; i < 5; i++){
			child_x_arr[i] = x;
			child_y_arr[i] = y;
		}
	}
	
	@Override
	
	public void moveStep() {
		
		child_x_arr[step_counter % 5] = x;
		child_y_arr[step_counter % 5] = y;
		if (child != null && children_to_make == 8) {
			child.setX(child_x_arr[(step_counter - 3) % 5]);
			child.setY(child_y_arr[(step_counter - 3) % 5]);
		}
		else if (child != null) {
			child.setX(child_x_arr[(step_counter - 4) % 5]);
			child.setY(child_y_arr[(step_counter - 4) % 5]);
		}
		step_counter++;
	}
	

}
