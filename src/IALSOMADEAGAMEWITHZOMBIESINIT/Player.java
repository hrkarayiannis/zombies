package IALSOMADEAGAMEWITHZOMBIESINIT;

public class Player extends GameObject {
	final static String player_path = "Images/player.png";

	public final static byte DEFAULT = 0;
	public final static byte TRISHOT = 1;
	public final static byte RAPIDFIRE = 2;
	public final static byte FLAME = 3;

	public final static int RESPAWN_TIME_STEPS = 100;
	public final static int STARTING_LIVES = 3;

	// A score count that changes based on enemies slain.
	private int score = 0;
	// The number of lives remaining before the game is over.
	private int lives = STARTING_LIVES;
	// Time left for the player to spawn
	private int spawn_timer = 0;

	// Counts "steps_since_fire" to keep track of fire rate
	private int steps_since_fire = 10;
	// Directly controls how quickly the player can fire
	private final static int steps_between_fire = 5;
	// Number that adjusts fire rate according to things like powerups
	private double fire_rate_modifier = 1;

	// speed_modifier will enhance the player's speed after picking up a powerup
	private double speed_modifier = 1;
	// Number of frames that speed_modifier will remain altered for
	private long speed_timer = 0;

	// ammo_type affects the type of projectile fired
	private int ammo_type = DEFAULT;
	// the number of times that projectiles of ammo_type can be fired
	private int special_ammo_count = 0;

	// Each player has a shield that needs a shield "fuel" powerup to function
	private Shield shield = new Shield(0, 0);
	// Amount of time that the player will be shielded for
	private int shield_duration = 0;

	// Constructor that loads a player sprite
	public Player(int x, int y) {
		super(x, y, player_path);
		speed = 4;
	}

	public int getLives() {
		return lives;
	}

	public int getScore() {
		return score;
	}

	// Finds the effective steps between fire ie the delay between shots
	public int getStepsBetweenFire() {
		return (int) (steps_between_fire / fire_rate_modifier);
	}

	public int getStepsSinceFire() {
		return steps_since_fire;
	}

	public long getSpeedTimer() {
		return speed_timer;
	}

	public int getAmmoType() {
		return ammo_type;
	}

	public int getAmmoCount() {
		return special_ammo_count;
	}

	public boolean isShielded() {
		return shield_duration > 0;
	}

	// The shield needs to have time left to function otherwise it should be
	// "off"
	public Shield getShield() {
		if (isShielded() == true)
			return shield;
		return null;
	}

	// Sets the speed modifier to be the passed value. Buff lasts for 600 steps
	// of the timer.
	public void setSpeedMod(double d) {
		speed_modifier = d;
		setSpeedTimer(600);
	}

	public void setSpeedTimer(long amount) {
		speed_timer = amount;
	}

	public void setAmmoType(byte type) {
		ammo_type = type;
	}

	public void setAmmoCount(int amount) {
		special_ammo_count = amount;
	}

	public void setFireRateMod(double d) {
		fire_rate_modifier = d;
	}
	public void setStepsSinceFire(int amount) {
		steps_since_fire = amount;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void incrementScore(int amount) {
		score += amount;
	}

	public void incrementLives() {
		lives++;
	}

	public void incrementShieldDuration(int amount) {
		shield_duration += amount;
	}

	public void incrementStepsSinceFire() {
		steps_since_fire++;
	}

	public void decrementLives() {
		lives--;
	}

	public void decrementSpawnTimer() {
		spawn_timer--;
	}

	public void decrementShieldDuration() {
		shield_duration--;
	}

	public void decrementAmmoCount() {
		special_ammo_count--;
	}

	public void startSpawnTimer() {
		spawn_timer = RESPAWN_TIME_STEPS;
	}

	public boolean spawnTimerExpired() {
		return spawn_timer < 0;
	}

	// Speed modifier can increase (or decrease) how fast the player moves
	// Cardinal movements
	public void moveUp() {
		y -= speed_modifier * speed;
	}

	public void moveDown() {
		y += speed_modifier * speed;
	}

	public void moveLeft() {
		x -= speed_modifier * speed;
	}

	public void moveRight() {
		x += speed_modifier * speed;
	}

	// Diagonal movements
	public void moveUpAndRight() {
		y -= (int) (speed_modifier * speed);
		x += (int) (speed_modifier * speed);
	}

	public void moveUpAndLeft() {
		y -= (int) (speed_modifier * speed);
		x -= (int) (speed_modifier * speed);
	}

	public void moveDownAndRight() {
		y += (int) (speed_modifier * speed);
		x += (int) (speed_modifier * speed);
	}

	public void moveDownAndLeft() {
		y += (int) (speed_modifier * speed);
		x -= (int) (speed_modifier * speed);
	}

	/*
	 * Updates all timers and counts for all modifiers of ammo, movespeed, etc.
	 */
	public void updateMods() {
		speed_timer--;
		if (speed_timer < 0)
			resetSpeedMod();

		if (special_ammo_count < 0) {
			resetAmmoType();
			resetFireMod();
		}
		if (isShielded()) {
			decrementShieldDuration();
			shield.setCoordsTo(x, y);
		}
	}

	// Reset modifiers to inital values...
	public void resetStepsSinceFire() {
		steps_since_fire = 10;
	}

	public void resetSpeedMod() {
		speed_modifier = 1;
	}

	public void resetAmmoType() {
		ammo_type = DEFAULT;
	}

	public void resetFireMod() {
		fire_rate_modifier = 1;
	}

	public void resetLives() {
		lives = STARTING_LIVES;
	}

	public void resetSpawnTimer() {
		spawn_timer = 0;
	}

	public void resetShieldDuration() {
		shield_duration = 0;
	}

	/*
	 * Removes all bonus effects from the player and removes a life.
	 */
	public void deathReset() {
		speed_timer = 0;
		resetSpeedMod();

		special_ammo_count = 0;
		resetAmmoType();
		resetFireMod();
		resetShieldDuration();

		lives--;
		startSpawnTimer();
	}

	/*
	 * Used to reset the player to initial conditions. Usually when the game is
	 * restarted
	 */

	public void fullResetToPos(int x, int y) {
		deathReset();
		resetLives();
		resetSpawnTimer();

		setX(x);
		setY(y);
		score = 0;
	}

}
