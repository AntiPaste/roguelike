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
public class Player extends Block {

	public Player(int x, int y) {
		super(x, y);

		this.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	}

	@Override
	public int getType() {
		return Block.PLAYER;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.RED);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}

}
