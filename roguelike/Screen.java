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
	private JLayeredPane enemyMaps;

	public Screen() {
		this.window = new JFrame("Roguelike");
		this.window.setDefaultCloseOperation(this.window.EXIT_ON_CLOSE);
		this.window.setSize(this.windowWidth, this.windowHeight);
		this.window.setLocationRelativeTo(null);
		this.window.setResizable(false);
		this.window.setVisible(true);

		this.container = this.window.getContentPane();

		this.enemyMaps = new JLayeredPane();
		GridLayout grid = new GridLayout(2, 2);

		this.enemyMaps.setLayout(grid);
		this.enemyMaps.add(new EnemyMap());
		this.enemyMaps.add(new EnemyMap());
		this.enemyMaps.add(new EnemyMap());
		this.enemyMaps.add(new EnemyMap());

		this.container.add(this.enemyMaps, BorderLayout.CENTER);
		this.window.setContentPane(this.container);
	}

}