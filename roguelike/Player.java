/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import javax.swing.BorderFactory;

public class Player extends Block {

	private boolean isPlayer;
	private Integer playerID;

	public Player(int x, int y) {
		this(x, y, false);
		this.setVisibility(true);
	}

	public Player(int x, int y, boolean isPlayer) {
		super(x, y);

		this.isPlayer = isPlayer;
		this.setVisibility(true);
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
	}

	public void setPlayerID(int playerID) {
		this.playerID = playerID;
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

		g.setColor(this.isPlayer ? Color.RED : Color.GREEN);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		if (!this.isPlayer) {
			String drawID = this.playerID == null ? "?" : this.playerID.toString();

			FontMetrics fm = g.getFontMetrics();
			Rectangle2D textBounds = fm.getStringBounds(drawID, g);

			int textX = (Settings.blockSize - (int) textBounds.getWidth()) / 2;
			int textY = (Settings.blockSize - (int) textBounds.getHeight()) / 2 + fm.getAscent();

			g.setColor(Color.WHITE);
			g.drawString(drawID, textX, textY);
		}
	}
}