package IMADEA_view;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/*
 * This interface is to be used to keep the View and Controller
 * as separated as possible.
 */

public interface BestListener{
	
	public void handleKeyEventPressed(KeyEvent e);
	public void handleKeyEventReleased(KeyEvent e);
	
	public void handleMouseEventPressed(MouseEvent e);
	public void handleMouseEventReleased(MouseEvent e);

}
