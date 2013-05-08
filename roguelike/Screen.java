/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Frozen
 */
public class Screen {

	private int windowWidth = 1024;
	private int windowHeight = 768;

	private int players;

	private JFrame window;
	private Container container;

	public Screen() {
		this(5);
	}

	public Screen(int players) {
		this.players = players;

		// Create master JFrame
		this.window = new JFrame("Roguelike");
		this.window.setDefaultCloseOperation(this.window.EXIT_ON_CLOSE);
		this.window.setSize(this.windowWidth, this.windowHeight);
		this.window.setLocationRelativeTo(null);
		this.window.setResizable(false);
		this.window.setVisible(true);

		// Store JFrame content pane
		this.container = this.window.getContentPane();
		this.container.setLayout(null);
	}

	public void run() {
		this.createEnemyMaps();
	}

	public void refresh() {
		this.window.setContentPane(this.container);
	}

	public void createEnemyMaps() {
		int enemies = players - 1;
		int columns = (int) Math.ceil((double) enemies / 2);
		int enemyMapsWidth = columns * 32;

		// Create enemyMaps panel
		JPanel enemyMaps = new JPanel();
		enemyMaps.setLayout(new GridBagLayout());
		enemyMaps.setBounds(this.windowWidth - enemyMapsWidth, 0, enemyMapsWidth, 64);

		// Initialise enemyMapsGrid
		GridBagConstraints enemyMapsGrid = new GridBagConstraints();

		// Add in EnemyMaps
		for (int row = 0; row < 2; row++) {
			enemyMapsGrid.gridx = 0;
			enemyMapsGrid.gridy = row;

			for (int column = 0; column < columns; column++) {
				if (enemies % 2 == 1 && row == 1 && column == 0) {
					enemyMapsGrid.gridx++;
					continue;
				}

				enemyMaps.add(new EnemyMap(), enemyMapsGrid);
				enemyMapsGrid.gridx++;
			}
		}

		// Take us there!
		this.container.add(enemyMaps);
		this.refresh();
	}

	public void createChat() {
		throw new UnsupportedOperationException("Not yet implemented.");
	}

}