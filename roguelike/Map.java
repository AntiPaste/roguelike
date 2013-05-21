/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Frozen
 */
public class Map {

	private Roguelike roguelike;
	private Screen screen;
	private List<Block> blocks;
	private Player player;

	public Map(Roguelike roguelike) {
		this.roguelike = roguelike;
		this.screen = this.roguelike.getScreen();
		this.screen.setMap(this);

		this.populateMap();
	}

	public void populateMap() {
		this.blocks = new ArrayList<Block>();

		for (int x = 0; x < 32; x++) {
			for (int y = 0; y < 24; y++) {
				if (x == 0 || y == 0 || x == 31 || y == 23) {
					this.blocks.add(new Wall(x, y));
					continue;
				}

				this.blocks.add(new Ground(x, y));
			}
		}

		this.player = new Player(2, 2);
		this.blocks.add(this.player);
		this.blocks.remove(50);

		this.screen.redrawMap();
	}

	public Block getBlockAt(int x, int y) {
		for (Block block : this.blocks) {
			if (block.getPosX() == x && block.getPosY() == y) {
				return block;
			}
		}

		return null;
	}

	public List<Block> getBlocks() {
		return this.blocks;
	}

	public Player getPlayer() {
		return this.player;
	}

	public void moveRelative(int dx, int dy) {
		this.moveAbsolute(this.player.getPosX() + dx, this.player.getPosY() + dy);
	}

	public boolean withinMap(int x, int y) {
		return (x >= 0 && x <= Settings.gridWidth && y >= 0 && y <= Settings.gridHeight);
	}

	public void moveAbsolute(int x, int y) {
		if (!this.withinMap(x, y)) {
			return;
		}

		if (this.getBlockAt(x, y).getType() == Block.WALL) {
			return;
		}

		int posX = this.player.getPosX();
		int posY = this.player.getPosY();

		this.blocks.remove(this.getBlockAt(posX, posY));
		this.blocks.add(new Ground(posX, posY));

		this.blocks.remove(this.getBlockAt(x, y));

		this.player.setPosX(x);
		this.player.setPosY(y);

		this.blocks.remove(this.player);
		this.blocks.add(this.player);

		this.screen.redrawMap();
	}

}
