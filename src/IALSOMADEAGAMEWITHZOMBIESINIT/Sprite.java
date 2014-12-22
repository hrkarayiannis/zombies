package IALSOMADEAGAMEWITHZOMBIESINIT;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

/**
 * A sprite to be displayed on the screen. Note that a sprite
 * contains no state information, i.e. its just the image and 
 * not the location. This allows us to use a single sprite in
 * lots of different places without having to store multiple 
 * copies of the image.
 * 
 * @author Kevin Glass
 * Modified slightly by Haralabos Karayiannis
 */
public class Sprite {
	/** The image to be drawn for this sprite */
	private BufferedImage image;
	
	/**
	 * Create a new sprite based on an image
	 * 
	 * @param image The image that is this sprite
	 */
	public Sprite(Image image) {
		this.image = (BufferedImage) image;
	}
	
	/**
	 * Get the width of the drawn sprite
	 * 
	 * @return The width in pixels of this sprite
	 */
	public int getWidth() {
		return image.getWidth(null);
	}

	/**
	 * Get the height of the drawn sprite
	 * 
	 * @return The height in pixels of this sprite
	 */
	public int getHeight() {
		return image.getHeight(null);
	}
	
	/**
	 * Draw the sprite onto the graphics context provided
	 * 
	 * @param g The graphics context on which to draw the sprite
	 * @param x The x location at which to draw the sprite
	 * @param y The y location at which to draw the sprite
	 */
	public void draw(Graphics g,int x,int y) {
		g.drawImage(image, x - getWidth() /2, y - getHeight() / 2, null);
	}

	public BufferedImage getImage() {
		return image;
	}
	
	public void rotateImage(double radians) {
		ImageIcon icon = new ImageIcon(this.image);
		BufferedImage blank = new BufferedImage(icon.getIconWidth(),
				icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = (Graphics2D) blank.getGraphics();
		g2.rotate(radians, icon.getIconWidth() / 2, icon.getIconHeight() / 2);
		g2.drawImage(this.image, 0, 0, null);
		this.image = blank;
	}
}