/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike;

import java.awt.*;
import java.util.List;
import javax.swing.*;

/**
 *
 * @author Frozen
 */
public class Screen {

	private JFrame window;
	private Container container;
	private Map map;
	private JPanel gamePanel;

	public Screen() {
		// Create master JFrame
		this.window = new JFrame("Roguelike");
		this.window.setDefaultCloseOperation(this.window.EXIT_ON_CLOSE);
		this.window.setSize(Settings.windowWidth, Settings.windowHeight);
		this.window.setLocationRelativeTo(null);
		this.window.setResizable(false);
		this.window.setVisible(true);

		// Store JFrame content pane
		this.container = new JLayeredPane();
		this.container.setBounds(0, 0, Settings.windowWidth, Settings.windowHeight);
		this.window.setContentPane(this.container);
	}

	public JFrame getWindow() {
		return this.window;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public void redrawMap() {
		if (this.gamePanel != null) {
			this.gamePanel.setVisible(false);
			this.gamePanel.removeAll();
			this.gamePanel = null;
		}

		this.gamePanel = new JPanel();
		this.gamePanel.setLayout(new GridBagLayout());
		this.gamePanel.setBounds(128, 96, Settings.windowWidth - 256, Settings.windowHeight - 192);

		GridBagConstraints gameGrid = new GridBagConstraints();
		gameGrid.anchor = GridBagConstraints.FIRST_LINE_START;
		gameGrid.weightx = 1.0;
		gameGrid.weighty = 1.0;
		gameGrid.gridx = 0;
		gameGrid.gridy = 0;

		Player player = this.map.getPlayer();
		gameGrid.gridx = player.getPosX();
		gameGrid.gridy = player.getPosY();

		player.setLayout(null);
		player.setPreferredSize(new Dimension(Settings.blockSize, Settings.blockSize));

		this.gamePanel.add(player, gameGrid);

		for (Block block : this.map.getBlocks()) {
			gameGrid.gridx = block.getPosX();
			gameGrid.gridy = block.getPosY();

			block.setLayout(null);
			block.setPreferredSize(new Dimension(Settings.blockSize, Settings.blockSize));

			this.gamePanel.add(block, gameGrid);
		}

		this.container.add(this.gamePanel, JLayeredPane.DEFAULT_LAYER);
	}

	public void showChat() {
		throw new UnsupportedOperationException("Not yet implemented.");
	}
}