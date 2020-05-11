package tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.Handler;

public class Tile {

	public static ArrayList<Tile> tiles = new ArrayList<Tile>();
	public static Tile dirtTile = new DirtTile(0);
	public static Tile grassTile = new GrassTile(1);
	public static Tile wallTile = new WallTile(2);
	public static Tile waterTile = new WaterTile(3);
	public static Tile bossEntrance = new BossRoomEntranceTile(4);
	
	public static final int TILEWIDTH = 64, TILEHEIGHT = 64;
	
	protected BufferedImage texture;
	protected static Handler handler;
	public final int id;
	
	protected static boolean unlocked = false;
	
	public Tile(BufferedImage texture, int id) {
		this.id = id;
		this.texture = texture;
	}
	
	public void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
	}
	
	public void updateTextures() {}
	
	public static void setHandler(Handler handle) {
		handler = handle;
	}
	
	public boolean isDoor() {
		return false;
	}
	
	public boolean isSolid() {
		return false;
	}
	
	public float speedMultiplier() {
		return 1;
	}

	public static ArrayList<Tile> getTiles() {
		return tiles;
	}

	public static void setTiles(ArrayList<Tile> tiles) {
		Tile.tiles = tiles;
	}
	
	public void enter() {
		
	}

	public boolean isUnlocked() {
		return unlocked;
	}

	public void setUnlocked(boolean uclckd) {
		unlocked = uclckd;
	}
	
}
