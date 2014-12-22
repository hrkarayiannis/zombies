package IALSOMADEAGAMEWITHZOMBIESINIT.Powerups;

import IALSOMADEAGAMEWITHZOMBIESINIT.Player;

public class Rapidfire extends BasicPowerup {

	
	public final static String rapidfire_path = "Images/rapidfire.png";
	
	
	public Rapidfire(int x, int y) {
		super(x, y, rapidfire_path);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see IALSOMADEAGAMEWITHZOMBIESINIT.BasicPowerup#applyTo(
	 * IALSOMADEAGAMEWITHZOMBIESINIT.Player)
	 * 
	 * Rapidfire provides a very high rate of fire, but with slight inaccuracy.
	 */
	@Override
	public void applyTo(Player p) {

		if (p.getAmmoType() != Player.RAPIDFIRE) {
			p.setFireRateMod(4);
			p.setAmmoCount(150);
		} else
			p.setAmmoCount(p.getAmmoCount() + 150);

		p.setAmmoType(Player.RAPIDFIRE);
	}

}
