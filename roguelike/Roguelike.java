/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike;

/**
 *
 * @author Frozen
 */
public class Roguelike {

	private Screen screen;
	private Map map;
	private Keyboard keyboard;

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		new Roguelike().run();
	}

	public void run() {
		this.screen = new Screen();
		this.map = new Map(this);
		this.keyboard = new Keyboard(this);
		
		this.screen.getWindow().addKeyListener(this.keyboard);
	}

	public Screen getScreen() {
		return this.screen;
	}

	public Map getMap() {
		return this.map;
	}

}