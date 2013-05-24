/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike;

import javax.swing.JPanel;

public abstract class Block extends JPanel {

	public static final int WALL = 1;
	public static final int GROUND = 2;
	public static final int PLAYER = 3;
	private int x;
	private int y;
	private boolean visible = false;

	public Block(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void setPosX(int x) {
		this.x = x;
	}

	public void setPosY(int y) {
		this.y = y;
	}

	public int getPosX() {
		return this.x;
	}

	public int getPosY() {
		return this.y;
	}

	public void setVisibility(boolean visible) {
		this.visible = visible;
	}

	public boolean getVisibility() {
		return this.visible;
	}

	public abstract int getType();
}