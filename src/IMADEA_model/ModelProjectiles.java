package IMADEA_model;

import java.awt.MouseInfo;
import java.awt.Point;
import java.util.ArrayList;

import IALSOMADEAGAMEWITHZOMBIESINIT.Player;
import IALSOMADEAGAMEWITHZOMBIESINIT.Projectiles.BasicProjectile;
import IALSOMADEAGAMEWITHZOMBIESINIT.Projectiles.Flameshot;
import IALSOMADEAGAMEWITHZOMBIESINIT.Projectiles.Rapidshot;
import IALSOMADEAGAMEWITHZOMBIESINIT.Projectiles.Tribullet;

public class ModelProjectiles {
	
	/**
	 * Creates projectiles based on what the player's ammo type is. The mouse location is
	 * used to aim the projectile's launch. Anything of form mouse.getY() - n is to make
	 * the aim a little bit more accurate because apparently just getting y is off a little.
	 * 
	 * @param player Player that is firing the projectile.
	 * @param projectiles An ArrayList to which the new projectile(s) will be added.
	 */
	public static void fireProjectile(Player player, ArrayList<BasicProjectile> projectiles) {
			int ammo_type = player.getAmmoType();
			Point mouse = MouseInfo.getPointerInfo().getLocation();
						
			if (ammo_type == Player.DEFAULT) {
				projectiles.add(shootDefault(player, mouse));
			} else if (ammo_type == Player.TRISHOT) {
				projectiles.addAll(shootTribullet(player, mouse));
				player.decrementAmmoCount();
			} else if (ammo_type == Player.RAPIDFIRE) {
				projectiles.add(shootRapidfire(player, mouse));
				player.decrementAmmoCount();
			} else if (ammo_type == Player.FLAME){
				projectiles.addAll(shootFlameshot(player, mouse));
				player.decrementAmmoCount();
			}
		
	}
	
	/**
	 * Creates several new Flame projectiles that are launched from the player's location 
	 * towards the mouse cursor's location. There is a slight variance in their trajectories
	 * to create a conal spray.
	 * 
	 * @param player The Player that is firing the projectiles.
	 * @param mouse A Point that holds the mouse cursor's location.
	 * @return An ArrayList containing all of the new Flames.
	 */
	private static ArrayList<BasicProjectile> shootFlameshot(Player player, Point mouse) {
		ArrayList<BasicProjectile> new_projectiles = new ArrayList<BasicProjectile>();

		for (int i = 0; i < 6; i++) {
			Flameshot p = new Flameshot(player.getX(), player.getY());

			p.setOrientationToFace((int) mouse.getX(), (int) mouse.getY() - 8);
			p.incrementOrientation(-Math.PI / 6);
			p.incrementOrientation(Math.random() * Math.PI / 3);
			new_projectiles.add(p);
		}
		
		return new_projectiles;
	}

	/**
	 * Create a new Default/BasicProjectile that is launched from the player's location
	 * towards the mouse cursor's location. The projectile should be oriented to travel 
	 * in a straight line to the target mouse location. 
	 * 
	 * @param player The Player that is firing the projectile.
	 * @param mouse A Point that holds the mouse cursor's location.
	 * @return The new BasicProjectile that was created.
	 */
	private static BasicProjectile shootDefault(Player player, Point mouse) {
		BasicProjectile p = new BasicProjectile(player.getX(), player.getY());
		p.setOrientationToFace((int) mouse.getX(), (int) mouse.getY() - 8);
		return p;
	}
	
	/**
	 * Fires a volley of Tribullets that are launched from the player's location 
	 * towards the mouse cursor's location. The first shot travels straight at the 
	 * target mouse location and the two other shots are offset by +/- 30 degrees.
	 * 
	 * @param player The Player that is firing the projectiles.
	 * @param mouse A Point that holds the mouse cursor's location.
	 * @return An ArrayList containing all of the new Tribullets.
	 */
	private static ArrayList<BasicProjectile> shootTribullet(Player player, Point mouse) {
		ArrayList<BasicProjectile> new_projectiles = new ArrayList<BasicProjectile>();
		
		Tribullet p1 = new Tribullet(player.getX(), player.getY());
		Tribullet p2 = new Tribullet(player.getX(), player.getY());
		Tribullet p3 = new Tribullet(player.getX(), player.getY());

		p1.setOrientationToFace((int) mouse.getX(), (int) mouse.getY() - 8);
		p2.setOrientationToFace((int) mouse.getX(), (int) mouse.getY() - 8);
		p2.incrementOrientation(Math.PI / 6);
		p3.setOrientationToFace((int) mouse.getX(), (int) mouse.getY() - 8);
		p3.incrementOrientation(-Math.PI / 6);

		new_projectiles.add(p1);
		new_projectiles.add(p2);
		new_projectiles.add(p3);
		
		return new_projectiles;
		
	}
	
	/**
	 * Fires a new Rapidshot projectile from the player's location towards the
	 * mouse cursor's location. Each projectile is offset by a small random angle 
	 * due to the sheer speed and recoil of the shot (or that's the story at least).
	 * 
	 * @param player The Player that is firing the projectile.
	 * @param mouse A Point that holds the mouse cursor's location.
	 * @return The new Rapidshot that was created.
	 */
	private static BasicProjectile shootRapidfire(Player player, Point mouse) {
		Rapidshot p = new Rapidshot(player.getX(), player.getY());

		p.setOrientationToFace((int) mouse.getX(), (int) mouse.getY() - 8);
		p.incrementOrientation(-Math.PI / 24);
		p.incrementOrientation(Math.random() * Math.PI / 12);
		
		return p;		
	}

}
