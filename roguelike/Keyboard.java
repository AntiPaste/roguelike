/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

	private Roguelike roguelike;
	private Map map;
	private Client client;

	public Keyboard(Roguelike roguelike) {
		this.roguelike = roguelike;
		this.map = this.roguelike.getMap();
	}

	@Override
	public void keyTyped(KeyEvent event) {
	}

	@Override
	public void keyPressed(KeyEvent event) {
	}

	@Override
	public void keyReleased(KeyEvent event) {
		int x = this.map.getPlayer().getPosX();
		int y = this.map.getPlayer().getPosY();

		if (this.client == null) {
			this.client = this.roguelike.getClient();
		}

		switch (event.getKeyCode()) {
			case KeyEvent.VK_UP:
				this.client.send(String.format("move=%d:%d", x, y - 1));
				break;

			case KeyEvent.VK_RIGHT:
				this.client.send(String.format("move=%d:%d", x + 1, y));
				break;

			case KeyEvent.VK_DOWN:
				this.client.send(String.format("move=%d:%d", x, y + 1));
				break;

			case KeyEvent.VK_LEFT:
				this.client.send(String.format("move=%d:%d", x - 1, y));
				break;
		}
	}
}