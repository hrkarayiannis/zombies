package IALSOMADEAGAMEWITHZOMBIESINIT.Powerups;

import IALSOMADEAGAMEWITHZOMBIESINIT.Player;

public class Shielder extends BasicPowerup {

	final static String shielder_path = "Images/shielder.png";
	
	public Shielder(int x, int y) {
		super(x, y, shielder_path);
	}
	
	/*
	 * (non-Javadoc)
	 * @see IALSOMADEAGAMEWITHZOMBIESINIT.Powerups.BasicPowerup#applyTo(IALSOMADEAGAMEWITHZOMBIESINIT.Player)
	 */
	@Override
	public void applyTo(Player p) {
		p.incrementShieldDuration(600);
	}

}
