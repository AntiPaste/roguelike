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

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		new Roguelike().run();
	}

	public void run() {
		this.screen = new Screen(6);
		this.screen.run();
	}

}