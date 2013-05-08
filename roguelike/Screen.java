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
	private JFrame window;
	private Container container;
	private JPanel enemyMaps;

	public Screen() {
		this.window = new JFrame("Roguelike");
		this.window.setDefaultCloseOperation(this.window.EXIT_ON_CLOSE);
		this.window.setSize(this.windowWidth, this.windowHeight);
		this.window.setLocationRelativeTo(null);
		this.window.setResizable(false);
		this.window.setVisible(true);

		this.container = this.window.getContentPane();

		this.enemyMaps = new JPanel();
		GridBagConstraints grid = new GridBagConstraints();

		grid.gridx = 0;
		grid.gridy = 0;

		this.enemyMaps.setLayout(new GridBagLayout());
		this.enemyMaps.add(new EnemyMap().getButton(), grid);
		grid.gridy++;
		this.enemyMaps.add(new EnemyMap().getButton(), grid);
		grid.gridy++;
		this.enemyMaps.add(new EnemyMap().getButton(), grid);
		grid.gridy++;
		this.enemyMaps.add(new EnemyMap().getButton(), grid);

		this.container.add(this.enemyMaps, BorderLayout.CENTER);
		this.window.setContentPane(this.container);
	}

}