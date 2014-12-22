package IMADEA_view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import IALSOMADEAGAMEWITHZOMBIESINIT.Sprite;
import IALSOMADEAGAMEWITHZOMBIESINIT.SpriteStore;

public class View extends Canvas {

	// This is here because I hate yellow squiggles...
	private static final long serialVersionUID = 1L;
	
	public final static byte INFO = 0;
	public final static byte DEATH = 1;

	private BufferStrategy strategy;

	private int x_bound;
	private int y_bound;

	private BestListener best;

	public View(int x_bound, int y_bound) {
		this.x_bound = x_bound;
		this.y_bound = y_bound;

		JFrame container = frameAndPanelSetup(x_bound, y_bound);
		
		// Listen for user input
		addKeyListener(new KeyInputHandler());
		addMouseListener(new MouseInputHandler());
		requestFocus();
		
		changeCursor();
		  
		// Create 2 buffer strategy for flipping
		createBufferStrategy(2);
		strategy = getBufferStrategy();

		// Exit the game if the 'X' is clicked in the window
		container.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	private void changeCursor() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Sprite s = SpriteStore.get().getSprite("Images/reticle_outlined.png");
		Image image = s.getImage();
		Cursor c = toolkit.createCustomCursor(image , new Point(this.getX(), this.getY()), "img");
		this.setCursor (c);
	}

	/*
	 * Set up the frame and panel for viewing
	 */
	private JFrame frameAndPanelSetup(int x_bound, int y_bound) {
		this.setBounds(4, 4, x_bound, y_bound);
		// Will be handling repainting with the BufferStrategy
		setIgnoreRepaint(true);
		
		JFrame frame = new JFrame("WELCOME TO MY GAME! I PUT ZOMBIES IN IT FOR YOUR PAIN!");

		JPanel panel = (JPanel) frame.getContentPane();
		panel.setPreferredSize(new Dimension(x_bound, y_bound));
		panel.setLayout(null);
		panel.add(this);
		
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
		return frame;
	}

	/*
	 * Creates a new Graphics2D object that sprites will be drawn on
	 * 
	 * @return returns the new object, currently painted all green
	 */
	public Graphics2D setUpGraphics2D() {
		Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
		Color bgcolor = Color.GREEN.darker().darker();
		g.setColor(bgcolor);
		// +128 to avoid having a little white space on the edges.
		g.fillRect(0, 0, x_bound, y_bound);

		return g;
	}

	/*
	 * Disposes of the passed in buffer and displayed the flipped buffer.
	 * 
	 * @param g Graphics2D object to be disposed of
	 */
	public void tearDownGraphics2D(Graphics2D g) {
		// Flip the buffer and display it
		g.dispose();
		strategy.show();
	}

	/*
	 * Simple class used to send mouse events to the controller
	 */
	private class MouseInputHandler extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			best.handleMouseEventPressed(e);
		}

		public void mouseReleased(MouseEvent e) {
			best.handleMouseEventReleased(e);
		}
	}

	/*
	 * Simple class used to send keyboard events to the controller
	 */
	private class KeyInputHandler extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			best.handleKeyEventPressed(e);
		}

		public void keyReleased(KeyEvent e) {
			best.handleKeyEventReleased(e);
		}
	}

	public void setBest(BestListener best) {
		this.best = best;
	}

	
	/*
	 * Draws a given message on screen and changes the font according to 
	 * what kind of message it is.
	 * 
	 * @param message The String to be printed
	 * @param x The x coordinate location for where to print
	 * @param y The y coordinate location for where to print
	 * @param type A flag that tells what type of message to change styles
	 */
	public void drawStringAt(String message, int x, int y, byte type) {
		Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
		g.setColor(Color.WHITE);
		
		if (type == DEATH)
			g.setFont(new Font("Haettenschweiler", Font.PLAIN, 36));
		if (type == INFO)
			g.setFont(new Font("Haettenschweiler", Font.PLAIN, 24));
		g.drawString(message, x, y);
	}
	
	/*
	 * Creates and draws an image from the path given.
	 * 
	 * @param path File path to get image from
	 * @param x The x coordinate for where to draw the image
	 * @param y The y coordinate for where to draw the image
	 */
	public void drawImagePath(String path, int x, int y) {
		Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
		Sprite img = SpriteStore.get().getSprite(path);
		img.draw(g, x, y);
	}

	/*
	 * Creates and draws an image from the sprite given.
	 * 
	 * @param sprite A sprite that contains the image to draw
	 * @param x The x coordinate for where to draw the image
	 * @param y The y coordinate for where to draw the image
	 */
	public void drawSpriteAt(Sprite sprite, int x, int y) {
		Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
		sprite.draw(g, x, y);
	}
}
