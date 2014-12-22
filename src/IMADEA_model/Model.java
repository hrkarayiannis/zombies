package IMADEA_model;

import java.awt.MouseInfo;
import java.awt.Point;
import java.util.ArrayList;

import IALSOMADEAGAMEWITHZOMBIESINIT.BasicGameObject;
import IALSOMADEAGAMEWITHZOMBIESINIT.Player;
import IALSOMADEAGAMEWITHZOMBIESINIT.Enemies.BasicZombie;
import IALSOMADEAGAMEWITHZOMBIESINIT.Enemies.EvilFace;
import IALSOMADEAGAMEWITHZOMBIESINIT.Powerups.BasicPowerup;
import IALSOMADEAGAMEWITHZOMBIESINIT.Projectiles.BasicProjectile;
import IMADEA_model.ModelSpawning;

public class Model {
	private Player player;
	private ArrayList<BasicZombie> zombies = new ArrayList<BasicZombie>();
	private ArrayList<BasicProjectile> projectiles = new ArrayList<BasicProjectile>();
	private ArrayList<BasicPowerup> powerups = new ArrayList<BasicPowerup>();
	private ArrayList<BasicGameObject> removals = new ArrayList<BasicGameObject>();
	
	private final int x_bound;
	private final int y_bound;

	private int steps_since_zombie = 0;
	private int steps_since_powerup = 0;
	// Amount of frames between spawns of powerups or zombies... no real
	// significance in the numbers other than game balance.
	private int steps_between_powerups = 100;
	private int steps_between_zombies = 15;
		
	public Model(int x_bound, int y_bound) {
		player = new Player(x_bound / 2, y_bound / 2);

		this.x_bound = x_bound;
		this.y_bound = y_bound;
	}
	
	public Player getPlayer() {
		return player;
	}
	public void validatePlayerBounds() {
		player.validateBounds(0, 0, x_bound, y_bound);
	}

	public ArrayList<BasicZombie> getZombies(){
		return zombies;
	}
	public ArrayList<BasicPowerup> getPowerups(){
		return powerups;
	}
	public ArrayList<BasicProjectile> getProjectiles(){
		return projectiles;
	}
	public ArrayList<BasicGameObject> getRemovals(){
		return removals;
	}
	public void addToZombies(BasicZombie zombie){
		zombies.add(zombie);
	}
	public void addToPowerups(BasicPowerup powerup) {
		powerups.add(powerup);
	}
	public void addToProjectiles(BasicProjectile projectile) {
		projectiles.add(projectile);
	}
	public void addToRemovals(BasicGameObject object){
		removals.add(object);
	}
	
	public void resetAllTimers() {
		this.steps_since_zombie = 0;
		this.steps_since_powerup = 0;
		player.setStepsSinceFire(0);
	}
	
	public int getXBound(){
		return x_bound;
	}
	public int getYBound() {
		return y_bound;
	}
	
	public void removeExpiredItems() {
		zombies.removeAll(removals);
		projectiles.removeAll(removals);
		powerups.removeAll(removals);
		removals.clear();
	}

	public void removeAll() {
		zombies.clear();
		projectiles.clear();
		powerups.clear();
		removals.clear();
	}
	
	public void modelStep() {
		removeExpiredItems();
		stepPlayer();
		stepNonPlayer();
		
		attemptSpawnZombie();
		attemptSpawnPowerup();
		CollisionUtilities.processCollisions(this);
	}
	private void stepPlayer() {
		if (player.spawnTimerExpired() == true) {
			Point mouse = MouseInfo.getPointerInfo().getLocation();
			player.setOrientationToFace((int) mouse.getX(), (int) mouse.getY() - 8);
			player.updateMods();
		} else
			player.decrementSpawnTimer();

	}
	private void stepNonPlayer() {
		stepZombies();
		stepProjectiles();
		stepPowerups();
	}

	private void stepPowerups() {
		for (int i = 0; i < powerups.size(); i++) {
			BasicPowerup current_powerup = powerups.get(i);
			current_powerup.decrementTimeAlive();
		}
	}

	private void stepProjectiles() {
		for (int i = 0; i < projectiles.size(); i++) {
			BasicProjectile current_projectile = projectiles.get(i);
			current_projectile.moveStep();
			// Only keep track of projectiles that are either in bounds or slightly out
			current_projectile.validateBounds(-x_bound / 4, - y_bound / 4, x_bound * 5 / 4, y_bound * 5 / 4);
			if (current_projectile.isRemovable())
				removals.add(current_projectile);
		}
	}

	private void stepZombies() {
		for (int i = 0; i < zombies.size(); i++) {
			BasicZombie current_zombie = zombies.get(i);
			
			
			if (player.spawnTimerExpired() == true)
				current_zombie.setOrientationToFace(player.getX(), player.getY());
			current_zombie.validateBounds(- x_bound / 4, - y_bound / 4, x_bound * 5 / 4 , y_bound * 5 / 4);
			current_zombie.moveStep();
		}
	}
	private void attemptSpawnZombie() {
		if (player.spawnTimerExpired() == true) {
			if (steps_since_zombie >= steps_between_zombies){
				ModelSpawning.spawnZombie(player, zombies, x_bound, y_bound);
				steps_since_zombie = 0;
			}
			else 
				steps_since_zombie++;
		}
	}
	private void attemptSpawnPowerup() {
		if (steps_since_powerup >= steps_between_powerups){
			ModelSpawning.spawnPowerup(powerups, x_bound, y_bound);
			steps_since_powerup = 0;
		}
		else
			steps_since_powerup++;
		
	}
	public void attemptFireProjectile(){
		if (player.getStepsSinceFire() >= player.getStepsBetweenFire()){
			ModelProjectiles.fireProjectile(player, projectiles);
			player.setStepsSinceFire(0);
		}
		else
			player.incrementStepsSinceFire();
	}
}
