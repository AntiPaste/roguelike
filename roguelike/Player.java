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

	private boolean isPlayer;

	public Player(int x, int y) {
		this(x, y, false);
	}

	public Player(int x, int y, boolean isPlayer) {
		super(x, y);

		this.isPlayer = isPlayer;
		this.setVisibility(true);
		this.setBorder(BorderFactory.createLineBorder(Color.black, 1));
	}

	@Override
	public int getType() {
		return Block.PLAYER;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (!this.getVisibility()) {
			return;
		}

		g.setColor((this.isPlayer ? Color.RED : Color.GREEN));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}

}
