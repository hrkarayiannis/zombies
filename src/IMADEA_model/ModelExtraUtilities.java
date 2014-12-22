package IMADEA_model;

import java.util.ArrayList;

import IALSOMADEAGAMEWITHZOMBIESINIT.Player;
import IALSOMADEAGAMEWITHZOMBIESINIT.Shield;
import IALSOMADEAGAMEWITHZOMBIESINIT.SpriteAndCoords;
import IALSOMADEAGAMEWITHZOMBIESINIT.Enemies.BasicZombie;
import IALSOMADEAGAMEWITHZOMBIESINIT.Enemies.EvilFace;
import IALSOMADEAGAMEWITHZOMBIESINIT.Powerups.BasicPowerup;
import IALSOMADEAGAMEWITHZOMBIESINIT.Powerups.Flamethrower;
import IALSOMADEAGAMEWITHZOMBIESINIT.Powerups.Rapidfire;
import IALSOMADEAGAMEWITHZOMBIESINIT.Powerups.Trishot;
import IALSOMADEAGAMEWITHZOMBIESINIT.Projectiles.BasicProjectile;

public class ModelExtraUtilities {
	
	final static String plusone_path = "Images/plusone.png";
	final static String shielder_path = "Images/shielder.png";
	final static String rapidfire_path = "Images/rapidfire.png";
	final static String speed_boost_path = "Images/speedboost-36.png";
	final static String trishot_path = "Images/greenbolt.png";
	
	/*
	 * Makes all of the zombies reverse their movement direction.
	 */
	public static void reverseZombies(Model model) {
		ArrayList<BasicZombie> zombies = model.getZombies();
		for (int i = 0; i < zombies.size(); i++) {
			BasicZombie current_zombie = zombies.get(i);
			if (!(current_zombie instanceof EvilFace))
				current_zombie.reverseOrientation();
		}
	}
	
	/*
	 * Fully restores the model to it's original state to be used for a new game
	 * instance.
	 */
	public static void resetModel(Model model) {
		Player player = model.getPlayer();
		int x_bound = model.getXBound();
		int y_bound = model.getYBound();
		
		player.fullResetToPos(x_bound / 2, y_bound / 2);
		model.removeAll();

		model.resetAllTimers();
	}
	
	public static String getPlayerAmmoPath(Model model) {
		Player player = model.getPlayer();
		switch (player.getAmmoType()){
		
		case Player.RAPIDFIRE:
			return Rapidfire.rapidfire_path;
		case Player.TRISHOT:
			return Trishot.trishot_path;
		case Player.FLAME:
			return Flamethrower.flamethrower_path;
		default: 
			return "";
		}
	}
	
	
	public static ArrayList<SpriteAndCoords> getAllEntities(Model model) {
		Player player = model.getPlayer();
		Shield shield = player.getShield();
		ArrayList<BasicProjectile> projectiles = model.getProjectiles();
		ArrayList<BasicZombie> zombies = model.getZombies();
		ArrayList<BasicPowerup> powerups = model.getPowerups();
		
		ArrayList<SpriteAndCoords> all_entities = new ArrayList<SpriteAndCoords>();
		
		// The order of what items are added in turn affects the drawing order.
		SpriteAndCoords current_entity;
		
		if (player.isShielded()){
			current_entity = new SpriteAndCoords(shield.getSprite(),shield.getX(), shield.getY());
			all_entities.add(current_entity);
		}
		if (player.spawnTimerExpired() == true){
			current_entity = new SpriteAndCoords(player.getSprite(),player.getX(), player.getY());
			all_entities.add(current_entity);
		}
		
		for (int i = 0; i < projectiles.size(); i++){
			BasicProjectile curr_proj = projectiles.get(i);
			current_entity = new SpriteAndCoords(curr_proj.getSprite(),curr_proj.getX(), curr_proj.getY());
			all_entities.add(current_entity);
		}
		for (int i = 0; i < zombies.size(); i++){
			BasicZombie curr_zombie = zombies.get(i);
			current_entity = new SpriteAndCoords(curr_zombie.getSprite(),curr_zombie.getX(), curr_zombie.getY());
			all_entities.add(current_entity);
		}
		for (int i = 0; i < powerups.size(); i++){
			BasicPowerup curr_pow = powerups.get(i);
			current_entity = new SpriteAndCoords(curr_pow.getSprite(),curr_pow.getX(), curr_pow.getY());
			all_entities.add(current_entity);
		}
	
		
		
		return all_entities;
	}


}
