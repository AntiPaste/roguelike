/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

/**
 *
 * @author Frozen
 */
public class Keyboard implements KeyListener {

	private Roguelike roguelike;
	private Map map;

	public Keyboard(Roguelike roguelike) {
		this.roguelike = roguelike;
		this.map = this.roguelike.getMap();
	}

	@Override
	public void keyTyped(KeyEvent event) {}

	@Override
	public void keyPressed(KeyEvent event) {}

	@Override
	public void keyReleased(KeyEvent event) {
		switch (event.getKeyCode()) {
			case KeyEvent.VK_UP:
				this.map.moveRelative(0, -1);
				break;

			case KeyEvent.VK_DOWN:
				this.map.moveRelative(0, 1);
				break;

			case KeyEvent.VK_LEFT:
				this.map.moveRelative(-1, 0);
				break;

			case KeyEvent.VK_RIGHT:
				this.map.moveRelative(1, 0);
				break;

			case KeyEvent.VK_Q:
				try {
					this.roguelike.startServer();
				} catch (IOException e) {
					System.out.println("[!] Server failure");
					System.exit(-1);
				}
				break;
		}
	}

}
