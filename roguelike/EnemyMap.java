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
public class EnemyMap {

	public EnemyMap() {

	}

	public JButton getButton() {
		JButton button = new JButton("Zoom");
		button.setPreferredSize(new Dimension(32, 32));
		return button;
	}

}