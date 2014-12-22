package IMADEA_model;

import java.awt.Rectangle;
import java.util.ArrayList;

import IALSOMADEAGAMEWITHZOMBIESINIT.BasicGameObject;
import IALSOMADEAGAMEWITHZOMBIESINIT.Player;
import IALSOMADEAGAMEWITHZOMBIESINIT.Enemies.BasicZombie;
import IALSOMADEAGAMEWITHZOMBIESINIT.Enemies.BigSlime;
import IALSOMADEAGAMEWITHZOMBIESINIT.Enemies.FastFace;
import IALSOMADEAGAMEWITHZOMBIESINIT.Enemies.WhiteFace;
import IALSOMADEAGAMEWITHZOMBIESINIT.Powerups.BasicPowerup;
import IALSOMADEAGAMEWITHZOMBIESINIT.Projectiles.BasicProjectile;

public class CollisionUtilities {

	/*
	 * processCollisions() iterates through all zombies and projectiles to check
	 * if anything collides with the other, or with the player. Projectiles and
	 * dead zombies are added to removals to be cleaned up later.
	 * 
	 */

	public static void processCollisions(Model model) {

		processPowerupCollisions(model);
		processZombieCollisions(model);
	}

	/*
	 * Helper function that iterates through all zombies to check collisions and
	 * do appropriate actions.
	 */
	private static void processZombieCollisions(Model model) {
		ArrayList<BasicZombie> zombies = model.getZombies();
		
		for (int i = 0; i < zombies.size(); i++) {
			BasicZombie current_zombie = zombies.get(i);
			Player player = model.getPlayer();

			if (!(current_zombie instanceof WhiteFace)) {
				processProjectileCollisions(model, current_zombie);
				if (collided(current_zombie, player.getShield()))
					current_zombie.kill();
			}

			if (current_zombie.isDead()) {
				if (current_zombie instanceof BigSlime)
					ModelSpawning.spawnSmallSlimes(model,
							(BigSlime) current_zombie);
				if (current_zombie instanceof FastFace)
					((FastFace) current_zombie).killChild();
				
				player.incrementScore(current_zombie.getPoints());
				model.addToRemovals(current_zombie);
				
				continue;
			}
			
			// Check if zombies are eating the player			
			if (collided(current_zombie, player) && !player.isShielded()) {
				if (player.spawnTimerExpired() == true) {
					player.deathReset();
				}
			}
		}
	}

	/*
	 * Helper function that checks if the passed zombie has been hit by any
	 * projectiles in the ArrayList.
	 * 
	 * @param current_zombie A zombie that could get hit by a projectile
	 */
	private static void processProjectileCollisions(Model model, BasicZombie current_zombie) {
		ArrayList<BasicProjectile> projectiles = model.getProjectiles();
		Player player = model.getPlayer();

		for (int j = 0; j < projectiles.size(); j++) {
			BasicProjectile current_projectile = projectiles.get(j);
			// Check if projectiles are hitting the zombies to deal damage

			if (!current_projectile.isRemovable()) {
				if (collided(current_zombie, current_projectile)) {
					current_projectile.applyTo(current_zombie);

					if (current_projectile.isRemovable())
						model.addToRemovals(current_projectile);
				}
			}
		}
	}

	/*
	 * Helper function that checks if a player has collided with a powerup and
	 * applies the powerup to the player if so.
	 */
	private static void processPowerupCollisions(Model model) {
		ArrayList<BasicPowerup> powerups = model.getPowerups();
		Player player = model.getPlayer();

		for (int i = 0; i < powerups.size(); i++) {
			BasicPowerup current_powerup = powerups.get(i);
			if (collided(player, current_powerup)) {
				current_powerup.applyTo(player);
				model.addToRemovals(current_powerup);
			}
			// Powerup shouldn't be on the field any more
			if (current_powerup.getTimeAlive() < 0)
				model.addToRemovals(current_powerup);
		}
	}

	/*
	 * Helper function for the above method. Two Rectangles are generated using
	 * the passed in GameObjects and are considered colliding if their
	 * rectangles intersect.
	 * 
	 * @param o1 The first GameObject to check collisions
	 * 
	 * @param o2 The second GameObject to check collisions
	 * 
	 * @return true if the GameObjects collide, false otherwise
	 */
	private static boolean collided(BasicGameObject o1, BasicGameObject o2) {
		if (o1 == null || o2 == null)
			return false;
		int o1_width = o1.getSpriteWidth();
		int o1_height = o1.getSpriteHeight();

		int o2_width = o2.getSpriteWidth();
		int o2_height = o2.getSpriteHeight();

		Rectangle o1_rect = new Rectangle(o1.getX() - o1_width / 2, o1.getY()
				- o1_height / 2, o1_width, o1_height);
		Rectangle o2_rect = new Rectangle(o2.getX() - o2_width / 2, o2.getY()
				- o2_height / 2, o2_width, o2_height);

		return o1_rect.intersects(o2_rect);
	}


}
