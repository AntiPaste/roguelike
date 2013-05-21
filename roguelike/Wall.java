/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Frozen
 */
public class Wall extends Block {

	public Wall(int x, int y) {
		super(x, y);
	}

	@Override
	public int getType() {
		return Block.WALL;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}

}
