package tiles;

import gfx.Assets;

public class DirtTile extends Tile{
	public DirtTile(int id) {
		super(Assets.dirtTile, id);
	}
	
	public boolean isDeadly() {
		return false;
	}
}
