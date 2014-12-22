package IMADEA_model;

import java.util.ArrayList;
import java.util.Random;

import IALSOMADEAGAMEWITHZOMBIESINIT.Player;
import IALSOMADEAGAMEWITHZOMBIESINIT.Enemies.BasicZombie;
import IALSOMADEAGAMEWITHZOMBIESINIT.Enemies.BigSlime;
import IALSOMADEAGAMEWITHZOMBIESINIT.Enemies.BigZombie;
import IALSOMADEAGAMEWITHZOMBIESINIT.Enemies.EvilFace;
import IALSOMADEAGAMEWITHZOMBIESINIT.Enemies.FastFace;
import IALSOMADEAGAMEWITHZOMBIESINIT.Enemies.SmallSlime;
import IALSOMADEAGAMEWITHZOMBIESINIT.Enemies.WhiteFace;
import IALSOMADEAGAMEWITHZOMBIESINIT.Powerups.BasicPowerup;
import IALSOMADEAGAMEWITHZOMBIESINIT.Powerups.Flamethrower;
import IALSOMADEAGAMEWITHZOMBIESINIT.Powerups.Plusone;
import IALSOMADEAGAMEWITHZOMBIESINIT.Powerups.Rapidfire;
import IALSOMADEAGAMEWITHZOMBIESINIT.Powerups.Shielder;
import IALSOMADEAGAMEWITHZOMBIESINIT.Powerups.SpeedBoost;
import IALSOMADEAGAMEWITHZOMBIESINIT.Powerups.Trishot;

public class ModelSpawning {
	
	/*
	 * Spawns a zombie within a certain bound around the player. If a zombie were to spawn 
	 * directly on the player then a new location is calculated to avoid "frustrations"
	 * 
	 * @param player The player that the zombie will spawn around
	 * @param zombies An ArrayList that the newly spawned zombie will be added to
	 * @param x_bound The x coordinate limitation for where the zombie will spawn
	 * @param y_bound the y coordinate limitation for where the zombie will spawn
	 */
	public static void spawnZombie(Player player, ArrayList<BasicZombie> zombies, int x_bound, int y_bound) {
		int px = player.getX();
		int py = player.getY();

		// minimum distance away that a zombie will spawn
		int min = 100;

		// Get a random location in a rectangle around the player's location
		Random rand = new Random();
		int random_x = rand.nextInt(x_bound + 1) - x_bound / 2 + px;
		int random_y = rand.nextInt(y_bound + 1) - y_bound / 2 + py;

		// While it's too close to the player then re-roll
		while (random_x < (px + min) && random_x > (px - min)
				&& random_y < (py + min) && random_y > (py - min)) {
			random_x = rand.nextInt(x_bound + 1) - x_bound / 2 + px;
			random_y = rand.nextInt(y_bound + 1) - y_bound / 2 + py;
		}
		BasicZombie b;
		
		// Choose a type of zombie to spawn. Some kinds have a higher chance than others.
		double decider = Math.random();
		if (decider > 0.5)
		b = new BasicZombie(random_x, random_y);
		else if (decider > 0.3)
			b = new EvilFace(random_x, random_y);
		else if (decider > 0.2)
			b = new BigZombie(random_x, random_y);
		else if (decider > 0.1)
			b = new BigSlime(random_x, random_y);
		else {
			b = new FastFace(random_x, random_y);
			spawnFastFaceChildren(zombies, (FastFace) b);
		}
		zombies.add(b);
		
	}
	
	private static void spawnFastFaceChildren(ArrayList<BasicZombie> zombies, FastFace b) {
		FastFace current_face = b;
		for (int i = b.getChildrenToMake(); i > 0; i--) {
			WhiteFace child = new WhiteFace(current_face.getX(), current_face.getY(), i);
			current_face.setChild(child);
			zombies.add(child);
			current_face = child;
		}
		
	}

	/*
	 * Spawns a type of powerup within a certain bounds. 
	 * 
	 * @param powerups An ArrayList to which the newly spawned powerup will be added
	 * @param x_bound The x coordinate limitation for where the powerup will spawn
	 * @param y_bound the y coordinate limitation for where the powerup will spawn
	 */
	public static void spawnPowerup(ArrayList<BasicPowerup> powerups, int x_bound, int y_bound) {
		int random_x = (int) (Math.random() * (x_bound));
		int random_y = (int) (Math.random() * (y_bound));

		// Choose a random powerup to spawn. Some powerups have higher chance than others.
		double decider = Math.random();
		if (decider > 0.95) {
			SpeedBoost sb = new SpeedBoost(random_x, random_y);
			powerups.add(sb);

		} else if (decider > 0.90) {
			Plusone p1 = new Plusone(random_x, random_y);
			powerups.add(p1);	
		}
		else if (decider > 0.75) {
			Shielder sh = new Shielder(random_x, random_y);
			powerups.add(sh);
		}
		else if (decider > 0.50) {
			Rapidfire rf = new Rapidfire(random_x, random_y);
			powerups.add(rf);
		}
		else if (decider > 0.25) {
			Trishot t = new Trishot(random_x, random_y);
			powerups.add(t);
		}
		else {
			Flamethrower f = new Flamethrower(random_x, random_y);
			powerups.add(f);
		}
	}

	public static void spawnSmallSlimes(Model model, BigSlime parent) {
		for (int i = 0; i < parent.getChildrenToMake(); i++) {
			SmallSlime child = new SmallSlime(parent);
			model.addToZombies(child);
		}
		model.addToRemovals(parent);
	}

}
