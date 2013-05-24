/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.BorderFactory;

public class Ground extends Block {

	public Ground(int x, int y) {
		super(x, y);
	}

	@Override
	public void setVisibility(boolean visibility) {
		super.setVisibility(visibility);
		setBorder(visibility ? BorderFactory.createLineBorder(Color.BLACK, 1) : null);
	}

	@Override
	public int getType() {
		return Block.GROUND;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (!this.getVisibility()) {
			return;
		}

		g.setColor(Color.GRAY);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
}