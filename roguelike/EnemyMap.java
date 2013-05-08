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
public class EnemyMap extends JPanel {

	private int mapWidth = 32;
	private int mapHeight = 32;

	public EnemyMap() {
		setLayout(null);
		setPreferredSize(new Dimension(this.mapWidth, this.mapHeight));
		setBorder(BorderFactory.createLineBorder(Color.black, 1));

		add(this.getButton());
	}

	public final JButton getButton() {
		JButton button = new JButton("Z");
		button.setPreferredSize(new Dimension(this.mapWidth, this.mapHeight));
		button.setBounds(0, 0, this.mapWidth, this.mapHeight);
		return button;
	}

}