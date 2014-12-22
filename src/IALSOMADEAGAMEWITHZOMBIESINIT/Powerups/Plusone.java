package IALSOMADEAGAMEWITHZOMBIESINIT.Powerups;

import IALSOMADEAGAMEWITHZOMBIESINIT.Player;

public class Plusone extends BasicPowerup {

	final static String plusone_path = "Images/plusone.png";
	
	
	public Plusone(int x, int y) {
		super(x, y, plusone_path);
	}

	@Override
	/*
	 * (non-Javadoc)
	 * @see IALSOMADEAGAMEWITHZOMBIESINIT.BasicPowerup#applyTo(IALSOMADEAGAMEWITHZOMBIESINIT.Player)
	 * 
	 * This powerup grants the player an additional life.
	 */
	public void applyTo(Player p) {
		p.incrementLives();
	}

}
