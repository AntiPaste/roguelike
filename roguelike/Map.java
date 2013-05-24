/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roguelike;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Map {

	private Roguelike roguelike;
	private Screen screen;
	private List<Block> blocks;
	private java.util.Map<Integer, Player> players;
	private Player player;
	private int playerID;

	public Map(Roguelike roguelike) {
		this.roguelike = roguelike;
		this.screen = this.roguelike.getScreen();
		this.screen.setMap(this);

		this.populateMap();
	}

	public void populateMap() {
		int[][] map = {
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
			{1, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
			{1, 2, 1, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
			{1, 2, 1, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
			{1, 2, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
			{1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
			{1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
			{1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
			{1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
			{1, 2, 2, 2, 2, 2, 2, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
			{1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
			{1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
			{1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
			{1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
			{1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
			{1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
			{1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
			{1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
			{1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
			{1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
			{1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
			{1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
			{1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
			{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
		};

		this.blocks = new ArrayList();
		this.players = new HashMap();

		for (int x = 0; x < Settings.gridWidth; x++) {
			for (int y = 0; y < Settings.gridHeight; y++) {
				if (map[y][x] == Block.WALL) {
					this.blocks.add(new Wall(x, y));
				} else if (map[y][x] == Block.GROUND) {
					this.blocks.add(new Ground(x, y));
				} else if (map[y][x] == Block.PLAYER) {
					this.player = new Player(x, y, true);
					this.blocks.add(new Ground(x, y));
				}
			}
		}

		this.screen.redrawMap();
	}

	public Block getBlockAt(int x, int y, boolean ignorePlayer) {
		if (!ignorePlayer && this.player.getPosX() == x && this.player.getPosY() == y) {
			return this.player;
		}

		for (Block block : this.blocks) {
			if ((block.getPosX() == x) && (block.getPosY() == y)) {
				return block;
			}
		}

		return null;
	}

	public Block getBlockAt(int x, int y) {
		return this.getBlockAt(x, y, true);
	}

	public void setBlocks(List<Block> blocks) {
		this.blocks = blocks;
		this.screen.redrawMap();
	}

	public List<Block> getBlocks() {
		return this.blocks;
	}

	public void setPlayerID(int playerID) {
		this.playerID = playerID;
		this.players.remove(this.playerID);
		this.players.put(this.playerID, this.player);
	}

	public int getPlayerID() {
		return this.playerID;
	}

	public void addPlayer(int playerID) {
		Player newPlayer = new Player(-1, -1);
		newPlayer.setPlayerID(playerID);

		this.players.put(playerID, newPlayer);
	}

	public Player getPlayer() {
		return this.player;
	}

	public java.util.Map<Integer, Player> getPlayers() {
		return this.players;
	}

	public void moveRelative(int playerID, int dx, int dy) {
		this.moveAbsolute(playerID, this.player.getPosX() + dx, this.player.getPosY() + dy);
	}

	public boolean withinMap(int x, int y) {
		return (x >= 0) && (x <= Settings.gridWidth) && (y >= 0) && (y <= Settings.gridHeight);
	}

	public void moveAbsolute(int playerID, int x, int y) {
		if (!this.withinMap(x, y)) {
			return;
		}

		Player player = this.players.get(playerID);
		
		if (player == null) {
			System.out.println("[!] Error moving player " + playerID);
			return;
		}

		Block moveTo = this.getBlockAt(x, y);
		Block moveFrom = this.getBlockAt(player.getPosX(), player.getPosY());

		if (moveFrom != null) {
			moveFrom.setVisibility(true);
		}

		if (moveTo.getType() == Block.WALL || moveTo.getType() == Block.PLAYER) {
			moveTo.setVisibility(true);
			this.screen.redrawMap();
			return;
		}

		player.setPosX(x);
		player.setPosY(y);

		this.players.remove(playerID);
		this.players.put(playerID, player);

		this.screen.redrawMap();
	}
}