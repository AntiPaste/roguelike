/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class Screen {

	private JFrame window;
	private Container container;
	private Map map;
	private JPanel gamePanel;

	public Screen() {
		this.window = new JFrame("Roguelike");
		this.window.setDefaultCloseOperation(this.window.EXIT_ON_CLOSE);
		this.window.setSize(Settings.windowWidth, Settings.windowHeight);
		this.window.setLocationRelativeTo(null);
		this.window.setResizable(false);
		this.window.setVisible(true);
		this.window.setFocusable(true);

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
		this.gamePanel.setBounds(Settings.gridHorizontalPadding, Settings.gridVerticalPadding, Settings.windowWidth - Settings.gridHorizontalPadding * 2, Settings.windowHeight - Settings.gridVerticalPadding * 2);

		GridBagConstraints gameGrid = new GridBagConstraints();
		gameGrid.anchor = GridBagConstraints.FIRST_LINE_START;;
		gameGrid.weightx = 1.0;
		gameGrid.weighty = 1.0;
		gameGrid.gridx = 0;
		gameGrid.gridy = 0;

		for (Player player : this.map.getPlayers().values()) {
			gameGrid.gridx = player.getPosX();
			gameGrid.gridy = player.getPosY();

			player.setLayout(null);
			player.setPreferredSize(new Dimension(Settings.blockSize, Settings.blockSize));

			this.gamePanel.add(player, gameGrid);
		}

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
