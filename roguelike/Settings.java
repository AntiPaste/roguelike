/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike;

/**
 *
 * @author Frozen
 */
public final class Settings {
	public static final int networkPort = 4444;
	
	public static final int windowWidth = 1024;
	public static final int windowHeight = 768;

	public static final int blockSize = 24;
	
	public static final int gridHorizontalPadding = 128;
	public static final int gridVerticalPadding = 96;
	public static final int gridWidth = (Settings.windowWidth - Settings.gridHorizontalPadding * 2) / Settings.blockSize;
	public static final int gridHeight = (Settings.windowHeight - Settings.gridVerticalPadding * 2) / Settings.blockSize;

	public static final int enemyMapWidth = 32;
	public static final int enemyMapHeight = 32;
}
