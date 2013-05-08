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

	private int windowWidth = 640;
	private int windowHeight = 480;
	private JFrame window;
	private Container contentPane;
	private JLayeredPane pane;

	public Screen() {
		this.window = new JFrame("Roguelike");
		this.window.setDefaultCloseOperation(this.window.EXIT_ON_CLOSE);
		this.window.setSize(this.windowWidth, this.windowHeight);
		this.window.setLocationRelativeTo(null);
		this.window.setResizable(false);
		this.window.setVisible(true);

		this.contentPane = this.window.getContentPane();

		this.pane = new JLayeredPane();
		this.pane.setLayout(new BoxLayout(this.pane, BoxLayout.PAGE_AXIS));
		this.pane.add(new GamePanel());

		this.contentPane.add(this.pane, BorderLayout.CENTER);
		this.window.setContentPane(this.contentPane);
	}

}
