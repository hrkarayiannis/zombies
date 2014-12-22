package IALSOMADEAGAMEWITHZOMBIESINIT.Powerups;

import IALSOMADEAGAMEWITHZOMBIESINIT.Player;

public class Trishot extends BasicPowerup {
	
	// Lol it's not even green right now
	public final static String trishot_path = "Images/greenbolt.png";
	
	public Trishot(int x, int y) {
		super(x, y, trishot_path);
	}

	@Override
	/*
	 * (non-Javadoc)
	 * 
	 * @see IALSOMADEAGAMEWITHZOMBIESINIT.BasicPowerup#applyTo(
	 * IALSOMADEAGAMEWITHZOMBIESINIT.Player)
	 * 
	 * Trishot causes the player to fire a powerful spread, but at a slower rate
	 */
	public void applyTo(Player p) {

		if (p.getAmmoType() != Player.TRISHOT) {
			p.setFireRateMod(0.6);
			p.setAmmoCount(75);
		} else
			p.setAmmoCount(p.getAmmoCount() + 75);

		p.setAmmoType(Player.TRISHOT);
	}

}
