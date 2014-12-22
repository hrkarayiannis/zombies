package IALSOMADEAGAMEWITHZOMBIESINIT.Powerups;

import IALSOMADEAGAMEWITHZOMBIESINIT.BasicGameObject;
import IALSOMADEAGAMEWITHZOMBIESINIT.Player;

public abstract class BasicPowerup extends BasicGameObject {

	// Frames remaining that the powerup can be picked up
	private long time_alive = 420;

	// A regular basic powerup shouldn't exist. Only for use by subclasses
	protected BasicPowerup(int x, int y, String path) {
		super(x, y, path);
	}

	public long getTimeAlive() {
		return time_alive;
	}

	public void decrementTimeAlive() {
		time_alive--;
	}

	/*
	 * Applies the effect of the powerup to the player such as movement speed
	 * increase or a different weapon.
	 * 
	 * @param p Player that is getting powered-up
	 */
	public abstract void applyTo(Player p);
}
