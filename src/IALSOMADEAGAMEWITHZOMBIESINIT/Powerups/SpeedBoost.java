package IALSOMADEAGAMEWITHZOMBIESINIT.Powerups;

import IALSOMADEAGAMEWITHZOMBIESINIT.Player;

public class SpeedBoost extends BasicPowerup {

	final static String speedboost_path = "Images/speedboost-36.png";
	
	public SpeedBoost(int x, int y) {
		super(x, y, speedboost_path);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see IALSOMADEAGAMEWITHZOMBIESINIT.BasicPowerup#applyTo(
	 * IALSOMADEAGAMEWITHZOMBIESINIT.Player)
	 * 
	 * This Powerup enhances the player's movement by an amount
	 */
	public void applyTo(Player p) {
		p.setSpeedMod(1.3);
	}

}
