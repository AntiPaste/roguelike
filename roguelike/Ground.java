/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.BorderFactory;

/**
 *
 * @author Frozen
 */
public class Ground extends Block {

	public Ground(int x, int y) {
		super(x, y);
		
		this.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	}

	@Override
	public int getType() {
		return Block.GROUND;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}

}
