package flppy;

import java.awt.Graphics;

import javax.swing.JPanel;

public class Renderer extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected void paintComponent(Graphics g){
	
	super.paintComponent(g);
	GamePlayF.flappy.repaint(g);
	}

}
