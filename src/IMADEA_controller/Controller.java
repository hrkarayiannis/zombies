package IMADEA_controller;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import IALSOMADEAGAMEWITHZOMBIESINIT.Player;
import IALSOMADEAGAMEWITHZOMBIESINIT.SpriteAndCoords;
import IMADEA_model.Model;
import IMADEA_model.ModelExtraUtilities;
import IMADEA_view.BestListener;
import IMADEA_view.View;

public class Controller implements BestListener {

	// View that is being controlled
	private View view;
	// Model that is being controlled
	private Model model;

	// Variable that keeps track of if the game loop should continue
	private volatile boolean game_over = false;
	private volatile boolean player_done_spawning = true;
	private volatile boolean ready_for_spawn = false;

	// Group of booleans keep track of what movement the player wants
	private boolean MOVE_UP = false;
	private boolean MOVE_DOWN = false;
	private boolean MOVE_LEFT = false;
	private boolean MOVE_RIGHT = false;
	private boolean MOUSE_CLICKED = false;
	private volatile boolean PAUSED = false;
	
	private int x_bound;
	private int y_bound;
	
	private volatile String death_msg = "";
	final static String heart_path = "Images/heart.png";
	
	ArrayList<String> all_death_messages = new ArrayList<String>();
	/*
	 * Default constructor
	 */
	public Controller(int x_bound, int y_bound) {
		this.model = new Model(x_bound, y_bound);
		this.view = new View(x_bound, y_bound);
		view.setBest(this);
		
		this.x_bound = x_bound;
		this.y_bound = y_bound;
		
		all_death_messages.add("rip in pepperonis");
		all_death_messages.add("d3dz");
		all_death_messages.add("You are dead.");
		all_death_messages.add("You had a lot more to live for.");
		all_death_messages.add("Your body was gone an hour later.");
		all_death_messages.add("Your family will never know how you died.");
		all_death_messages.add("That was absolutely your fault.");
		all_death_messages.add("Beep.. beep.. beeeeeeeeeeeeeeeee");
		all_death_messages.add("Crushed.");
		all_death_messages.add("Smashed.");
		all_death_messages.add("Get styled upon.");
		all_death_messages.add("ur dead LOL get wrecked");
		all_death_messages.add("Sucks to suck.");
		all_death_messages.add("You die in a hilarious pose.");
	}

	/*
	 * Checks what control flags are set and tells the player to move and shoot
	 * accordingly.
	 */
	private void performUserControls() {
		Player player = model.getPlayer();
		if (MOUSE_CLICKED)
			model.attemptFireProjectile();

		// READ KEYBOARD FLAGS
		if (MOVE_UP && !MOVE_DOWN && MOVE_LEFT && !MOVE_RIGHT) {
			player.moveUpAndLeft();
		} else if (MOVE_UP && !MOVE_DOWN && !MOVE_LEFT && MOVE_RIGHT) {
			player.moveUpAndRight();
		} else if (!MOVE_UP && MOVE_DOWN && MOVE_LEFT && !MOVE_RIGHT) {
			player.moveDownAndLeft();
		} else if (!MOVE_UP && MOVE_DOWN && !MOVE_LEFT && MOVE_RIGHT) {
			player.moveDownAndRight();
		} else if (MOVE_UP && !MOVE_DOWN && (MOVE_LEFT == MOVE_RIGHT)) {
			player.moveUp();
		} else if (!MOVE_UP && MOVE_DOWN && (MOVE_LEFT == MOVE_RIGHT)) {
			player.moveDown();
		} else if ((MOVE_UP == MOVE_DOWN) && MOVE_LEFT && !MOVE_RIGHT) {
			player.moveLeft();
		} else if ((MOVE_UP == MOVE_DOWN) && !MOVE_LEFT && MOVE_RIGHT) {
			player.moveRight();
		}
	}

	/*
	 * 
	 * (non-Javadoc)
	 * 
	 * @see
	 * IMADEA_view.BestListener#handleKeyEventPressed(java.awt.event.KeyEvent)
	 */

	@Override
	public void handleKeyEventPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_E)
			MOVE_UP = true;
		if (e.getKeyCode() == KeyEvent.VK_D)
			MOVE_DOWN = true;
		if (e.getKeyCode() == KeyEvent.VK_S)
			MOVE_LEFT = true;
		if (e.getKeyCode() == KeyEvent.VK_F)
			MOVE_RIGHT = true;
		// Quit the game with ESC key
		if (e.getKeyChar() == KeyEvent.VK_ESCAPE)
			System.exit(0);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * IMADEA_view.BestListener#handleKeyEventReleased(java.awt.event.KeyEvent)
	 */

	@Override
	public void handleKeyEventReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_E)
			MOVE_UP = false;
		if (e.getKeyCode() == KeyEvent.VK_D)
			MOVE_DOWN = false;
		if (e.getKeyCode() == KeyEvent.VK_S)
			MOVE_LEFT = false;
		if (e.getKeyCode() == KeyEvent.VK_F)
			MOVE_RIGHT = false;
		if (e.getKeyCode() == KeyEvent.VK_SPACE && game_over == true) {
			ModelExtraUtilities.resetModel(model);
			game_over = false;
			player_done_spawning = true;
			ready_for_spawn = false;
			death_msg = "";
		}
		// IDK WHY BUT ONLY SPACE WORKS FOR THIS LOL
		else if (e.getKeyChar() == KeyEvent.VK_SPACE && game_over == false)
			PAUSED = !PAUSED;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * IMADEA_view.BestListener#handleMouseEventPressed(java.awt.event.MouseEvent
	 * )
	 */
	@Override
	public void handleMouseEventPressed(MouseEvent e) {
		MOUSE_CLICKED = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * IMADEA_view.BestListener#handleMouseEventReleased(java.awt.event.MouseEvent
	 * )
	 */
	@Override
	public void handleMouseEventReleased(MouseEvent e) {
		MOUSE_CLICKED = false;
	}

	/*
	 * Runs the main game loop as long as the player has lives or an exit event
	 * occurs.
	 */
	public void gameLoop() {
		while (true) {

			// Set up the background
			Graphics2D g = view.setUpGraphics2D();

			updateModel(g);
			
			drawAllSprites();
			drawOverlay();			
			
			
			// Flip the buffer
			view.tearDownGraphics2D(g);

			// Ghetto timer to maintain controlled frames per second
			// Sleeping for 22 gives around 45 fps
			try {
				Thread.sleep(22);
			} catch (Exception e) {
			}
		}
	}

	private void drawOverlay() {
		Player player = model.getPlayer();
		int heart_count = player.getLives();
		drawHearts(heart_count);
		drawStrings();
		String ammo_path = ModelExtraUtilities.getPlayerAmmoPath(model);
		if (!ammo_path.isEmpty()) 
			view.drawImagePath(ammo_path, 138, 18);
	}

	private void drawAllSprites() {
		ArrayList<SpriteAndCoords> entities = ModelExtraUtilities.getAllEntities(model);
		for (int i = 0; i < entities.size(); i++) {
			view.drawSpriteAt(entities.get(i).getSprite(),entities.get(i).getX(), entities.get(i).getY());
		}
	}
	
	
	private void drawHearts(int heart_count) {
		for (int i = 0; i < heart_count; i++){
			// calculates what column to put the heart; 30 x pos offset from edge
			int x = 36 * (int) (i % 5) + 30;
			// calculates what row to put the heart; 48 y pos offset from edge
			int y = 36 * (int) (i / 5) + 48;
			view.drawImagePath(heart_path, x, y);
		}
		
	}

	
	/*
	 * Helper that will tell view to draw certain messages for different events
	 * in the game.
	 */
	private void drawStrings() {
		Player player = model.getPlayer();
		if (PAUSED)
			view.drawStringAt("Game is paused. Press [Space Bar] to resume.",
					x_bound / 2 - 158, 25, View.INFO);
		if (game_over)
			view.drawStringAt("GG NUB. Press [Space Bar] to play again.",
					x_bound / 2 - 128, y_bound / 2, View.INFO);
		if (!player_done_spawning){
			view.drawStringAt(death_msg, player.getX() - 5 * death_msg.length(),
					player.getY(), View.DEATH);
		}
		
		if (player.getAmmoType() != Player.DEFAULT) {
			view.drawStringAt("x" + player.getAmmoCount(), 162, 24, View.INFO);
		}
		
		view.drawStringAt("Score: " + player.getScore(), 16, 24,
				View.INFO);
	}

	/*
	 * Helper function that calls all methods in Model that are needed each
	 * step/frame of the game.
	 * 
	 * @param g Graphics buffer that the Model will draw to.
	 */

	private String chooseDeathMessage() {
		// Lots of death messages are from "Risk of Rain"
		
		int decider = (int) (Math.random() * all_death_messages.size());
		return all_death_messages.get(decider);
		
	}

	private void updateModel(Graphics2D g) {
		Player player = model.getPlayer();
		if (game_over == false && PAUSED == false) {
			attemptMovement();
			model.modelStep();

			player_done_spawning = player.spawnTimerExpired();
			game_over = player.getLives() <= 0;
		}
		processPlayerSpawning(player);
	}

	/*
	 * Helper method that will move the player if the player has spawned,
	 * AKA is not dead.
	 */
	private void attemptMovement() {
		if (player_done_spawning == true) {
			performUserControls();
			model.validatePlayerBounds();
		}
	}
	
	/*
	 * Helper method that will disallow the player to move if they are busy
	 * spawning. (Lots of controlling logic)
	 */
	private void processPlayerSpawning(Player player) {
		if (player_done_spawning == false) {
			if (ready_for_spawn == false) {
				death_msg = chooseDeathMessage();
				ModelExtraUtilities.reverseZombies(model);
				ready_for_spawn = true;
			} else {
				if (player.spawnTimerExpired())
					player_done_spawning = true;
			}

		} else
			ready_for_spawn = false;
	}
}
