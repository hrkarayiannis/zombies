package IALSOMADEAGAMEWITHZOMBIESINIT;

import IMADEA_controller.Controller;

public class IALSOMADEAGAMEWITHZOMBIESINIT {
	public static void main(String argv[]) {

		int x_bound = 1280;
		int y_bound = 720;

		// Set up the Model, View, and Controller and start the gameLoop!
		Controller controller = new Controller(x_bound, y_bound);

		controller.gameLoop();
	}

}
