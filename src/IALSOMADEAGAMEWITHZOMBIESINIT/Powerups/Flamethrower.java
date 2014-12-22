package IALSOMADEAGAMEWITHZOMBIESINIT.Powerups;

import IALSOMADEAGAMEWITHZOMBIESINIT.Player;

public class Flamethrower extends BasicPowerup{

		public final static String flamethrower_path = "Images/flamethrower.png";
	
	
	public Flamethrower(int x, int y) {
		super(x, y, flamethrower_path);
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

		if (p.getAmmoType() != Player.FLAME) {
			p.setFireRateMod(2);
			p.setAmmoCount(150);
		} else
			p.setAmmoCount(p.getAmmoCount() + 150);

		p.setAmmoType(Player.FLAME);
	}

}
