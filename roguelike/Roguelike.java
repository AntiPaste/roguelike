/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike;

import java.util.*;

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
		List<EnemyMap> enemyMaps = new ArrayList<EnemyMap>();
		for (int i = 0; i < 4; i++)
			enemyMaps.add(new EnemyMap());

		this.screen = new Screen();
		this.screen.showEnemyMaps(enemyMaps);
	}

}